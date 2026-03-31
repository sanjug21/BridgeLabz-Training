import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UpperToLowerConverter {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java UpperToLowerConverter <source> <destination>");
            return;
        }

        String sourcePath = args[0];
        String destinationPath = args[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationPath))) {

            char[] buffer = new char[4096];
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                for (int i = 0; i < charsRead; i++) {
                    buffer[i] = Character.toLowerCase(buffer[i]);
                }
                writer.write(buffer, 0, charsRead);
            }
            System.out.println("Converted file saved to: " + destinationPath);
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
