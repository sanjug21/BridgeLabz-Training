import java.util.ArrayList;
import java.util.List;

public class EventAttendeeWelcomeMessage {

    public static void main(String[] args) {
        List<String> attendees = new ArrayList<>();
        attendees.add("Alice Cooper");
        attendees.add("Bob Martin");
        attendees.add("Charlie Brown");
        attendees.add("Diana Prince");
        attendees.add("Edward Norton");

        System.out.println("Welcome Messages:");
        attendees.forEach(attendee -> System.out.println("Welcome to the event, " + attendee + "!"));
    }
}
