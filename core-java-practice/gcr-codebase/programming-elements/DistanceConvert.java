import java.util.Scanner;

public class DistanceConvert {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double feets=sc.nextDouble();
        double yards=feets/3;
        double miles=feets/5280;
        System.out.println("The distance in feets is "+feets+" while in yards is "+yards+" and in miles is "+miles);
    }
}
