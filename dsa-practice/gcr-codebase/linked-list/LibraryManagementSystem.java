import java.util.*;

class Book {
    String title;
    String author;
    String genre;
    String bookId;
    boolean isAvailable;

    Book(String title, String author, String genre, String bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
    }
}


public class LibraryManagementSystem {
    static Scanner sc = new Scanner(System.in);
    DoubleNode<Book> head;

    public void addBookAtBeginning(Book book) {
        DoubleNode<Book> nn = new DoubleNode<>(book);
        if (head == null) {
            head = nn;
        } else {
            nn.next = head;
            head.prev = nn;
            head = nn;
        }
        System.out.println("Book added at the beginning successfully.");
    }

    public void addBookAtEnd(Book book) {
        DoubleNode<Book> nn = new DoubleNode<>(book);
        if (head == null) {
            head = nn;
            return;
        }
        DoubleNode<Book> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = nn;
        nn.prev = temp;
        System.out.println("Book added at the end successfully.");
    }

    public void addBookAtPosition(Book book, int position) {
        DoubleNode<Book> nn = new DoubleNode<>(book);
        if (position == 1) {
            addBookAtBeginning(book);
            return;
        }
        DoubleNode<Book> temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of bounds.");
        } else {
            nn.next = temp.next;
            nn.prev = temp;
            if (temp.next != null) {
                temp.next.prev = nn;
            }
            temp.next = nn;
            System.out.println("Book added at position " + position + " successfully.");
        }
    }

    public void deleteBook(String bookId) {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        DoubleNode<Book> temp = head;
        while (temp != null) {
            if (temp.data.bookId.equals(bookId)) {
                if (temp.prev == null) { // Head node
                    head = temp.next;
                    if (head != null) head.prev = null;
                } else {
                    temp.prev.next = temp.next;
                    if (temp.next != null) {
                        temp.next.prev = temp.prev;
                    }
                }
                System.out.println("Book with ID " + bookId + " deleted successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void searchBook(String query) {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        DoubleNode<Book> temp = head;
        boolean found = false;
        System.out.printf("%-20s %-15s %-10s %-10s %-10s%n", "Title", "Author", "Genre", "ID", "Available");
        System.out.println("-------------------------------------------------------------------------");
        while (temp != null) {
            if (temp.data.title.equalsIgnoreCase(query) || temp.data.author.equalsIgnoreCase(query)) {
                System.out.printf("%-20s %-15s %-10s %-10s %-10s%n",
                        temp.data.title, temp.data.author, temp.data.genre, temp.data.bookId, temp.data.isAvailable ? "Yes" : "No");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No book found matching: " + query);
        }
    }

    public void updateAvailability(String bookId, boolean status) {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        DoubleNode<Book> temp = head;
        while (temp != null) {
            if (temp.data.bookId.equals(bookId)) {
                temp.data.isAvailable = status;
                System.out.println("Availability updated for Book ID " + bookId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void displayBooks(boolean reverse) {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.printf("%-20s %-15s %-10s %-10s %-10s%n", "Title", "Author", "Genre", "ID", "Available");
        System.out.println("-------------------------------------------------------------------------");

        if (!reverse) {
            DoubleNode<Book> temp = head;
            while (temp != null) {
                System.out.printf("%-20s %-15s %-10s %-10s %-10s%n",
                        temp.data.title, temp.data.author, temp.data.genre, temp.data.bookId, temp.data.isAvailable ? "Yes" : "No");
                temp = temp.next;
            }
        } else {
            DoubleNode<Book> temp = head;
            while (temp.next != null) { // Go to last node
                temp = temp.next;
            }
            while (temp != null) { // Traverse backwards
                System.out.printf("%-20s %-15s %-10s %-10s %-10s%n",
                        temp.data.title, temp.data.author, temp.data.genre, temp.data.bookId, temp.data.isAvailable ? "Yes" : "No");
                temp = temp.prev;
            }
        }
    }

    public void countBooks() {
        int count = 0;
        DoubleNode<Book> temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total number of books: " + count);
    }

    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        System.out.println("==== Library Management System ====");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Search Book");
            System.out.println("4. Update Availability");
            System.out.println("5. Display All Books (Forward)");
            System.out.println("6. Display All Books (Reverse)");
            System.out.println("7. Count Books");
            System.out.println("8. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    lms.addBookInput();
                    break;
                case 2:
                    System.out.println("Enter Book ID to delete: ");
                    String delId = sc.nextLine();
                    lms.deleteBook(delId);
                    break;
                case 3:
                    System.out.println("Enter Title or Author to search: ");
                    String query = sc.nextLine();
                    lms.searchBook(query);
                    break;
                case 4:
                    System.out.println("Enter Book ID: ");
                    String id = sc.nextLine();
                    System.out.println("Is Available? (true/false): ");
                    boolean status = sc.nextBoolean();
                    lms.updateAvailability(id, status);
                    break;
                case 5:
                    lms.displayBooks(false);
                    break;
                case 6:
                    lms.displayBooks(true);
                    break;
                case 7:
                    lms.countBooks();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void addBookInput() {
        try {
            System.out.println("Enter Book Title: ");
            String title = sc.nextLine();
            System.out.println("Enter Author: ");
            String author = sc.nextLine();
            System.out.println("Enter Genre: ");
            String genre = sc.nextLine();
            System.out.println("Enter Book ID: ");
            String id = sc.nextLine();

            Book book = new Book(title, author, genre, id, true);

            System.out.println("Where to add? (1. Beginning, 2. End, 3. Position): ");
            int posChoice = sc.nextInt();
            sc.nextLine();

            if (posChoice == 1) addBookAtBeginning(book);
            else if (posChoice == 2) addBookAtEnd(book);
            else if (posChoice == 3) {
                System.out.println("Enter position: ");
                int pos = sc.nextInt();
                sc.nextLine();
                addBookAtPosition(book, pos);
            } else addBookAtEnd(book);

        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
            sc.nextLine();
        }
    }
}
