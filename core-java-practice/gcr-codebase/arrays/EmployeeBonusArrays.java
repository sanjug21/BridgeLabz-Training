import java.util.Scanner;

public class EmployeeBonusArrays {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        // Arrays to store salary, years of service, new salary and bonus for 10 employees
        double[] salary=new double[10];
        double[] years=new double[10];
        double[] newSalary=new double[10];
        double[] bonus=new double[10];
        
        double totalBonus=0;
        double totalOldSalary=0;
        double totalNewSalary=0;

        // Loop to take input for 10 employees
        for(int i=0;i<10;i++){
            System.out.print("Enter years of service for employee "+(i+1)+": ");
            double y=sc.nextDouble();
            System.out.print("Enter salary for employee "+(i+1)+": ");
            double s=sc.nextDouble();

            // Validation for positive input
            if(y<0 || s<0){
                System.out.println("Invalid input. Please enter again.");
                i--; 
                continue;
            }
            years[i]=y;
            salary[i]=s;
        }

        // Loop to calculate bonus and new salary
        for(int i=0;i<10;i++){
            if(years[i]>5){
                bonus[i]=salary[i]*0.05;
            }else{
                bonus[i]=salary[i]*0.02;
            }
            newSalary[i]=salary[i]+bonus[i];
            
            totalBonus+=bonus[i];
            totalOldSalary+=salary[i];
            totalNewSalary+=newSalary[i];
        }

        System.out.println("Total Bonus Payout: "+totalBonus);
        System.out.println("Total Old Salary: "+totalOldSalary);
        System.out.println("Total New Salary: "+totalNewSalary);
        sc.close();
    }
}