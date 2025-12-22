
import java.util.*;

public class ManualStringComparison {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter first string:");
        String str1=sc.next();

        System.out.println("Enter second string:");
        String str2=sc.next();
        boolean manualResult=compareStrings(str1,str2);
        boolean builtInResult=str1.equals(str2);

        System.out.println("Manual Comparison Result: "+manualResult);
        System.out.println("Built-in equals() Result: "+builtInResult);

        if (manualResult==builtInResult) {
            System.out.println("Both methods gave the same result.");
        } else {
            System.out.println("Results differ.");
        }
        sc.close();
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