import com.mpatric.mp3agic.*;

import java.io.Serializable;

public class Song implements Serializable {
    private String title;
    private String artist;
    private double duration;
    private String filePath;

    // Constructor for manually entered song details
    public Song(String title, String artist, double duration, String filePath) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.filePath = filePath;
    }

    // Constructor for extracting metadata from an MP3 file
    public Song(String filePath) {
        this.filePath = filePath;
        extractMetadata();
    }

    private void extractMetadata() {
        try {
            Mp3File mp3file = new Mp3File(filePath);
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                this.title = id3v2Tag.getTitle() != null ? id3v2Tag.getTitle() : "Unknown Title";
                this.artist = id3v2Tag.getArtist() != null ? id3v2Tag.getArtist() : "Unknown Artist";
                this.duration = mp3file.getLengthInSeconds() / 60.0; // Convert seconds to minutes
            } else {
                this.title = "Unknown Title";
                this.artist = "Unknown Artist";
                this.duration = 0.0;
            }
        } catch (Exception e) {
            System.out.println("Error reading metadata: " + e.getMessage());
            this.title = "Unknown Title";
            this.artist = "Unknown Artist";
            this.duration = 0.0;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public double getDuration() {
        return duration;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return title + " by " + artist + " (" + String.format("%.2f", duration) + " mins)";
    }
}
