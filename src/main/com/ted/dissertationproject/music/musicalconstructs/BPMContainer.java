package main.com.ted.dissertationproject.music.musicalconstructs;

import java.util.Random;

import main.com.ted.dissertationproject.music.Variables;

/**
 * <h1>BPM Container class which handles different note timings between different notes</h1>
 */
public class BPMContainer { // Class which handles conversion of BPM to MS
	
	private int bpm; 
	private int[] timings; // The various timing intervals for each type of notes 
	
	/**
	 * Constructor for BPM Container
	 * <p><b>Note:</b>Once the bpm is passed the timings are assigned for each rythmic note
	 * @param bpm - Name to call the sheet music
	 */
	public BPMContainer(int bpm) {
		this.bpm = bpm;
		this.timings = getNoteStepTimings(bpm);
	}
	
	/**
	 * Gets the BPM of the melody
	 * @return The BPM of the melody
	 */
	public int getBPM() {
		return bpm;
	}
	
	/**
	 * Gets the timings of the melody
	 * @return The timings of the melody
	 */
	public int[] getTimings() {
		return timings;
	}
	
	/**
	 * Returns note timings in an array taking into account the BPM
	 * @param bpm - beats per minute
	 * @return return the timings for each note step based on the various rhythmic notes
	 */
	private int[] getNoteStepTimings(int bpm) { // Returns the ms timings for an eighth, quarter, half and whole note based on the BPM 
		int[] timings = new int[Variables.NO_OF_DIFFERENT_NOTES];
		timings[0] = (Variables.MILL_SECONDS_PER_MIN / bpm) / 2; // Eighth note
		timings[1] = (Variables.MILL_SECONDS_PER_MIN / bpm); // Quarter note
		timings[2] = (Variables.MILL_SECONDS_PER_MIN / bpm) * 2; // Half note
		timings[3] = (Variables.MILL_SECONDS_PER_MIN / bpm) * 4; // Whole note
		return timings;
	}
	
	/**
	 * Gets a random rhythmic note timing
	 * @return A random timing milliseconds which corresponds to a musical note based on the melody's BPM
	 */
	public int getRandomInterval() {
		Random random = new Random();
		int randomInt = random.nextInt(timings.length);
		return timings[randomInt];
	}
	
	/**
	 * Returns a random quarter or eighth note
	 * @return Random quarter or eighth note in milliseconds
	 */
	public int getRandomQuaterOrEighthNote() {
		Random random = new Random();
		int[] timings = {this.timings[0], this.timings[1]};
		int randomInt = random.nextInt(timings.length);
		return timings[randomInt];
	}
}
