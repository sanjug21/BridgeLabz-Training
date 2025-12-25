import java.util.*;

public class SmallestLargest {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number 1: ");
        int number1=sc.nextInt();
        System.out.print("Enter number 2: ");
        int number2=sc.nextInt();
        System.out.print("Enter number 3: ");
        int number3=sc.nextInt();
        
        SmallestLargest smallestLargest=new SmallestLargest();
        // Finds smallest and largest among 3 numbers
        int[] result=smallestLargest.findSmallestAndLargest(number1,number2,number3);
        
        System.out.println("Smallest: "+result[0]);
        System.out.println("Largest: "+result[1]);
        sc.close();
    }

    public int[] findSmallestAndLargest(int number1,int number2,int number3){
        int min=number1;
        int max=number1;
        if(number2<min)min=number2;
        if(number3<min)min=number3;
        if(number2>max)max=number2;
        if(number3>max)max=number3;
        return new int[]{min,max};
    }
}