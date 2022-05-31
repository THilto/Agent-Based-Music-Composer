package main.com.ted.dissertationproject.music.princeton;

import java.util.Random;

import main.com.ted.dissertationproject.music.Variables;

/**
 * Guitar String class which makes use of a RingBuffer to play notes
 * Code taken and slightly changed from: https://github.com/Loquats/Guitar/blob/master/src/GuitarString.java
 */
public class GuitarString {
	private RingBuffer g;
	private int tickTock;
	
	/**
	 * Creates a new GuitarString in a ring buffer by deviding sample rate and requency
	 * @param frequency - The Guitar String frequency wishing to be played
	 */
	public GuitarString (double frequency) {
		g = new RingBuffer(Variables.SAMPLE_RATE /(int)frequency);
	}
	
	/**
	 * Plucks string by removing each element then replacing it with random double variables (white noise)
	 */
	public void pluck() {
		for(int i=0; i < g.size(); i++)
			g.dequeue();
		Random rand = new Random();
		for(int i=0; i < g.size(); i++)
			g.enqueue(rand.nextDouble() - 0.5);
	}
	
	/**
	 * Advances the melody to the next step
	 */
	public void tic() {
		g.dequeue();
		g.enqueue((g.peek(0) + g.peek(1))* 0.5 * 0.994);
		tickTock += 1;
	}
	
	/**
	 * Returns the current sample
	 */
	public double sample() {
		return g.peek(0);
	}
	
	public int time() {
		return tickTock;
	}
	
}