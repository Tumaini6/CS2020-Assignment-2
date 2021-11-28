package CS2020.assignment2;

import java.util.ArrayList;
import java.util.UUID;

public class Artist
{
    private UUID artistID;
    private String firstName;
    private String lastName;
    private String dob;
    private String placeOfBirth;
    private ArrayList<Song> songs = new ArrayList<Song>();
    
    Artist (String first, String last, String dateOfBirth, String pob){
        artistID = UUID.randomUUID();
        firstName = first;
        lastName = last;
        dob = dateOfBirth;
        placeOfBirth = pob;
    }
    Artist (UUID id, String first, String last, String dateOfBirth, String pob){
        artistID = id;
        firstName = first;
        lastName = last;
        dob = dateOfBirth;
        placeOfBirth = pob;
    }

    // Getter classes
    public UUID getArtistID(){
        return artistID;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getDob(){
        return dob;
    }

    public String getPlaceOfBirth(){
        return placeOfBirth;
    }
    public ArrayList<Song> getSongs(){
        return songs;
    }

    // Setter classes
    public void setArtistID(UUID newArtistID){
        artistID = newArtistID;
    }

    public void setFirstName(String newFirstName){
        firstName = newFirstName;
    }

    public void setLastName(String newLastName){
        lastName = newLastName;
    }

    public void setDob(String newDob){
        dob = newDob;
    }

    public void getPlaceOfBirth(String newPlaceOfBirth){
        placeOfBirth = newPlaceOfBirth;
    }
    public void addSong(Song song){
        songs.add(song);
    }
    
}
