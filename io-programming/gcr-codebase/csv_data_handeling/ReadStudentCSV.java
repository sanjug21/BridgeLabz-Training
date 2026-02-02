import java.io.*;


public class ReadStudentCSV {
    public static void main(String[] args) {
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/students.csv";
        String line;
        String separator = ",";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            System.out.println("========== STUDENT RECORDS ==========");
            System.out.println(String.format("%-5s %-20s %-5s %-10s", "ID", "Name", "Age", "Marks"));
            System.out.println("=====================================");

            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(separator);
                if (fields.length == 4) {
                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    String age = fields[2].trim();
                    String marks = fields[3].trim();

                    System.out.println(String.format("%-5s %-20s %-5s %-10s", id, name, age, marks));
                }
            }
            System.out.println("=====================================");
        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + csvFile + "' not found!");
            System.err.println("Please ensure the CSV file exists in the current directory.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
