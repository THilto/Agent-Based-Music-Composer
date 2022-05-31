package main.com.ted.dissertationproject.ui;

import main.com.ted.dissertationproject.music.Variables;

public class UIConstraints {
	
	/**
	 * Constraint for the BPM input box
	 * <p>Checks to see if the BPM inputed is an integer and between 20 and 500
	 * @param bpm - The BPM being checked
	 * @return True or False depending if the BPM meets the criteria
	 */
	public boolean bpmConstraint(String bpm) {
		if(isInteger(bpm)) {
			int intBpm = Integer.parseInt(bpm);
			if(intBpm >= Variables.MINIMUM_BPM && intBpm <= Variables.MAXIMUM_BPM) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Constraint for the key input box
	 * <p>Checks to see if the key inputed is a scale key and between the characters A and G
	 * @param key - The Key being checked
	 * @return True or False depending if the Key meets the criteria
	 */
	public boolean keyConstraint(String key) {
		if(key.length() == 1 || key.length() == 2) {
			char firstCharacter = Character.toLowerCase(key.charAt(0));
			if((firstCharacter >= 'a' && firstCharacter <= 'g')) {
				if(key.length() == 2) {
					Character secondCharacter = key.charAt(1);
					if(secondCharacter == '#') {
						return true;
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Constraint for the number of bars input box
	 * Checks to see if the number of bars inputed is an integer and between 1 and 16
	 * @param bars - The number of bars being checked
	 * @return True or False depending if the number of bars meets the criteria
	 */
	public boolean noBarsConstaint(String bars) {
		if(isInteger(bars)) {
			int intBars = Integer.parseInt(bars);
			if(intBars >= Variables.MINIMUM_BARS && intBars <= Variables.MAXIMUM_BARS) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Constraint for the re-evaluation input box
	 * Checks to see if the re-evaluated bar input is between 1 and the number of bars in the melody
	 * @param barNumber - The bar being re-evaluated
	 * @param totalBarNumber - Total number of bars in the melody
	 * @return True or False depending if the re-evaluated bar meets the criteria
	 */
	public boolean reevaluateConstraint(String barNumber, int totalBarNumber) {
		if(isInteger(barNumber)) {
			int intBarNumber = Integer.parseInt(barNumber);
			if(intBarNumber >= 3 && intBarNumber <= totalBarNumber) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if a String is an integer
	 * <p><b1>Note: </b1> Taken from: https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java
	 * @param string - The string being checked
	 * @return True or False depending if the re-evaluated bar meets the criteria
	 */
	private boolean isInteger(String string) {
		if(string == null) {
	        return false;
	    }
	    int length = string.length();
	    if(length == 0) {
	        return false;
	    }
	    int i = 0;
	    if(string.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for(; i < length; i++) {
	        char c = string.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
}
