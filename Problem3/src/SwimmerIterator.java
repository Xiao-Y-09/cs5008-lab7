import java.util.*;

public class SwimmerIterator implements Iterator<Swimmer> {

    private List<Swimmer> swimmers;
    private int nextQualifiedIndex;

    public SwimmerIterator(List<Swimmer> swimmers) {
        this.swimmers = swimmers;
        // Find the first qualifying swimmer upfront so hasNext() is always O(1)
        advance();
    }

    // Scans forward from the current position to find the next qualifying swimmer.
    // Stores their index in nextQualifiedIndex, or swimmers.size() if none found.
    // This is the core of the eager advancement pattern — we do the work once,
    // not on every hasNext() call.
    private void advance() {
        for (int i = nextQualifiedIndex; i < swimmers.size(); i++) {
            if (qualified(swimmers.get(i))) {
                nextQualifiedIndex = i;
                return;
            }
        }
        // No qualifying swimmer found — signal exhaustion
        nextQualifiedIndex = swimmers.size();
    }

    // Encapsulates both filter conditions in one place.
    // Single Responsibility: the iterator doesn't need to know the details,
    // just whether a swimmer passes or not.
    private boolean qualified(Swimmer swimmer) {
        // Condition 1: must have at least 5 recorded butterfly times
        if (swimmer.getButterfly50mTimes().size() < 5) {
            return false;
        }
        // Condition 2: must have achieved at least one Olympic qualifying freestyle
        // time (≤ 26.17s)
        for (double time : swimmer.getFreestyle50mTimes()) {
            if (time <= 26.17) {
                return true;
            }
        }
        return false;
    }

    // O(1) — nextQualifiedIndex is always pre-computed by advance()
    @Override
    public boolean hasNext() {
        return nextQualifiedIndex < swimmers.size();
    }

    @Override
    public Swimmer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Swimmer result = swimmers.get(nextQualifiedIndex);
        // Move past the current result before scanning, so advance() doesn't re-find it
        nextQualifiedIndex++;
        advance();
        return result;
    }
}