import java.util.Scanner;

public class LeapYearSingleIf {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int year=sc.nextInt();
        
        if(year>=1582){
            if((year%4==0 && year%100!=0) || (year%400==0)){
                System.out.println("Year is a Leap Year");
            } else {
                System.out.println("not a Leap Year");
            }
        } else {
            System.out.println("The year must be at least 1582");
        }
        sc.close();
    }
}
