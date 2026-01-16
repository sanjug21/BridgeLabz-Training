public class LinearSearchNegative {

    public static void main(String[] args) {
        int[] numbers = {10, 5, 20, -3, 8, -15};
        System.out.println("Array: {10, 5, 20, -3, 8, -15}");
        
        int negativeIndex = findFirstNegative(numbers);
        if (negativeIndex != -1) {
            System.out.println("First negative number found at index: " + negativeIndex);
        } else {
            System.out.println("No negative number found.");
        }
    }

    public static int findFirstNegative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return i;
            }
        }
        return -1;
    }
}