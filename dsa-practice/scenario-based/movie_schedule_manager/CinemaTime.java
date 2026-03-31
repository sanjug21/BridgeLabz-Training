import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaTime {

    // Requirement: Use a List for Movie objects
    private List<Movie> movies = new ArrayList<>();

    // Requirement: Method to addMovie(String title, String time)
    public void addMovie(String title, String time) {
        try {
            validateTime(time);
            movies.add(new Movie(title, time));
            System.out.println("Success: Added \"" + title + "\" at " + time);
        } catch (InvalidTimeFormatException e) {
            System.err.println("Error adding movie: " + e.getMessage());
        }
    }

    // Requirement: Throw InvalidTimeFormatException for improperly formatted showtimes
    private void validateTime(String time) throws InvalidTimeFormatException {
        // Regex to check HH:MM format
        if (time == null || !time.matches("([01]\\d|2[0-3]):[0-5]\\d")) {
            throw new InvalidTimeFormatException("Invalid time format '" + time + "'. Expected HH:MM (24-hour).");
        }
    }

    // Requirement: searchMovie(String keyword) – use String.contains()
    public void searchMovie(String keyword) {
        System.out.println("\n--- Search Results for: " + keyword + " ---");
        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                // Requirement: Combine titles and times into a formatted string
                System.out.println(String.format("Found: %s | Showtime: %s", movie.getTitle(), movie.getShowtime()));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No movies found matching that keyword.");
        }
    }

    // Requirement: displayAllMovies()
    public void displayAllMovies() {
        System.out.println("\n--- Current Cinema Schedule ---");
        if (movies.isEmpty()) {
            System.out.println("No movies scheduled.");
            return;
        }
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    // Requirement: Handle IndexOutOfBoundsException for invalid search indices
    // This method demonstrates accessing a movie by a specific index (e.g., from a UI selection)
    public void printMovieByIndex(int index) {
        try {
            Movie movie = movies.get(index);
            System.out.println("Selected Movie: " + movie.getTitle() + " at " + movie.getShowtime());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Invalid index " + index + ". Please select a valid movie number.");
        }
    }

    // Requirement: Convert list to array when generating printable reports
    public void generatePrintableReport() {
        System.out.println("\n--- Generating Printable Report (Array Mode) ---");
        Movie[] moviesArray = movies.toArray(new Movie[0]);

        for (int i = 0; i < moviesArray.length; i++) {
            System.out.println("[" + (i + 1) + "] " + moviesArray[i].getTitle() + " - " + moviesArray[i].getShowtime());
        }
    }

    public static void main(String[] args) {
        CinemaTime cinema = new CinemaTime();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to Cinema Time Booking System ===\n");

        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Movie");
            System.out.println("2. Display All Movies");
            System.out.println("3. Search Movie");
            System.out.println("4. View Movie by Index");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("Enter showtime (HH:MM format): ");
                    String time = scanner.nextLine().trim();
                    cinema.addMovie(title, time);
                    break;

                case "2":
                    cinema.displayAllMovies();
                    break;

                case "3":
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine().trim();
                    cinema.searchMovie(keyword);
                    break;

                case "4":
                    System.out.print("Enter movie index (starting from 0): ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine().trim());
                        cinema.printMovieByIndex(index);
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Please enter a valid number.");
                    }
                    break;

                case "5":
                    cinema.generatePrintableReport();
                    break;

                case "6":
                    running = false;
                    System.out.println("Thank you for using Cinema Time. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        scanner.close();
    }
}
