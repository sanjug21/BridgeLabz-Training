package get_class_information;

public class Employee {
    private int employeeId;
    private String name;
    private String department;
    public double salary;
    
    public Employee() {}
    
    public Employee(int employeeId, String name, String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    
    public void work() {
        System.out.println(name + " is working");
    }
    
    public static void displayCompanyName() {
        System.out.println("ABC Corporation");
    }

    public int getEmployeeId() {
        return employeeId;
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
