package genius;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
 
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.*;
import javax.swing.event.*;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import genius.SongSearch.Hit;

import javax.swing.JFrame;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.lang.Character;

public class Gui extends JPanel{

	LyricalMiracle lm;
     
    private JLabel label;
    private JLabel labe;
    private JTextField textField;
    private JButton button;
    
    private JLabel label1;
    private JLabel label2;
    private JLabel kGramLabel;
    private JLabel numCharsLabel;
    private JTextField songTitle;
    private JTextField artist;
    private JButton Check;
    private boolean debugMode;
    private int kGram = 15;
    private int numChars = 5000;
    
    private JButton finalbutton;
    
    private JFileChooser fileChooser;
    String whereToSave;
    String savedLoc;
    String supportSavedLoc;
    String savedLocation;
     
    private int mode;
    
    public Gui() {
         
        fileChooser = new JFileChooser();
         
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
 
        // creates the GUI
        label = new JLabel("Song Info:");
        labe = new JLabel("Folder to Save:");
        label1 = new JLabel("Song Title");
        label2 = new JLabel("Song Creator");
        
        textField = new JTextField(30);
        
        Check = new JButton("Check for song");
        songTitle = new JTextField(20);
        artist = new JTextField(20);
        
        button = new JButton("Where to save");
         
    	button.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent evt) {
    			mode = 1;
    			try {
					buttonActionPerformed(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
    
    	Check.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent evt) {
    			mode = 2;
    			try {
					buttonActionPerformed(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
        
    	JToggleButton toggleButton = new JToggleButton("Save Analysis Files");
    	ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent)
            {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    debugMode = true;
                }
                else {
                    debugMode = false;
                }
            }
        };
    	
        SpinnerModel value =  
                new SpinnerNumberModel(15, //initial value  
                   5, //minimum value  
                   25, //maximum value  
                   1);
        
        JSpinner spinner = new JSpinner(value);   
        
        spinner.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {  
        	kGram = (int) ((JSpinner)e.getSource()).getValue();
        	System.out.println(kGram);
        }});
        
        SpinnerModel value2 =  
                new SpinnerNumberModel(5000, //initial value  
                   100, //minimum value  
                   10000, //maximum value  
                   100);
        
        JSpinner spinner2 = new JSpinner(value2);
        spinner2.addChangeListener(new ChangeListener() {public void stateChanged(ChangeEvent e) {  
        	numChars = (int) ((JSpinner)e.getSource()).getValue();
        	System.out.println(numChars);
        }});
        
        kGramLabel = new JLabel("Kernel Size (Kgram) [15 Recommended]:");
        numCharsLabel = new JLabel("Number of Returned Characters [5000 Recommended]:");
        
    	//then maybe a slider for KGram Length & Number of Characters to print (15, 5000) is the standard.
    	
    	finalbutton = new JButton("Run the program!");
    	
