import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInfoWriter {

    public static void main(String[] args) {
        String outputPath = args.length > 0 ? args[0] : "user_info.txt";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             FileWriter writer = new FileWriter(outputPath, true)) {

            System.out.print("Enter your name: ");
            String name = br.readLine();

            System.out.print("Enter your age: ");
            String age = br.readLine();

            System.out.print("Enter your favorite programming language: ");
            String language = br.readLine();

            writer.write("Name: " + name + System.lineSeparator());
            writer.write("Age: " + age + System.lineSeparator());
            writer.write("Favorite Language: " + language + System.lineSeparator());
            writer.write("---" + System.lineSeparator());

            System.out.println("Data saved to " + outputPath);
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
