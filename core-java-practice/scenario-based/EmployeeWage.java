import java.util.Scanner;

public class EmployeeWage {
    
    static Scanner sc = new Scanner(System.in);
    // all these values are provided in the problem statement or we have considered them as constants
    static int Wage_Per_Hour=20;
    static int Full_Day_Hour=8;
    static int Part_Time_Hour=4;
    static int No_Of_Working_Days=20;
    static int Max_Hours_In_Month=100;
    static int Absent=0;
    static final int Full_Time=1;
    static final int Part_Time=2;


 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Employee Wage Computation Program");
        System.out.println("Choose an option to execute:");
        System.out.println("1. Check Employee is Present or Absent");
        System.out.println("2. Calculate Daily Employee Wage");
        System.out.println("3. Part Time Employee Wage");   
        System.out.println("4. Solving using Switch Case Statement");
        System.out.println("5. Calculating Wages for a Month");
        System.out.println("6. Calculating Wages till a condition of total working hours or days is reached for a month");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                isEmployeePresent();
                break;
            case 2:
                calculateDailyWage();
                break;
            case 3:
                partTimeEmployeeWage();
                break;
            case 4:
                switchCaseEmployeeWage();
                break;
            case 5:
                monthlyWageComputation();
                break;
            case 6:
                monthlyWageTillCondition();
                break;
            default:
                System.out.println("Invalid choice! Please select a valid option.");
        }
        sc.close();   
    }

    // UC-1: Check Employee is Present or Absent
    public static void isEmployeePresent(){
        // generate random number 0 or 1
        int attendance = (int) Math.floor(Math.random() * 10) % 2;
        if(attendance == 0){
            System.out.println("Employee is Absent");
        } else {
            System.out.println("Employee is Present");
        }

    }

    // UC-2: Calculate Daily Employee Wage
    public static void calculateDailyWage(){
        int typeOfEmp=typeOfEmployee();
        int empDailyWage=0;
        if (typeOfEmp==Full_Time) {
            empDailyWage=Full_Day_Hour * Wage_Per_Hour;
        } else if(typeOfEmp==Part_Time){
            empDailyWage=Part_Time_Hour * Wage_Per_Hour;
        } else  {
            empDailyWage=0;
        }
        String empTypeName=(typeOfEmp==Full_Time)?"Full Time":(typeOfEmp==Part_Time)?"Part Time":"Absent";
        System.out.println("Daily Employee Wage for "+empTypeName+" is: "+empDailyWage);
    }

    // UC-3: Part Time Employee Wage
    public static void partTimeEmployeeWage(){
        int partTimeWage=Part_Time_Hour * Wage_Per_Hour;
        System.out.println("Part Time Employee Daily Wage is: "+partTimeWage);
    }

    // UC-4: Solving using Switch Case Statement
    public static void switchCaseEmployeeWage(){
        int typeOfEmp=typeOfEmployee();
        int empDailyWage=0;
        switch (typeOfEmp) {
            case Full_Time:
                empDailyWage=Full_Day_Hour * Wage_Per_Hour;
                break;
            case Part_Time:
                empDailyWage=Part_Time_Hour * Wage_Per_Hour;
                break;
            default:
                empDailyWage=0;
        }
        String empTypeName=(typeOfEmp==Full_Time)?"Full Time":(typeOfEmp==Part_Time)?"Part Time":"Absent";
        System.out.println("Daily Employee Wage for "+empTypeName+" is: "+empDailyWage);
    }
      
    // UC-5: Calculating Wages for a Month
    public static void monthlyWageComputation(){
        int totalWage=0;
        for (int day = 1; day <= No_Of_Working_Days; day++) {
            int typeOfEmp=typeOfEmployee();
            switch (typeOfEmp) {
                case Full_Time:
                    totalWage+=Full_Day_Hour * Wage_Per_Hour;
                    break;
                case Part_Time:
                    totalWage+=Part_Time_Hour * Wage_Per_Hour;
                    break;
                default:
                    totalWage+=0;
            }
        }
        System.out.println("Total Employee Wage for a Month is: "+totalWage);
    }

    // UC-6: Calculating Wages till a condition of total working hours or days is reached for a month
    public static void monthlyWageTillCondition(){
        int totalWage=0;
        int totalHours=0;
        int day=0;
        while (totalHours<Max_Hours_In_Month && day<No_Of_Working_Days) {
            day++;
            int typeOfEmp=typeOfEmployee();
            switch (typeOfEmp) {
                case Full_Time:
                    totalWage+=Full_Day_Hour * Wage_Per_Hour;
                    totalHours+=Full_Day_Hour;
                    break;
                case Part_Time:
                    totalWage+=Part_Time_Hour * Wage_Per_Hour;
                    totalHours+=Part_Time_Hour;
                    break;
                default:
                    totalWage+=0;
            }
        }
        
        System.out.println("Day: " + day + " Total Hours: " + totalHours + " Total Wage: " + totalWage);
    }


    public static int typeOfEmployee(){
        // generate random number 0, 1 or 2
        // 0 - absent, 1 - full time, 2 - part time
        return (int) Math.floor(Math.random() * 10) % 3;
    }

}