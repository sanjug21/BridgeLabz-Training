import java.io.*;

public class WriteEmployeeCSV {
    public static void main(String[] args) {
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/employees.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            // Write header
            writer.println("ID,Name,Department,Salary");

            // Write employee records
            writer.println("101,John Smith,IT,75000");
            writer.println("102,Sarah Johnson,HR,65000");
            writer.println("103,Michael Brown,Finance,80000");
            writer.println("104,Emily Davis,IT,72000");
            writer.println("105,Robert Wilson,Sales,68000");
            writer.println("106,Jessica Martinez,Marketing,70000");

            System.out.println("CSV file '" + csvFile + "' created successfully!");
            System.out.println("6 employee records written to file.");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
