import java.util.*;

public class DuplicateRemover {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str=sc.next();
        
        String res="";
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(res.indexOf(ch)==-1)res+=ch;
        }
        System.out.println("Modified string: "+res);
        sc.close();
    }
}