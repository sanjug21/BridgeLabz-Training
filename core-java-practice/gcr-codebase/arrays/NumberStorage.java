import java.util.Scanner;

public class NumberStorage {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double[] numbers=new double[10];
        double total=0.0;
        int index=0;

        // Loop to take input until 0, negative or array full upto 10
        while(true){
            if(index==10){
                break;
            }
            System.out.print("Enter a number (0 or negative to stop): ");
            double input=sc.nextDouble();
            if(input<=0){
                break;
            }
            numbers[index]=input;
            index++;
        }

        System.out.print("Numbers entered: ");
        for(int i=0;i<index;i++){
            System.out.print(numbers[i]+" ");
            total+=numbers[i];
        }
        System.out.println("\nTotal sum: "+total);
        sc.close();
    }
}