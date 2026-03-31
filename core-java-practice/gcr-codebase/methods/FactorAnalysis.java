import java.util.*;

public class FactorAnalysis {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n=sc.nextInt();
        
        FactorAnalysis fa=new FactorAnalysis();
        int[] factors=fa.findFactors(n);
        
        System.out.print("Factors: ");
        for(int f:factors)System.out.print(f+" ");
        System.out.println();
        
        System.out.println("Greatest Factor: "+fa.findGreatestFactor(factors));
        System.out.println("Sum of Factors: "+fa.sumFactors(factors));
        System.out.println("Product of Factors: "+fa.productFactors(factors));
        System.out.println("Product of Cubes of Factors: "+fa.productCubeFactors(factors));
        sc.close();
    }

    public int[] findFactors(int n){
        int count=0;
        for(int i=1;i<=n;i++)if(n%i==0)count++;
        int[] factors=new int[count];
        int idx=0;
        for(int i=1;i<=n;i++)if(n%i==0)factors[idx++]=i;
        return factors;
    }

    public int findGreatestFactor(int[] factors){
        int max=factors[0];
        for(int f:factors)if(f>max)max=f;
        return max;
    }

    public int sumFactors(int[] factors){
        int sum=0;
        for(int f:factors)sum+=f;
        return sum;
    }

    public long productFactors(int[] factors){
        long prod=1;
        for(int f:factors)prod*=f;
        return prod;
    }

    public double productCubeFactors(int[] factors){
        double prod=1;
        for(int f:factors)prod*=Math.pow(f,3);
        return prod;
    }
}