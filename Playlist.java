import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Playlist implements Serializable {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(int index) {
        if (index >= 0 && index < songs.size()) {
            Song removed = songs.remove(index);
            System.out.println("Removed song: " + removed.getTitle());
        } else {
            System.out.println("Invalid song index.");
        }
    }

    public void displaySongs() {
        if (songs.isEmpty()) {
            System.out.println("No songs in this playlist.");
            return;
        }
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i));
        }
    }

    public List<Song> getSongs() {
        return songs;
    }
}
