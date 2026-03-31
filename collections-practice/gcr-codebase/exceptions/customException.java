import java.util.Scanner;

public class customException {
	static class InvalidAgeException extends Exception {
		InvalidAgeException(String message) {
			super(message);
		}
	}

	static void validateAge(int age) throws InvalidAgeException {
		if (age < 18) {
			throw new InvalidAgeException("Age must be 18 or above");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Enter age: ");
			int age = scanner.nextInt();
			validateAge(age);
			System.out.println("Access granted!");
		} catch (InvalidAgeException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Invalid input");
		} finally {
			scanner.close();
		}
	}
}
