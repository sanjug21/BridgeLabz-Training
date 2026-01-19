public class SearchPerformanceAnalysis {

    // Linear Search: O(N)
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search: O(log N) - Requires sorted array
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 1000000};

        System.out.printf("%-20s %-20s %-20s%n", "Dataset Size (N)", "Linear Search (ns)", "Binary Search (ns)");
        System.out.println("----------------------------------------------------------------");

        for (int n : datasetSizes) {
            int[] data = new int[n];
            for (int i = 0; i < n; i++) data[i] = i; // Populate sorted data

            int target = n - 1; // Worst-case scenario for Linear Search

            long start = System.nanoTime();
            linearSearch(data, target);
            long linearTime = System.nanoTime() - start;

            start = System.nanoTime();
            binarySearch(data, target);
            long binaryTime = System.nanoTime() - start;

            System.out.printf("%-20d %-20d %-20d%n", n, linearTime, binaryTime);
        }
    }
}
