import java.util.*;

public class PalindromeChecker {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string to check for palindrome:");
        String text=sc.next();

        boolean res1=isPalindromeIterative(text);
        boolean res2=isPalindromeRecursive(text,0,text.length()-1);
        boolean res3=isPalindromeArray(text);

        System.out.println("Logic 1 (Iterative): "+res1);
        System.out.println("Logic 2 (Recursive): "+res2);
        System.out.println("Logic 3 (Array Rev): "+res3);

        sc.close();
    }

    // Logic 1: Iterative start/end comparison
    public static boolean isPalindromeIterative(String text) {
        int start=0;
        int end=text.length()-1;
        while (start<end) {
            if (text.charAt(start)!=text.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // Logic 2: Recursive
    public static boolean isPalindromeRecursive(String text, int start, int end) {
        if (start>=end) {
            return true;
        }
        if (text.charAt(start)!=text.charAt(end)) {
            return false;
        }
        return isPalindromeRecursive(text, start + 1, end - 1);
    }

    // Logic 3: Character Arrays
    public static boolean isPalindromeArray(String text) {
        char[] original=text.toCharArray();
        char[] reversed=reverseString(text);
        
        for (int i=0;i<original.length;i++) {
            if (original[i]!=reversed[i]) {
                return false;
            }
        }
        return true;
    }

    public static char[] reverseString(String text) {
        char[] rev=new char[text.length()];
        int j=0;
        for (int i=text.length()-1;i>=0;i--) {
            rev[j++]=text.charAt(i);
        }
        return rev;
    }
}