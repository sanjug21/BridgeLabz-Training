import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSerialization {

    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : "employees.ser";

        try {
            List<Employee> employees = readEmployeesFromConsole();
            serializeEmployees(employees, filePath);
            List<Employee> loaded = deserializeEmployees(filePath);

            System.out.println("Employees loaded from file:");
            for (Employee e : loaded) {
                System.out.println(e);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static List<Employee> readEmployeesFromConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Employee> employees = new ArrayList<>();

        System.out.print("How many employees to add? ");
        int count = Integer.parseInt(reader.readLine());

        for (int i = 0; i < count; i++) {
            System.out.println("-- Employee " + (i + 1) + " --");
            System.out.print("Id: ");
            int id = Integer.parseInt(reader.readLine());

            System.out.print("Name: ");
            String name = reader.readLine();

            System.out.print("Department: ");
            String dept = reader.readLine();

            System.out.print("Salary: ");
            double salary = Double.parseDouble(reader.readLine());

            employees.add(new Employee(id, name, dept, salary));
        }
        return employees;
    }

    private static void serializeEmployees(List<Employee> employees, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(employees);
            System.out.println("Employees saved to " + filePath);
        }
    }

    private static List<Employee> deserializeEmployees(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = ois.readObject();
            return (List<Employee>) obj;
        }
    }

    private static class Employee implements Serializable {
        private static final long serialVersionUID = 1L;

        private final int id;
        private final String name;
        private final String department;
        private final double salary;

        Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", department='" + department + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
