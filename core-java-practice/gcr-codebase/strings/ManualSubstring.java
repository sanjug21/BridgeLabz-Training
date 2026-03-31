

import java.util.*;

public class ManualSubstring {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter a string:");
        String text=sc.next();

        System.out.println("Enter start index:");
        int start=sc.nextInt();

        System.out.println("Enter end index:");
        int end=sc.nextInt();

        String manualSub=createSubstring(text,start,end);
        String builtInSub=text.substring(start,end);

        System.out.println("Manual Substring: "+manualSub);
        System.out.println("Built-in Substring: "+builtInSub);

        boolean isEqual=compareStrings(manualSub,builtInSub);
        System.out.println("Comparison Result: "+isEqual);
        sc.close();
    }

    public static String createSubstring(String str, int start, int end) {
        StringBuilder sb=new StringBuilder();
        for (int i=start;i<end;i++) {
            sb.append(str.charAt(i));
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