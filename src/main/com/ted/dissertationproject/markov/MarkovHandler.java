package main.com.ted.dissertationproject.markov;

import java.util.ArrayList;
import java.util.List;

import main.com.ted.dissertationproject.music.Variables;

/**
 * <h1>Markov Handler which deals with probability of notes being played</h1>
 * <p>Markov Chain in this instance works by having a higher chance of playing a note that has previously been played after the last note
 * <p>More info: https://explodingart.com/jmusic/jmtutorial/Markov1.html
 */
public class MarkovHandler {
	
	private double probability;
	
	/**
	 * Gets potential next notes
	 * <p>Takes the note being played and checks what notes tend to appear after it the List
	 * @param melody - The List to be checked
	 * @param noteToCheck - The note being checked in the list
	 * @return Returns the notes which have a chance of being played again in an array
	 */
	public List<String> getPotentialNextNotes(List<List<String>> melody, String noteToCheck) {
		List<String> melodyOneD = ArrayHandler.convert2DArrayTo1D(melody);
		List<String> array = new ArrayList<String>();
		for(int noteLoop = 0; noteLoop < melodyOneD.size(); noteLoop++) {
			if(noteToCheck.equalsIgnoreCase(melodyOneD.get(noteLoop)) && melodyOneD.size() != (noteLoop + 1)) {
				array.add(melodyOneD.get(noteLoop + 1));
			}
		}
		return array;
	}
	
	/**
	 * Calculates the chance of each previous note being played
	 * @param notes - List of notes that have a chance of being played
	 * @return Chance of each note being played in an Object array
	 */
	public Object[][] calculateOdds(List<String> notes) {
		Object[][] notesProbability = new Object[notes.size()][2];
		this.probability = Variables.CHANCE_OF_RECURRING_NOTE / notes.size();
		double chance = probability;
		for(int i = 0; i < notes.size(); i++) {
			notesProbability[i][0] = notes.get(i);
			notesProbability[i][1] = chance;
			chance = probability + chance;
		}
		return notesProbability;
	}
	
	/**
	 * Picks a note based on probability
	 * <p>Generates a random number and then picks a note that has been played before at a 70% probability (Variables.CHANCE_OF_RECURRING_NOTE).
	 * The other 30% selects a random note
	 * @param notesAndProbability - The notes and its probability of being hcosne
	 * @return Either a selected note or the "RANDOM".
	 */
	public String pickNote(Object[][] notesAndPorbability) {
		double random = Math.random();
		double calculation = 0;
		for(int i = 0; i < notesAndPorbability.length; i++) {
			double newProbability = (double) notesAndPorbability[i][1];
			if(random >= calculation && random < (calculation + this.probability) && random < Variables.CHANCE_OF_RECURRING_NOTE) {
				return (String) notesAndPorbability[i][0];
			}
			calculation = calculation + this.probability;
		}
		return "RANDOM";
	}
}
