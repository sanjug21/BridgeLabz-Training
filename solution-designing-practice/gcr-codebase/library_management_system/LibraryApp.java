public class LibraryApp {

    public static void main(String[] args) {
        LibraryCatalog catalog = LibraryCatalog.getInstance();

        User student = UserFactory.createUser("student", "Alice");
        User faculty = UserFactory.createUser("faculty", "Dr. Bob");
        User librarian = UserFactory.createUser("librarian", "Ms. Carol");

        student.showRole();
        faculty.showRole();
        librarian.showRole();

        catalog.addObserver(student);
        catalog.addObserver(faculty);
        catalog.addObserver(librarian);

        Book book1 = new Book.BookBuilder("Design Patterns")
                .author("GoF")
                .edition("2nd")
                .genre("Software Engineering")
                .build();

        catalog.addBook(book1);
    }
}
