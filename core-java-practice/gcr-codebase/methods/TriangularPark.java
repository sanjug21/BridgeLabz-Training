import java.util.*;

public class TriangularPark {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter side 1: ");
        double side1=sc.nextDouble();
        System.out.print("Enter side 2: ");
        double side2=sc.nextDouble();
        System.out.print("Enter side 3: ");
        double side3=sc.nextDouble();
        
        TriangularPark triangularPark=new TriangularPark();
        // Calculate number of rounds to complete 5km run
        double rounds=triangularPark.calculateRounds(side1,side2,side3);
        
        System.out.println("Rounds to complete 5km run: "+rounds);
        sc.close();
    }

    public double calculateRounds(double side1,double side2,double side3){
        double perimeter=side1+side2+side3;
        return 5000/perimeter;
    }
}