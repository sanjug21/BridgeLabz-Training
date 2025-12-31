public class Book {
    String title;
    String author;
    double price;
    public Book() {
    }
    public Book(String title,String author,double price) {
        this.title=title;
        this.author=author;
        this.price=price;
    }
    public void displayInfo() {
        System.out.println("Title: "+title);
        System.out.println("Author: "+author);
        System.out.println("Price: "+price);
    }
   public static void main(String[] args) {
    Book defBook=new Book();
    Book paramBook=new Book("Dark","Sanju",100);
    defBook.displayInfo();
    paramBook.displayInfo();
   }
}