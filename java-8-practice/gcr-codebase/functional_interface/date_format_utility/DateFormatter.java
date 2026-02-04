package date_format_utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface DateFormatter {

    static String formatDate(LocalDate date, String pattern) {
        if (date == null || pattern == null || pattern.isBlank()) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }
}
