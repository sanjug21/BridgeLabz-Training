package student_course_registration_system;

public class CourseLimitExceededException extends Exception{
    public CourseLimitExceededException(String message) {
        super(message);
    }
}
 
