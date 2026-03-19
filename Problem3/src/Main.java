import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Test case 1: Mixed list — only swimmer B should pass
        // Swimmer A: only 3 butterfly times (fails condition 1)
        // Swimmer B: 5 butterfly times + a qualifying freestyle time of 25.50s (passes
        // both)
        // Swimmer C: 5 butterfly times but no freestyle time under 26.17s (fails
        // condition 2)
        Swimmer swimmerA = new Swimmer("Alice",
                Arrays.asList(30.1, 31.2, 29.8), // only 3 butterfly times
                Arrays.asList(28.0), Arrays.asList(32.0),
                Arrays.asList(27.0, 28.0));

        Swimmer swimmerB = new Swimmer("Bob",
                Arrays.asList(29.0, 30.1, 28.5, 31.0, 27.9), // 5 butterfly times
                Arrays.asList(27.0), Arrays.asList(31.0),
                Arrays.asList(27.0, 25.50, 26.80)); // 25.50 qualifies

        Swimmer swimmerC = new Swimmer("Carol",
                Arrays.asList(28.0, 29.0, 30.0, 31.0, 27.5), // 5 butterfly times
                Arrays.asList(27.0), Arrays.asList(31.0),
                Arrays.asList(27.00, 26.50, 26.20)); // none under 26.17

        List<Swimmer> list1 = Arrays.asList(swimmerA, swimmerB, swimmerC);
        SwimmerIterator iter1 = new SwimmerIterator(list1);

        System.out.println("Test 1 — expect only Bob:");
        while (iter1.hasNext()) {
            System.out.println(iter1.next().getName());
        }

        // Test case 2: Nobody qualifies — iterator should produce nothing
        Swimmer swimmerD = new Swimmer("Diana",
                Arrays.asList(30.0, 31.0), // only 2 butterfly times
                Arrays.asList(27.0), Arrays.asList(31.0),
                Arrays.asList(25.00));

        List<Swimmer> list2 = Arrays.asList(swimmerD);
        SwimmerIterator iter2 = new SwimmerIterator(list2);

        System.out.println("Test 2 — expect nothing:");
        while (iter2.hasNext()) {
            System.out.println(iter2.next().getName());
        }
        System.out.println("(done)");
    }
}