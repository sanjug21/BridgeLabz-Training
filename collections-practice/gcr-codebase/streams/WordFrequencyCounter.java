import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java WordFrequencyCounter <filePath>");
            return;
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return;
        }

        try {
            Map<String, Integer> frequencies = countWords(file);
            List<Entry<String, Integer>> top = topFive(frequencies);
            System.out.println("Top 5 words:");
            for (Entry<String, Integer> entry : top) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

    private static Map<String, Integer> countWords(File file) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase().split("[^a-z0-9']+");
                for (String word : words) {
                    if (word.isEmpty()) {
                        continue;
                    }
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            }
        }
        return map;
    }

    private static List<Entry<String, Integer>> topFive(Map<String, Integer> map) {
        List<Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Comparator.comparing(Entry<String, Integer>::getValue).reversed());
        return entries.subList(0, Math.min(5, entries.size()));
    }
}
