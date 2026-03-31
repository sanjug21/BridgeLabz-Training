import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkExtractor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter text containing URLs:");
        String text = sc.nextLine();

        // 2. Extract Links
        List<String> links = extractLinks(text);

        // 3. Display Results
        if (links.isEmpty()) {
            System.out.println("No links found.");
        } else {
            System.out.println("Extracted Links:");
            System.out.println(String.join(", ", links));
        }

        sc.close();
    }

    public static List<String> extractLinks(String text) {
        List<String> links = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return links;
        }

        // URL pattern for http and https
        String regex = "https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/[^\\s]*)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            links.add(matcher.group());
        }

        return links;
    }
}
