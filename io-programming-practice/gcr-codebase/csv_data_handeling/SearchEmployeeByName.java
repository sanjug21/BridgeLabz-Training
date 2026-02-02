import java.io.*;


public class SearchEmployeeByName {
    public static void main(String[] args) {
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/employees.csv";
        String searchName = "Pooja Desai";
        String line;
        String separator = ",";
        boolean found = false;

        System.out.println("========== EMPLOYEE SEARCH ==========");
        System.out.println("Searching for: " + searchName);
        System.out.println("=====================================");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
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
                    String department = fields[2].trim();
                    String salary = fields[3].trim();

                    if (name.equalsIgnoreCase(searchName)) {
                        System.out.println("Employee Found!");
                        System.out.println("ID           : " + id);
                        System.out.println("Name         : " + name);
                        System.out.println("Department   : " + department);
                        System.out.println("Salary       : $" + salary);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("✗ Employee '" + searchName + "' not found in the database.");
            }
            System.out.println("=====================================");

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + csvFile + "' not found!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
