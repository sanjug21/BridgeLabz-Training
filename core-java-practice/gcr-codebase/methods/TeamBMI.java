import java.util.*;

public class TeamBMI {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        // Index 0: weight, 
        // Index 1: height, 
        // Index 2: BMI
        double[][] data=new double[10][3]; 
        TeamBMI teamBMI=new TeamBMI();
        
        for(int i=0;i<10;i++){
            System.out.print("Enter weight (kg) for person "+(i+1)+": ");
            data[i][0]=sc.nextDouble();
            System.out.print("Enter height (cm) for person "+(i+1)+": ");
            data[i][1]=sc.nextDouble();
        }
        
        teamBMI.calculateBMI(data);
        String[] statuses=teamBMI.getBMIStatus(data);
        
        for(int i=0;i<10;i++){
            System.out.println("Person "+(i+1)+": Height="+data[i][1]+"cm, Weight="+data[i][0]+"kg, BMI="+data[i][2]+", Status="+statuses[i]);
        }
        sc.close();
    }

    public void calculateBMI(double[][] data){
        for(int i=0;i<data.length;i++){
            double heightInMeters=data[i][1]/100.0;
            data[i][2]=data[i][0]/(heightInMeters*heightInMeters);
        }
    }

    public String[] getBMIStatus(double[][] data){
        String[] status=new String[data.length];
        for(int i=0;i<data.length;i++){
            double bmi=data[i][2];
            if(bmi<=18.5)status[i]="Underweight";
            else if(bmi<=24.9)status[i]="Normal";
            else if(bmi<=29.9)status[i]="Overweight";
            else status[i]="Obese";
        }
        return status;
    }
}