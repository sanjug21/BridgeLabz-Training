import java.util.*;

public class AnagramChecker {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter first string:");
        String str1=sc.next();
        System.out.println("Enter second string:");
        String str2=sc.next();

        boolean result=areAnagrams(str1, str2);
        if (result) {
            System.out.println("The strings are anagrams.");
        } else {
            System.out.println("The strings are NOT anagrams.");
        }
        sc.close();
    }

    public static boolean areAnagrams(String s1, String s2) {
        if (s1.length()!=s2.length()) {
            return false;
        }

        int[] freq1=new int[256];
        int[] freq2=new int[256];

        for (int i=0;i<s1.length();i++) {
            freq1[s1.charAt(i)]++;
            freq2[s2.charAt(i)]++;
        }
        // Compares frequency of chars
        for (int i=0;i<256;i++) {
            if (freq1[i]!=freq2[i]) {
                return false;
            }
        }

        return true;
    }
}