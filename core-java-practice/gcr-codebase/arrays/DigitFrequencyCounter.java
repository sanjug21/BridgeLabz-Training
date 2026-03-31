import java.util.Scanner;

public class DigitFrequencyCounter {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number=sc.nextLong();
        
        long temp=number;
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
            digits[i]=(int)(temp%10);
            temp/=10;
        }

        int[] frequency=new int[10];
        // Calculate frequency of each digit
        for(int i=0;i<count;i++){
            frequency[digits[i]]++;
        }

        // Display frequency
        for(int i=0;i<10;i++){
            if(frequency[i]>0){
                System.out.println("Frequency of "+i+": "+frequency[i]);
            }
        }
        sc.close();
    }
}