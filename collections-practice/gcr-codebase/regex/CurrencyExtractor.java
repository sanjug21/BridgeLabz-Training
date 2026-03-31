import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyExtractor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter text containing currency values:");
        String text = sc.nextLine();

        // 2. Extract Currency Values
        List<String> currencyValues = extractCurrencyValues(text);

        // 3. Display Results
        if (currencyValues.isEmpty()) {
            System.out.println("No currency values found.");
        } else {
            System.out.println("Currency Values Found:");
            System.out.println(String.join(", ", currencyValues));
        }

        sc.close();
    }

    public static List<String> extractCurrencyValues(String text) {
        List<String> currencyValues = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return currencyValues;
        }

        // Pattern for currency with $ symbol and decimal numbers
        String regex = "\\$\\d+\\.\\d{2}|\\d+\\.\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            currencyValues.add(matcher.group());
        }

        return currencyValues;
    }
}
