import java.io.*;
import java.util.*;


public class GenerateCSVReportFromDatabase {
    
    static class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;

        public Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }
    }

    public static void main(String[] args) {
        String outputFile = "java-8/gcr-codebase/csv_data_handeling/employee_report.csv";

        System.out.println("========== GENERATING CSV REPORT FROM DATABASE ==========");
        System.out.println("Connecting to database...");
        
        List<Employee> employees = fetchEmployeesFromDatabase();
        
        System.out.println("Fetched " + employees.size() + " records from database");
        System.out.println("Generating CSV report...");
        System.out.println("=========================================================");

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("Employee ID,Name,Department,Salary");
            
            int recordsWritten = 0;
            for (Employee emp : employees) {
                writer.println(emp.getId() + "," + 
                             emp.getName() + "," + 
                             emp.getDepartment() + "," + 
                             String.format("%.2f", emp.getSalary()));
                recordsWritten++;
                System.out.println("Written: " + emp.getId() + " - " + emp.getName() + 
                                 " (" + emp.getDepartment() + ", $" + 
                                 String.format("%.2f", emp.getSalary()) + ")");
            }

            System.out.println("=========================================================");
            System.out.println("Report generated successfully!");
            System.out.println("Total records written: " + recordsWritten);
            System.out.println("Output file: " + outputFile);
            System.out.println("=========================================================");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static List<Employee> fetchEmployeesFromDatabase() {
        List<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee(101, "Amit Kumar", "IT", 75000.00));
        employees.add(new Employee(102, "Kavya Reddy", "HR", 65000.00));
        employees.add(new Employee(103, "Rajesh Sharma", "Finance", 80000.00));
        employees.add(new Employee(104, "Pooja Desai", "IT", 72000.00));
        employees.add(new Employee(105, "Arun Nair", "Sales", 68000.00));
        employees.add(new Employee(106, "Divya Pillai", "Marketing", 70000.00));
        employees.add(new Employee(107, "Vikram Singh", "IT", 78000.00));
        employees.add(new Employee(108, "Priya Sharma", "HR", 66000.00));
        employees.add(new Employee(109, "Rahul Verma", "Finance", 82000.00));
        employees.add(new Employee(110, "Sneha Iyer", "Sales", 69000.00));
        
        return employees;
    }
}
