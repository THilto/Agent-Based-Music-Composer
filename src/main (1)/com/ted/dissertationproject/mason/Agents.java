package main.com.ted.dissertationproject.mason;

import sim.engine.SimState;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

/**
 * <h1>Agents class which handles starting and stepping of the agent</h1>
 * <p>Extends Simstate to represent the simulation
 * <p>More Info: https://cs.gmu.edu/~eclab/projects/mason/manual.pdf
 */
public class Agents extends SimState {
	
	private Agent agent;
	
	/**Represents the yard where the agent exists*/
	public Continuous2D agentSpace = new Continuous2D(1.0, 40, 30); 
	
	/**
	 * Constructor that contains the grid lines
	 * @param seed - Random seed which influences how the simulation runs
	 */
	public Agents(Long seed) {
		super(seed);
	}
	
	/**
	 * Method from SimState which starts the simulation
	 * Sets the location of the agent to the middle of the space and schedules the start
	 */
	public void start() {
		super.start();
		
		agentSpace.clear();
		
		this.agent = new Agent();
		agentSpace.setObjectLocation(agent, new Double2D(0,0));
		schedule.scheduleRepeating(agent);
	}
}



