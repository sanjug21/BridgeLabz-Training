import java.util.Scanner;

public class AreaOfTriangle {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int base=sc.nextInt();
        int height=sc.nextInt();
        double areaInCM=0.5*base*height;
        double areaInFeet=areaInCM/30.48/12;
        System.out.println("Your Height in cm is "+areaInCM+" while in feet is "+areaInFeet+" and inches is "+areaInFeet);
        sc.close();
    }
}
