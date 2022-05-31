package com.ted.dissertationproject.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ted.dissertationproject.music.musicalconstructs.TimeSignatureContainer;

class TimeSignatureContainerTest {

	private TimeSignatureContainer timeSignatureContainer;
	private int bpm = 120;

    @BeforeEach                                         
    public void setUp() throws Exception {
    	timeSignatureContainer = new TimeSignatureContainer(3, 8);
    }

    @Test                                               
    @DisplayName("Top part time signature")   
    public void timeSignatureTop() {
    	timeSignatureContainer.getTimeSignatureTop();
    	assertEquals(3, timeSignatureContainer.getTimeSignatureTop(), "Top Time Signature: 3");
    }
    
    @Test                                               
    @DisplayName("Number of bars")   
    public void numberOfBars() {
    	timeSignatureContainer.getNumberOfBars();
    	assertEquals(8, timeSignatureContainer.getNumberOfBars(), "Number of bars: 8");
    }
    
    @Test                                               
    @DisplayName("Bar Length in ms")   
    public void getBarLength() {
    	timeSignatureContainer.getNumberOfBars();
    	assertEquals(1500, timeSignatureContainer.getBarLength(120), "Bar length: ");
    }
    

}
