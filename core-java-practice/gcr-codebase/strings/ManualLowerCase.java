

import java.util.*;

public class ManualLowerCase {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter text to convert to Lowercase:");
        String text=sc.nextLine();

        String manualLower=toLowerCaseManual(text);
        String builtInLower=text.toLowerCase();

        System.out.println("Manual Lowercase: " + manualLower);
        System.out.println("Built-in Lowercase: " + builtInLower);

        boolean result=compareStrings(manualLower, builtInLower);
        System.out.println("Results match: " + result);
        sc.close();
    }

    public static String toLowerCaseManual(String str) {
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<str.length();i++) {
            char c=str.charAt(i);
            if (c>='A'&&c<='Z') {
                // Convert to lowercase by adding 32
                c=(char)(c+32);
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