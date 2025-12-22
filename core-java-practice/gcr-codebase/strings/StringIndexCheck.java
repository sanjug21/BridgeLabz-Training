

import java.util.Scanner;

public class StringIndexCheck {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter a string:");
        String input=scanner.next();

        // method to generate exception 
        // generateException(input);

        // method to handle exception
        handleException(input);
        scanner.close();
    }

    public static void generateException(String text) {
        System.out.println("Generating StringIndexOutOfBoundsException...");
        // Accessing index equal to length (valid indices are 0 to length-1)
        char c=text.charAt(text.length());
        System.out.println("Character: " + c);
    }

    public static void handleException(String text) {
        System.out.println("Handling StringIndexOutOfBoundsException...");
        try {
            char c=text.charAt(text.length());
            System.out.println("Character: " + c);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Caught Exception: " + e);
        }
    }
}