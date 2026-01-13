package library_management_system;
import java.util.Date;
public class Transaction {
    private Book book;
    private Member member;
    private Date issueDate;
    private Date returnDate;
    private boolean isReturned;

    public Transaction(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.issueDate = new Date();
        this.isReturned = false;
    }

    public void returnBook() {
        this.returnDate = new Date();
        this.isReturned = true;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public Book getBook() {
        return book;
    }
    public Date getIssueDate() {
        return issueDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }

    public Member getMember() {
        return member;
    }
}
