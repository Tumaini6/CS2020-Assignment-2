package CS2020.assignment2;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;

public class Utils
{
    
    public static HashMap returnSongDurationAndTitleFormatted(ArrayList<Song> songList){
        HashMap<UUID, String> songHashMap = new HashMap<UUID, String>();
        for(Song song : songList){
            songHashMap.put(song.getSongID(), song.getTitle() + " (" + song.getDuration()/60 + ":" + song.getDuration()%60 + ")");
        }
        return songHashMap;
    }
    
}
