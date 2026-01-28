import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter text containing email addresses:");
        String text = sc.nextLine();

        // 2. Extract Emails
        List<String> emails = extractEmails(text);

        // 3. Display Results
        if (emails.isEmpty()) {
            System.out.println("No email addresses found.");
        } else {
            System.out.println("Extracted Email Addresses:");
            for (String email : emails) {
                System.out.println(email);
            }
        }

        sc.close();
    }

    public static List<String> extractEmails(String text) {
        List<String> emails = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return emails;
        }

        // Email pattern
        String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            emails.add(matcher.group());
        }

        return emails;
    }
}
