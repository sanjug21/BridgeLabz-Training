import java.util.Arrays;
import java.util.Scanner;

public class InsertionSort {
    // Problem: Sort employee IDs in ascending order using Insertion Sort.
    // Hint: Divide the array into sorted and unsorted parts, pick an element from unsorted and insert into sorted.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of employees: ");
        int n = scanner.nextInt();
        
        int[] ids = new int[n];
        System.out.println("Enter the employee IDs:");
        for (int i = 0; i < n; i++) {
            ids[i] = scanner.nextInt();
        }
        
        System.out.println("IDs before sorting: " + Arrays.toString(ids));
        
        insertionSort(ids);
        
        System.out.println("IDs after sorting: " + Arrays.toString(ids));
        scanner.close();
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
