class Student {
    public int rollNo;
    protected String name;
    private double CGPA;

    public  Student(int rollNo, String name, double CGPA) {
        this.rollNo = rollNo;
        this.name = name;
        this.CGPA = CGPA;
    }
    // Getter method for private member CGPA
    public double getCGPA() {
        return CGPA;
    }
    // Setter method for private member CGPA
    public double updateCGPA(double newCGPA) {
        this.CGPA = newCGPA;
        return CGPA;
    }
}

public class PostgraduateStudent extends Student {
    PostgraduateStudent(int rollNo, String name, double CGPA) {
        super(rollNo, name, CGPA);
    }

    
    public static void main(String[] args) {
        PostgraduateStudent pgStudent = new PostgraduateStudent(01, "Sanju", 9.8);
        System.out.println("Roll No: " + pgStudent.rollNo);
        System.out.println("Name: " + pgStudent.name);

        // Accessing private member CGPA in subclass is not allowed directly

        // this is giving error as CGPA is not visible
        // System.out.println("CGPA: " + pgStudent.CGPA);


        // Accessing private member CGPA using public getter method
        System.out.println("CGPA: " + pgStudent.getCGPA());
    }
    
}
