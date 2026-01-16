import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleToFileWriter {
    public static void main(String[] args) {
        String filePath = "d:\\Software\\capgemini\\BridgeLabz-Training\\dsa-practice\\gcr-codebase\\linear_and_binary_search\\output.txt";
        
        System.out.println("Enter text to write to file (type 'exit' to stop):");

        // 1. Create InputStreamReader wrapped in BufferedReader to read from System.in
        // 2. Create FileWriter to write to the file
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             FileWriter fw = new FileWriter(filePath)) {
            
            String line;
            while ((line = br.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                fw.write(line);
                fw.write(System.lineSeparator()); // Add newline after each input
            }
            System.out.println("Data successfully written to " + filePath);
            
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
