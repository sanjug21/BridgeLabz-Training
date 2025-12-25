import java.util.*;

public class TrigonometricFunctions {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter angle in degrees: ");
        double angle=sc.nextDouble();
        
        TrigonometricFunctions trigonometricFunctions=new TrigonometricFunctions();
        // Calculate trigonometric functions using defined methods
        double[] results=trigonometricFunctions.calculateTrigonometricFunctions(angle);
        
        System.out.println("Sine: "+results[0]);
        System.out.println("Cosine: "+results[1]);
        System.out.println("Tangent: "+results[2]);
        sc.close();
    }

    public double[] calculateTrigonometricFunctions(double angle){
        double radians=Math.toRadians(angle);
        return new double[]{Math.sin(radians),Math.cos(radians),Math.tan(radians)};
    }
}