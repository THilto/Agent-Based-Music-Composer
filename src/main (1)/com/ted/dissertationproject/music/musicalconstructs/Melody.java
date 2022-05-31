package main.com.ted.dissertationproject.music.musicalconstructs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import main.com.ted.dissertationproject.Main;
import main.com.ted.dissertationproject.markov.ArrayHandler;
import main.com.ted.dissertationproject.markov.MarkovHandler;
import main.com.ted.dissertationproject.music.Variables;
import main.com.ted.dissertationproject.music.abc4j.MusicSheet;
import main.com.ted.dissertationproject.music.princeton.GuitarLoop;
import main.com.ted.dissertationproject.music.princeton.GuitarString;
import main.com.ted.dissertationproject.ui.EvaluationScreen;

/**
 * <h1>Melody class</h1>
 * <p>Contains all the musical constraints, displaying the agent-based simulation and music sheet in one main class
 * <p>SwingWorker is required to allow long running tasks in a thread while providing UI updates in the JFrame
 * <p>More Info: https://docs.oracle.com/javase/tutorial/uiswing/concurrency/worker.html
 */
public class Melody extends SwingWorker <Void, Void> {
	
	private BPMContainer bpmContainer;
	private ScaleContainer scaleContainer;
	private TimeSignatureContainer timeSignatureContainer;
	private MusicSheet musicSheet;
	private MasonUI masonUI;
	private EvaluationScreen evaluationScreen;
	private GuitarLoop guitarLoop;
	
	/** List inside a list which containing each note of the melody, each new list representing a bar */
	private List<List<String>> melodyList;
	private GuitarString[] guitarStrings;
	private int[] bpmIntervals;

	/**
	 * Constructor for holding melodic constraints
	 * @param bpm - BPM Container Class 
	 * @param scale - Scale Container Class 
	 * @param timeSignature - Time Signature Container Class 
	 * @param musicSheet - Sheet Music Class 
	 * @param masonUI - MASON UI Class 
	 */
	public Melody(BPMContainer bpm, ScaleContainer scale, TimeSignatureContainer timeSignature, MusicSheet musicSheet, MasonUI masonUI) { // Creates a new Melody object with various the various constraints
		this.bpmContainer = bpm;
		this.scaleContainer = scale;
		this.timeSignatureContainer = timeSignature;
		this.musicSheet = musicSheet;
		this.masonUI = masonUI;
		
		this.evaluationScreen = new EvaluationScreen();
		this.melodyList = new ArrayList<List<String>>();
		this.guitarStrings = scale.getGuitarStrings();
		this.guitarLoop = new GuitarLoop(scale.getGuitarStrings());
		this.bpmIntervals = bpm.getTimings();
	}
	
	/**
	 * Constructor for holding melodic constraints
	 * @param bpm - BPM Container Class 
	 * @param scale - Scale Container Class 
	 * @param timeSignature - Time Signature Container Class 
	 * @param musicSheet - Sheet Music Class 
	 * @param masonUI - MASON UI Class 
	 * @param melodyList - 2D List containing the melody
	 */
	public Melody(BPMContainer bpm, ScaleContainer scale, TimeSignatureContainer timeSignature, MusicSheet musicSheet, MasonUI masonUI, List<List<String>> melodyList) { // Creates a new Melody object with various the various constraints
		this.bpmContainer = bpm;
		this.scaleContainer = scale;
		this.timeSignatureContainer = timeSignature;
		this.musicSheet = musicSheet;
		this.masonUI = masonUI;
		this.evaluationScreen = new EvaluationScreen();
		
		this.melodyList = melodyList;
		this.guitarStrings = scale.getGuitarStrings();
		this.guitarLoop = new GuitarLoop(scale.getGuitarStrings());
		this.bpmIntervals = bpm.getTimings();
	}
	
	/**
	 * Gets the BPM container
	 * @return An instance of the BPM Container Class
	 */
	public BPMContainer getBPMContainer() { // Returns the BPM Container
		return bpmContainer;
	}
	
	/**
	 * Gets the Scale Container Class
	 * @return An instance of the Scale Container Class
	 */
	public ScaleContainer getScaleContainer() { // Returns the Scale Container
		return scaleContainer;
	}
	
