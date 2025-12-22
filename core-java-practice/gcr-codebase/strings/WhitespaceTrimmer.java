import java.util.*;

public class WhitespaceTrimmer {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string with leading/trailing spaces:");
        String text=sc.nextLine();

        int[] indices=findContentIndices(text);
        String manualTrim=createSubstring(text,indices[0],indices[1]);
        String builtinTrim=text.trim();

        System.out.println("\nManual Trim Result:     '"+manualTrim+"'");
        System.out.println("Built-in trim() Result: '"+builtinTrim+"'");

        boolean areEqual=compareStrings(manualTrim,builtinTrim);
        System.out.println("Results are equal: "+areEqual);

        sc.close();
    }

    public static int[] findContentIndices(String str) {
        int start=0;
        while (start<str.length()&&Character.isWhitespace(str.charAt(start))) {
            start++;
        }

        int end=str.length();
        while (end>start&&Character.isWhitespace(str.charAt(end-1))) {
            end--;
        }
        return new int[]{start, end};
    }

    public static String createSubstring(String str, int start, int end) {
        if (start>=end) {
            return "";
        }
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