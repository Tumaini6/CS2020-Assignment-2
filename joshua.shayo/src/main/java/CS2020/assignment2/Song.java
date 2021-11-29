package CS2020.assignment2;

import java.util.UUID;

/**
 * Object type to represent a song
 * 
 * @author Joshua Shayo
 * @version 1.0
*/
public class Song
{
    private UUID songID;
    private UUID artistID;
    private String title;
    private int duration;
    
    /**
     * The constructor method for Song when the songID is not supplied
     * @param IDOfArtist the ID of the artist
     * @param titleForSong the title of the song
     * @param durationOfSong the duration of the song
     */
    Song(UUID IDOfArtist, String titleForSong, int durationOfSong){
        songID = UUID.randomUUID();
        artistID = IDOfArtist;
        title = titleForSong;
        duration = durationOfSong;
    }
    
    /**
     * The constructor method for Song when the songID is supplied
     * @param id the ID of the song
     * @param IDOfArtist the ID of the artist
     * @param titleForSong the title of the song
     * @param durationOfSong the duration of the song
     */
    Song(UUID id, UUID IDOfArtist, String titleForSong, int durationOfSong){
        songID = id;
        artistID = IDOfArtist;
        title = titleForSong;
        duration = durationOfSong;
    }

    // Getter classes
    
    /**
     * Gets the getSongID of the song
     * @return ID of the song
     */
    public UUID getSongID(){
        return songID;
    }
    
    /**
     * Gets the ArtistID of the song
     * @return ID of the artist of the song
     */
    public UUID getArtistID(){
        return artistID;
    }

    /**
     * Gets the title of the song
     * @return title of the song
     */
    public String getTitle(){
        return title;
    }
    
    /**
     * Gets the duration of the song
     * @return duration of the song
     */
    public int getDuration(){
        return duration;
    }


    // Setter classes
    
    /**
     * Sets the songID of the song
     * @param newSongID new songID value
     */
    public void setSongID(UUID newSongID){
        songID = newSongID;
    }

    /**
     * Sets the artistID of the song
     * @param newArtistID new artistID value
     */
    public void setArtistID(UUID newArtistID){
        artistID = newArtistID;
    }
    
    /**
     * Sets the title of the song
     * @param newTitle new title value
     */
    public void setTitle(String newTitle){
        title = newTitle;
    }

    /**
     * Sets the duration of the song
     * @param newDuration new duration value
     */
    public void setDuration(int newDuration){
        duration = newDuration;
    }
        
}
