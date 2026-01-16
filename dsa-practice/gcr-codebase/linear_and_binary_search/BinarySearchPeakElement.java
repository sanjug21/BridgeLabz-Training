public class BinarySearchPeakElement {

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 5, 6, 4};
        
        System.out.println("Array: {1, 2, 1, 3, 5, 6, 4}");
        
        int peakIndex = findPeakElement(arr);
        
        if (peakIndex != -1) {
            System.out.println("Peak element found at index: " + peakIndex);
            System.out.println("Peak element value: " + arr[peakIndex]);
        } else {
            System.out.println("No peak element found.");
        }
    }

    public static int findPeakElement(int[] arr) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid > 0 && arr[mid] < arr[mid - 1]) {
                right = mid - 1; // Search left half
            } else if (mid < n - 1 && arr[mid] < arr[mid + 1]) {
                left = mid + 1;  // Search right half
            } else {
                return mid;      // Found peak
            }
        }
        return -1;
    }
}
