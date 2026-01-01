public class Books {
    static String LibraryName = "City Library";
    String title;
    String author;
    final int isbn;

    Books(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    // static method to display library name
    static void displayLibraryName() {
        System.out.println("Library Name: " + LibraryName);
    }
    
    // method to display book details
    void displayInfo() {
        System.out.println("Library: " + LibraryName);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
    }

    public static void main(String[] args) {
        Books b1 = new Books("The Dark", "Sanju", 12345);
        Books b2 = new Books("Light Cashew", "Shubham", 54321);

        // check instane of objects
        System.out.println("b1 is instance of Books: " + (b1 instanceof Books));
        System.out.println("b2 is instance of Books: " + (b2 instanceof Books));

        System.out.println(Books.LibraryName);

        // display book details
        b1.displayInfo();
        b2.displayInfo();


    }


}
