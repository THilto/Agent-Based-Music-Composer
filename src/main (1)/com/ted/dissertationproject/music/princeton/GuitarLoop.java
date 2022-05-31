package main.com.ted.dissertationproject.music.princeton;

import javax.swing.SwingWorker;

import main.com.ted.dissertationproject.music.Variables;

/**
 * <h1>Guitar Loop which handles the playing of Audio with StdAudio</h1>
 * <p>More Info: https://introcs.cs.princeton.edu/java/stdlib/StdAudio.java.html
 */
public class GuitarLoop extends SwingWorker <Void, Void> {
	
	private GuitarString[] guitarStrings;
	
	/**
	 * Constructor for Guitar Loop
	 * @param guitarStrings - the guitar strings
	 */
	public GuitarLoop(GuitarString[] guitarStrings) {
		this.guitarStrings = guitarStrings;
	}
	
	/**
	 * Runs the guitar loop to listen for guitar string plucks
	 * <p>When a String is plucked the guitar loop plays the note and vibrations simulating a guitar being played
	 * @param guitarStrings - the guitar strings being played
	 */
	public void runGuitarLoop(GuitarString[] guitarStrings) {
		Variables.FINISHED_LOOP = false;
		while (!Variables.FINISHED_LOOP) {
		            
			double sample = 0;
			for(int i = 0; i < 7; i ++)   {
				sample = sample + guitarStrings[i].sample();
			}
		        	
			StdAudio.play(sample);

			for(int i = 0; i < 7; i ++)   {
			    guitarStrings[i].tic();
		    }
		}
	}
	
	/** 
	 * Swingworker used to listen for guitar string plucks 
	 */
	@Override
	protected Void doInBackground() throws Exception {
		runGuitarLoop(guitarStrings);
		return null;
	}

}
