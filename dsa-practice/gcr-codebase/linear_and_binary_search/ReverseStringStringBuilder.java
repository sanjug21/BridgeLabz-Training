import java.util.Scanner;

public class ReverseStringStringBuilder {
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        StringBuilder sb=new StringBuilder(s);
        sb.reverse();
        System.out.println("Original String is: "+s);
        System.out.println("Reversed String is: "+sb.toString());
        sc.close();
    }
}
