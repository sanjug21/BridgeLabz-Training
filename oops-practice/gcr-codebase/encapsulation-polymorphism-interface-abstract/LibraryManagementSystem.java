import java.util.ArrayList;
import java.util.List;

// Interface for reservation capabilities
interface Reservable {
    void reserveItem(String borrower);
    boolean checkAvailability();
}

// Abstract class for Library Items
abstract class LibraryItem {
    private String itemId;
    private String title;
    private String author;

    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }
    // Encapsulation: Getters for itemId, title, and author
    public String getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }



    // Abstract method to be implemented by subclasses
    public abstract int getLoanDuration();
    public abstract String getReservedBy();




    // Concrete method to print item details
    public void getItemDetails() {
        System.out.println("ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
    }
}

class LibBook extends LibraryItem implements Reservable {
    private boolean isReserved = false;
    private String reservedBy; // Encapsulated borrower data

    public LibBook(String id, String title, String author) {
        super(id, title, author);
    }
    // implement the Reservable abstract methods
    @Override
    public int getLoanDuration() {
        return 14; // 14 days loan for books
    }
    // implement the Reservable interface methods
    @Override
    public String getReservedBy() {
        return reservedBy;
    }
    // implement the Reservable interface methods
    @Override
    public void reserveItem(String borrower) {
        if (!isReserved) {
            this.isReserved = true;
            this.reservedBy = borrower;
            System.out.println("Book reserved successfully for " + borrower);
        } else {
            System.out.println("Book is already reserved.");
        }
    }
    // implement the Reservable interface methods
    @Override
    public boolean checkAvailability() {
        return !isReserved;
    }
}

class Magazine extends LibraryItem implements Reservable {
    private boolean isReserved = false;
    private String reservedBy;

    public Magazine(String id, String title, String author) {
        super(id, title, author);
    }
    // implement the Reservable abstract methods
    @Override
    public int getLoanDuration() {
        return 7; // 7 days loan for magazines
    }
    // implement the Reservable interface methods
    public String getReservedBy() {
        return reservedBy;
    }
    // implement the Reservable interface methods
    @Override
    public void reserveItem(String borrower) {
        if (!isReserved) {
            this.isReserved = true;
            this.reservedBy = borrower;
            System.out.println("Magazine reserved successfully for " + borrower);
        } else {
            System.out.println("Magazine is already reserved.");
        }
    }
    // implement the Reservable interface methods
    @Override
    public boolean checkAvailability() {
        return !isReserved;
    }
}

class DVD extends LibraryItem implements Reservable {
    private boolean isReserved = false;
    private String reservedBy;

    public DVD(String id, String title, String author) {
        super(id, title, author);
    }
    // implement the Reservable abstract methods
    @Override
    public int getLoanDuration() {
        return 3; // 3 days loan for DVDs
    }
    // implement the Reservable interface methods
    public String getReservedBy() {
        return reservedBy;
    }
    // implement the Reservable interface methods
    @Override
    public void reserveItem(String borrower) {
        if (!isReserved) {
            this.isReserved = true;
            this.reservedBy = borrower;
            System.out.println("DVD reserved successfully for " + borrower);
        } else {
            System.out.println("DVD is already reserved.");
        }
    }
    // implement the Reservable interface methods
    @Override
    public boolean checkAvailability() {
        return !isReserved;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        List<LibraryItem> library = new ArrayList<>();
        library.add(new LibBook("B001", "The Dark", "Sanju"));
        library.add(new Magazine("M001", "Unknown", "Shubham"));
        library.add(new DVD("D001", "Underworld", "Sanju"));


        System.out.println("=== Library Management System ===");
        for (LibraryItem item : library) {
            item.getItemDetails();
            System.out.println("Loan Duration: " + item.getLoanDuration() + " days");
            

            if (item instanceof Reservable) {
                Reservable resItem = (Reservable) item;
                if (resItem.checkAvailability()) {
                    resItem.reserveItem("Ritu");
                } else {
                    System.out.println("Item is currently unavailable.");
                    System.out.println("Reserved by: " + item.getReservedBy());
                }
            }
            System.out.println("\n");
        }
    }
}