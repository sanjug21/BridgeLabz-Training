import java.util.*;

public class PalindromeCheck {
    
    public static void main(String[] args) {
        String s=getInput();
        if(isPalindrome(s)) displayOutput(s+" is a Palindrome");
        else displayOutput(s+" is not a Palindrome");
    }

    static String getInput(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter string: ");
        String s=sc.next();
        sc.close();
        return s;
    }

    static boolean isPalindrome(String s){
        String rev="";
        for(int i=s.length()-1;i>=0;i--){
            rev+=s.charAt(i);
        }
        return s.equals(rev);
    }

    static void displayOutput(String msg){
        System.out.println(msg);
    }
}