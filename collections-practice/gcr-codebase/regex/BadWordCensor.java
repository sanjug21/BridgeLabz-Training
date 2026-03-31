import java.util.Scanner;
import java.util.regex.Pattern;

public class BadWordCensor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter a sentence:");
        String text = sc.nextLine();

        // 2. Input Bad Words
        System.out.println("Enter bad words separated by commas (e.g., damn,stupid):");
        String badWordsInput = sc.nextLine();
        String[] badWords = badWordsInput.split(",");

        // 3. Censor Bad Words
        String censored = censorBadWords(text, badWords);

        // 4. Display Result
        System.out.println("Original: \"" + text + "\"");
        System.out.println("Censored: \"" + censored + "\"");

        sc.close();
    }

    public static String censorBadWords(String text, String[] badWords) {
        if (text == null || text.isEmpty() || badWords == null) {
            return text;
        }

        String result = text;
        for (String badWord : badWords) {
            if (badWord != null && !badWord.trim().isEmpty()) {
                String trimmedBadWord = badWord.trim();
                // Case-insensitive replacement
                String regex = "(?i)\\b" + Pattern.quote(trimmedBadWord) + "\\b";
                result = result.replaceAll(regex, "****");
            }
        }

        return result;
    }
}
