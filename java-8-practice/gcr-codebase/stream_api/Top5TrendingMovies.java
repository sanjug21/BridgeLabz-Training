import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Movie {
    String title;
    double rating;
    int releaseYear;

    public Movie(String title, double rating, int releaseYear) {
        this.title = title;
        this.rating = rating;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}

public class Top5TrendingMovies {

    public static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Matrix", 8.7, 1999));
        movies.add(new Movie("Inception", 8.8, 2010));
        movies.add(new Movie("Interstellar", 8.6, 2014));
        movies.add(new Movie("The Dark Knight", 9.0, 2008));
        movies.add(new Movie("Pulp Fiction", 8.9, 1994));
        movies.add(new Movie("Fight Club", 8.8, 1999));
        movies.add(new Movie("Forrest Gump", 8.8, 1994));
        movies.add(new Movie("The Shawshank Redemption", 9.3, 1994));
        movies.add(new Movie("The Godfather", 9.2, 1972));
        movies.add(new Movie("Goodfellas", 8.7, 1990));

        System.out.println("Top 5 Trending Movies:");
        movies.stream()
                .filter(m -> m.getRating() >= 8.5)
                .sorted(Comparator.comparing(Movie::getRating).reversed()
                        .thenComparing(Comparator.comparing(Movie::getReleaseYear).reversed()))
                .limit(5)
                .forEach(m -> System.out.println(m.getTitle() + " - Rating: " + m.getRating() + " (" + m.getReleaseYear() + ")"));
    }
}
