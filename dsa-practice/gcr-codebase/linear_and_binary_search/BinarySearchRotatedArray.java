public class BinarySearchRotatedArray {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        
        System.out.println("Array: {4, 5, 6, 7, 0, 1, 2}");
        
        int minIndex = findRotationPoint(arr);
        
        System.out.println("Smallest element is at index: " + minIndex);
        System.out.println("Smallest element value: " + arr[minIndex]);
    }

    public static int findRotationPoint(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left; // Returns the index of the smallest element
    }
}
