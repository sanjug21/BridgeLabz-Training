import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Book {
    String title;
    String genre;
    int pages;

    public Book(String title, String genre, int pages) {
        this.title = title;
        this.genre = genre;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }
}

public class LibraryBookStatistics {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Alchemist", "Fiction", 208));
        books.add(new Book("Sapiens", "Non-Fiction", 498));
        books.add(new Book("Atomic Habits", "Non-Fiction", 320));
        books.add(new Book("Harry Potter", "Fiction", 410));
        books.add(new Book("The Hobbit", "Fiction", 310));

        Map<String, IntSummaryStatistics> statsByGenre = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre,
                        Collectors.summarizingInt(Book::getPages)));

        System.out.println("Book Statistics By Genre:");
        statsByGenre.forEach((genre, stats) -> {
            System.out.println(genre + " -> Total: " + stats.getSum()
                    + ", Average: " + stats.getAverage()
                    + ", Max: " + stats.getMax());
        });
    }
}
