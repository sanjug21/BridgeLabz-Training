import java.util.ArrayList;
import java.util.List;

public class EmailNotifications {

    public static void sendEmailNotification(String email) {
        System.out.println("Sending notification to: " + email);
    }

    public static void main(String[] args) {
        List<String> emails = new ArrayList<>();
        emails.add("john.doe@example.com");
        emails.add("sarah.smith@example.com");
        emails.add("mike.jones@example.com");
        emails.add("emma.wilson@example.com");
        emails.add("david.brown@example.com");

        System.out.println("Sending Email Notifications:");
        emails.forEach(email -> sendEmailNotification(email));
    }
}
