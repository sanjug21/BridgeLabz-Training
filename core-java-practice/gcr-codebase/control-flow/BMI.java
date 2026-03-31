import java.util.Scanner;

public class BMI {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double weight=sc.nextDouble();
        double height=sc.nextDouble();
        
        double heightInMeters=height/100;
        double bmi=weight/(heightInMeters*heightInMeters);
        
        if(bmi<18.5){
            System.out.println("Underweight");
        } else if(bmi>=18.5 && bmi<25){
            System.out.println("Normal weight");
        } else if(bmi>=25 && bmi<30){
            System.out.println("Overweight");
        } else {
            System.out.println("Obese");
        }
        sc.close();
    }
}
