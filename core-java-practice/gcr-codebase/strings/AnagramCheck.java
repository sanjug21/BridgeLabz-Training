import java.util.*;

public class AnagramCheck {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter string 1: ");
        String s1=sc.next();
        System.out.print("Enter string 2: ");
        String s2=sc.next();
        
        if(checkAnagram(s1,s2))System.out.println("Strings are anagrams");
        else System.out.println("Strings are not anagrams");
        sc.close();
    }
    
    public static boolean checkAnagram(String s1,String s2){
        if(s1.length()!=s2.length())return false;
        int[] count=new int[256];
        for(int i=0;i<s1.length();i++){
            count[s1.charAt(i)]++;
            count[s2.charAt(i)]--;
        }
        // if freqeuency of both strings is same, count array will have all 0s
        for(int c:count)if(c!=0)return false;
        return true;
    }
}