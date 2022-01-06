package genius;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.io.*;

public class LyricalMiracle
{ 
	private final static String songLyricsURL = "http://www.songlyrics.com";
	 
	private int k, n;
	private String text;
	MarkovModel model;
	private String file;
	private String writeable;
	private String txt1;
	private String txt2;
	private String txt3;
	
	public LyricalMiracle(int order, int numCharsToGenerate, String fileName1, String fileName2, String fileName3) {
		k = order;
		n = numCharsToGenerate;
		file = fileName1;
		
		try {
			getText1(fileName1);
			getText2(fileName2);
			getText3(fileName3);
		}
		catch (IOException e){
			e.printStackTrace();
			System.out.println("Files reading didn't work.");
		}
		
		generateText();
	}
	
	private void getText1(String fileName) throws IOException {
		StringBuffer buffer = new StringBuffer();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
		    for (String line = null; (line = br.readLine()) != null;) {
		    	// Convert UTF-8 encoding to ASCII
		    	line = new String(line.getBytes("ASCII"));
		    	buffer.append(line);
		    }
		} catch (Exception e) {
			System.out.println("Getting the original song resulted in an error, trying again.");
			buffer.append(fileName);
		}
		
		txt1 = buffer.toString();
	}
	private void getText2(String fileName) throws IOException {
		StringBuffer buffer = new StringBuffer();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
		    for (String line = null; (line = br.readLine()) != null;) {
		    	// Convert UTF-8 encoding to ASCII
		    	line = new String(line.getBytes("ASCII"));
		    	buffer.append(line);
		    }
		}
		
		txt2 = buffer.toString();
	}
	private void getText3(String fileName) throws IOException {
		StringBuffer buffer = new StringBuffer();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
		    for (String line = null; (line = br.readLine()) != null;) {
		    	// Convert UTF-8 encoding to ASCII
		    	line = new String(line.getBytes("ASCII"));
		    	buffer.append(line);
		    }
		} catch (Exception e) {
			System.out.println("Getting the top songs resulted in an error, trying again.");
		}
		
		txt3 = buffer.toString();
	}
	
	private void generateText() {
		text = txt1 + txt2 + txt3;
		String newTxt = txt2 + txt3;
		Tagger taggin = new Tagger(newTxt);
		Tagger tagStart = new Tagger(txt1);
		System.out.println("=================================================");
		System.out.println(text);
		System.out.println("=================================================");
		model = new MarkovModel(text, k);
		Random numGenerator = new Random();
		int randomNum = numGenerator.nextInt(text.length() - k);
		
		// chooses a random substring from within the text of length k
		String kgram = text.substring(randomNum, randomNum + k);

		StringBuffer buffer = new StringBuffer();
		
		for (int i=0; i<n; i++) {
			char nextChar = model.nextCharacter(kgram);
			
			// if no possible next char
			if (nextChar == (char) 255) {
				buffer = new StringBuffer();
				kgram = text.substring(0, k);
				nextChar = model.nextCharacter(kgram);
				i = 0;
			}
			
			// new kgram is equal to kgram - first letter of kgram + nextChar
			kgram = kgram.substring(1, kgram.length()) + nextChar;
			buffer.append(nextChar);
			// Start a new line when a full stop is encountered
			if (nextChar == '.') buffer.append('\n');
		}
		
		writeable = buffer.toString();
		
		try {
			file = (file.substring(0,file.indexOf("\\songToUse.txt")) + "\\finishedText.txt");
		} catch (Exception e) {
			file = (file.substring(0,file.indexOf("\\supportingLyrics.txt")) + "\\finishText.txt");
		}
		System.out.println(file);
		
		System.out.println(buffer.toString());
    	try {
    		PrintWriter out = new PrintWriter(file);
    		out.print("");
    		out.println(writeable);
    		out.println("Original Song's Tagging: ");
    		out.println(tagStart.tag());
    		out.println("Other Song's Tagging: ");
    		out.println(taggin.tag());
    		out.println("Generated Song's Tagging: ");
    		Tagger generatedTagger = new Tagger(writeable);
    		out.println(generatedTagger.tag());
    		out.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	System.out.println(writeable);
	}
	
	public static List<String> getSongLyrics( String band, String songTitle) throws IOException {
	     List<String> lyrics= new ArrayList<String>();
	 
	     Document doc = Jsoup.connect(songLyricsURL+ "/"+band.replace(" ", "-").toLowerCase()+"/"+songTitle.replace(" ", "-").toLowerCase()+"-lyrics/").get();
	     String title = doc.title();
	     System.out.println(title);
	     Element p = doc.select("p.songLyricsV14").get(0);
	     for (Node e: p.childNodes()) {
	          if (e instanceof TextNode) {
	            lyrics.add(((TextNode)e).getWholeText());
	          }
	     }
	     return lyrics;
	}
	
	//Next method to get other lyrics created by the author
	
	public static List<String> getOtherLyrics( String band ) throws IOException {
	     List<String> lyrics= new ArrayList<String>();
	 
	     //Change code below
	     Document doc = Jsoup.connect(songLyricsURL+ "/"+band.replace(" ", "-").toLowerCase()+"-lyrics/").get();
	     String title = doc.title();
	     System.out.println(title);
	     Element p = doc.select("p.songLyricsV14").get(0);
	     for (Node e: p.childNodes()) {
	          if (e instanceof TextNode) {
	            lyrics.add(((TextNode)e).getWholeText());
	          }
	     }
	     return lyrics;
	}
}