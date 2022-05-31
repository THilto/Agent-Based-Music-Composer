package main.com.ted.dissertationproject.music;

/**
 * Class containing all variables for improved readability
 */
public final class Variables {
	
	public final static int MILL_SECONDS_PER_MIN = 60000; //60 seconds
	public final static int SAMPLE_RATE = 44100;
	public final static int NO_OF_DIFFERENT_NOTES = 4;
	public final static int NO_OF_NOTES_MAJOR_SCALE = 7;
	//public final static String[] MUSIC_NOTES = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"}; // All notes in a scale
	public final static String[] MUSIC_NOTES = {"A", "^A", "B", "C", "^C", "D", "^D", "E", "F", "^F", "G", "^G"}; // All notes in a scale
	public final static Double[] MUSIC_NOTE_FREQ = {440.0, 446.16,  493.88, 523.25, 554.37, 587.33, 622.25, 659.25, 698.46, 739.99, 783.99, 830.61}; // Note frequencies start with A end with G#
	public final static int[] MAJOR_SCALE_STEPS = {2, 2, 1, 2, 2, 2, 1}; // Steps between each note in a major scale
	public final static double CHANCE_OF_RECURRING_NOTE = 0.7; // 70%
	
	public final static String REMOVE_NUMBER = "\\d";
	public final static String EXTRACT_NUMBER = "\\D+";
	public final static String CONTAINS_NUMBER = ".*\\d.*";
	
	public final static String BPM_ERROR_MESSAGE = "BPM needs to be in the range of 20-500";
	public final static String KEY_ERROR_MESSAGE = "You need to set a proper scale e.g. G#";
	public final static String NO_OF_BARS_ERROR_MESSAGE = "Number of bars needs to be between 4 and 16";
	public final static String RE_EVALUATE_ERROR_MESSAGE = "Evaluation bar needs to be between 1 and";
	
	public final static int MINIMUM_BPM = 20;
	public final static int MAXIMUM_BPM = 500;
	public final static int MINIMUM_BARS = 4;
	public final static int MAXIMUM_BARS = 16;
	
	public final static String WHOLE_NOTE = "8";
	public final static String HALF_NOTE = "4";
	public final static String QUARTER_NOTE = "2";
	
	public static boolean FINISHED_LOOP = false;
	public static boolean RE_EVALUATE = false;
	

}
