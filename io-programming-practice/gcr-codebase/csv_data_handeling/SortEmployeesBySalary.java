import java.io.*;
import java.util.*;

public class SortEmployeesBySalary {
    
    static class Employee {
        String id;
        String name;
        String department;
        double salary;

        Employee(String id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("%-5s %-20s %-15s $%-12.2f", id, name, department, salary);
        }
    }

    public static void main(String[] args) {
        String csvFile = "java-8/gcr-codebase/csv_data_handeling/employees.csv";
        String line;
        String separator = ",";
        List<Employee> employees = new ArrayList<>();

        // Read CSV file
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
                    String salaryStr = fields[3].trim();

                    try {
                        double salary = Double.parseDouble(salaryStr);
                        employees.add(new Employee(id, name, department, salary));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid salary value: " + salaryStr);
                    }
                }
            }

            // Sort employees by salary in descending order
            employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));

            // Display top 5 highest-paid employees
            System.out.println("========== TOP 5 HIGHEST-PAID EMPLOYEES ==========");
            System.out.println(String.format("%-5s %-20s %-15s %-15s", "ID", "Name", "Department", "Salary"));
            System.out.println("==================================================");

            int count = 0;
            for (Employee emp : employees) {
                if (count >= 5) {
                    break;
                }
                System.out.println(emp);
                count++;
            }

            System.out.println("==================================================");
            System.out.println("Total employees in file: " + employees.size());

        } catch (FileNotFoundException e) {
            System.err.println("Error: File '" + csvFile + "' not found!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
