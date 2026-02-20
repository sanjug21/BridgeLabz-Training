import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventFeedbackManager {
    private Map<String, List<Integer>> feedbackRatings;
    private Map<String, Event> events;

    public EventFeedbackManager() {
        this.feedbackRatings = new HashMap<>();
        this.events = new HashMap<>();
    }

    public void addEvent(Event event) {
        events.put(event.eventId, event);
        feedbackRatings.put(event.eventId, new ArrayList<>());
        System.out.println("Event added: " + event.eventName);
    }

    public void submitFeedback(String eventId, int rating) throws InvalidRatingException {
        if (rating < 1 || rating > 5) {
            throw new InvalidRatingException(
                "Invalid rating: " + rating + ". Rating must be between 1 and 5"
            );
        }

        if (!events.containsKey(eventId)) {
            System.out.println("Event not found: " + eventId);
            return;
        }

        feedbackRatings.get(eventId).add(rating);
        System.out.println("Feedback submitted for event " + eventId + ": " + rating + " stars");
    }

    public double calculateAverageRating(String eventId) {
        if (!feedbackRatings.containsKey(eventId) || feedbackRatings.get(eventId).isEmpty()) {
            return 0.0;
        }

        List<Integer> ratings = feedbackRatings.get(eventId);
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return (double) sum / ratings.size();
    }

    public void displayTopRatedEvents() {
        System.out.println("\n=======================================");
        System.out.println("        TOP RATED EVENTS");
        System.out.println("=======================================");

        if (events.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        List<Map.Entry<String, Double>> eventRatings = new ArrayList<>();
        for (String eventId : events.keySet()) {
            double avgRating = calculateAverageRating(eventId);
            if (avgRating > 0) {
                eventRatings.add(new HashMap.SimpleEntry<>(eventId, avgRating));
            }
        }

        eventRatings.sort((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()));

        System.out.println("Rank | Event ID | Event Name | Average Rating | Total Ratings");
        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < eventRatings.size(); i++) {
            String eventId = eventRatings.get(i).getKey();
            Event event = events.get(eventId);
            double avgRating = eventRatings.get(i).getValue();
            int totalRatings = feedbackRatings.get(eventId).size();

            System.out.printf("%-4d | %-8s | %-20s | %.2f | %d%n",
                (i + 1), eventId, event.eventName, avgRating, totalRatings);
        }

        System.out.println("=======================================");
    }

    public void displayEventFeedback(String eventId) {
        if (!events.containsKey(eventId)) {
            System.out.println("Event not found: " + eventId);
            return;
        }

        Event event = events.get(eventId);
        List<Integer> ratings = feedbackRatings.get(eventId);

        System.out.println("\n--- Event Feedback ---");
        System.out.println(event);
        System.out.println("Total Ratings: " + ratings.size());

        if (ratings.isEmpty()) {
            System.out.println("No ratings submitted yet.");
            return;
        }

        System.out.println("Average Rating: " + String.format("%.2f", calculateAverageRating(eventId)) + " stars");
        
        int[] ratingCount = new int[6];
        for (int rating : ratings) {
            ratingCount[rating]++;
        }

        System.out.println("\nRating Distribution:");
        for (int i = 5; i >= 1; i--) {
            System.out.println(i + " star: " + ratingCount[i]);
        }
    }

    public void displayAllEvents() {
        System.out.println("\n--- All Events ---");
        if (events.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        for (Event event : events.values()) {
            double avgRating = calculateAverageRating(event.eventId);
            int totalRatings = feedbackRatings.get(event.eventId).size();
            System.out.println(event + " | Avg Rating: " + 
                String.format("%.2f", avgRating) + " | Total Ratings: " + totalRatings);
        }
    }

    public void displayStatistics() {
        System.out.println("\n--- System Statistics ---");
        System.out.println("Total Events: " + events.size());

        int totalRatings = 0;
        double totalAvgRating = 0.0;
        int eventsWithRatings = 0;

        for (String eventId : events.keySet()) {
            List<Integer> ratings = feedbackRatings.get(eventId);
            totalRatings += ratings.size();
            if (!ratings.isEmpty()) {
                totalAvgRating += calculateAverageRating(eventId);
                eventsWithRatings++;
            }
        }

        System.out.println("Total Ratings Submitted: " + totalRatings);
        if (eventsWithRatings > 0) {
            System.out.println("Overall Average Rating: " + 
                String.format("%.2f", totalAvgRating / eventsWithRatings) + " stars");
        }
    }
}
