package main.com.ted.dissertationproject.markov;

import java.util.ArrayList;
import java.util.List;

import main.com.ted.dissertationproject.music.Variables;

/**
 * <h1>Array Handler class handles array data required for conversion</h1>
 * <p>Necessary to for the conversion of data to be displayed by ABC4J and the Mavic Chain
 */
public class ArrayHandler {
	
	
	/**
	 * Removed numbers from a 2D List
	 * <p>Loops through the old array, removes the number from the array and adds it to the new array which is returned
	 * Used to find corresponding next notes in the mavic chain
	 * @param array - The array with numbers
	 * @return The new array without numbers
	 */
	public static List<List<String>> removeNumbersFromList(List<List<String>> array) {
		List<List<String>> newArray = new ArrayList<List<String>>();
		
		for(int bar = 0; bar < array.size(); bar++) {
			newArray.add(new ArrayList<String>());
			for(int note = 0; note < array.get(bar).size(); note++) {
				String noNumber = array.get(bar).get(note).replaceAll(Variables.REMOVE_NUMBER, "");
				newArray.get(bar).add(noNumber);
			}
		}
		return newArray;
	}
	
	/**
	 * Converts a 2D List to a 1D List
	 * @param array - 2D List being converted
	 * @return 1D List
	 */
	public static List<String> convert2DArrayTo1D(List<List<String>> array) {
		List<String> newArray = new ArrayList<String>(getArrayTotalSize(array));
		for(int bar = 0; bar < array.size(); bar++) {
			for(int note = 0; note < array.get(bar).size(); note++) {
				newArray.add(array.get(bar).get(note).replaceAll(Variables.REMOVE_NUMBER, ""));
			}
		}
		return newArray;
	}
	
	/**
	 * Gets the total number of elements in a 2D List
	 * @param array - List to be looked into
	 * @return The total number of elements in a 2D List
	 */
	public static int getArrayTotalSize(List<List<String>> array) {
		int i = 0;
		for(int bar = 0; bar < array.size(); bar++) {
			for(int note = 0; note < array.get(bar).size(); note++) {
				i++;
			}
		}
		return i;
	}
}
