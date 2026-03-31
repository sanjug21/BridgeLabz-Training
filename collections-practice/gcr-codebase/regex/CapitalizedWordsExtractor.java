import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapitalizedWordsExtractor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter a sentence:");
        String text = sc.nextLine();

        // 2. Extract Capitalized Words
        List<String> capitalizedWords = extractCapitalizedWords(text);

        // 3. Display Results
        if (capitalizedWords.isEmpty()) {
            System.out.println("No capitalized words found.");
        } else {
            System.out.println("Capitalized Words:");
            System.out.println(String.join(", ", capitalizedWords));
        }

        sc.close();
    }

    public static List<String> extractCapitalizedWords(String text) {
        List<String> capitalizedWords = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return capitalizedWords;
        }

        // Pattern for words starting with uppercase letter
        String regex = "\\b[A-Z][a-z]*\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            capitalizedWords.add(matcher.group());
        }

        return capitalizedWords;
    }
}
