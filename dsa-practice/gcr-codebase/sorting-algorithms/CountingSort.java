import java.util.Arrays;
import java.util.Scanner;

public class CountingSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();
        
        int[] ages = new int[n];
        System.out.println("Enter the ages (between 10 and 18):");
        for (int i = 0; i < n; i++) {
            ages[i] = scanner.nextInt();
        }
        
        System.out.println("Ages before sorting: " + Arrays.toString(ages));
        
        countingSort(ages);
        
        System.out.println("Ages after sorting: " + Arrays.toString(ages));
        scanner.close();
    }

    public static void countingSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[arr.length];

        // Store count of each character
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Build the output character array
        // To make it stable we are operating in reverse order.
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
}
