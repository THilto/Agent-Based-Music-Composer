package main.com.ted.dissertationproject.music.abc4j;

import java.util.List;

import javax.swing.JFrame;

import abc.notation.Tune;
import abc.parser.TuneParser;
import abc.ui.swing.JScoreComponent;

/**
 * <h1>Music class which handles displaying a melody in sheet music form</h1>
 * <p>A MusicSheet object can be created then displayed as musical notation within a JFrame
 * <p>ABC4J is used for this process https://code.google.com/archive/p/abc4j/
 */
public class MusicSheet {
	
	private String musicText;
	private JFrame jframe;
	
	private String sheetName;
	private int timeSignatureTop;
	private String key;
	private int bpm;
	
	/**
	 * Constructor for class MusicSheet
	 * @param sheetName - Name to call the sheet music
	 * @param timeSignatureTop - Value at the top of the time signature which signifies how many beats per bar
	 * @param key - Key of the song
	 * @param BPM - Song's beats per minute
	 */
	public MusicSheet(String sheetName, int timeSignatureTop, String key, int bpm) { // Creates a default music sheet
		this.sheetName = sheetName;
		this.timeSignatureTop = timeSignatureTop;
		this.key = key;
		this.bpm = bpm;
		this.jframe = new JFrame();
		initialiseMusicSheet();
	}
	
	/**
	 * Initialises the music sheet by setting the musicText variable to ABC4J notation
	 */
	public void initialiseMusicSheet() {
		this.musicText 
		= "X:0\n" 
		+ "T:" + sheetName + "\n" 
		+ "M:" + timeSignatureTop + "/4\n" 
		+ "L:1/8\n" 
		+ "K:" + key + "\n" 
		+ "Q:" + bpm + "\n"
		+ "[";
	}
	
	/**
	 * Converts the 2D array to music notation
	 * @param array - The 2D array containing music notes and bars
	 */
	public void convertToABC4JNotation(List<List<String>> array) {
		String melody = "[| ";
		for(int i = 0; i < array.size(); i++) { // Bar Loop
			for(int j = 0; j < array.get(i).size(); j++) { // Note array
				melody += array.get(i).get(j) + " ";
			}
			melody += "| ";
		}
		melody += "]";
		this.musicText += melody;
	}
	
	/**
	 * Parse and display the musical notation within a JFrame
	 * <p><b1>Note:</b1> This is done by passing the musicText String into the Tune parser 
	 * with ABC Notation and displaying it in a JFrame
	 */
	public void displayMusicSheet() { // Displays the music sheet in a JFrame
		Tune tune = new TuneParser().parse(musicText);
		
		JScoreComponent jscore = new JScoreComponent();
		jscore.setJustification(true);
		jscore.setTune(tune);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close frame but keep application running
		jframe.add(jscore);
		jframe.pack();
		jframe.setVisible(true);
		jframe.setResizable(false);
	}
	
	/**
	 * Disposes of the Music Sheet JFrame properly
	 */
	public void disposeMusicSheet() {
		jframe.setVisible(false);
		jframe.dispose();
	}
	
}
