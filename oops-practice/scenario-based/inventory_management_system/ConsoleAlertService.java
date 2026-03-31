package inventory_management_system;

public class ConsoleAlertService implements AlertService {
    @Override
    public void sendAlert(String message) {
        System.out.println("ALERT: " + message);
    }
}
