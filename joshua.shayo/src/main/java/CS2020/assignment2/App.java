package CS2020.assignment2;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import java.io.*;
import com.opencsv.CSVWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.HashMap;


/**
 * The main class for CS2020 assignment2
 * 
 * @author Joshua Shayo
 * @version 1.0
*/
public class App {
    
    protected static JFrame frame;
    
    protected static JMenuBar menuBar;
    protected static JMenuItem about;
    protected static JMenuItem data;
    protected static JMenuItem expCSV;
    
    
    protected static JList<Artist> list;
    protected static JScrollPane scroll;
    
    
    protected static JButton adm;
    protected static JButton adfd;
    protected static JButton ds;
    
    
    protected static JPanel detailsPanel;
    
    protected static JPanel topTFPanel;
    protected static JPanel dobPanel;
    protected static JLabel dobL;
    protected static JTextField dobTF;
    protected static JPanel pobPanel;
    protected static JLabel pobL;
    protected static JTextField pobTF;
    protected static JPanel bowPanel;
    protected static JLabel bowL;
    protected static JTextField bowTF;
    
    
    protected static JTextArea stf;
    protected static JScrollPane stfPanel;
    
    protected static JPopupMenu popup;
    protected static JMenuItem delete;
    
    protected static JPopupMenu detailsPopup;
    
