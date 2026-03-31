import java.util.Arrays;

public class LinearBinarySearchCombined {

    public static void main(String[] args) {
        // Example Input
        int[] numbers = {3, 4, -1, 1, 7, 8, 2};
        int target = 7;

        System.out.println("Original Array: " + Arrays.toString(numbers));

        // --- Part 1: Linear Search for First Missing Positive ---
        int firstMissing = findFirstMissingPositive(numbers);
        System.out.println("First missing positive integer: " + firstMissing);

        // --- Part 2: Binary Search for Target ---
        // Binary Search requires the array to be sorted
        Arrays.sort(numbers);
        System.out.println("Sorted Array for Binary Search: " + Arrays.toString(numbers));
        
        int targetIndex = binarySearch(numbers, target);
        if (targetIndex != -1) {
            System.out.println("Target " + target + " found at index: " + targetIndex);
        } else {
            System.out.println("Target " + target + " not found.");
        }
    }

    public static int findFirstMissingPositive(int[] arr) {
        int n = arr.length;
        // We only need to track numbers from 1 to n. 
        // If all 1..n are present, the answer is n+1.
        boolean[] present = new boolean[n + 1];

        for (int num : arr) {
            // Only mark positive integers that fit within our tracking array
            if (num > 0 && num <= n) {
                present[num] = true;
            }
        }

        // Linear search to find the first unmarked index
        for (int i = 1; i <= n; i++) {
            if (!present[i]) {
                return i;
            }
        }
        return n + 1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}