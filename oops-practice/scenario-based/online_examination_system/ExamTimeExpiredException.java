package online_examination_system;

// Exception Handling: Custom Exception for exam timing
public class ExamTimeExpiredException extends Exception {
    public ExamTimeExpiredException(String message) {
        super(message);
    }
}