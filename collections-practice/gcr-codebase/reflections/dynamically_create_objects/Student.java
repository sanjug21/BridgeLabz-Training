package dynamically_create_objects;

public class Student {
    private String studentId;
    private String name;
    private int age;
    private String course;
    private double gpa;
    
    // Default constructor
    public Student() {
        this.studentId = "Unknown";
        this.name = "Unknown";
        this.age = 0;
        this.course = "Not Enrolled";
        this.gpa = 0.0;
    }
    
    // Constructor with parameters
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.age = 18;
        this.course = "Not Assigned";
        this.gpa = 0.0;
    }
    
    // Constructor with all parameters
    public Student(String studentId, String name, int age, String course, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
        this.gpa = gpa;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getCourse() {
        return course;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void displayInfo() {
        System.out.println("Student Information:");
        System.out.println("  ID: " + studentId);
        System.out.println("  Name: " + name);
        System.out.println("  Age: " + age);
        System.out.println("  Course: " + course);
        System.out.println("  GPA: " + gpa);
    }
    
    @Override
    public String toString() {
        return String.format("Student[ID=%s, Name=%s, Age=%d, Course=%s, GPA=%.2f]",
                           studentId, name, age, course, gpa);
    }
}
