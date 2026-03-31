import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordCountFileReader {

    public static void main(String[] args) {
        // Using the absolute path to ensure the file is found
        String filePath = "d:\\Software\\capgemini\\BridgeLabz-Training\\dsa-practice\\gcr-codebase\\linear_and_binary_search\\file.txt";
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the word to count: ");
        String targetWord = sc.next(); // Read a single word
        
        int count = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into words based on whitespace
                String[] words = line.split("\\s+");
                
                for (String word : words) {
                    if (word.equals(targetWord)) {
                        count++;
                    }
                }
            }
            System.out.println("The word '" + targetWord + "' appears " + count + " times.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        sc.close();
    }
}
