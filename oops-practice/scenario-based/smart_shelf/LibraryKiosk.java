package smart_shelf;

// Main Controller
public class LibraryKiosk {
    public static void main(String[] args) {
        System.out.println("=== SmartShelf Real-Time Sorting System ===");
        
        SmartShelf shelf = new SmartShelf();

        // Scenario: Users adding books one by one.
        // The shelf must remain sorted after each addition.

        shelf.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        shelf.displayShelf();

        shelf.addBook(new Book("A Tale of Two Cities", "Charles Dickens")); // Should go to top
        shelf.displayShelf();

        shelf.addBook(new Book("Harry Potter", "J.K. Rowling"));
        shelf.displayShelf();

        shelf.addBook(new Book("Don Quixote", "Miguel de Cervantes")); // Should insert in middle
        shelf.displayShelf();

        shelf.addBook(new Book("Clean Code", "Robert C. Martin"));
        shelf.displayShelf();
    }
}