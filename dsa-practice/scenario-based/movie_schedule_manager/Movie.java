public class Movie {

    private String title;
    private String showtime;

    public Movie(String title, String showtime) {
        this.title = title;
        this.showtime = showtime;
    }

    public String getTitle() {
        return title;
    }

    public String getShowtime() {
        return showtime;
    }

    @Override
    public String toString() {
        return String.format("Movie: %-20s | Time: %s", title, showtime);
    }
}
