class People {
    String name;
    int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class Teacher extends People {
    String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    public void displayRole() {
        System.out.println("Role: Teacher");
        System.out.println("Subject: " + subject);
    }
}
// using stu as class student already exist with different properties ans creating an error while running the program
class Stu extends People {
    String grade;

    public Stu(String name, int age, String grade) {
        super(name, age);
        this.grade = grade;
    }

    public void displayRole() {
        System.out.println("Role: Student");
        System.out.println("Grade: " + grade);
    }
}

class Staff extends People {
    String department;

    public Staff(String name, int age, String department) {
        super(name, age);
        this.department = department;
    }

    public void displayRole() {
        System.out.println("Role: Staff");
        System.out.println("Department: " + department);
    }
}

public class SchoolSystem {
    public static void main(String[] args) {
        // hierarchy inheritance

        // instance of People
        People person1 = new People("Sanju", 24);
        person1.displayInfo();
        System.out.println();
        
        // instance of Teacher
        Teacher person2 = new Teacher("Manish", 40, "Physics");
        person2.displayInfo();
        person2.displayRole();
        System.out.println();
        

        // instance of Student
        Stu person3 = new Stu("Shubham", 16, "10th Grade");
        person3.displayInfo();
        person3.displayRole();
        System.out.println();
        


        // instance of Staff
        Staff person4 = new Staff("Sagar", 50, "Administration");
        person4.displayInfo();
        person4.displayRole();
    
    }
}