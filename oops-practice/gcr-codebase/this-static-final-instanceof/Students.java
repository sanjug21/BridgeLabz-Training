public class Students {
    public String name;
    public char grade;
    public final int rollNumber;
    public static String universityName="GLA University";
    private static int totalStudents;
    
    Students(String name, char grade, int rollNumber){
        this.name = name;
        this.grade = grade;
        this.rollNumber = rollNumber;
        totalStudents++;
    }
    // Static method to get total number of students
    public static int getTotalStudents(){
        return totalStudents;
    }

    // display method to display student details
    public void displayDetails(){
        System.out.println("Name: " + name);
        System.out.println("Grade: " + grade);
        System.out.println("Roll Number: " + rollNumber);
    }
    public static void main(String[] args) {
        Students s1 = new Students("Sagar", 'A', 101);
        Students s2 = new Students("Manish", 'B', 102);

        // Using instanceof to check object type
        System.out.println("s1 instanceof Student: " + (s1 instanceof Students));
        System.out.println("s2 instanceof Student: " + (s2 instanceof Students));

        // Display total students and university name
        System.out.println("Total Students: " + Students.getTotalStudents());
        System.out.println("University Name: " + Students.universityName);

        // Display student details
        s1.displayDetails();
        s2.displayDetails();
        
        

    }


}
