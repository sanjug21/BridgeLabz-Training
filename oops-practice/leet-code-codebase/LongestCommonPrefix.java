import java.util.Arrays;
import java.util.Scanner;

public class LongestCommonPrefix {
    // 14. Longest Common Prefix
    // https://leetcode.com/problems/longest-common-prefix/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of strings:");
        int n=sc.nextInt();
        String[] strs=new String[n];
        System.out.println("Enter strings:");
        for(int i=0;i<n;i++)strs[i]=sc.next();

        LongestCommonPrefix obj=new LongestCommonPrefix();
        String ans = obj.longestCommonPrefix(strs);
        System.out.println("Longest Common Prefix: " + ans);
        sc.close();
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs==null || strs.length==0)return "";
        Arrays.sort(strs);
        String s1=strs[0];
        String s2=strs[strs.length-1];
        int idx=0;
        while(idx<s1.length() && idx<s2.length()){
            if(s1.charAt(idx)==s2.charAt(idx)){
                idx++;
            }else{
                break;
            }
        }
        return s1.substring(0, idx);
    }
}
