import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PlaylistManager {
    private Map<String, Playlist> playlists;
    private final String SAVE_FILE = "playlists.ser";

    public PlaylistManager() {
        playlists = new HashMap<>();
        loadPlaylists();
    }

    public void createPlaylist(String name) {
        if (playlists.containsKey(name)) {
            System.out.println("A playlist with this name already exists.");
        } else {
            playlists.put(name, new Playlist(name));
            System.out.println("Playlist '" + name + "' created.");
        }
    }

    public void deletePlaylist(String name) {
        if (playlists.containsKey(name)) {
            playlists.remove(name);
            System.out.println("Playlist '" + name + "' deleted.");
        } else {
            System.out.println("Playlist not found.");
        }
    }

    public void viewPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists available.");
            return;
        }
        System.out.println("Playlists:");
        int index = 1;
        for (String name : playlists.keySet()) {
            System.out.println(index + ". " + name);
            index++;
        }
    }

    public void managePlaylist(String name, Scanner scanner) {
        if (!playlists.containsKey(name)) {
            System.out.println("Playlist not found.");
            return;
        }

        Playlist playlist = playlists.get(name);
        while (true) {
            System.out.println("\nManaging Playlist: " + playlist.getName());
            System.out.println("1. Add Song");
            System.out.println("2. View Songs");
            System.out.println("3. Remove Song");
            System.out.println("4. Go Back");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter path to MP3 file: ");
                    String filePath = scanner.nextLine();
                    File file = new File(filePath);
                    if (file.exists()) {
                        Song song = new Song(filePath);
                        playlist.addSong(song);
                        System.out.println("Added: " + song);
                    } else {
                        System.out.println("File not found.");
                    }
                    break;
                case "2":
                    playlist.displaySongs();
                    break;
                case "3":
                    playlist.displaySongs();
                    System.out.print("Enter song number to remove: ");
                    try {
                        int songIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        playlist.removeSong(songIndex);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void savePlaylists() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(playlists);
            System.out.println("Playlists saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving playlists: " + e.getMessage());
        }
    }

    public void loadPlaylists() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            playlists = (Map<String, Playlist>) ois.readObject();
            System.out.println("Playlists loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved playlists found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading playlists: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        PlaylistManager manager = new PlaylistManager();
        Scanner scanner = new Scanner(System.in);

        Runtime.getRuntime().addShutdownHook(new Thread(manager::savePlaylists)); // Save playlists on exit

        while (true) {
            System.out.println("\n--- Playlist Manager ---");
            System.out.println("1. Create Playlist");
            System.out.println("2. View Playlists");
            System.out.println("3. Manage Playlist");
            System.out.println("4. Delete Playlist");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter playlist name: ");
                    String name = scanner.nextLine();
                    manager.createPlaylist(name);
                    break;
                case "2":
                    manager.viewPlaylists();
                    break;
                case "3":
                    manager.viewPlaylists();
                    System.out.print("Enter playlist name to manage: ");
                    name = scanner.nextLine();
                    manager.managePlaylist(name, scanner);
                    break;
                case "4":
                    manager.viewPlaylists();
                    System.out.print("Enter playlist name to delete: ");
                    name = scanner.nextLine();
                    manager.deletePlaylist(name);
                    break;
                case "5":
                    System.out.println("Exiting Playlist Manager. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
