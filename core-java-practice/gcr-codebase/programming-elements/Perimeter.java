import java.util.Scanner;

public class Perimeter {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int l=sc.nextInt();
        int b=sc.nextInt();
        int p=2*(l+b);
        System.out.println("Perimeter of rectangle is: "+p);
    }
}
