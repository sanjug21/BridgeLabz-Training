public class Song {
    String title;
    String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String key() {
        return title.toLowerCase() + "|" + artist.toLowerCase();
    }

    @Override
    public String toString() {
        return title + " - " + artist;
    }
}
