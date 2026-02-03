import java.util.*;

class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String department;

    public Employee(String employeeId, String firstName, String lastName, String department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Dept: %s", employeeId, getFullName(), department);
    }
}

public class NameUppercasing {

    public static void main(String[] args) {
        List<Employee> employees = createEmployeeList();

        System.out.println("Uppercase Names:");
        employees.stream()
                .map(Employee::getFullName)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    private static List<Employee> createEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("E001", "John", "Doe", "Engineering"));
        employees.add(new Employee("E002", "Jane", "Smith", "Marketing"));
        employees.add(new Employee("E003", "Bob", "Johnson", "Engineering"));
        employees.add(new Employee("E004", "Alice", "Brown", "HR"));
        employees.add(new Employee("E005", "Charlie", "Davis", "Finance"));
        employees.add(new Employee("E006", "Diana", "Wilson", "Engineering"));
        return employees;
    }
}
