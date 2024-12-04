# PlaylistManager
The Playlist Manager is a console-based Java application that allows users to create and manage playlists effortlessly. Users can add songs manually or by extracting metadata from MP3 files, providing a seamless experience for organizing music. 

Features

	•	Create Playlists: Name and create multiple playlists.
	•	Add Songs:
	•	Manually input song details (title, artist, duration).
	•	Extract metadata (title, artist, duration) directly from MP3 files using the mp3agic library.
	•	View and Manage Playlists:
	•	Display all playlists and their songs.
	•	Search for songs by title or artist.
	•	Sort playlists by title, artist, or duration.
	•	Delete Songs and Playlists: Remove individual songs or entire playlists.
	•	Persistent Storage: Automatically save playlists and reload them between sessions.


How to Run

Prerequisites

	  1.	Install the Java Development Kit (JDK).
	  2.	Download mp3agic.jar and include it in the project directory.

Steps

	  1.	Clone the repository:
  git clone https://github.com/your-username/playlist-manager.git
  cd playlist-manager

  	2.	Compile the code:

  javac -cp .:mp3agic.jar *.java

  	3.	Run the program:

  java -cp .:mp3agic.jar PlaylistManager



  Technologies Used

	•	Java
	•	mp3agic library for MP3 metadata extraction.

 Future Enhancements

	•	Add a graphical user interface (GUI).
	•	Support additional audio file formats.
 	•	Support integration with Spotify.
 

 This project is an intuitive and practical tool for playlist management while showcasing advanced Java        programming concepts.
