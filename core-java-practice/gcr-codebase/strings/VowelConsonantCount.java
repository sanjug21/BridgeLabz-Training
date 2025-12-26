import java.util.*;

public class VowelConsonantCount {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str=sc.nextLine();
        
        int v=0,c=0;
        str=str.toLowerCase();
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(ch>='a'&&ch<='z'){
                if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u')v++;
                else c++;
            }
        }
        System.out.println("Vowels: "+v);
        System.out.println("Consonants: "+c);
        sc.close();
    }
}