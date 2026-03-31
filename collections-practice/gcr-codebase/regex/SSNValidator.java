import java.util.Scanner;
import java.util.regex.Pattern;

public class SSNValidator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter text containing SSN:");
        String text = sc.nextLine();

        // 2. Extract and Validate SSN
        String ssn = extractSSN(text);

        // 3. Display Result
        if (ssn != null) {
            boolean isValid = validateSSN(ssn);
            if (isValid) {
                System.out.println("\"" + ssn + "\" is valid");
            } else {
                System.out.println("\"" + ssn + "\" is invalid");
            }
        } else {
            System.out.println("No SSN found in the text.");
        }

        sc.close();
    }

    public static String extractSSN(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }

        // SSN pattern: XXX-XX-XXXX
        String regex = "\\b\\d{3}-\\d{2}-\\d{4}\\b";
        Pattern pattern = Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }

        return null;
    }

    public static boolean validateSSN(String ssn) {
        if (ssn == null || ssn.isEmpty()) {
            return false;
        }

        // Valid SSN format: XXX-XX-XXXX
        String regex = "^\\d{3}-\\d{2}-\\d{4}$";
        return Pattern.matches(regex, ssn);
    }
}
