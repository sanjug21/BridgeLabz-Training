public class Book {
    String title;
    String author;
    double price;
    boolean availability;

    public Book() {
        
    }

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.availability =true;
    }
    public void borrowBook() {
        if (availability) {
            availability = false;
            System.out.println("You have borrowed the book: " + title);
        } else {
            System.out.println("Sorry, the book: " + title + " is currently unavailable.");
        }
    }

    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
    }

    public static void main(String[] args) {
        // default constructor
        Book defBook = new Book();
        // parameterized constructor
        Book paramBook = new Book("Dark", "Sanju", 100);
        defBook.displayInfo();
        paramBook.displayInfo();
        System.out.println();

        // borrowing books
        paramBook.borrowBook();
        paramBook.borrowBook();
    }
}
