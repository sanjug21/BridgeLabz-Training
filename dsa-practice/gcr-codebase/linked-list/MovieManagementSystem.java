import java.util.*;

class Movie {
    String title;
    String director;
    int year;
    double rating;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }
}

public class MovieManagementSystem {
    static Scanner sc = new Scanner(System.in);
    DoubleNode<Movie> head;
    DoubleNode<Movie> tail;

    public void addMovie(Movie movie, int position) {
        DoubleNode<Movie> nn = new DoubleNode<>(movie);
        if (head == null) {
            head = tail = nn;
            return;
        }

        // Add at beginning
        if (position == 1) {
            nn.next = head;
            head.prev = nn;
            head = nn;
            return;
        }

        // Traverse to find position
        DoubleNode<Movie> temp = head;
        int currentPos = 1;
        while (temp.next != null && currentPos < position - 1) {
            temp = temp.next;
            currentPos++;
        }

        // Add at end or specific position
        if (temp.next == null) {
            // Add at end
            tail.next = nn;
            nn.prev = tail;
            tail = nn;
        } else {
            // Add in between
            nn.next = temp.next;
            nn.prev = temp;
            temp.next.prev = nn;
            temp.next = nn;
        }
    }

    public void removeMovie(String title) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        DoubleNode<Movie> temp = head;
        while (temp != null) {
            if (temp.data.title.equalsIgnoreCase(title)) {
                if (temp == head && temp == tail) { // Only one node
                    head = tail = null;
                } else if (temp == head) { // Remove head
                    head = head.next;
                    head.prev = null;
                } else if (temp == tail) { // Remove tail
                    tail = tail.prev;
                    tail.next = null;
                } else { // Remove middle
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                System.out.println("Movie '" + title + "' deleted successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie '" + title + "' not found.");
    }

    public void searchMovie(String query, boolean isRating) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        DoubleNode<Movie> temp = head;
        boolean found = false;
        System.out.printf("%-20s %-20s %-10s %-5s%n", "Title", "Director", "Year", "Rating");
        System.out.println("-------------------------------------------------------------");
        while (temp != null) {
            boolean match = false;
            if (isRating) {
                if (String.valueOf(temp.data.rating).equals(query))
                    match = true;
            } else {
                if (temp.data.director.equalsIgnoreCase(query))
                    match = true;
            }

            if (match) {
                System.out.printf("%-20s %-20s %-10d %-5.1f%n",
                        temp.data.title, temp.data.director, temp.data.year, temp.data.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found)
            System.out.println("No records found.");
    }

    public void displayMovies(boolean reverse) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        System.out.printf("%-20s %-20s %-10s %-5s%n", "Title", "Director", "Year", "Rating");
        System.out.println("-------------------------------------------------------------");

        DoubleNode<Movie> temp = reverse ? tail : head;
        while (temp != null) {
            System.out.printf("%-20s %-20s %-10d %-5.1f%n",
                    temp.data.title, temp.data.director, temp.data.year, temp.data.rating);
            temp = reverse ? temp.prev : temp.next;
        }
    }

    public void updateMovieRating(String title, double newRating) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        DoubleNode<Movie> temp = head;
        while (temp != null) {
            if (temp.data.title.equalsIgnoreCase(title)) {
                temp.data.rating = newRating;
                System.out.println("Rating updated successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie '" + title + "' not found.");
    }

    public void addMovieInput() {
        try {
            System.out.println("Enter Movie Details:");
            System.out.print("Title: ");
            String title = sc.nextLine();
            if (title.trim().isEmpty())
                throw new InvalidInputException("Title cannot be empty.");

            System.out.print("Director: ");
            String director = sc.nextLine();
            if (director.trim().isEmpty())
                throw new InvalidInputException("Director cannot be empty.");

            System.out.print("Year: ");
            int year = sc.nextInt();
            if (year < 1800 || year > 2100)
                throw new InvalidInputException("Invalid year.");

            System.out.print("Rating (0.0 - 10.0): ");
            double rating = sc.nextDouble();
            if (rating < 0 || rating > 10)
                throw new InvalidInputException("Invalid rating.");

            System.out.println("Where to add? (1. Start, 2. End, 3. Specific Position)");
            int choice = sc.nextInt();
            int pos = -1; // Default to end if logic handles it, but we use explicit logic
            if (choice == 1)
                pos = 1;
            else if (choice == 2)
                pos = Integer.MAX_VALUE;
            else if (choice == 3) {
                System.out.print("Enter position: ");
                pos = sc.nextInt();
            } else
                throw new InvalidInputException("Invalid position choice.");
            sc.nextLine(); // consume newline

            addMovie(new Movie(title, director, year, rating), pos);
            System.out.println("Movie added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine(); // clear buffer
        }
    }

    public static void main(String[] args) {
        MovieManagementSystem list = new MovieManagementSystem();
        System.out.println("==== Movie Management System ====");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Movie");
            System.out.println("2. Remove Movie");
            System.out.println("3. Search Movie");
            System.out.println("4. Display Movies (Forward)");
            System.out.println("5. Display Movies (Reverse)");
            System.out.println("6. Update Rating");
            System.out.println("7. Exit");

            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
            }
            sc.nextLine();

            switch (choice) {
                case 1:
                    list.addMovieInput();
                    break;
                case 2:
                    System.out.print("Enter Movie Title to remove: ");
                    list.removeMovie(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Search by: 1. Director 2. Rating");
                    int searchType = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter query: ");
                    String query = sc.nextLine();
                    list.searchMovie(query, searchType == 2);
                    break;
                case 4:
                    list.displayMovies(false);
                    break;
                case 5:
                    list.displayMovies(true);
                    break;
                case 6:
                    System.out.print("Enter Movie Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter New Rating: ");
                    double rating = sc.nextDouble();
                    list.updateMovieRating(title, rating);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}