import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileLineByLine {
    
    public static void main(String[] args) {
        String filePath = "d:\\Software\\capgemini\\BridgeLabz-Training\\dsa-practice\\gcr-codebase\\linear_and_binary_search\\file.txt";
        // Using try-with-resources to ensure the BufferedReader (and FileReader) is closed automatically
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}