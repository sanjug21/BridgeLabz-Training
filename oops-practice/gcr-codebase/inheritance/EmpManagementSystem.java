class Emp {
    String name;
    int id;
    double salary;

    Emp(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: $" + salary);
    }
}

class Managers extends Emp {
    int teamSize;

    Managers(String name, int id, double salary, int teamSize) {
        super(name, id, salary);
        this.teamSize = teamSize;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Role: Manager");
        System.out.println("Team Size: " + teamSize);
    }
}

class Developer extends Emp {
    String programmingLanguage;

    Developer(String name, int id, double salary, String programmingLanguage) {
        super(name, id, salary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Role: Developer");
        System.out.println("Programming Language: " + programmingLanguage);
    }
}

class Intern extends Emp {
    String university;

    Intern(String name, int id, double salary, String university) {
        super(name, id, salary);
        this.university = university;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Role: Intern");
        System.out.println("University: " + university);
    }
}

public class EmpManagementSystem {
    public static void main(String[] args) {
        // hierarchical inheritance
        System.out.println("Employee Management System");
        System.out.println();

        Emp mgr = new Managers("Sanju", 101, 90000.0, 10);
        Emp dev = new Developer("Shubham", 102, 85000.0, "Java");
        Emp intern = new Intern("Ritu", 103, 25000.0, "IIT");

        mgr.displayDetails();
        System.out.println("----------------");
        dev.displayDetails();
        System.out.println("----------------");
        intern.displayDetails();
    }
}