import java.util.Scanner;

public class DigitAnalysis2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number=sc.nextLong();
        
        int maxDigit=10;
        long[] digits=new long[maxDigit];
        int index=0;
        long temp=number;
        
        // Loop to extract digits with dynamic array resizing
        while(temp!=0){
            if(index==maxDigit){
                // Increase size of array by 10
                maxDigit+=10;
                long[] tempArray=new long[maxDigit];
                // Copy elements to new array
                for(int i=0;i<index;i++){
                    tempArray[i]=digits[i];
                }
                digits=tempArray;
            }
            digits[index]=temp%10;
            temp/=10;
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
