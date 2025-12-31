class Employee {
    public String employeeId;
    protected String department;
    private double salary;
    public Employee(String employeeId, String department, double salary) {
        this.employeeId = employeeId;
        this.department = department;
        this.salary = salary;
    }
    // Setter method for private member salary
    public double updateSalary(double newSalary) {
        this.salary = newSalary;
        return salary;
    }
}

public class Manager extends Employee {
    public Manager(String employeeId, String department, double salary) {
        super(employeeId, department, salary);
    }

    public static void main(String[] args) {
        Manager mgr = new Manager("E123", "Sales", 75000.0);

        // both are public and protected members are accessible here
        System.out.println("Employee ID: " + mgr.employeeId);
        System.out.println("Department: " + mgr.department);

        // Accessing and updating private member salary using public setter method
        System.out.println("Updated Salary: " + mgr.updateSalary(80000.0));

    }
}

