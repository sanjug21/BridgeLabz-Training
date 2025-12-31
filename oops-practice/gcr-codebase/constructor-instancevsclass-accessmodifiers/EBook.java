class LibraryBook {
    public String ISBN;
    protected String title;
    private String author;

    public LibraryBook(String ISBN, String title, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
    }
    // Getter and Setter for private member author
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}

public class EBook extends LibraryBook {
    EBook(String ISBN, String title, String author) {
        super(ISBN, title, author);
    }

    public static void main(String[] args) {
        EBook ebook = new EBook("978-3-16-148410-0", "The Unknown", "Sanju");
        System.out.println("ISBN: " + ebook.ISBN);
        System.out.println("Title: " + ebook.title);

        // Accessing private member author using public getter and setter methods
        System.out.println("Author: " + ebook.getAuthor());
        ebook.setAuthor("Shubham");
        System.out.println("Updated Author: " + ebook.getAuthor());
    }

}