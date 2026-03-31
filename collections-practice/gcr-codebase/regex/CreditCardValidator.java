import java.util.Scanner;
import java.util.regex.Pattern;

public class CreditCardValidator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Credit Card Number
        System.out.println("Enter a credit card number:");
        String cardNumber = sc.nextLine();

        // 2. Validate Credit Card
        String cardType = validateCreditCard(cardNumber);

        // 3. Display Result
        if (cardType != null) {
            System.out.println("\"" + cardNumber + "\" is a valid " + cardType + " card");
        } else {
            System.out.println("\"" + cardNumber + "\" is not a valid credit card");
        }

        sc.close();
    }

    public static String validateCreditCard(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return null;
        }

        // Remove spaces and dashes
        String cleanedNumber = cardNumber.replaceAll("[\\s-]", "");

        // Visa: starts with 4, 16 digits
        if (Pattern.matches("^4\\d{15}$", cleanedNumber)) {
            return "Visa";
        }

        // MasterCard: starts with 5, 16 digits
        if (Pattern.matches("^5\\d{15}$", cleanedNumber)) {
            return "MasterCard";
        }

        return null;
    }
}
