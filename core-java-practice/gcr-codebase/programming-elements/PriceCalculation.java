import java.util.Scanner;

public class PriceCalculation {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double price=sc.nextDouble();
        double quantity=sc.nextInt();
        double total=price*quantity;
        System.out.println("The total purchase price is INR "+total+" if the quantity "+quantity+" and unit price is INR "+price);
        sc.close();
        }
}
