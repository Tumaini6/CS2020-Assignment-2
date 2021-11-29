package CS2020.assignment2;

import java.util.ArrayList;
import java.util.UUID;


/**
 * Object type to represent a musician
 * 
 * @author Joshua Shayo
 * @version 1.0
*/
public class Artist
{
    private UUID artistID;
    private String firstName;
    private String lastName;
    private String dob;
    private String placeOfBirth;
    private ArrayList<Song> songs = new ArrayList<Song>();
    
    
    /**
     * The constructor method for Artist when the id is not supplied
     * @param first the firstname of the artist
     * @param last the lastname of the artist
     * @param dateOfBirth the date of birth of the artist
     * @param pob the place of birth of the artist
     */
    Artist (String first, String last, String dateOfBirth, String pob){
        artistID = UUID.randomUUID();
        firstName = first;
        lastName = last;
        dob = dateOfBirth;
        placeOfBirth = pob;
    }
    
    /**
     * The constructor method for Artist when the id is supplied
     * @param id the id of the artist
     * @param first the firstname of the artist
     * @param last the lastname of the artist
     * @param dateOfBirth the date of birth of the artist
     * @param pob the place of birth of the artist
     */
    Artist (UUID id, String first, String last, String dateOfBirth, String pob){
        artistID = id;
        firstName = first;
        lastName = last;
        dob = dateOfBirth;
        placeOfBirth = pob;
    }
    
    /**
     * Overides the to string to provide a more suitable format for the artist object
     * @return full name of the artist
     */
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    // Getter classes
    
    /**
     * Gets the artistID of the artist
     * @return artistID
     */
    public UUID getArtistID(){
        return artistID;
    }
    
    /**
     * Gets the firstname of the artist
     * @return firstName
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Gets the lastname of the artist
     * @return lastName
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Gets the date of birth of the artist
     * @return date of birth
     */
    public String getDateOfBirth(){
        return dob;
    }

    /**
     * Gets the place of birth of the artist
     * @return place of birth
     */
    public String getPlaceOfBirth(){
        return placeOfBirth;
    }
    
    /**
     * Gets the list of songs by the artist
     * @return ArrayList of the songs by the artist
     */
    public ArrayList<Song> getSongs(){
        return songs;
    }

    // Setter classes
    
    /**
     * Sets the artistID of the artist
     * @param newArtistID new ID value
     */
    public void setArtistID(UUID newArtistID){
        artistID = newArtistID;
    }

    /**
     * Sets the firstname of the artist
     * @param newFirstName new firstname value
     */
    public void setFirstName(String newFirstName){
        firstName = newFirstName;
    }

    /**
     * Sets the lastname of the artist
     * @param newLastName new lastname value
     */
    public void setLastName(String newLastName){
        lastName = newLastName;
    }

    /**
     * Sets the date of birth of the artist
     * @param newDob new dob value
     */
    public void setDob(String newDob){
        dob = newDob;
    }

    /**
     * Sets the place of birth of the artist
     * @param newPlaceOfBirth new place of birth value
     */
    public void getPlaceOfBirth(String newPlaceOfBirth){
        placeOfBirth = newPlaceOfBirth;
    }
    
    /**
     * Adds a song to the list of songs by the artist
     * @param song new song to be added to the song ArrayList
     */
    public void addSong(Song song){
        songs.add(song);
    }
    
}
