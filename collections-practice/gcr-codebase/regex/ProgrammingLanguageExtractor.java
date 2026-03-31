import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgrammingLanguageExtractor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter text containing programming language names:");
        String text = sc.nextLine();

        // 2. Extract Programming Languages
        List<String> languages = extractProgrammingLanguages(text);

        // 3. Display Results
        if (languages.isEmpty()) {
            System.out.println("No programming languages found.");
        } else {
            System.out.println("Programming Languages Found:");
            System.out.println(String.join(", ", languages));
        }

        sc.close();
    }

    public static List<String> extractProgrammingLanguages(String text) {
        List<String> languages = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return languages;
        }

        // Common programming languages
        String[] knownLanguages = {
            "Java", "Python", "JavaScript", "TypeScript", "C", "C\\+\\+", "C#",
            "Ruby", "PHP", "Swift", "Kotlin", "Go", "Rust", "Scala", "Perl",
            "R", "MATLAB", "SQL", "HTML", "CSS"
        };

        for (String lang : knownLanguages) {
            String regex = "\\b" + lang + "\\b";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) {
                // Get the actual matched text to preserve case
                matcher.reset();
                while (matcher.find()) {
                    String matched = matcher.group();
                    if (!languages.contains(matched)) {
                        languages.add(matched);
                    }
                }
            }
        }

        return languages;
    }
}
