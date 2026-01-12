package student_course_registration_system;

public abstract class RegistrationService {
    public abstract void registerStudent(Student student);
    public abstract void enrollStudent(Student student, Course course);
    public abstract void dropStudent(Student student, Course course);
}