	/**
	 * Gets the Time Signature Container
	 * @return An instance of the Time Signature Container Class
	 */
	public TimeSignatureContainer getTimeSignatureContainer() { // Returns the Time Signature Container
		return timeSignatureContainer;
	}
	
	/**
	 * Gets the Sheet Music
	 * @return The Sheet Music Container Class
	 */
	public MusicSheet getMusicSheet() { // Returns the Music Sheet container
		return musicSheet;
	}
	
	/**
	 * Gets the Evaluation Screen
	 * @return The Evaluation Screen Class
	 */
	public EvaluationScreen getEvaluationScreen() {
		return evaluationScreen;
	}
	
	/**
	 * Gets the Melody Bars and Notes as a 2D Array 
	 * @return The melodyList
	 */
	public List<List<String>> getMelodyList() {
		return melodyList;
	}
	
	/**
	 * Sets the Melody List
	 * @param melodyList - The Melody List 
	 */
	public void setMelodyList(List<List<String>> melodyList) {
		this.melodyList = melodyList;
	}
	
	/**
	 * Plays melody
	 * <p>Runs through the number of bars specified and plays each note using StdAudio found here: https://introcs.cs.princeton.edu/java/stdlib/StdAudio.java.html
	 */
	public void Play() {
		
		generateUI();
		sleepForSecond();
		
		double barTimeRemaining = timeSignatureContainer.getBarLength(bpmContainer.getBPM()); // Length of a bar in milliseconds
		
		for(int barNumber = 0; barNumber < 2; barNumber++) {
			
			this.melodyList.add(new ArrayList<String>());
		    
		    generateInputBar(barTimeRemaining, barNumber);
		}
		
		MarkovHandler markov = new MarkovHandler();
		
		List<List<String>> inputList = melodyList;
		
		for(int barNumber = 2; barNumber < timeSignatureContainer.getNumberOfBars(); barNumber++) {
			generateOutputBar(inputList, barTimeRemaining, barNumber);
		}
		
		
		getEvaluationScreen().launchUI();
		masonUI.closeUI();
		musicSheet.convertToABC4JNotation(melodyList);
		musicSheet.displayMusicSheet();
		Variables.FINISHED_LOOP = true;
	}
	
	/**
	 * Plays melody by 2D List
	 * Ran during the evaluation process when a melody is already constructed and reconstructs to whichever bar is set to null
	 */
	public void reevaluateMelody(List<List<String>> melody) {

		String[] scaleNotes = scaleContainer.getScaleNotes();
		GuitarString[] guitarString = scaleContainer.getGuitarStrings();
		
		generateUI();
		musicSheet.initialiseMusicSheet();
		sleepForSecond();
		
		/** Loop for each bar */
		for(int barNumber = 0; barNumber < melody.size(); barNumber++) {
			/** If the bar is not null play like normal OR generate new bar*/
			if(melody.get(barNumber).get(0) != null) {
				/** Loop for each note in the bar */
				for(int j = 0; j < melody.get(barNumber).size(); j++) { // Note loop
					
					
					String noteAndInterval = melody.get(barNumber).get(j);
					/** Note without numbers as ABC4J displays note length as a number for each bar */
					String note = noteAndInterval.replaceAll(Variables.REMOVE_NUMBER, ""); // Note
					
					/** Index of the note in the scale, used to get the guitar string */
					int index = Arrays.asList(scaleNotes).indexOf(note);
					GuitarString string = guitarString[index];
					
					int[] bpmIntervals = bpmContainer.getTimings(); // Different interval times
					int intervalTime = 0;
					
					masonUI.stepOne(note);
					string.pluck();
					
					/** If statement to find the corresponding note interval */
					if(noteAndInterval.matches(Variables.CONTAINS_NUMBER)) { // If contains number
						String interval = noteAndInterval.replaceAll(Variables.EXTRACT_NUMBER, ""); // Note timing
						
						if (interval.equals(Variables.WHOLE_NOTE)) {
							intervalTime = bpmIntervals[3];
						} else if (interval.equals(Variables.HALF_NOTE)) {
							intervalTime = bpmIntervals[2];
						} else if (interval.equals(Variables.QUARTER_NOTE)) {
							intervalTime = bpmIntervals[1];
						}
					} else {
						intervalTime = bpmIntervals[0];
					}
					sleepInterval(intervalTime);
				}
			} else {
				/** Length of a bar in milliseconds */
				double barTimeRemaining = timeSignatureContainer.getBarLength(bpmContainer.getBPM());
				
				List<List<String>> inputList = new ArrayList<List<String>>();
				inputList.add(melodyList.get(0));
				inputList.add(melodyList.get(1));
				generateOutputReevaluationBar(inputList, barTimeRemaining, barNumber);
				
				this.melodyList.get(barNumber).remove(0);
			}
		}
		
		getEvaluationScreen().launchUI();
		masonUI.closeUI();
		musicSheet.convertToABC4JNotation(melodyList);
		musicSheet.displayMusicSheet();
		Variables.FINISHED_LOOP = true;
	}
	
