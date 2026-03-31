import java.util.Scanner;

public class CleanseAndInvert {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the word");
        String input = sc.nextLine();

        String result = CleanseAndInvertMethod(input);

        if (result.isEmpty()) {
            System.out.println("Invalid Input");
        } else {
            System.out.println("The generated key is - " + result);
        }

        sc.close();
    }

    public static String CleanseAndInvertMethod(String input) {
        // 1. Validate input is not null and has at least 6 characters
        if (input == null || input.length() < 6) {
            return "";
        }

        // 2. Validate input does not contain space, digit or special characters
        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c)) {
                return "";
            }
        }

        // Password Generation Logic:
        // Convert to lowercase
        String lowercase = input.toLowerCase();

        // Remove all characters with even ASCII values
        StringBuilder oddAsciiChars = new StringBuilder();
        for (char c : lowercase.toCharArray()) {
            if ((int) c % 2 != 0) {
                oddAsciiChars.append(c);
            }
        }

        // Reverse the remaining characters
        String reversed = oddAsciiChars.reverse().toString();

        // Convert characters at even positions (0-based index) to uppercase
        StringBuilder finalKey = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            if (i % 2 == 0) {
                finalKey.append(Character.toUpperCase(reversed.charAt(i)));
            } else {
                finalKey.append(reversed.charAt(i));
            }
        }

        return finalKey.toString();
    }
}
