package date_format_utility;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DateFormatUtility {

    public static void main(String[] args) {
        LocalDate invoiceDate = LocalDate.of(2026, 2, 4);
        List<String> patterns = Arrays.asList(
                "dd-MM-yyyy",
                "MMM dd, yyyy",
                "yyyy/MM/dd"
        );

        System.out.println("Date Format Utility");
        System.out.println("===================");

        for (String pattern : patterns) {
            String formatted = DateFormatter.formatDate(invoiceDate, pattern);
            System.out.println(pattern + " -> " + formatted);
        }
    }
}
