import java.util.*;

public class ToggleCase {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str=sc.nextLine();
        
        String res="";
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(ch>='A'&&ch<='Z')res+=(char)(ch+32);
            else if(ch>='a'&&ch<='z')res+=(char)(ch-32);
            else res+=ch;
        }
        System.out.println("Toggled case: "+res);
        sc.close();
    }
}