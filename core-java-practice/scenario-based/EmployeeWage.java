import java.util.Scanner;

public class EmployeeWage {
   
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // all these values are provided in the problem statement
        int wagePerHour=20;
        int fullDayHour=8;
        int partTimeHour=4;
        int noOfWorkingDays=20;
        int maxHoursInMonth=100;

        System.out.println("Welcome to Employee Wage Computation Program");


        // UC-1: Check Employee is Present or Absent
        System.out.println("UC-1: Check Employee is Present or Absent");
        boolean isPresent=isEmployeePresent();
        if (isPresent) {
            System.out.println("Employee is Present");
        } else {
            System.out.println("Employee is Absent");
        }



        // UC-2: Calculate Daily Employee Wage  
        System.out.println("UC-2: Calculate Daily Employee Wage");
        int empType=typeOfEmployee();
        int empDailyWage=0;
        if (empType==1) {
            empDailyWage=calculateDailyWage(fullDayHour, wagePerHour);
        } else if(empType==2){
            empDailyWage=calculateDailyWage(partTimeHour, wagePerHour);
        } else  {
            empDailyWage=0;
        }
        String empTypeName=(empType==1)?"Full Time":(empType==2)?"Part Time":"Absent";
        System.out.println("Daily Employee Wage for "+empTypeName+" is: "+empDailyWage);


        // UC-3: Part Time Employee Wage
        System.out.println("UC-3: Part Time Employee Wage");
        System.out.println("Enter type of Employee (1.Full Time 2.Part Time): ");
        empType=sc.nextInt();
        if (empType==1) {
            System.out.println("Full Time Employee Daily Wage is: "+calculateDailyWage(fullDayHour, wagePerHour));
        } else if (empType==2) {
            System.out.println("Part Time Employee Daily Wage is: "+calculateDailyWage(partTimeHour, wagePerHour));
        } else {
            System.out.println("Invalid Employee Type");
        }


        // UC-4:Solving using Switch Case Statement
        System.out.println("UC-4: Solving using Switch Case Statement");
        System.out.println("Enter type of Employee (1.Full Time 2.Part Time): ");
        empType=sc.nextInt();
        switch (empType) {
            case 1:
                System.out.println("Full Time Employee Daily Wage is: "+calculateDailyWage(fullDayHour, wagePerHour));
                break;
            case 2:
                System.out.println("Part Time Employee Daily Wage is: "+calculateDailyWage(partTimeHour, wagePerHour));
                break;
            default:
                System.out.println("Invalid Employee Type");
        }


        // UC-5: Calculating Wages for a Month
        System.out.println("UC-5: Calculating Wages for a Month");
        int totalWage=0;
        for (int day = 1; day <= noOfWorkingDays; day++) {
            int typeOfEmp=typeOfEmployee();
            switch (typeOfEmp) {
                case 1:
                    totalWage+=calculateDailyWage(fullDayHour, wagePerHour);
                    break;
                case 2:
                    totalWage+=calculateDailyWage(partTimeHour, wagePerHour);
                    break;
                default:
                    totalWage+=0;
            }
        }
        System.out.println("Total Employee Wage for a Month is: "+totalWage);


        // UC-6: Calculating Wages till a condition of total working hours or days is reached for a month
        System.out.println("UC-6: Calculating Wages till a condition of total working hours or days is reached for a month");
        totalWage=0;
        int totalHours=0;
        int day=0;
        while (totalHours<maxHoursInMonth && day<noOfWorkingDays) {
            day++;
            int typeOfEmp=typeOfEmployee();
            switch (typeOfEmp) {
                case 1:
                    totalWage+=calculateDailyWage(fullDayHour, wagePerHour);
                    totalHours+=fullDayHour;
                    break;
                case 2:
                    totalWage+=calculateDailyWage(partTimeHour, wagePerHour);
                    totalHours+=partTimeHour;
                    break;
                default:
                    totalWage+=0;
            }
        }
        System.out.println("Total Employee Wage till condition of total working hours or days is reached for a month: "+totalWage);
        sc.close();

        
        

    }

    public static boolean isEmployeePresent(){
        // generate random number 0 or 1
        int attendance = (int) Math.floor(Math.random() * 10) % 2;
        // attendance=1 means employee is present else absent
        return attendance==1;
    }

    public static int calculateDailyWage(int noOfHours, int wagePerHour){
        return noOfHours*wagePerHour;
    }

    public static int typeOfEmployee(){
        // generate random number 0, 1 or 2
        // 0 - absent, 1 - full time, 2 - part time
        return (int) Math.floor(Math.random() * 10) % 3;
    }

}