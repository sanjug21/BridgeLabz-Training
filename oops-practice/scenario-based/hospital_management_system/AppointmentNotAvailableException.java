package hospital_management_system;

public class AppointmentNotAvailableException extends Exception {
    public AppointmentNotAvailableException(String message) {
        super(message);
    }
}