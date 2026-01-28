import java.util.Scanner;

public class MultipleSpaceReplacer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter text with multiple spaces:");
        String text = sc.nextLine();

        // 2. Replace Multiple Spaces
        String result = replaceMultipleSpaces(text);

        // 3. Display Result
        System.out.println("Original: \"" + text + "\"");
        System.out.println("Result: \"" + result + "\"");

        sc.close();
    }

    public static String replaceMultipleSpaces(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        // Replace multiple spaces with single space
        return text.replaceAll("\\s+", " ");
    }
}
