import java.util.Scanner;

public class LengthOfLastWord {
    // 58. Length of Last Word
    // https://leetcode.com/problems/length-of-last-word/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string:");
        String s=sc.nextLine();

        LengthOfLastWord obj=new LengthOfLastWord();
        int ans = obj.lengthOfLastWord(s);
        System.out.println("Length of last word: " + ans);
        sc.close();
    }

    public int lengthOfLastWord(String s) {
        s=s.trim();
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)==' '){
                return s.length()-1-i;
            }
        }
        return s.length();
    }
}