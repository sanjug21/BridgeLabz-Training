package book_shelf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BookShelf {
    // HashMap maps genre -> list of books (BookLinkedList)
    private Map<String, BookLinkedList> genreShelves;
    
    // HashSet to avoid duplicate books (using title for uniqueness)
    private Set<String> bookIndex;

    public BookShelf() {
        this.genreShelves = new HashMap<>();
        this.bookIndex = new HashSet<>();
    }

    // Add a book to the library
    public void addBook(String title, String author, String genre) {
        if (bookIndex.contains(title)) {
            System.out.println("Book '" + title + "' already exists in the library.");
            return;
        }

        Book newBook = new Book(title, author, genre);
        
        // If genre doesn't exist, create a new BookLinkedList for it
        genreShelves.putIfAbsent(genre, new BookLinkedList());
        
        // Add book to the specific genre list
        genreShelves.get(genre).add(newBook);
        
        // Add to index to track uniqueness
        bookIndex.add(title);
        System.out.println("Added: " + newBook);
    }

    // Remove a book from the library
    public void removeBook(String title) {
        if (!bookIndex.contains(title)) {
            System.out.println("Book '" + title + "' not found.");
            return;
        }

        // Iterate through genres to find and remove the book
        for (Map.Entry<String, BookLinkedList> entry : genreShelves.entrySet()) {
            BookLinkedList books = entry.getValue();
            
            // remove returns true if the element was removed
            boolean removed = books.remove(title);
            
            if (removed) {
                bookIndex.remove(title);
                System.out.println("Removed book: " + title);
                
                // Clean up: Remove genre key if list is empty
                if (books.isEmpty()) {
                    genreShelves.remove(entry.getKey());
                }
                return;
            }
        }
    }

    // Borrow a book
    public void borrowBook(String title) {
        Book book = findBook(title);
        if (book != null) {
            book.borrowBook();
        } else {
            System.out.println("Book '" + title + "' not available to borrow.");
        }
    }

    // Return a book
    public void returnBook(String title) {
        Book book = findBook(title);
        if (book != null) {
            book.returnBook();
        } else {
            System.out.println("Book '" + title + "' does not belong to this library.");
        }
    }

    // Helper method to find a book object by title
    private Book findBook(String title) {
        for (BookLinkedList list : genreShelves.values()) {
            Book b = list.find(title);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    // Display books by genre
    public void displayBooksByGenre(String genre) {
        if (genreShelves.containsKey(genre)) {
            System.out.println("\n--- Books in Genre: " + genre + " ---");
            genreShelves.get(genre).display();
        } else {
            System.out.println("No books found for genre: " + genre);
        }
    }

    public static void main(String[] args) {
        BookShelf library = new BookShelf();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books by Genre");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String addTitle = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String addAuthor = scanner.nextLine();
                    System.out.print("Enter book genre: ");
                    String addGenre = scanner.nextLine();
                    library.addBook(addTitle, addAuthor, addGenre);
                    break;
                case 2:
                    System.out.print("Enter title of book to remove: ");
                    String removeTitle = scanner.nextLine();
                    library.removeBook(removeTitle);
                    break;
                case 3:
                    System.out.print("Enter title of book to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowTitle);
                    break;
                case 4:
                    System.out.print("Enter title of book to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 5:
                    System.out.print("Enter genre to display: ");
                    String displayGenre = scanner.nextLine();
                    library.displayBooksByGenre(displayGenre);
                    break;
                case 0:
                    System.out.println("Exiting library system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}