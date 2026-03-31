import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ReadWithInputStreamReader {

    public static void main(String[] args) {
        String filePath = "d:\\Software\\capgemini\\BridgeLabz-Training\\dsa-practice\\gcr-codebase\\linear_and_binary_search\\file.txt";
        
        // 1. Create FileInputStream to read raw bytes
        // 2. Wrap in InputStreamReader to decode bytes to characters using UTF-8
        // 3. Wrap in BufferedReader for efficient line-by-line reading
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            
            String line;
            System.out.println("Reading file content with UTF-8 encoding:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
