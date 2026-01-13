package library_management_system;
import java.util.*;

public class LibraryManagementSystem {
    private List<Book> catalog = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    // Book Catalog Management
    public void addBook(String id, String title, String author) {
        catalog.add(new Book(id, title, author));
        System.out.println("Book added: " + title);
    }

    public void displayBooks() {
        System.out.println("\n--- Library Catalog ---");
        for (Book b : catalog) {
            System.out.println(b);
        }
    }

    // Member Registration
    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName() + " (" + member.getClass().getSimpleName() + ")");
    }

    // Issue Book
    public void issueBook(String bookId, String memberId) {
        try {
            Book book = findBook(bookId);
            Member member = findMember(memberId);

            if (book == null) {
                System.out.println("Error: Book with ID " + bookId + " not found.");
                return;
            }
            if (member == null) {
                System.out.println("Error: Member with ID " + memberId + " not found.");
                return;
            }

            if (!book.isAvailable()) {
                throw new BookNotAvailableException("Book '" + book.getTitle() + "' is currently not available.");
            }

            book.setAvailable(false);
            transactions.add(new Transaction(book, member));
            System.out.println("Success: '" + book.getTitle() + "' issued to " + member.getName());

        } catch (BookNotAvailableException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Return Book
    public void returnBook(String bookId, int daysLate) {
        Transaction activeTx = null;
        // Find active transaction for this book
        for (Transaction t : transactions) {
            if (t.getBook().getBookId().equals(bookId) && !t.isReturned()) {
                activeTx = t;
                break;
            }
        }

        if (activeTx != null) {
            activeTx.returnBook();
            activeTx.getBook().setAvailable(true);
            
            double fine = activeTx.getMember().calculateFine(daysLate);
            System.out.println("Returned: '" + activeTx.getBook().getTitle() + "' by " + activeTx.getMember().getName());
            if (fine > 0) {
                System.out.println("Late Fee Applied: Rs " + fine);
            } else {
                System.out.println("No Late Fee.");
            }
        } else {
            System.out.println("Error: No active issue record found for Book ID " + bookId);
        }
    }

    private Book findBook(String id) {
        for (Book b : catalog) {
            if (b.getBookId().equalsIgnoreCase(id)) return b;
        }
        return null;
    }

    private Member findMember(String id) {
        for (Member m : members) {
            if (m.getMemberId().equalsIgnoreCase(id)) return m;
        }
        return null;
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        System.out.println("=== Library Management System Initialized ===\n");

        // 1. Add Books
        library.addBook("B001", "The Great Gatsby", "F. Scott Fitzgerald");
        library.addBook("B002", "Java Programming", "James Gosling");
        library.addBook("B003", "Clean Code", "Robert C. Martin");

        // 2. Register Members
        Member student = new Student("Alice", "S101");
        Member staff = new Staff("Dr. Bob", "T202");
        library.registerMember(student);
        library.registerMember(staff);

        library.displayBooks();

        // 3. Issue Books
        System.out.println("\n--- Issuing Books ---");
        library.issueBook("B001", "S101"); // Alice borrows Gatsby
        library.issueBook("B002", "T202"); // Bob borrows Java
        library.issueBook("B001", "T202"); // Bob tries to borrow Gatsby (Already issued)

        // 4. Return Books with Late Fee Calculation
        System.out.println("\n--- Returning Books ---");
        // Alice returns late (5 days) - Student Rate
        library.returnBook("B001", 5); 
        
        // Bob returns late (5 days) - Staff Rate
        library.returnBook("B002", 5);

        library.displayBooks();
    }
}
