import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FrequencyCounter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Strings
        System.out.println("Enter strings separated by spaces (e.g., apple banana apple):");
        String inputLine = sc.nextLine();
        
        sc.close();

        if (inputLine.trim().isEmpty()) {
            System.out.println("No input provided.");
            return;
        }

        List<String> words = Arrays.asList(inputLine.split("\\s+"));

        // 2. Calculate Frequency
        Map<String, Integer> frequency = findFrequency(words);

        // 3. Display Results
        System.out.println("Frequency: " + frequency);

    }

    
    public static Map<String, Integer> findFrequency(List<String> list) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String item : list) {
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }
        return frequencyMap;
    }
}