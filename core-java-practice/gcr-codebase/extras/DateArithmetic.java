import java.time.LocalDate;
import java.util.*;

public class DateArithmetic {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        System.out.print("Enter a date (yyyy-MM-dd): ");
        String input=sc.next();
        
        LocalDate date = LocalDate.parse(input);

        // Add 7 days, 1 month, 2 years, then subtract 3 weeks 
        LocalDate result = date.plusDays(7).plusMonths(1).plusYears(2).minusWeeks(3);
        
        System.out.println("Result after arithmetic: " + result);
        sc.close();
    }
}