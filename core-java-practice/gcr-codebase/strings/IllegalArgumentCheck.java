
import java.util.*;

public class IllegalArgumentCheck {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string:");
        String input=sc.next();

        // 1. Call method to generate exception
        // generateException(input);

        // 2. Call method to handle exception
        handleException(input);
        sc.close();
    }

    public static void generateException(String text) {
        System.out.println("Generating Exception with invalid substring indices...");
        // Start index greater than end index
        String sub=text.substring(2, 1);
        System.out.println("Substring: " + sub);
    }

    public static void handleException(String text) {
        System.out.println("Handling Exception...");
        try {
            String sub=text.substring(2, 1);
            System.out.println("Substring: " + sub);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException: " + e);
        } catch (RuntimeException e) {
            System.out.println("Caught RuntimeException: " + e);
        }
    }
}