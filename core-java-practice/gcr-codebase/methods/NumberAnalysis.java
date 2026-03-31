import java.util.*;

public class NumberAnalysis {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] numbers=new int[5];
        NumberAnalysis na=new NumberAnalysis();
        
        for(int i=0;i<5;i++){
            System.out.print("Enter number "+(i+1)+": ");
            numbers[i]=sc.nextInt();
        }        
        for(int num:numbers){
            if(na.isPositive(num)){
                if(na.isEven(num)) System.out.println(num+" is Positive and Even");
                else System.out.println(num+" is Positive and Odd");
            }else{
                System.out.println(num+" is Negative or Zero");
            }
        }
        
        int comparison=na.compare(numbers[0],numbers[numbers.length-1]);
        if(comparison==1)System.out.println("First element is greater than last element");
        else if(comparison==-1)System.out.println("First element is less than last element");
        else System.out.println("First and last elements are equal");
        sc.close();
    }

    public boolean isPositive(int n){
        return n>0;
    }

    public boolean isEven(int n){
        return n%2==0;
    }

    public int compare(int n1,int n2){
        if(n1>n2) return 1;
        if(n1<n2) return -1;
        return 0;
    }
}