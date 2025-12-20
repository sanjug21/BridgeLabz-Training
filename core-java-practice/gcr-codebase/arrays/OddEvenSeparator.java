import java.util.Scanner;

public class OddEvenSeparator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a natural number: ");
        int number=sc.nextInt();

        if(number<=0){
            System.out.println("Error: Not a natural number.");
            System.exit(0);
        }

        int size=number/2+1;
        int[] oddNumbers=new int[size];
        int[] evenNumbers=new int[size];
        
        int oddIndex=0;
        int evenIndex=0;

        // Loop from 1 to number
        for(int i=1;i<=number;i++){
            if(i%2==0){
                evenNumbers[evenIndex]=i;
                evenIndex++;
            }else{
                oddNumbers[oddIndex]=i;
                oddIndex++;
            }
        }

        // Prints odd numbers
        System.out.print("Odd numbers: ");
        for(int i=0;i<oddIndex;i++){
            System.out.print(oddNumbers[i]+" ");
        }
        System.out.println();

        // Prints even numbers
        System.out.print("Even numbers: ");
        for(int i=0;i<evenIndex;i++){
            System.out.print(evenNumbers[i]+" ");
        }
        System.out.println();
        sc.close();
    }
}