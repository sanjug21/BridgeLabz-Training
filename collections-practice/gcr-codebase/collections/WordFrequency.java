import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordFrequency {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Text
        System.out.println("Enter a line of text:");
        String text = sc.nextLine();

        // 2. Count Frequency
        Map<String, Integer> frequency = countFrequency(text);
        System.out.println("Word Frequency: " + frequency);

        sc.close();
    }

    public static Map<String, Integer> countFrequency(String text) {
        Map<String, Integer> map = new HashMap<>();
        if (text == null || text.trim().isEmpty()) return map;

        // Split by non-word characters and convert to lowercase
        String[] words = text.toLowerCase().split("\\W+");
        for (String word : words) {
            if (!word.isEmpty()) map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }
}