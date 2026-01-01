import java.util.Scanner;

public class ValidAnagram {
    // 242. Valid Anagram
    // https://leetcode.com/problems/valid-anagram/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter first string:");
        String s=sc.next();
        System.out.println("Enter second string:");
        String t=sc.next();

        ValidAnagram obj=new ValidAnagram();
        boolean ans = obj.isAnagram(s, t);
        System.out.println("Is Anagram: " + ans);
        sc.close();
    }

    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())return false;
        int[] count=new int[26];
        for(char c:s.toCharArray())count[c-'a']++;
        for(char c:t.toCharArray())count[c-'a']--;
        for(int i:count)if(i!=0)return false;
        return true;
    }
}