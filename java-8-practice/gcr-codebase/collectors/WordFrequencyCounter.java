import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        String paragraph = "Java streams make data processing simple. Streams process data in a functional style.";
        // regex to remove punctuation and convert to lower case
        List<String> words = Arrays.stream(paragraph.toLowerCase()
                        .replaceAll("[^a-z0-9 ]", "")
                        .split("\\s+"))
                .collect(Collectors.toList());

        Map<String, Integer> wordCount = words.stream()
                .collect(Collectors.toMap(w -> w, w -> 1, Integer::sum, LinkedHashMap::new));

        System.out.println("Word Frequency:");
        wordCount.forEach((word, count) -> System.out.println(word + " -> " + count));
    }
}
