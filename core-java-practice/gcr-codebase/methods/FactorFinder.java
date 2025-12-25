import java.util.*;

public class FactorFinder {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number=sc.nextInt();
        
        FactorFinder finder=new FactorFinder();
        int[] factors=finder.findFactors(number);
        
        System.out.print("Factors are: ");
        for(int factor:factors) {
            System.out.print(factor+" ");
        }
        System.out.println();
        
        System.out.println("Sum of factors is: "+finder.sumFactors(factors));
        System.out.println("Product of factors is: "+finder.productFactors(factors));
        System.out.println("Sum of squares of factors is: "+finder.sumSquareFactors(factors));
        sc.close();
    }

    public int[] findFactors(int number){
        int count=0;
        for(int i=1;i<=number;i++){
            if(number%i==0)count++;
        }
        int[] factors=new int[count];
        int index=0;
        for(int i=1;i<=number;i++){
            if(number%i==0)factors[index++]=i;
        }
        return factors;
    }

    public int sumFactors(int[] factors){
        int sum=0;
        for(int factor:factors)sum+=factor;
        return sum;
    }

    public long productFactors(int[] factors){
        long product=1;
        for(int factor:factors)product*=factor;
        return product;
    }

    public double sumSquareFactors(int[] factors){
        double sum=0;
        for(int factor:factors)sum+=Math.pow(factor,2);
        return sum;
    }
}