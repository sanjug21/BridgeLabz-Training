public class BookDetails {
    
    String title;
    String author;
    double price;

    public BookDetails(String title, String author, double price){
        this.title=title;
        this.author=author;
        this.price=price;
    }

    public void display(){
        System.out.println("Title of the book: "+title);
        System.out.println("Author of the book: "+author);
        System.out.println("Price of the book: "+price);
    }

    public static void main(String[] args){
        BookDetails book1=new BookDetails("2States", "Chetan Bhagat", 500.0);
        book1.display();
        BookDetails book2=new BookDetails("Wings Of Fire", "Abdul kalam.A.P.J", 500.0);
        book2.display();
    }
}