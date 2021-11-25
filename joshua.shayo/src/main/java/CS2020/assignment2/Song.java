package CS2020.assignment2;

import java.util.UUID;

public class Song
{
    private UUID songID;
    private UUID artistID;
    private String title;
    private int duration;
    
    Song(UUID IDOfArtist, String titleForSong, int durationOfSong){
        songID = UUID.randomUUID();
        artistID = IDOfArtist;
        title = titleForSong;
        duration = durationOfSong;
    }

    // Getter classes
    public UUID getSongID(){
        return songID;
    }

    public UUID getArtistID(){
        return artistID;
    }

    public String getTitle(){
        return title;
    }

    public int getDuration(){
        return duration;
    }


    // Setter classes
    public void setSongID(UUID newSongID){
        songID = newSongID;
    }

    public void setArtistID(UUID newArtistID){
        artistID = newArtistID;
    }

    public void setTitle(String newTitle){
        title = newTitle;
    }

    public void setDuration(int newDuration){
        duration = newDuration;
    }
        
}
