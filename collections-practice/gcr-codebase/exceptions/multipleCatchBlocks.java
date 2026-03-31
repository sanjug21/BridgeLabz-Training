import java.util.Scanner;

public class multipleCatchBlocks {

	static void printValueAtIndex(int[] numbers, int index) {
		try {
			int value = numbers[index];
			System.out.println("Value at index " + index + ": " + value);
		} catch (NullPointerException e) {
			System.out.println("Array is not initialized!");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid index!");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Enter array size (use -1 to simulate null array): ");
			int size = scanner.nextInt();

			int[] numbers = null;
			if (size >= 0) {
				numbers = new int[size];
				System.out.println("Enter " + size + " integers:");
				for (int i = 0; i < size; i++) {
					numbers[i] = scanner.nextInt();
				}
			}

			System.out.print("Enter index to retrieve: ");
			int index = scanner.nextInt();

			printValueAtIndex(numbers, index);
		} finally {
			scanner.close();
		}
	}
}
