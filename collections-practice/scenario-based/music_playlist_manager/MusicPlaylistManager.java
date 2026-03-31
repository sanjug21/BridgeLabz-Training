public class MusicPlaylistManager {

    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        try {
            playlist.addSong(new Song("Skyline", "Nova"));
            playlist.addSong(new Song("Morning Light", "Ava"));
            playlist.addSong(new Song("Skyline", "Nova"));
        } catch (SongAlreadyExistsException ex) {
            System.out.println("Add failed: " + ex.getMessage());
        }

        System.out.println("\nPlaylist:");
        playlist.showPlaylist();

        Song nowPlaying = playlist.playNext();
        if (nowPlaying != null) {
            System.out.println("\nNow Playing: " + nowPlaying);
        }

        nowPlaying = playlist.playNext();
        if (nowPlaying != null) {
            System.out.println("Now Playing: " + nowPlaying);
        }

        System.out.println("\nRecently Played:");
        playlist.showRecentlyPlayed();
    }
}
