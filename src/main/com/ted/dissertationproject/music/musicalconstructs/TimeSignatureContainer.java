package main.com.ted.dissertationproject.music.musicalconstructs;

import main.com.ted.dissertationproject.music.Variables;

public class TimeSignatureContainer {
	
	private int timeSignatureTop;
	private int numberOfBars; 
	
	/**
	 * Constructor for class TimeSignatureContainer
	 * @param top - The top value of the time signature
	 * @param barNumber - Number of bars in the melody
	 */
	public TimeSignatureContainer(int top, int barNumber) {
		this.timeSignatureTop = top;
		this.numberOfBars = barNumber;
	}
	
	public int getTimeSignatureTop() {
		return timeSignatureTop;
	}
	
	/**
	 * Gets the number of bars in a melody
	 * @return The number of bars in a melody
	 */
	public int getNumberOfBars() {
		return numberOfBars;
	}
	
	/**
	 * Gets the length of a bar in milliseconds based on the bpm
	 * @param bpm - The beats per minute of the melody
	 * @return A single bar length in milliseconds
	 */
	public double getBarLength(int bpm) {
		return (Variables.MILL_SECONDS_PER_MIN / bpm) * timeSignatureTop;
	}
}
