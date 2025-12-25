import java.util.*;

public class CollinearPoints {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter x1 y1 points: ");
        double x1=sc.nextDouble();
        double y1=sc.nextDouble();
        System.out.println("Enter x2 y2 points: ");
        double x2=sc.nextDouble();
        double y2=sc.nextDouble();
        System.out.println("Enter x3 y3 points: ");
        double x3=sc.nextDouble();
        double y3=sc.nextDouble();
        
        CollinearPoints cp=new CollinearPoints();
        boolean slopeCheck=cp.checkCollinearSlope(x1,y1,x2,y2,x3,y3);
        boolean areaCheck=cp.checkCollinearArea(x1,y1,x2,y2,x3,y3);
        
        System.out.println("Collinear using slope: "+slopeCheck);
        System.out.println("Collinear using area: "+areaCheck);
        sc.close();
    }

    public boolean checkCollinearSlope(double x1,double y1,double x2,double y2,double x3,double y3){
        double slopeAB=(y2-y1)/(x2-x1);
        double slopeBC=(y3-y2)/(x3-x2);
        double slopeAC=(y3-y1)/(x3-x1);
        // Check if slopes are equal or not
        return slopeAB==slopeBC && slopeBC==slopeAC;
    }

    public boolean checkCollinearArea(double x1,double y1,double x2,double y2,double x3,double y3){
        double area=0.5*(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2));
        return area==0;
    }
}