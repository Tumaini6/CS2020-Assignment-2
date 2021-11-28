package CS2020.assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;




public class App {
    
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                generateAndDisplayGUI();
            }
        });
        
        
        
        
    }
    
    private static void generateAndDisplayGUI(){
        JFrame frame = new JFrame("Joshua Shayo : assignment2");
        
        
        frame.setSize(800,600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new AboutAction());
        menuBar.add(about);
        
        JMenuItem data = new JMenuItem("Data");
        menuBar.add(data);
//         data.addActionListener(this);
        
        JMenuItem expCSV = new JMenuItem("Export to CSV");
        menuBar.add(expCSV);
//         expCSV.addActionListener(this);
        
        
        //Scrollable list
        JList<Artist> list = new JList<Artist>();
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Utils.readArtistAndSongsFromDatabase(list);
        
        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(550,80));
        scroll.setVerticalScrollBarPolicy(scroll.VERTICAL_SCROLLBAR_ALWAYS);
        
        frame.add(scroll, BorderLayout.CENTER);
        
        //Buttons
        
        //Panel for all buttons
        JPanel buttonPanel = new JPanel();
        
        //Create and add buttons to the button panel
        JButton adm = new JButton("Add Data Manually");
        buttonPanel.add(adm, BorderLayout.WEST);
        JButton adfd = new JButton("Add Data From Database");
        buttonPanel.add(adfd, BorderLayout.CENTER);
        JButton ds = new JButton("Delete Selected");
        buttonPanel.add(ds, BorderLayout.EAST);
        
        //Add button panel to the main frame
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        //Detail Pane
        
        //Panel for artist details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setPreferredSize(new Dimension(250,500));
        
        //Panel for text fields at the top
        JPanel topTFPanel = new JPanel();
        topTFPanel.setPreferredSize(new Dimension(250,100));
        
        //Panel for Date of birth
        JPanel dobPanel = new JPanel();
        dobPanel.setLayout(new BorderLayout());
        dobPanel.setPreferredSize(new Dimension(250,20));
        //Populate panel for Date of Birth
        JLabel dobL = new JLabel("Date Of Birth:");
        JTextField dobTF = new JTextField();
        dobTF.setPreferredSize(new Dimension(100,20));
        
        dobPanel.add(dobL, BorderLayout.WEST);
        dobPanel.add(dobTF, BorderLayout.EAST);
        //Add Date of Birth panel to top text frame panel
        topTFPanel.add(dobPanel);
        
        //Panel for Place of birth
        JPanel pobPanel = new JPanel();
        pobPanel.setLayout(new BorderLayout());
        pobPanel.setPreferredSize(new Dimension(250,20));
        //Populate panel for Place of Birth
        JLabel pobL = new JLabel("Place Of Birth:");
        JTextField pobTF = new JTextField();
        pobTF.setPreferredSize(new Dimension(100,20));
        pobPanel.add(pobL, BorderLayout.WEST);
        pobPanel.add(pobTF, BorderLayout.EAST);
        //Add Date of Place panel to top text frame panel
        topTFPanel.add(pobPanel);
        
        //Panel for Born on Weekend
        JPanel bowPanel = new JPanel();
        bowPanel.setLayout(new BorderLayout());
        pobPanel.setPreferredSize(new Dimension(250,20));
        //Populate panel for Born on Weekend
        JLabel bowL = new JLabel("Born on Weekend:");
        JTextField bowTF = new JTextField();
        bowTF.setPreferredSize(new Dimension(100,20));
        bowPanel.add(bowL, BorderLayout.WEST);
        bowPanel.add(bowTF, BorderLayout.EAST);
        //Add Born on Weekend to top text frame panel
        topTFPanel.add(bowPanel);
        
        //Add the whole top panel to details panel
        detailsPanel.add(topTFPanel, BorderLayout.NORTH);
        
        //Panel for scrollable text field
        JTextField stf = new JTextField();
        JScrollPane stfPanel = new JScrollPane(stf);
        stfPanel.setPreferredSize(new Dimension(250,400));
        
        detailsPanel.add(stfPanel, BorderLayout.SOUTH);
        
        
        frame.add(detailsPanel, BorderLayout.EAST);
        
        
        
        
        frame.setVisible(true);
        
    }
    
    static class AboutAction implements ActionListener{
        public void actionPerformed (ActionEvent e){
            JOptionPane.showMessageDialog(null, "Assignment 2 App v.0.1", "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
 