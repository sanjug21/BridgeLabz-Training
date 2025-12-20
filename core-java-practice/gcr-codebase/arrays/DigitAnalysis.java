import java.util.Scanner;

public class DigitAnalysis {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number=sc.nextLong();
        
        int maxDigit=10;
        long[] digits=new long[maxDigit];
        int index=0;
        
        long tempNum=number;
        // Loop to extract digits
        while(tempNum!=0){
            if(index==maxDigit){
                break;
            }
            digits[index]=tempNum%10;
            tempNum/=10;
            index++;
        }

        long largest=0;
        long secondLargest=0;

        // Loop to find largest and second largest
        for(int i=0;i<index;i++){
            if(digits[i]>largest){
                secondLargest=largest;
                largest=digits[i];
            }else if(digits[i]>secondLargest && digits[i]!=largest){
                secondLargest=digits[i];
            }
        }

        System.out.println("Largest digit: "+largest);
        System.out.println("Second largest digit: "+secondLargest);
        sc.close();
    }
}