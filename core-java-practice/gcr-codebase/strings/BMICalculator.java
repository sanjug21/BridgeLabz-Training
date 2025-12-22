import java.util.*;

public class BMICalculator {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        // 10 members, [weight, height]
        double[][] teamData=new double[10][2]; 

        System.out.println("Enter Weight (kg) and Height (cm) for 10 team members:");
        for (int i=0;i<10;i++) {
            System.out.println("Person " + (i + 1) + ":");
            System.out.print("Weight (kg): ");
            teamData[i][0]=sc.nextDouble();
            System.out.print("Height (cm): ");
            teamData[i][1]=sc.nextDouble();
        }

        String[][] bmiReport=processTeamData(teamData);
        displayTable(bmiReport);
        sc.close();
    }

    public static String[] calculateBMIAndStatus(double weight, double heightCm) {
        double heightM=heightCm/100.0;
        double bmi=weight/(heightM*heightM);
        
        String status;
        if (bmi<18.5) status = "Underweight";
        else if (bmi<25) status = "Normal weight";
        else if (bmi<30) status = "Overweight";
        else status = "Obese";

        return new String[]{
            String.valueOf(heightCm),
            String.valueOf(weight),
            String.format("%.2f", bmi),
            status
        };
    }

    public static String[][] processTeamData(double[][] data) {
        String[][] report=new String[data.length][4];
        for (int i=0;i<data.length;i++) {
            double weight=data[i][0];
            double height=data[i][1];
            report[i]=calculateBMIAndStatus(weight, height);
        }
        return report;
    }

    public static void displayTable(String[][] data) {
        System.out.println( "Height(cm) | Weight(kg) |    BMI    |     Status     ");
        for (String[] row:data) {
            System.out.println(" " + row[0] + "     |  " + row[1] + "    | " + row[2] + " | " + row[3] + " ");
        }
    }
}