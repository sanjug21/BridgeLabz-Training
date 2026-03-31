import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class checkedException {
    public static void main(String[] args) {

        // Path filePath = Path.of("collections-practice", "gcr-codebase", "exceptions", "dat.txt");
        Path filePath = Path.of("collections-practice", "gcr-codebase", "exceptions", "data.txt");
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Unable to read file: " + filePath.toAbsolutePath());
        }
    }
}
