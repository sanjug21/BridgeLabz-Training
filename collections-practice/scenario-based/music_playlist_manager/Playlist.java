import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class Playlist {
    private LinkedList<Song> playlist = new LinkedList<>();
    private Stack<Song> recentlyPlayed = new Stack<>();
    private Set<String> uniqueSongs = new HashSet<>();

    public void addSong(Song song) throws SongAlreadyExistsException {
        if (uniqueSongs.contains(song.key())) {
            throw new SongAlreadyExistsException("Song already exists: " + song);
        }
        playlist.add(song);
        uniqueSongs.add(song.key());
    }

    public Song playNext() {
        if (playlist.isEmpty()) {
            return null;
        }
        Song song = playlist.removeFirst();
        recentlyPlayed.push(song);
        return song;
    }

    public void showPlaylist() {
        for (Song song : playlist) {
            System.out.println(song);
        }
    }

    public void showRecentlyPlayed() {
        for (int i = recentlyPlayed.size() - 1; i >= 0; i--) {
            System.out.println(recentlyPlayed.get(i));
        }
    }
}
