import java.io.*;

public class CountCSVRows {
    public static void main(String[] args) {
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/students.csv";
        int rowCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    System.out.println("Header: " + line);
                    continue;
                }

                if (!line.trim().isEmpty()) {
                    rowCount++;
                }
            }

            System.out.println("=====================================");
            System.out.println("File: " + csvFile);
            System.out.println("Total records (excluding header): " + rowCount);
            System.out.println("=====================================");

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + csvFile + "' not found!");
            System.err.println("Please ensure the CSV file exists in the current directory.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
