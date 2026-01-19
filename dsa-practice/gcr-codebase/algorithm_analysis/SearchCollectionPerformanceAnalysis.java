import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class SearchCollectionPerformanceAnalysis {

    public static void main(String[] args) {
        // Dataset sizes to benchmark
        int[] datasetSizes = {1000, 100000, 1000000};
        Random rand = new Random();

        System.out.printf("%-20s %-20s %-20s %-20s%n", "Dataset Size (N)", "Array (ms)", "HashSet (ms)", "TreeSet (ms)");
        System.out.println("--------------------------------------------------------------------------------");

        for (int n : datasetSizes) {
            // Prepare Data Structures
            List<Integer> arrayList = new ArrayList<>(n);
            HashSet<Integer> hashSet = new HashSet<>(n);
            TreeSet<Integer> treeSet = new TreeSet<>();

            // Populate with random integers
            for (int i = 0; i < n; i++) {
                int val = rand.nextInt(n * 10); // Use a range larger than N to reduce duplicates
                arrayList.add(val);
                hashSet.add(val);
                treeSet.add(val);
            }

            // Target: Search for a value guaranteed not to be in the list (e.g., -1)
            // This forces the Array to perform a full scan (Worst Case O(N))
            int target = -1;

            // 1. Array Search (Linear Search O(N))
            long start = System.nanoTime();
            arrayList.contains(target);
            long end = System.nanoTime();
            double arrayTime = (end - start) / 1_000_000.0; // Convert ns to ms

            // 2. HashSet Search (Hashing O(1))
            start = System.nanoTime();
            hashSet.contains(target);
            end = System.nanoTime();
            double hashSetTime = (end - start) / 1_000_000.0;

            // 3. TreeSet Search (Balanced BST O(log N))
            start = System.nanoTime();
            treeSet.contains(target);
            end = System.nanoTime();
            double treeSetTime = (end - start) / 1_000_000.0;

            System.out.printf("%-20d %-20.4f %-20.4f %-20.4f%n", n, arrayTime, hashSetTime, treeSetTime);
        }
    }
}