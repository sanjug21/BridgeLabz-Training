import java.util.Scanner;

public class FirstUniqueCharacter {
    // 387. First Unique Character in a String
    // https://leetcode.com/problems/first-unique-character-in-a-string/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a string:");
        String s=sc.next();

        FirstUniqueCharacter obj=new FirstUniqueCharacter();
        int ans = obj.firstUniqChar(s);
        System.out.println("Index of first unique character: " + ans);
        sc.close();
    }

    public int firstUniqChar(String s) {
        int n=s.length();
        int fre[] = new int[26];
        for(int i=0;i<n;i++){
            fre[s.charAt(i)-'a']++;
        }   
        for(int i=0;i<n;i++){
            if(fre[s.charAt(i)-'a']==1)return i;
        }
        return -1;
    }
}