import java.time.LocalDate;
import java.util.*;

public class DateComparison {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        System.out.print("Enter first date (yyyy-MM-dd): ");
        LocalDate date1 = LocalDate.parse(sc.next());
        
        System.out.print("Enter second date (yyyy-MM-dd): ");
        LocalDate date2 = LocalDate.parse(sc.next());

        if (date1.isBefore(date2)) {
            System.out.println(date1+" is before "+date2);
        } else if (date1.isAfter(date2)) {
            System.out.println(date1+" is after "+date2);
        } else if (date1.isEqual(date2)) {
            System.out.println(date1+" is the same as "+date2);
        }
        
        sc.close();
    }
}