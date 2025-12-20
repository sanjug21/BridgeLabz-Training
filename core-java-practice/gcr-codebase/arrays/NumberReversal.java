import java.util.Scanner;

public class NumberReversal {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number=sc.nextInt();
        int temp=number;
        int count=0;
        // Find the count of digits
        while(temp!=0){
            temp/=10;
            count++;
        }
        int[] digits=new int[count];
        temp=number;
        // Store digits in array
        for(int i=0;i<count;i++){
            digits[i]=temp%10;
            temp/=10;
        }
        int[] reverseDigits=new int[count];
        // Store elements in reverse order
        for(int i=0;i<count;i++){
            reverseDigits[i]=digits[i];
        }

        System.out.print("Reversed number digits: ");
        for(int i=0;i<count;i++){
            System.out.print(reverseDigits[i]+" ");
        }
        sc.close();
    }
}