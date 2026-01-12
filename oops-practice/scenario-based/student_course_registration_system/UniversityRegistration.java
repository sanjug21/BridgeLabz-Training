package student_course_registration_system;
import java.util.*;

public class UniversityRegistration extends RegistrationService {
    private List<Student> students = new ArrayList<>();

    @Override
    public void registerStudent(Student student) {
        students.add(student);
        System.out.println("Student registered: " + student.getName());
    }

    @Override
    public void enrollStudent(Student student, Course course) {
        try {
            student.addCourse(course);
        } catch (CourseLimitExceededException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void dropStudent(Student student, Course course) {
        student.removeCourse(course);
    }
}
