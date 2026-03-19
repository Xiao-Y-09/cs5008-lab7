import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Main {

    // Consumes a List<Integer> and returns a Map of each number
    // to its relative frequency (occurrences / total size).
    private static Map<Integer, Double> getFreq(List<Integer> list) {
        Map<Integer, Double> finalMap = new HashMap<>();

        // Guard against null or empty input — nothing to count
        if (list == null || list.isEmpty())
            return finalMap;

        // Pass 1: Count raw occurrences of each number
        Map<Integer, Integer> counts = new HashMap<>();
        for (Integer i : list) {
            Integer currentCount = counts.get(i);
            if (currentCount == null) {
                counts.put(i, 1);
            } else {
                counts.put(i, currentCount + 1);
            }
        }

        // Pass 2: Divide each count by total list size to get relative frequency.
        // Cast to double first to avoid integer division truncating to 0.
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            finalMap.put(entry.getKey(), (double) entry.getValue() / list.size());
        }

        return finalMap;
    }

    public static void main(String[] args) {
        System.out.println("Hello from Problem 1!");

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(4);

        // Expected: {1=0.3, 2=0.2, 3=0.2, 4=0.3}
        System.out.println(getFreq(list));
    }
}