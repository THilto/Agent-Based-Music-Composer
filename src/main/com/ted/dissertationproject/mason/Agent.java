package main.com.ted.dissertationproject.mason;

import java.util.Random;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Double2D;

/**
 * <h1>Agent class which handles when the agent is stepped</h1>
 * <p>Extends Steppable part of sim.engine
 * <p>More Info: https://cs.gmu.edu/~eclab/projects/mason/manual.pdf
 */
public class Agent implements Steppable {
	
	public static Double2D coordinates;
	
	/**
	 * Sets the coordinates for each agent to jump in
	 * @param note - Note being set to the coordinates
	 */
	public static void setCoordinates(String note) {
		Double2D location = new Double2D();
		switch(note)  {
			case "A":
				location = new Double2D(0, 0);
				break;
			case "^A":
				location = new Double2D(10, 0);
				break;
			case "B":
				location = new Double2D(20, 0);
				break;
			case "C":
				location = new Double2D(30, 0);
				break;
			case "^C":
				location = new Double2D(0, 10);
				break;
			case "D":
				location = new Double2D(10, 10);
				break;
			case "^D":
				location = new Double2D(20, 10);
				break;
			case "E":
				location = new Double2D(30, 10);
				break;
			case "F":
				location = new Double2D(0, 20);
				break;
			case "^F":
				location = new Double2D(10, 20);
				break;
			case "G":
				location = new Double2D(20, 20);
				break;
			case "^G":
				location = new Double2D(30, 20);
				break;
		}
		Agent.coordinates = location;
	}
	
	/**
	 * Steps the simulation in a random direction
	 * @param state - Passes the SimState variable
	 */
	public void step(SimState state) {
		
		Agents agents = (Agents) state;
		
		int x = (int) coordinates.getX();
		int y = (int) coordinates.getY();
		
		Random random = new Random();
		
		Double randomX = (double) (random.nextInt((x + 10) - x + 1) + x);
		Double randomY = (double) (random.nextInt((y + 10) - y + 1) + y);
		
		agents.agentSpace.setObjectLocation(this, new Double2D(randomX, randomY));
		
	}
}