    /**
     * Main method for App
     * @param args arguments for main method
     */
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                App songApp = new App();
                songApp.generateAndDisplayGUI();
            }
        });
        
        
        
        
    }
    
    /**
     * initializes all of the gui elements
     */
    private static void generateAndDisplayGUI(){
        frame = new JFrame("Joshua Shayo : assignment2");
        
        
        frame.setSize(800,600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
        
        
        menuBar = new JMenuBar();
        menuBar.setSize(300, 10);
        frame.setJMenuBar(menuBar);
        
        about = new JMenuItem("About");
        about.addActionListener(new AboutAction());
        menuBar.add(about);
        
        data = new JMenuItem("Data");
        data.addActionListener(new DataAction());
        menuBar.add(data);
        
        expCSV = new JMenuItem("Export to CSV");
        expCSV.addActionListener(new ExportToCSV());
        menuBar.add(expCSV);
        
        
        //Popup menu
        popup = new JPopupMenu();
        delete = new JMenuItem("Delete Selected Item");
        delete.addActionListener(new DeleteSelected());
        
        //Scrollable list
        
        list = new JList<Artist>();
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation (JList.VERTICAL);
        list.addListSelectionListener(new ArtistSelection());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked( MouseEvent e ){
                if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1){
                    popup.show(frame , e.getX(), e.getY());
                }
            }
        });
        
        scroll = new JScrollPane(list);
        
        scroll.setVerticalScrollBarPolicy(scroll.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_NEVER);
        
        frame.add(scroll, BorderLayout.CENTER);
        
        //Buttons
        
        //Panel for all buttons
        JPanel buttonPanel = new JPanel();
        
        //Create and add buttons to the button panel
        adm = new JButton("Add Data Manually");
        buttonPanel.add(adm, BorderLayout.WEST);
        adm.addActionListener(new AddDataManually());
        
        adfd = new JButton("Add Data From Database");
        buttonPanel.add(adfd, BorderLayout.CENTER);
        adfd.addActionListener(new AddDataFromDatabase());
        ds = new JButton("Delete Selected");
        ds.addActionListener(new DeleteSelected());
        ds.setEnabled(false);
        buttonPanel.add(ds, BorderLayout.EAST);
        
        //Add button panel to the main frame
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        //Detail Pane
        
        //Panel for artist details
        detailsPanel = new JPanel();
        detailsPanel.setPreferredSize(new Dimension(250,500));
        
        //Panel for text fields at the top
        topTFPanel = new JPanel();
        topTFPanel.setPreferredSize(new Dimension(250,100));
        
        //Panel for Date of birth
        dobPanel = new JPanel();
        dobPanel.setLayout(new BorderLayout());
        dobPanel.setPreferredSize(new Dimension(250,20));
        //Populate panel for Date of Birth
        dobL = new JLabel("Date Of Birth:");
        dobTF = new JTextField();
        dobTF.setEditable(false);
        dobTF.setPreferredSize(new Dimension(100,20));
        
        dobPanel.add(dobL, BorderLayout.WEST);
        dobPanel.add(dobTF, BorderLayout.EAST);
        //Add Date of Birth panel to top text frame panel
        topTFPanel.add(dobPanel);
        
        //Panel for Place of birth
        pobPanel = new JPanel();
        pobPanel.setLayout(new BorderLayout());
        pobPanel.setPreferredSize(new Dimension(250,20));
        //Populate panel for Place of Birth
        pobL = new JLabel("Place Of Birth:");
        pobTF = new JTextField();
        pobTF.setEditable(false);
        pobTF.setPreferredSize(new Dimension(100,20));
        pobPanel.add(pobL, BorderLayout.WEST);
        pobPanel.add(pobTF, BorderLayout.EAST);
        //Add Date of Place panel to top text frame panel
        topTFPanel.add(pobPanel);
        
        //Panel for Born on Weekend
        bowPanel = new JPanel();
        bowPanel.setLayout(new BorderLayout());
        pobPanel.setPreferredSize(new Dimension(250,20));
        //Populate panel for Born on Weekend
        bowL = new JLabel("Born on Weekend:");
        bowTF = new JTextField();
        bowTF.setEditable(false);
        bowTF.setPreferredSize(new Dimension(100,20));
        bowPanel.add(bowL, BorderLayout.WEST);
        bowPanel.add(bowTF, BorderLayout.EAST);
        //Add Born on Weekend to top text frame panel
        topTFPanel.add(bowPanel);
        
        //Add the whole top panel to details panel
        detailsPanel.add(topTFPanel, BorderLayout.NORTH);
        
        //Panel for scrollable text field
        stf = new JTextArea();
        stf.setEditable(false);
        stfPanel = new JScrollPane(stf);
        stfPanel.setPreferredSize(new Dimension(250,400));
        
        detailsPanel.add(stfPanel, BorderLayout.SOUTH);
        
        
        frame.add(detailsPanel, BorderLayout.EAST);
        
        
        
        
    }
    
    static String getData(){
        ListModel<Artist> model = list.getModel();
        if(model.getSize() == 0){
            return "The list is empty";
        }
        
        String data = "";
        int songCount = 0;
        
        String oldestName = model.getElementAt(0).toString();
        String youngestName = model.getElementAt(0).toString();
        
        String shortName = model.getElementAt(0).getSongs().get(0).getTitle();
        int shortDuration = model.getElementAt(0).getSongs().get(0).getDuration();
        String longName = model.getElementAt(0).getSongs().get(0).getTitle();
        int longDuration  = model.getElementAt(0).getSongs().get(0).getDuration();
        
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yyyy");
            Date oldestDate = sdf.parse(model.getElementAt(0).getDateOfBirth().replace(" ", "-"));
            Date youngestDate = sdf.parse(model.getElementAt(0).getDateOfBirth().replace(" ", "-"));

            for(int counter = 0; counter < model.getSize(); counter++){
                Artist artist = model.getElementAt(counter);

                Date dob = sdf.parse(artist.getDateOfBirth().replace(" ", "-"));

                songCount += artist.getSongs().size();

                if(oldestDate.compareTo(dob) > 0){
                    oldestDate = dob;
                    oldestName = artist.toString();
                }

                if(youngestDate.compareTo(dob) < 0){
                    youngestDate = dob;
                    youngestName = artist.toString();
                }
                
                for(Song song:artist.getSongs()){
                    if(song.getDuration() < shortDuration){
                        shortDuration = song.getDuration();
                        shortName = song.getTitle();
                    }else if(song.getDuration() == shortDuration){
                        shortName += (", " + song.getTitle());
                    }
                    
                    if(song.getDuration() > longDuration){
                        longDuration = song.getDuration();
                        longName = song.getTitle();
                    }else if(song.getDuration() == longDuration){
                        longName += (", " + song.getTitle());
                    }
                }

            }
        }catch(ParseException pe){
            System.out.println(pe.getMessage());
        }
        data += ("Number of artists: " + model.getSize());
        data += ("\nTotal number of songs: " + songCount);
        data += ("\nOldest artist is: " + oldestName);
        data += ("\nYoungest artist is: " + youngestName);
        data += ("\nShortest song names are: [" + shortName + "]");
        data += ("\nLongest song names are: [" + longName + "]");
        
        return data;
    }
    
    static class AboutAction implements ActionListener{
        public void actionPerformed (ActionEvent e){
            JOptionPane.showMessageDialog(null, "Assignment 2 App v.0.1", "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    static class DataAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(null, getData(), "Data Statistics", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    static class ExportToCSV implements ActionListener{
        public void actionPerformed (ActionEvent e){
            try(CSVWriter artistWriter = new CSVWriter(new FileWriter("resources/artists.csv"))){
                String artistHeaders[] = {"artistID", "dob", "placeOfBirth"};
                artistWriter.writeNext(artistHeaders);
                ListModel<Artist> model = list.getModel();
                for(int counter = 0; counter < model.getSize(); counter++){
                    Artist artist = list.getModel().getElementAt(counter);
                    String artistLine[] = {artist.getArtistID().toString(), artist.getDateOfBirth(), artist.getPlaceOfBirth()}; 
                    artistWriter.writeNext(artistLine);
                    try(CSVWriter songWriter = new CSVWriter(new FileWriter("resources/songs.csv"))){
                        String songsHeaders[] = {"songId", "artistID", "title", "duration"};
                        songWriter.writeNext(songsHeaders);
                        for(Song song:artist.getSongs()){
                            String songLine[] = {song.getSongID().toString(), song.getArtistID().toString(), song.getTitle(), String.valueOf(song.getDuration())};
                            songWriter.writeNext(songLine);
                        }
                        songWriter.flush();
                    }
                }
                artistWriter.flush();
            }catch (IOException ioE){
                System.out.println(ioE.getMessage());
            }
            
            
            
        }
    }
    
    static class AddDataManually implements ActionListener{
        public void actionPerformed (ActionEvent e){
            Utils.createExampleArtists(list);
            adm.setEnabled(false);
        }
    }
    
    static class AddDataFromDatabase implements ActionListener{
        public void actionPerformed (ActionEvent e){
            Utils.readArtistAndSongsFromDatabase(list);
            adfd.setEnabled(false);
        }
    }
    
    static class DeleteSelected implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String[] options = {"Cancel", "No", "Yes"};
            int conformation = JOptionPane.showOptionDialog(null, "Are you sure?", "Select an Option", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (conformation == 2){
                list.removeListSelectionListener(list.getListSelectionListeners()[0]);
                DefaultListModel model = new DefaultListModel();
                int index = list.getSelectedIndex();
                for(int i = 0; i<list.getModel().getSize(); i++){
                    model.addElement(list.getModel().getElementAt(i));
                }
                model.removeElementAt(index);

                if(model.size() == 0){
                    ds.setEnabled(false);
                }

                list.setModel(model);
                list.addListSelectionListener(new ArtistSelection());
                list.setSelectedIndex(0);
            }
        }
    }
    
    static class ArtistSelection implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent v){
            ds.setEnabled(true);
            popup.add(delete);
            Artist artist = (Artist) list.getSelectedValue();
            
            String songsText= "";
            int songCount = 1;
            for(String s:Utils.returnSongDurationAndTitleFormatted(artist.getSongs()).values()){
                songsText += (songCount++ + ". " + s);
            }
            
            
            dobTF.setText(artist.getDateOfBirth());
            pobTF.setText(artist.getPlaceOfBirth());
            bowTF.setText((Utils.checkIfBornOnWeekend(artist.getDateOfBirth()) ? "Yes":"No"));
            stf.setText(songsText);
            
            
            
        }
    }
    
}
 