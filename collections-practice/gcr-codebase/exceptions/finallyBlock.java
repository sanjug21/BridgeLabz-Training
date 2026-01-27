import java.util.Scanner;

public class finallyBlock {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Enter dividend: ");
			int dividend = scanner.nextInt();
			System.out.print("Enter divisor: ");
			int divisor = scanner.nextInt();

			int result = dividend / divisor;
			System.out.println("Result: " + result);
		} catch (ArithmeticException e) {
			System.out.println("Error: Cannot divide by zero");
		} finally {
			System.out.println("Operation completed");
			scanner.close();
		}
	}
}