	/**
	 * Generates a and plays a prime bar
	 * <p>A Prime bar is a randomly generated bar which will be used to develop the rest of the melody
	 * @param barTimeRemaining - The time remaining in the bar in milliseconds
	 * @param barNumber - The bar which the melody is currently generating
	 */
	private void generateInputBar(double barTimeRemaining, int barNumber) {
		while(barTimeRemaining != 0) {
	    	
			int index = scaleContainer.getRandomIndex();	
			
			GuitarString string = guitarStrings[index]; // The guitar string note
			
			String note = scaleContainer.getNoteByIndex(index); // The Note
	    	int randomNoteInterval = bpmContainer.getRandomQuaterOrEighthNote(); // The Note
	    			
	    	/** If there's time to play the note */
	    	if (barTimeRemaining >= randomNoteInterval) {
	    		/**  Remove the note length from the bar length */
	    		barTimeRemaining -= randomNoteInterval;
	    		
				masonUI.stepOne(note);
				string.pluck();
				
				/** Checks to see the note being and assigns it to the Melody List to be displayed by ABC4J */
				if(randomNoteInterval == bpmIntervals[1]) { // Quarter note
					this.melodyList.get(barNumber).add(note + 2);
				} else if(randomNoteInterval == bpmIntervals[0]) { // Eighth note
					this.melodyList.get(barNumber).add(note);
				}
				
				sleepInterval(randomNoteInterval);
			}
	    }
	}
	
	/**
	 * Generates a and plays a prime bar
	 * <p>A Prime bar is a randomly generated bar which will be used to develop the rest of the melody
	 * @param barTimeRemaining - The time remaining in the bar in milliseconds
	 * @param barNumber - The bar which the melody is currently generating
	 */
	private void generateOutputBar(List<List<String>> inputMelody, double barTimeRemaining, int barNumber) {
		MarkovHandler markov = new MarkovHandler();
		
		String[] scaleNotes = scaleContainer.getScaleNotes();
		GuitarString[] guitarString = scaleContainer.getGuitarStrings();
		
		String lastNote = melodyList.get(melodyList.size() - 1).get(melodyList.get(melodyList.size() - 1).size() - 1).replaceAll(Variables.REMOVE_NUMBER, "");
		
		List<List<String>> inputMelodyArrayNoNumbers = ArrayHandler.removeNumbersFromList(inputMelody);
		
		if(!Variables.RE_EVALUATE) {
			this.melodyList.add(new ArrayList<String>());
		}
		
		while(barTimeRemaining != 0) { // Bar Loop
			
			// Next notes that my be used for the melody
			List<String> potentialNextNotes = markov.getPotentialNextNotes(inputMelodyArrayNoNumbers, lastNote);
			
			// Calculate odds
			Object[][] odds = markov.calculateOdds(potentialNextNotes);
			
			String pickedNoted = markov.pickNote(odds);
			
			String note = pickedNoted;
			
			int randomNoteInterval = bpmContainer.getRandomInterval(); // The Note
			
			// Pick Interval
			if (barTimeRemaining >= randomNoteInterval) {
				
	    		barTimeRemaining -= randomNoteInterval;
	    		
	    		if(note.equals("RANDOM")) {
	    			note = scaleContainer.getRandomNote(); // The Note
	    		}
	    		
	    		int index = Arrays.asList(scaleNotes).indexOf(note);
				GuitarString string = guitarString[index];
				
				masonUI.stepOne(note);
				string.pluck();
	    		
	    		if(randomNoteInterval == bpmIntervals[3]) {
					this.melodyList.get(barNumber).add(note + 8);
				} else if(randomNoteInterval == bpmIntervals[2]) { // Half note
					this.melodyList.get(barNumber).add(note + 4);
				} else if(randomNoteInterval == bpmIntervals[1]) { // Quarter note
					this.melodyList.get(barNumber).add(note + 2);
				} else if(randomNoteInterval == bpmIntervals[0]) { // Eighth note
					this.melodyList.get(barNumber).add(note);
				}
	    		
	    		lastNote = note;
	    		
				sleepInterval(randomNoteInterval);
			}
		}
	}
	
