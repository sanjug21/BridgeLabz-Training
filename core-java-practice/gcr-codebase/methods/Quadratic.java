import java.util.*;

public class Quadratic {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a: ");
        double a=sc.nextDouble();
        System.out.print("Enter b: ");
        double b=sc.nextDouble();
        System.out.print("Enter c: ");
        double c=sc.nextDouble();
        
        Quadratic q=new Quadratic();
        double[] roots=q.findRoots(a,b,c);
        
        if(roots.length==0) System.out.println("No real roots.");
        else if(roots.length==1) System.out.println("One root: "+roots[0]);
        else{
            System.out.println("Root 1: "+roots[0]);
            System.out.println("Root 2: "+roots[1]);
        }
        sc.close();
    }

    public double[] findRoots(double a,double b,double c){
        // formula d=b^2-4ac
        double d=b*b-4*a*c;
        if(d>0){
            double root1=(-b+Math.sqrt(d))/(2*a);
            double root2=(-b-Math.sqrt(d))/(2*a);
            return new double[]{root1,root2};
        }else if(d==0){
            double root=-b/(2*a);
            return new double[]{root};
        }else return new double[]{};
    }
}