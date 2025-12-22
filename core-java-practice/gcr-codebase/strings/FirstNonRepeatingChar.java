import java.util.*;

public class FirstNonRepeatingChar {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string:");
        String text=sc.next();

        char result=findFirstNonRepeating(text);
        if (result!=0) {
            System.out.println("First non-repeating character: " + result);
        } else {
            System.out.println("No non-repeating character found.");
        }
        sc.close();
    }

    public static char findFirstNonRepeating(String text) {
        int[] frequency=new int[256]; // ASCII size

        // Calculate frequencies
        for (int i=0;i<text.length();i++) {
            char c=text.charAt(i);
            frequency[c]++;
        }

        // Find first character with frequency 1
        for (int i=0;i<text.length();i++) {
            char c=text.charAt(i);
            if (frequency[c]==1) {
                return c;
            }
        }

        return 0; // Return null char if not found
    }
}