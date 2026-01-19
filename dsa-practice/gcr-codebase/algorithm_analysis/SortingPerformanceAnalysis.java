
import java.util.Arrays;
import java.util.Random;

public class SortingPerformanceAnalysis {

    // Bubble Sort: O(N^2)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Optimization: Stop if already sorted
        }
    }

    // Merge Sort: O(N log N)
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) return;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    // Quick Sort: O(N log N)
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 100000}; // 100k used for reasonable runtime
        Random rand = new Random();

        System.out.printf("%-20s %-20s %-20s %-20s%n", "Dataset Size (N)", "Bubble Sort", "Merge Sort", "Quick Sort");
        System.out.println("--------------------------------------------------------------------------------");

        for (int n : datasetSizes) {
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = rand.nextInt(1000000);
            }

            // Clone arrays to ensure all algorithms sort the same data
            int[] arr1 = data.clone();
            int[] arr2 = data.clone();
            int[] arr3 = data.clone();

            // Bubble Sort (Skip for large N)
            String bubbleTime;
            if (n > 20000) {
                bubbleTime = "Unfeasible";
            } else {
                long start = System.currentTimeMillis();
                bubbleSort(arr1);
                long end = System.currentTimeMillis();
                bubbleTime = (end - start) + " ms";
            }

            // Merge Sort
            long start = System.currentTimeMillis();
            mergeSort(arr2);
            long end = System.currentTimeMillis();
            String mergeTime = (end - start) + " ms";

            // Quick Sort
            start = System.currentTimeMillis();
            quickSort(arr3, 0, n - 1);
            end = System.currentTimeMillis();
            String quickTime = (end - start) + " ms";

            System.out.printf("%-20d %-20s %-20s %-20s%n", n, bubbleTime, mergeTime, quickTime);
        }
    }
}
