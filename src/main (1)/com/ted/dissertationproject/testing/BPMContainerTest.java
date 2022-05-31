package com.ted.dissertationproject.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ted.dissertationproject.music.musicalconstructs.BPMContainer;

class BPMContainerTest {

	private BPMContainer bpmContainer;

    @BeforeEach                                         
    public void setUp() throws Exception {
    	bpmContainer = new BPMContainer(120);
    }
  

    @Test                                               
    @DisplayName("Note Step Timings")   
    public void noteSteps() {
    	int[] timings = bpmContainer.getTimings();
    	assertEquals(250, timings[0],  "Eighth note test");
    	assertEquals(500, timings[1],  "Quater note test");  
    	assertEquals(1000, timings[2],  "Half note test");  
    	assertEquals(2000, timings[3],  "Whole note test");  
    }
}
