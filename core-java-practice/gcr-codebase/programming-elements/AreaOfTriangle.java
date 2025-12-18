import java.util.Scanner;

public class AreaOfTriangle {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int b=sc.nextInt();
        int h=sc.nextInt();
        double areaInCM=0.5*b*h;
        double areaInFeet=areaInCM/30.48/12;
        System.out.println("Your Height in cm is "+areaInCM+" while in feet is "+areaInFeet+" and inches is "+areaInFeet);
    }
}
