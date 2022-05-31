package test.com.ted.dissertationproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.com.ted.dissertationproject.music.musicalconstructs.ScaleContainer;

public class ScaleContainerTest {
	
	private ScaleContainer scaleContainer;

	/**
	 * Ran at the initial setup
	 */
    @BeforeEach                                         
    public void setUp() throws Exception {
    	scaleContainer = new ScaleContainer("C");
    }

    /**
     * Checks to see if the key = C created  in the setUp() method
     */
    @Test                                               
    @DisplayName("Key")   
    public void getKey() {
    	assertEquals("C", scaleContainer.getKey(),  "Key Test");
    }
    
    @Test                                               
    @DisplayName("Notes in scale")   
    public void getScaleNotes() {
    	String[] scaleNotes = scaleContainer.getScaleNotes();
    	assertEquals("C", scaleNotes[0],  "Note: C");
    	assertEquals("D", scaleNotes[1],  "Note: D");
    	assertEquals("E", scaleNotes[2],  "Note: E");
    	assertEquals("F", scaleNotes[3],  "Note: F");
    	assertEquals("G", scaleNotes[4],  "Note: G");
    	assertEquals("A", scaleNotes[5],  "Note: A");
    	assertEquals("B", scaleNotes[6],  "Note: B");
    }
    
    @Test                                               
    @DisplayName("Index note")   
    public void getIndex() {
    	assertEquals("F", scaleContainer.getNoteByIndex(3),  "Note: F");
    }
    
}