    	finalbutton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent evt) {
    			mode = 3;
    			try {
    				buttonActionPerformed(evt);
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	});
    	toggleButton.addItemListener(itemListener);
    	
    	add(labe);
        add(button);
        add(textField);
    	add(label);
        add(label1);
        add(songTitle);
        add(label2);
        add(artist);
        add(Check);
        add(toggleButton);
        add(kGramLabel);
        add(spinner);
        add(numCharsLabel);
        add(spinner2);
        add(finalbutton);
         
    }
     
    private void buttonActionPerformed(ActionEvent evt) throws IOException {
        if (mode == 1) {
        	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                whereToSave = textField.getText();
            }
        } else if (mode == 3) {
        	//HAVE VARIABLE FOR ORDER AND NUMBER OF CHARS TO GENERATE
        	
        	lm = new LyricalMiracle(kGram, numChars, savedLoc, supportSavedLoc, savedLocation);
        	if (!debugMode) {
        		//remove files other than finishedtext.txt
        		File songFile = new File(whereToSave + "\\songToUse.txt");
        		if (songFile.delete()) {
        			System.out.println("Song file deleted");
        		} else {
        			System.out.println("Song file wasn't deleted");
        		}
        		
        		File supportFile = new File(whereToSave + "\\supportingLyrics.txt");
        		if (supportFile.delete()) {
        			System.out.println("Supporting file deleted");
        		} else {
        			System.out.println("Supporting file wasn't deleted");
        		}
        		
        		File suppFile = new File(whereToSave + "\\supplementalSong.txt");
        		if (suppFile.delete()) {
        			System.out.println("Supplemental file deleted");
        		} else {
        			System.out.println("Supplemental file wasn't deleted");
        		}
        		
        		File jsonFile = new File(whereToSave + "\\jsonfile.txt");
        		if (jsonFile.delete()) {
        			System.out.println("JSON file deleted");
        		} else {
        			System.out.println("JSON file wasn't deleted");
        		}
        	}
        } else if (mode == 2) {
            
        	String creator = artist.getText();
        	String title = songTitle.getText();
        	
        	creator = creator.replaceAll("\'", "\\'");
        	title = title.replaceAll("\'", "\\'");
        	
        	System.out.println(creator + "is the artist of " + title);
        	
        	String text = "";
        	
        	//Code that uses song name and title and scans for it using songlyrics.com webscraping
        	
        	try {
        		List<String> lyrics = new ArrayList<String> ();
        		lyrics = lm.getSongLyrics(creator, title);
        		text = "";
        		for (int i = 0; i < lyrics.size(); i++) {
        			text += lyrics.get(i) + "          ";
        		}
        		text = text.substring(0, text.length() - 10);
        		text += ".";
        		System.out.println(text);
        	
        		savedLoc = whereToSave + "\\songToUse.txt";
        	
        		PrintWriter out = new PrintWriter(savedLoc);
        		try {
        			out.print("");
        			out.println(text);
        			out.close();
        		} catch (Exception e) {
        			System.out.println("Song wasn't found.");
        		}
        	} catch (Exception e) {
        		System.out.println("This song was not found on the songlyrics.com database. Now trying on Genius.");
        		genius.GLA gla = new genius.GLA();
            	System.out.println(gla.search(creator));
            	SongSearch ss = new SongSearch(gla,creator);
            	LinkedList<Hit> ll = new LinkedList<Hit> (ss.getHits());
            	List<Hit> list = new ArrayList<Hit>(ll);
            	
            	String toAdd = "";
            	
            	for (int i = 0; i < list.size(); i++) {
            		toAdd += (ll.get(i)).fetchLyrics() + "\n";
            	}
            	
            	toAdd += text;
            	
            	System.out.println(toAdd);
            	savedLoc = whereToSave + "\\songToUse.txt";
        		PrintWriter out = new PrintWriter(savedLoc);
        		try {
        			out.print("");
        			out.println(toAdd);
        			out.close();
        		} catch (Exception a) {
        			a.printStackTrace();
        			System.out.println("Song wasn't found on Genius database either. Exiting...");
        		}
        	}
        	
        	//Code that scans genius.com for popular songs by the artist and writes them to file of supportingLyrics.txt
        	
        	genius.GLA gla = new genius.GLA();
        	System.out.println(gla.search(creator));
        	SongSearch ss = new SongSearch(gla,creator);
        	LinkedList<Hit> ll = new LinkedList<Hit> (ss.getHits());
        	List<Hit> list = new ArrayList<Hit>(ll);
        	
        	String toAdd = "";
        	
        	for (int i = 0; i < list.size(); i++) {
        		String checkForCorruptedEncoding = (ll.get(i)).fetchLyrics() + "\n";
        		
        		int charCount = checkForCorruptedEncoding.length();
        		int corruptedCharCount = 0;
        		for (int counter = 0; counter < charCount; counter++) {
        			
        			char testChar = checkForCorruptedEncoding.charAt(counter);
        			int charAscii = (int) testChar;
        			
        			if (charAscii > 127) { //if character is beyond traditional ascii table (romanized characters with no accents)
        				corruptedCharCount += 1;
        				
        			}
        		}
        		
        		double percentage = ((double) corruptedCharCount)/ ((double) charCount);
        		
        		if (percentage > 0.5) { //Tolerance for corrupted chars to total chars
        			checkForCorruptedEncoding = "";
        		}
        		
        		toAdd += checkForCorruptedEncoding;
        	}
        	
        	toAdd += text;   	
        	
        	System.out.println(toAdd);
        	
        	supportSavedLoc = whereToSave + "\\supportingLyrics.txt";
        	
        	PrintWriter output = new PrintWriter(supportSavedLoc);
        	try {
        		output.print("");
        		output.println(toAdd);
        		output.close();
        	} catch (Exception e) {
        		System.out.println("Output wasn't added.");
        	}
        	
        	//Use the url "itunes.apple.com/search?entity=musicArtist&term=<name of creator here>"
        	
        	String urlToUse = "https://itunes.apple.com/search?entity=musicArtist&term=" + creator.toLowerCase();
        	
        	int lastIndex = 0;
			while(lastIndex != -1){
			    lastIndex = urlToUse.indexOf(" ", lastIndex);
			    if(lastIndex != -1){
			    	urlToUse = urlToUse.substring(0, lastIndex) + "%20" +urlToUse.substring(lastIndex + " ".length());
			        lastIndex += "/,".length();
			    }
			}
        	
        	String useThisURL = encodeValue(urlToUse);
        	
        	InputStream inputStream = new URL(urlToUse).openStream();
        	FileOutputStream fileOS = new FileOutputStream(whereToSave + "\\jsonfile.txt");
        	int i = IOUtils.copy(inputStream, fileOS);
        	
        	String jsonDuder = readAllBytesJava7(whereToSave + "\\jsonfile.txt");
        	
        	String jsonString = jsonDuder ; //assign your JSON String here
        	JSONObject obj = new JSONObject(jsonString);

        	JSONArray arr = obj.getJSONArray("results");
        	String genreName = null;
        	for (int j = 0; j < arr.length(); j++)
        	{
        	    genreName = arr.getJSONObject(j).getString("primaryGenreName");
        	    break;
        	}
        	
        	System.out.println(genreName);
        	String yes = "";
        	
        	if (genreName.indexOf("/") != -1) { //Override for soul songs
        		genreName = genreName.substring(genreName.indexOf("/") + 1);
        		System.out.println(genreName);
        	}
        	yes = "http://www.popvortex.com/music/charts/top-" + genreName.toLowerCase() + "-songs.php";
        	
        	Document doc = Jsoup.connect(yes).get();
        	Elements feedItems = doc.select(".feed-item");

        	ArrayList<String> yote = new ArrayList<String>();
        	for(Element feedItem : feedItems) {
        	    String titleOfSong = feedItem.selectFirst(".title").text();
        	    String artist = feedItem.selectFirst(".artist").text();
        	    yote.add(artist + " / " +titleOfSong);
        	    System.out.println(artist + " / " + titleOfSong);
        	}
        	
        	String additionalSongs = "";
        	String supCreator = "";
        	String supTitle = "";
        	
        	for (int u = 0; u < yote.size(); u++) {
        		String testString = yote.get(u);
        		try {
            		System.out.println("Supplemental creator: " + testString.substring(0, testString.indexOf(" / ")));
            		supCreator = testString.substring(0, testString.indexOf(" / "));
            		
            		System.out.println("Supplemental song title: " + testString.substring(testString.indexOf(" / ") + 3, testString.length()));
            		supTitle = testString.substring(testString.indexOf(" / ") + 3, testString.length());
        			
            		List<String> suppLyrics = new ArrayList<String> ();
            		suppLyrics = lm.getSongLyrics(supCreator, supTitle);
            		for (int y = 0; y < suppLyrics.size(); y++) {
            			additionalSongs += suppLyrics.get(y) + " ";
            		}
            		
            		additionalSongs = additionalSongs.substring(0, additionalSongs.length() - 10);
            		additionalSongs += ".";
            		System.out.println(additionalSongs);
            	} catch (Exception e) {
            		//e.printStackTrace();
            		System.out.println("This song was not found on the songlyrics.com database.");
            	}
        	}
        	savedLocation = whereToSave + "\\supplementalSong.txt";
        	
    		PrintWriter out = new PrintWriter(savedLocation);
    		try {
    			out.print("");
    			out.println(additionalSongs);
    			out.close();
    		} catch (Exception e) {
    			System.out.println("Supplemental song didn't save.");
    		}
    		
    		System.out.println(additionalSongs);
    		System.out.println("==============================================================================================================================================================================================================");
    		System.out.println("Done with the song grabbing!");
    		System.out.println("Press the button \"Run the program!\" to create the lyrics!"); 
    		System.out.println("==============================================================================================================================================================================================================");
    		
    		
        }
    }
    
    //For URL Encoding for apple store
    
    private static String readAllBytesJava7(String filePath) 
    {
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        } 
        catch (IOException e) 
        {
            //e.printStackTrace();
            System.out.println("Apple store encoding didn't work.");
        }
        return content;
    }
    
    private String encodeValue(String value) throws UnsupportedEncodingException {
		return URLEncoder.encode(value, StandardCharsets.UTF_8.toString()).toString();
    }
    
    public void setMode(int mode) {
        this.mode = mode;
    }
     
    public String getSelectedFilePath() {
        return textField.getText();
    }
     
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }

    public static void main(String[] args) throws IOException {    
    	JFrame frame = new JFrame();
    	Gui gui = new Gui();
    	frame.add(gui);
    	gui.setSize(1000,135);
    	frame.setSize(1000,135);
    	frame.setVisible(true);
    	gui.setVisible(true);
    }
}