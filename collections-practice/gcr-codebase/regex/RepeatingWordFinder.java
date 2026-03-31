import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepeatingWordFinder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter a sentence:");
        String text = sc.nextLine();

        // 2. Find Repeating Words
        List<String> repeatingWords = findRepeatingWords(text);

        // 3. Display Results
        if (repeatingWords.isEmpty()) {
            System.out.println("No repeating words found.");
        } else {
            System.out.println("Repeating Words:");
            System.out.println(String.join(", ", repeatingWords));
        }

        sc.close();
    }

    public static List<String> findRepeatingWords(String text) {
        List<String> repeatingWords = new ArrayList<>();
        Set<String> foundWords = new HashSet<>();
        
        if (text == null || text.isEmpty()) {
            return repeatingWords;
        }

        // Pattern to find consecutive duplicate words
        String regex = "\\b(\\w+)\\s+\\1\\b";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String word = matcher.group(1).toLowerCase();
            if (!foundWords.contains(word)) {
                repeatingWords.add(word);
                foundWords.add(word);
            }
        }

        return repeatingWords;
    }
}
