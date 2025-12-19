import java.util.Scanner;

public class FeeDiscount2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double fee=sc.nextDouble();
        int discountPercentage=sc.nextInt();
        double discount=fee*(discountPercentage/100.0);
        System.out.println("The discount amount is "+discount+" and the final discount fee is "+(fee-discount));
        sc.close();
    }
}
