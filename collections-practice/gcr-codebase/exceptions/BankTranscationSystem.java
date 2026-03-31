import java.util.Scanner;

public class BankTranscationSystem {

	static class InsufficientBalanceException extends Exception {
		InsufficientBalanceException(String message) {
			super(message);
		}
	}

	private double balance;

	BankTranscationSystem(double openingBalance) {
		this.balance = openingBalance;
	}

	void withdraw(double amount) throws InsufficientBalanceException {
		if (amount < 0) {
			throw new IllegalArgumentException("Invalid amount!");
		}
		if (amount > balance) {
			throw new InsufficientBalanceException("Insufficient balance!");
		}
		balance -= amount;
		System.out.println("Withdrawal successful, new balance: " + balance);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Enter opening balance: ");
			double opening = scanner.nextDouble();
			BankTranscationSystem account = new BankTranscationSystem(opening);

			System.out.print("Enter withdrawal amount: ");
			double amount = scanner.nextDouble();

			account.withdraw(amount);
		} catch (InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid amount!");
		} finally {
			scanner.close();
		}
	}
}
