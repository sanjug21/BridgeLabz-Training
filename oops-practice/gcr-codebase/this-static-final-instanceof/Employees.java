public class Employees {
    String name;
    final int id;
    String designation;
    static int totalEmployees;
    static String companyName="Google";



    public Employees(String name, int id, String designation) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        Employees.totalEmployees++;
    }
    public static void displayTotalEmployees() {
        System.out.println("Total Employees: " + Employees.totalEmployees);
    }
    public void displayDetails() {
        System.out.println("Company Name: " + Employees.companyName);
        System.out.println("Employee ID: " + this.id);
        System.out.println("Employee Name: " + this.name);
        System.out.println("Employee Designation: " + this.designation);
    }
    public static void main(String[] args) {
        Employees e1 = new Employees("Sagar", 101, "Software Engineer");
        Employees e2 = new Employees("Anita", 102, "Data Scientist");

        // display instance details
        System.out.println("e1 is instance of Employees: " + (e1 instanceof Employees));
        System.out.println("e2 is instance of Employees: " + (e2 instanceof Employees));

        // display total employees and company name
        Employees.displayTotalEmployees();

        // display individual employee details
        e1.displayDetails();
        e2.displayDetails();

    }
}
