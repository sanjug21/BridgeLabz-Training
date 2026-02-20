import java.util.Scanner;

public class EventFeedbackSystem {

    public static void main(String[] args) {
        EventFeedbackManager manager = new EventFeedbackManager();
        Scanner sc = new Scanner(System.in);

        initializeDemoData(manager);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("  EVENT FEEDBACK & RATING ANALYZER");
            System.out.println("========================================");
            System.out.println("1. Add Event");
            System.out.println("2. Submit Feedback");
            System.out.println("3. View Event Feedback");
            System.out.println("4. Display Top Rated Events");
            System.out.println("5. Display All Events");
            System.out.println("6. Display Statistics");
            System.out.println("7. Exit");
            System.out.print("\nChoose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        addEvent(manager, sc);
                        break;
                    case 2:
                        submitFeedback(manager, sc);
                        break;
                    case 3:
                        viewEventFeedback(manager, sc);
                        break;
                    case 4:
                        manager.displayTopRatedEvents();
                        break;
                    case 5:
                        manager.displayAllEvents();
                        break;
                    case 6:
                        manager.displayStatistics();
                        break;
                    case 7:
                        System.out.println("Exiting system.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InvalidRatingException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void initializeDemoData(EventFeedbackManager manager) {
        manager.addEvent(new Event("E001", "Tech Conference 2026", "2026-01-15"));
        manager.addEvent(new Event("E002", "Music Festival", "2026-02-10"));
        manager.addEvent(new Event("E003", "Food Carnival", "2026-02-18"));
        manager.addEvent(new Event("E004", "Art Exhibition", "2026-01-25"));

        try {
            manager.submitFeedback("E001", 5);
            manager.submitFeedback("E001", 4);
            manager.submitFeedback("E001", 5);
            manager.submitFeedback("E001", 5);
            manager.submitFeedback("E001", 4);

            manager.submitFeedback("E002", 3);
            manager.submitFeedback("E002", 4);
            manager.submitFeedback("E002", 3);

            manager.submitFeedback("E003", 5);
            manager.submitFeedback("E003", 5);
            manager.submitFeedback("E003", 5);
            manager.submitFeedback("E003", 5);
            manager.submitFeedback("E003", 4);
            manager.submitFeedback("E003", 5);

            manager.submitFeedback("E004", 2);
            manager.submitFeedback("E004", 3);

            System.out.println("\nDemo data initialized successfully!");

        } catch (InvalidRatingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addEvent(EventFeedbackManager manager, Scanner sc) {
        System.out.print("\nEnter Event ID: ");
        String eventId = sc.nextLine();

        System.out.print("Enter Event Name: ");
        String eventName = sc.nextLine();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        manager.addEvent(new Event(eventId, eventName, date));
    }

    private static void submitFeedback(EventFeedbackManager manager, Scanner sc)
            throws InvalidRatingException {
        System.out.print("\nEnter Event ID: ");
        String eventId = sc.nextLine();

        System.out.print("Enter Rating (1-5): ");
        int rating = sc.nextInt();
        sc.nextLine();

        manager.submitFeedback(eventId, rating);
    }

    private static void viewEventFeedback(EventFeedbackManager manager, Scanner sc) {
        System.out.print("\nEnter Event ID: ");
        String eventId = sc.nextLine();
        manager.displayEventFeedback(eventId);
    }
}
