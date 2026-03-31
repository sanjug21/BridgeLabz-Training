package student_course_registration_system;


public class StudentRegistrationSystem {
    public static void main(String[] args) {
        Student s1 = new Student("Sanju", 20, "S001");
        Course c1 = new Course("CS101", "Intro to Java", 4);
        Course c2 = new Course("CS102", "Data Structures", 4);
        Course c3 = new Course("CS103", "Algorithms", 4);
        Course c4 = new Course("CS104", "Operating Systems", 4);

        RegistrationService regService = new UniversityRegistration();

        regService.registerStudent(s1);
        regService.enrollStudent(s1, c1);
        regService.enrollStudent(s1, c2);
        regService.enrollStudent(s1, c3);
        
        // This should trigger exception (Max 3 courses)
        regService.enrollStudent(s1, c4);

        regService.dropStudent(s1, c2);
        // Now this should work
        regService.enrollStudent(s1, c4);

        // Grade Management
        s1.assignGrade(c1, 85.5);
        s1.assignGrade(c3, 92.0);
        s1.viewGrades();
    }
}