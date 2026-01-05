import java.util.ArrayList;
import java.util.List;

// Department interface for common methods
interface Department {
    void assignDepartment(String department);
    String getDepartmentDetails();
}


// Abstract class for employees to implement common methods in their subclasses
abstract class EmployeeDetails {
    private String employeeId;
    private String name;
    private double baseSalary;

    public EmployeeDetails(String employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
    }
    // getter and setter methods for employeeId, name, and baseSalary
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }
    // abstract method to calculate salary for employees will be implemented in subclasses
    public abstract double calculateSalary();

    public void displayDetails() {
        System.out.println("ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: Rs " + baseSalary);
    }
}

class FullTimeEmployees extends EmployeeDetails implements Department {
    private double bonus;
    private String departmentName;

    public FullTimeEmployees(String id, String name, double baseSalary, double bonus) {
        super(id, name, baseSalary);
        this.bonus = bonus;
    }
    // abstract method to calculate salary for full-time employees
    @Override
    public double calculateSalary() {
        return getBaseSalary() + bonus;
    }
    // Department interface methods
    @Override
    public void assignDepartment(String department) {
        this.departmentName = department;
    }
    // Department interface methods
    @Override
    public String getDepartmentDetails() {
        return departmentName;
    }

    @Override
    public void displayDetails() {

        //super class method to display employee details
        super.displayDetails();
        System.out.println("Type: Full-Time");
        System.out.println("Department: " + getDepartmentDetails());
        System.out.println("Total Salary: Rs " + calculateSalary());
    }
}

class PartTimeEmployees extends EmployeeDetails implements Department {
    private int hoursWorked;
    private double hourlyRate;
    private String departmentName;

    public PartTimeEmployees(String id, String name, int hoursWorked, double hourlyRate) {
        super(id, name, 0);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
    // abstract method to calculate salary for part-time employees
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
    // Department interface methods
    @Override
    public void assignDepartment(String department) {
        this.departmentName = department;
    }
    // Department interface methods
    @Override
    public String getDepartmentDetails() {
        return departmentName;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: Part-Time");
        System.out.println("Department: " + getDepartmentDetails());
        System.out.println("Total Salary: Rs " + calculateSalary());
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        List<EmployeeDetails> employees = new ArrayList<>();

        FullTimeEmployees emp1 = new FullTimeEmployees("FT001", "Sanju", 5000, 1000);
        emp1.assignDepartment("IT");

        PartTimeEmployees emp2 = new PartTimeEmployees("PT001", "Manish", 40, 25);
        emp2.assignDepartment("HR");

        employees.add(emp1);
        employees.add(emp2);

        System.out.println("=== Employee Management System ===");
        for (EmployeeDetails emp : employees) {
            emp.displayDetails();
            System.out.println("\n");
        }
    }
}