import java.util.*;

public class CalendarDisplay {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter month (1-12): ");
        int month=scanner.nextInt();
        System.out.print("Enter year: ");
        int year=scanner.nextInt();

        displayCalendar(month, year);
        scanner.close();
    }

    public static void displayCalendar(int m, int y) {
        String[] months={
            "", "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        int[] daysInMonth={0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(y)) {
            daysInMonth[2]=29;
        }

        int d=1; 
        //  First day of the month
        int dayOfWeek=getDayOfWeek(m, d, y);

        System.out.println("\n   " + months[m] + " " + y);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        // Print initial spaces
        for (int i=0;i<dayOfWeek;i++) {
            System.out.print("    ");
        }

        for (int i=1;i<=daysInMonth[m];i++) {
            System.out.println(i);
            if ((i+dayOfWeek)%7==0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public static int getDayOfWeek(int m, int d, int y) {
        int y0=y-(14-m)/12;
        int x=y0+y0/4-y0/100+y0/400;
        int m0=m+12*((14-m)/12)-2;
        int d0=(d+x+31*m0/12)%7;
        return d0;
    }

    public static boolean isLeapYear(int year) {
        return (year%4==0&&year%100!=0)||(year%400==0);
    }
}