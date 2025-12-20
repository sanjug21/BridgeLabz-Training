import java.util.Scanner;

public class TeamBMI2D {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of persons: ");
        int number=sc.nextInt();

        // 2D array: col 0=weight, col 1=height, col 2=BMI
        double[][] personData=new double[number][3];
        String[] weightStatus=new String[number];
        for(int i=0;i<number;i++){
            System.out.print("Enter weight and height for person "+(i+1)+": ");
            double w=sc.nextDouble();
            double h=sc.nextDouble();
            
            // Validation for positive input
            if(w<0 || h<0){
                System.out.println("Please enter positive values.");
                i--;
                continue;
            }
            personData[i][0]=w;
            personData[i][1]=h;
            // Calculated BMI
            personData[i][2]=w/(h*h);            
            if(personData[i][2]<18.5){
                weightStatus[i]="Underweight";
            }else if(personData[i][2]<25){
                weightStatus[i]="Normal";
            }else if(personData[i][2]<30){
                weightStatus[i]="Overweight";
            }else{
                weightStatus[i]="Obese";
            }
            System.out.println("Person "+(i+1)+": Height="+personData[i][1]+", Weight="+personData[i][0]+", BMI="+personData[i][2]+", Status="+weightStatus[i]);
        }
        sc.close();
    }
}