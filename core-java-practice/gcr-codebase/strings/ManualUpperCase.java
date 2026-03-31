
import java.util.*;

public class ManualUpperCase {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter text to convert to Uppercase:");
        String text=sc.nextLine();

        String manualUpper=toUpperCaseManual(text);
        String builtInUpper=text.toUpperCase();

        System.out.println("Manual Uppercase: "+manualUpper);
        System.out.println("Built-in Uppercase: "+builtInUpper);

        boolean result=compareStrings(manualUpper,builtInUpper);
        System.out.println("Results match: "+result);
        sc.close();
    }

    public static String toUpperCaseManual(String str) {
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<str.length();i++) {
            char c=str.charAt(i);
            if (c>='a'&&c<='z') {
                // Convert to uppercase by subtracting 32
                c=(char)(c-32);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static boolean compareStrings(String s1, String s2) {
        if (s1.length()!=s2.length()) {
            return false;
        }
        for (int i=0;i<s1.length();i++) {
            if (s1.charAt(i)!=s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}