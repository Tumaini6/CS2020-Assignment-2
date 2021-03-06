package CS2020.assignment2;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.sql.*;


/**
 * A class with various tools used in other classes
 * 
 * @author Joshua Shayo
 * @version 1.0
*/
public class Utils
{
    /**
     * Takes an array list of songs and converts it to a HashMap with a formatted verison of the songs list
     * @param songList and ArrayList of songs
     * @return the HashMap formatted form of the song list
     */
    public static HashMap<UUID, String> returnSongDurationAndTitleFormatted(ArrayList<Song> songList){
        HashMap<UUID, String> songHashMap = new HashMap<UUID, String>();
        songList.forEach((song) -> {songHashMap.put(song.getSongID(), song.getTitle() + " (" + song.getDuration()/60 + ":" + song.getDuration()%60 + ")\n");});
        return songHashMap;
    }
    
    /**
     * Given a date will determine if it lands on a weekend or not
     * @param dobOfArtist the date to check
     * @return weather the date lands on a weekend
     */
    public static boolean checkIfBornOnWeekend(String dobOfArtist){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        DayOfWeek day = LocalDate.parse(dobOfArtist.replace(" ", "-"), formatter).getDayOfWeek();
        return day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY);
    }
    
    /**
     * Populates a given JList with a sample of Artist data
     * @param list JList to be populated 
     */
    public static void createExampleArtists(JList<Artist> list){
        ArtistListModel tempList = new ArtistListModel();
        // create the example artists
        Artist example1 = new Artist("Calvin", "Broadus*", "10 Oct 1971", "California");
        Artist example2 = new Artist("Bill", "Kapri*", "11 Jun 1997", "Florida");
        Artist example3 = new Artist("David", "Jones*", "8 Jan 1947", "London");
        Artist example4 = new Artist("Eunice", "Waymon*", "21 Feb 1933", "North Carolina");
        
        // populate their songs arrays
        example1.addSong(new Song(example1.getArtistID(), "Vato", 283));
        example1.addSong(new Song(example1.getArtistID(), "Drop It Like It's Hot", 266));
        example1.addSong(new Song(example1.getArtistID(), "Beutiful", 299));
        example1.addSong(new Song(example1.getArtistID(), "Gin N Juice", 211));
        
        example2.addSong(new Song(example2.getArtistID(), "Tunnel Vision", 268));
        example2.addSong(new Song(example2.getArtistID(), "Pride", 171));
        example2.addSong(new Song(example2.getArtistID(), "Drowning", 209));
        example2.addSong(new Song(example2.getArtistID(), "Roll in Peace", 213));
        
        example3.addSong(new Song(example3.getArtistID(), "Starman", 257));
        example3.addSong(new Song(example3.getArtistID(), "Rebel Rebel", 269));
        example3.addSong(new Song(example3.getArtistID(), "Changes", 218));
        example3.addSong(new Song(example3.getArtistID(), "Diamond Dogs", 364));
        
        example4.addSong(new Song(example4.getArtistID(), "Stars", 397));
        example4.addSong(new Song(example4.getArtistID(), "Feeling Good", 174));
        example4.addSong(new Song(example4.getArtistID(), "I Put A Spell On You", 155));
        example4.addSong(new Song(example4.getArtistID(), "Baltimore", 278));
        
        // add example artists to the JList
        for(int i = 0; i<list.getModel().getSize(); i++){
            tempList.addElement(list.getModel().getElementAt(i));
        }
        tempList.addElement(example1);
        tempList.addElement(example2);
        tempList.addElement(example3);
        tempList.addElement(example4);
        
        tempList.sortElements();
        list.setModel(tempList);
        
        
    }
    
    /**
     * Establishes a connection to the CS2020-assignment2 database
     * @return the connection to the database
     */
    public static Connection connectToDatabase(){
        Connection sqlConnect = null;
        
        try{
            Class.forName("org.sqlite.JDBC");
            sqlConnect = DriverManager.getConnection("jdbc:sqlite:resources/CS2020-assignment2.db");
            System.out.println("SQLite DB connected");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return sqlConnect;
        
    }
    
    /**
     * Populates a given JList with data from the CS2020-assignment2 database
     * @param list JList to be populated 
     */
    public static void readArtistAndSongsFromDatabase(JList<Artist> list){
        ArtistListModel tempList = new ArtistListModel();
        Connection c = connectToDatabase();
        for(int i = 0; i<list.getModel().getSize(); i++){
            tempList.addElement(list.getModel().getElementAt(i));
        }
        try{
            Statement stmt1 = c.createStatement();
            ResultSet artistsResults = stmt1.executeQuery("SELECT * FROM Artist");
            while (artistsResults.next() ) {
                String artistID = artistsResults.getString("artistID");
                
                ArrayList<String> name = new ArrayList<String>();
                for(String np:artistsResults.getString("name").split(" ")){
                    name.add(np);
                }
                if (name.size() == 1){
                    name.add("");
                }
                

                Artist artist = new Artist(UUID.fromString(artistID.strip()), name.get(0), name.get(1), artistsResults.getString("dob").strip(), artistsResults.getString("placeOfBirth").strip());

                ResultSet songsResults = c.createStatement().executeQuery("SELECT * FROM Song WHERE artistID = '" + artistID + "'");
                while (songsResults.next()) {
                    artist.addSong(new Song(UUID.fromString(songsResults.getString("songID").strip()), UUID.fromString(artistID.strip()), songsResults.getString("title").strip(), songsResults.getInt("duration")));
                }
                tempList.addElement(artist);
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
        tempList.sortElements();
        list.setModel(tempList);
    }
    
}

/**
 * Extends the DefaultListModel class to add sorting functionality
 */
class ArtistListModel extends DefaultListModel{
    public void sortElements(){
        boolean sorted = false;
        Artist current;
        Artist next;
        Object temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < this.getSize() - 1; i++) {
                current = (Artist)this.getElementAt(i);
                next = (Artist)this.getElementAt(i+1);
                if (current.getFirstName().compareTo(next.getFirstName())>0) {
                    temp = this.getElementAt(i);
                    this.setElementAt(this.getElementAt(i+1) ,i);
                    this.setElementAt(temp, i+1);
                    sorted = false;
                }
            }
        }
    }
}