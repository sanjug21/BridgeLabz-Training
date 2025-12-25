import java.util.*;

public class LineGeometry {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter x1 y1: ");
        double x1=sc.nextDouble();
        double y1=sc.nextDouble();
        System.out.print("Enter x2 y2: ");
        double x2=sc.nextDouble();
        double y2=sc.nextDouble();
        
        LineGeometry lg=new LineGeometry();
        double dist=lg.calculateDistance(x1,y1,x2,y2);
        double[] eq=lg.findLineEquation(x1,y1,x2,y2);
        
        System.out.println("Distance b/w points is: "+dist);
        System.out.println("Equation: y = "+eq[0]+"x + "+eq[1]);
        sc.close();
    }

    public double calculateDistance(double x1,double y1,double x2,double y2){
        return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
    }

    public double[] findLineEquation(double x1,double y1,double x2,double y2){
        // y = mx + b
        // m = (y2-y1)/(x2-x1)
        // b = y1 - m*x1
        double m=(y2-y1)/(x2-x1);
        double b=y1-m*x1;
        return new double[]{m,b};
    }
}