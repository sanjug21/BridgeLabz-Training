import java.util.*;

public class StringCompare {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter String 1: ");
        String s1=sc.next();
        System.out.print("Enter String 2: ");
        String s2=sc.next();
        
        int res=compare(s1,s2);
        if(res<0)System.out.println("\""+s1+"\" comes before \""+s2+"\" in lexicographical order");
        else if(res>0)System.out.println("\""+s2+"\" comes before \""+s1+"\" in lexicographical order");
        else System.out.println("Strings are equal");
        sc.close();
    }
    
    public static int compare(String s1,String s2){
        int len1=s1.length();
        int len2=s2.length();
        int min=Math.min(len1,len2);
        
        for(int i=0;i<min;i++){
            char c1=s1.charAt(i);
            char c2=s2.charAt(i);
            if(c1!=c2)return c1-c2;
        }
        return len1-len2;
    }
}