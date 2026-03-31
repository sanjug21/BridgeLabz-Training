import java.util.*;

public class LeapYear {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a year: ");
        int year=sc.nextInt();
        
        LeapYear lp=new LeapYear();
        if(lp.checkLeapYear(year))System.out.println(year+" is a Leap Year");
        else System.out.println(year+" is not a Leap Year");
        
        sc.close();
    }

    public boolean checkLeapYear(int year){
        if(year<1582) return false;
        return (year%4==0 && year%100!=0) || (year%400==0);
    }
}