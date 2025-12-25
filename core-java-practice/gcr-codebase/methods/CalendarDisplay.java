import java.util.*;

public class CalendarDisplay {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter month (1-12): ");
        int m=sc.nextInt();
        System.out.print("Enter year: ");
        int y=sc.nextInt();
        
        CalendarDisplay cal=new CalendarDisplay();
        cal.displayCalendar(m,y);
        sc.close();
    }

    public void displayCalendar(int m,int y){
        String monthName=getMonthName(m);
        int days=getDaysInMonth(m,y);
        int startDay=getDayOfWeek(1,m,y);
        
        System.out.println(monthName+" "+y);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
        
        // Prints initial spaces
        for(int i=0;i<startDay;i++)System.out.print("    ");
        
        for(int i=1;i<=days;i++){
             System.out.printf("%3d ",i);
            if((i+startDay)%7==0)
                System.out.println();
        }
        System.out.println();
    }

    public String getMonthName(int m){
        String[] months={"","January","February","March","April","May","June","July","August","September","October","November","December"};
        return months[m];
    }

    public int getDaysInMonth(int m,int y){
        int[] days={0,31,28,31,30,31,30,31,31,30,31,30,31};
        if(m==2 && isLeapYear(y))return 29;
        return days[m];
    }

    public boolean isLeapYear(int y){
        return (y%4==0 && y%100!=0) || (y%400==0);
    }

    public int getDayOfWeek(int d,int m,int y){
        int y0=y-(14-m)/12;
        int x=y0+y0/4-y0/100+y0/400;
        int m0=m+12*((14-m)/12)-2;
        int d0=(d+x+31*m0/12)%7;
        return d0;
    }
}