import java.io.*;

public class FilterStudentsByMarks {
    public static void main(String[] args) {
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/students.csv";
        String line;
        String separator = ",";
        int filteredCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            System.out.println("========== STUDENTS WITH MARKS > 80 ==========");
            System.out.println(String.format("%-5s %-20s %-5s %-10s", "ID", "Name", "Age", "Marks"));
            System.out.println("==============================================");

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
                    String marksStr = fields[3].trim();

                    try {
                        int marks = Integer.parseInt(marksStr);
                        if (marks > 80) {
                            System.out.println(String.format("%-5s %-20s %-5s %-10d", id, name, age, marks));
                            filteredCount++;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid marks value: " + marksStr);
                    }
                }
            }
            System.out.println("==============================================");
            System.out.println("Total students with marks > 80: " + filteredCount);

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + csvFile + "' not found!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
