import java.util.Scanner;

public class SumOfNaturalNumbersCheck {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        
        if(n>0){
            int formulaSum=n*(n+1)/2;
            int whileSum=0;
            int i=1;
            while(i<=n){
                whileSum+=i;
                i++;
            }
            if(formulaSum==whileSum){
                System.out.println("The sum of "+n+" natural numbers is "+formulaSum+" and the result from both computations was correct.");
            }
        } else {
            System.out.println("The number "+n+" is not a natural number");
        }
        sc.close();
    }
}
