import java.util.Scanner;

public class PalindromeNumber {
    // 9. Palindrome Number
    // https://leetcode.com/problems/palindrome-number/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number:");
        int x=sc.nextInt();

        PalindromeNumber obj=new PalindromeNumber();
        boolean ans = obj.isPalindrome(x);
        System.out.println("Is Palindrome: " + ans);
        sc.close();
    }

    public boolean isPalindrome(int x) {
        if(x<0)return false;
        int original=x;
        int reversed=0;
        while(x!=0){
            reversed=reversed*10+x%10;
            x/=10;
        }
        return original == reversed;
    }
}