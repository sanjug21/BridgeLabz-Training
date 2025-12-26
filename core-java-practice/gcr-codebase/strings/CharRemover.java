import java.util.*;

public class CharRemover {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter String: ");
        String str=sc.nextLine();
        System.out.print("Enter Character to Remove: ");
        char r=sc.next().charAt(0);
        
        String res="";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=r)res+=str.charAt(i);
        }

        // or we can use stringbuilder
       
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=r)sb.append(str.charAt(i));
        }
        String res2=sb.toString();

        System.out.println("Modified String: \""+res+"\"");
        System.out.println("Modified String using StringBuilder: \""+res2+"\"");
        sc.close();
    }
}