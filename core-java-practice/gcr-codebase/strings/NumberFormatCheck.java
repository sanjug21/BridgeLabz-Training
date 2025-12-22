
import java.util.Scanner;

public class NumberFormatCheck {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter a non-numeric string (e.g., 'abc'):");
        String input=scanner.next();

        // method to generate exception
        // generateException(input);

        // method to handle exception
        handleException(input);
        scanner.close();
    }

    public static void generateException(String text) {
        System.out.println("Generating NumberFormatException...");
        int num=Integer.parseInt(text);
        System.out.println("Number: " + num);
    }

    public static void handleException(String text) {
        System.out.println("Handling NumberFormatException...");
        try {
            int num=Integer.parseInt(text);
            System.out.println("Number: " + num);
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: " + e);
        } catch (RuntimeException e) {
            System.out.println("Caught RuntimeException: " + e);
        }
    }
}