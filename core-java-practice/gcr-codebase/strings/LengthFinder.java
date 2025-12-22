import java.util.*;

public class LengthFinder {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string:");
        String text=sc.next();

        int manualLength=findStringLength(text);
        int builtinLength=text.length();

        System.out.println("User-defined method length: " + manualLength);
        System.out.println("Built-in method length(): " + builtinLength);

        sc.close();
    }

    public static int findStringLength(String str) {
        int i=0;
        try {
            for (i=0;;i++) {
                str.charAt(i);
            }
        } catch (StringIndexOutOfBoundsException e) {
            // Reached the end of the string
        }
        return i;
    }
}