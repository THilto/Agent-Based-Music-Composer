package main.com.ted.dissertationproject.music.musicalconstructs;

import java.util.Arrays;
import java.util.Random;

import main.com.ted.dissertationproject.music.Variables;
import main.com.ted.dissertationproject.music.princeton.GuitarString;

public class ScaleContainer {
	
	private String[] scaleNotes;
	private GuitarString[] guitarStrings;
	
	/**
	 * Constructor for class ScaleContainer
	 * <p><b>Note:</b> Once the key is entered the local variables are set to the corresponding scales notes and note frequencies
	 * @param scale - The scale's Key
	 */
	public ScaleContainer(String key) {
		this.scaleNotes = getScaleNotes(key);
		this.guitarStrings = getFrequenciesFromScale();
	}
	
	/**
	 * Gets the scales key by getting the first note in scaleNotes array
	 * @return The scales key 
	 */
	public String getKey() { // Returns the key / first note in the scale
		return scaleNotes[0];
	}
	
	/**
	 * Gets all scale's notes
	 * @return The scale's key
	 */
	public String[] getScaleNotes() {
		return scaleNotes;
	}
	
	/**
	 * Gets all scale's notes
	 * @return The scale's notes
	 */
	public GuitarString[] getGuitarStrings() {
		return guitarStrings;
	}
	
	/*public String getNoteFromFrequency(double frequency) {
		int index = Arrays.asList(Variables.MUSIC_NOTE_FREQ).indexOf(frequency); // Returns the index position of frequency
		return Variables.MUSIC_NOTES[index];
	}*/
	
	/**
	 * Gets all scale notes based on the key
	 * <p>Uses a for loop to run through all notes and jumps between the intervals found within a major scale
	 * @param scale - The scale's Key
	 * @return The scale notes
	 */
	private String[] getScaleNotes(String key) {
		String convertedKey = convertKeyToABC4JNotation(key);
		int arrayPosition = Arrays.asList(Variables.MUSIC_NOTES).indexOf(convertedKey); // Searches for the position of the note
		String[] notesInScale = new String[Variables.NO_OF_NOTES_MAJOR_SCALE];
		notesInScale[0] = Variables.MUSIC_NOTES[arrayPosition];
		for(int i = 1; i < Variables.NO_OF_NOTES_MAJOR_SCALE; i++) { // For each note in the scale
			arrayPosition = arrayPosition + Variables.MAJOR_SCALE_STEPS[i - 1];
			if((arrayPosition) >= Variables.MUSIC_NOTES.length) { // If the array position is greater than the size of the array then restart
				arrayPosition = arrayPosition - Variables.MUSIC_NOTES.length;
			}
			notesInScale[i] = Variables.MUSIC_NOTES[arrayPosition]; // 
		}
		return notesInScale;
	}
	
	/**
	 * Gets all frequencies of notes within the set major scale
	 * @return 2D Array of GuitarString frequencies
	 */
	public GuitarString[] getFrequenciesFromScale() { // Returns a scales frequencies based on 
		GuitarString[] guitarstrings = new GuitarString[Variables.NO_OF_NOTES_MAJOR_SCALE];
		for(int i = 0; i < Variables.NO_OF_NOTES_MAJOR_SCALE; i ++) {
			double frequency = Variables.MUSIC_NOTE_FREQ[Arrays.asList(Variables.MUSIC_NOTES).indexOf(scaleNotes[i])]; // Gets the frequency based on the note
    		guitarstrings[i] = new GuitarString(frequency);
    	}
		return guitarstrings;
	}
	
	/**
	 * Gets a random note within a scale
	 * @return 2D Array of GuitarString frequencies
	 */
	public String getRandomNote() { // Returns a random note
		int randomInt = new Random().nextInt(scaleNotes.length);
		return scaleNotes[randomInt];
	}
	
	/**
	 * Gets a random note within a scale
	 * @return 2D Array of GuitarString frequencies
	 */
	public int getRandomIndex() {
		Random random = new Random();
		int index = random.nextInt(guitarStrings.length);
		return index;
	}
	
	/**
	 * Gets a note by the index parameter
	 * @Param index - Index of the scale array
	 * @return Note as a sTring
	 */
	public String getNoteByIndex(int index) {
		return scaleNotes[index];
	}
	
	private String convertKeyToABC4JNotation(String key) {
		String convertedKey = "";
		if(key.length() == 2) {
			convertedKey = "^" + key.charAt(0); 
			return convertedKey;
		}
		return key;
	}
	
}
