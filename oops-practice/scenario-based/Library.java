import java.util.*;

public class Library {
    int NoOfBooks;
    String[][] books;

    Library(int n) {
        this.NoOfBooks = n;
        this.books = new String[n][3];
    }

    void displayBooks() {
        System.out.println("Library Books List:");
        for (int i = 0; i < NoOfBooks; i++) {
            System.out.println("Book ID " + (i + 1) + ": Title: " + books[i][0] + ", Author: " + books[i][1]
                    + ", Status: " + books[i][2]);
        }
    }

    void searchBook(String keyword) {
        boolean found = false;
        System.out.println("Search Results for '" + keyword + "':");

        // prints all books matching the keyword in title
        for (int i = 0; i < NoOfBooks; i++) {
            if (books[i][0].toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(
                        "Book ID " + (i + 1) + ": " + books[i][0] + " by " + books[i][1] + " (" + books[i][2] + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found.");
        }
    }

    void updateBookStatus(int bookId, boolean isCheckout) {
        int index = bookId - 1;
        if (index >= 0 && index < NoOfBooks) {
            if (isCheckout) {
                if (books[index][2].equalsIgnoreCase("Available")) {
                    books[index][2] = "Checked Out";
                    System.out.println("Book checked out successfully.");
                } else {
                    System.out.println("Book is already checked out.");
                }
            } else {
                if (books[index][2].equalsIgnoreCase("Checked Out")) {
                    books[index][2] = "Available";
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book is already available.");
                }
            }
        } else {
            System.out.println("Invalid Book ID.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Library Management System");
        System.out.println("Enter number of books to store: ");
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline left by nextInt
        Library library = new Library(n);

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Book " + (i + 1));
            System.out.println("Enter Title: ");
            library.books[i][0] = sc.nextLine();
            System.out.println("Enter Author: ");
            library.books[i][1] = sc.nextLine();
            library.books[i][2] = "Available";
        }

        while (true) {
            System.out.println("\nChoose an action: ");
            System.out.println("1. Display all books");
            System.out.println("2. Search for a book by title");
            System.out.println("3. Check out a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            if (choice == 5)
                break;
            switch (choice) {

                // methods to perform certain operations
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.println("Enter partial title: ");
                    library.searchBook(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Enter Book ID to checkout: ");
                    library.updateBookStatus(sc.nextInt(), true);
                    break;
                case 4:
                    System.out.println("Enter Book ID to return: ");
                    library.updateBookStatus(sc.nextInt(), false);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}