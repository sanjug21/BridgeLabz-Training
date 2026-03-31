import java.io.*;


public class UpdateITSalary {
    public static void main(String[] args) {
        String inputFile = "java-8/gcr-codebase/csv_data_handeling/employees.csv";
        String outputFile = "java-8/gcr-codebase/csv_data_handeling/employees_updated.csv";
        String line;
        String separator = ",";
        int updatedCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

            System.out.println("========== SALARY UPDATE FOR IT DEPARTMENT ==========");
            System.out.println("Processing file: " + inputFile);
            System.out.println("Output file: " + outputFile);
            System.out.println("Salary increase: 10%");
            System.out.println("=====================================================");

            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    writer.println(line); // Write header as is
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(separator);
                if (fields.length == 4) {
                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    String department = fields[2].trim();
                    String salaryStr = fields[3].trim();

                    try {
                        double salary = Double.parseDouble(salaryStr);
                        double updatedSalary = salary;

                        if (department.equalsIgnoreCase("IT")) {
                            updatedSalary = salary * 1.10; // 10% increase
                            updatedCount++;
                            System.out.println("Updated: " + name + " - Old Salary: $" + salary + 
                                             " -> New Salary: $" + String.format("%.2f", updatedSalary));
                        }

                        // Write updated record
                        writer.println(id + "," + name + "," + department + "," + 
                                     String.format("%.2f", updatedSalary));

                    } catch (NumberFormatException e) {
                        System.err.println("Invalid salary value: " + salaryStr);
                        writer.println(line); // Write original line if error
                    }
                } else {
                    writer.println(line);
                }
            }

            System.out.println("=====================================================");
            System.out.println("File updated successfully!");
            System.out.println("Total IT employees salary updated: " + updatedCount);
            System.out.println("Updated file saved as: " + outputFile);

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + inputFile + "' not found!");
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}
