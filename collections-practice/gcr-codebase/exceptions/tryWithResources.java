import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class tryWithResources {
	public static void main(String[] args) {
		String fileName = "collections-practice/gcr-codebase/exceptions/data.txt";
		

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String firstLine = reader.readLine();
			if (firstLine != null) {
				System.out.println(firstLine);
			}
		} catch (IOException e) {
			System.out.println("Error reading file");
		}
	}
}