	private void generateOutputReevaluationBar(List<List<String>> inputMelody, double barTimeRemaining, int barNumber) {
		MarkovHandler markov = new MarkovHandler();
		
		String[] scaleNotes = scaleContainer.getScaleNotes();
		GuitarString[] guitarString = scaleContainer.getGuitarStrings();
		
		String lastNote = melodyList.get(barNumber - 1).get(melodyList.get(barNumber - 1).size() - 1).replaceAll(Variables.REMOVE_NUMBER, "");
		
		List<List<String>> inputMelodyArrayNoNumbers = ArrayHandler.removeNumbersFromList(inputMelody);
		
		while(barTimeRemaining != 0) { // Bar Loop
			
			// Next notes that my be used for the melody
			List<String> potentialNextNotes = markov.getPotentialNextNotes(inputMelodyArrayNoNumbers, lastNote);
			
			// Calculate odds
			Object[][] odds = markov.calculateOdds(potentialNextNotes);
			
			String pickedNoted = markov.pickNote(odds);
			
			String note = pickedNoted;
			
			int randomNoteInterval = bpmContainer.getRandomInterval(); // The Note
			
			// Pick Interval
			if (barTimeRemaining >= randomNoteInterval) {
				
	    		barTimeRemaining -= randomNoteInterval;
	    		
	    		if(note.equals("RANDOM")) {
	    			note = scaleContainer.getRandomNote(); // The Note
	    		}
	    		
	    		int index = Arrays.asList(scaleNotes).indexOf(note);
				GuitarString string = guitarString[index];
				
				masonUI.stepOne(note);
				string.pluck();
	    		
	    		if(randomNoteInterval == bpmIntervals[3]) {
					this.melodyList.get(barNumber).add(note + 8);
				} else if(randomNoteInterval == bpmIntervals[2]) { // Half note
					this.melodyList.get(barNumber).add(note + 4);
				} else if(randomNoteInterval == bpmIntervals[1]) { // Quarter note
					this.melodyList.get(barNumber).add(note + 2);
				} else if(randomNoteInterval == bpmIntervals[0]) { // Eighth note
					this.melodyList.get(barNumber).add(note);
				}
	    		
	    		lastNote = note;
	    		
				sleepInterval(randomNoteInterval);
			}
		}
	}
	
	/** 
	 * Generates the Agent Based UI and the Guitar Loop which listens for string plucks
	 */
	private void generateUI() {
		masonUI.execute();
		guitarLoop.execute();
	}
	
	/** 
	 * Sleeps the thread for an interval which corresponds to how long a beat lasts
	 * @param interval - The interval time the thread should sleep for in milliseconds
	 */
	private void sleepInterval(int interval) {
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Sleeps for a second
	 */
	private void sleepForSecond() {
		try {
			TimeUnit.SECONDS.sleep(1); // Sleep for a second to allow GuitarLoop to run
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	/** 
	 * Swingworker used to play the melody on a different thread
	 * A check is done to see if the melody being played is for the re-evaluation process
	 */
	@Override
	protected Void doInBackground() throws Exception {
		if(Variables.RE_EVALUATE == true) {
			reevaluateMelody(Main.getMelody().getMelodyList());
		} else {
			Play();
		}
		return null;
	}
}
