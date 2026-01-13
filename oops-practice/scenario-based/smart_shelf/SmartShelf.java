package smart_shelf;

import java.util.ArrayList;
import java.util.List;

// Manages the book list using Insertion Sort for real-time ordering
public class SmartShelf {
    private List<Book> books;

    public SmartShelf() {
        this.books = new ArrayList<>();
    }

    // Adds a book and places it in the correct sorted position immediately
    // This simulates the "Insertion" step of Insertion Sort
    public void addBook(Book newBook) {
        System.out.println("Adding to list: " + newBook.getTitle());
        
        // 1. Add new element to the end of the list
        books.add(newBook);
        
        // 2. Perform Insertion Sort logic for the newly added element
        // We assume the list from 0 to n-2 is already sorted.
        int n = books.size();
        Book key = books.get(n - 1); // The new book
        int j = n - 2;

        // Shift elements that are greater than key to the right
        // Using compareToIgnoreCase for alphabetical sorting
        while (j >= 0 && books.get(j).getTitle().compareToIgnoreCase(key.getTitle()) > 0) {
            books.set(j + 1, books.get(j));
            j = j - 1;
        }
        // Place the key in its correct position
        books.set(j + 1, key);
    }

    public void displayShelf() {
        System.out.println("--- Current SmartShelf (Sorted) ---");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
        System.out.println("-----------------------------------");
    }
}