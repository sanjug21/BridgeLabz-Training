import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateExtractor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter text containing dates (dd/mm/yyyy format):");
        String text = sc.nextLine();

        // 2. Extract Dates
        List<String> dates = extractDates(text);

        // 3. Display Results
        if (dates.isEmpty()) {
            System.out.println("No dates found.");
        } else {
            System.out.println("Extracted Dates:");
            System.out.println(String.join(", ", dates));
        }

        sc.close();
    }

    public static List<String> extractDates(String text) {
        List<String> dates = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return dates;
        }

        // Date pattern: dd/mm/yyyy
        String regex = "\\b\\d{2}/\\d{2}/\\d{4}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            dates.add(matcher.group());
        }

        return dates;
    }
}
