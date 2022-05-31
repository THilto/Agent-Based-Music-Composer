package main.com.ted.dissertationproject.music.musicalconstructs;

import javax.swing.SwingWorker;

import main.com.ted.dissertationproject.mason.Agent;
import main.com.ted.dissertationproject.mason.AgentsWithUI;
import sim.display.Console;
import sim.util.Double2D;

/**
 * <h1>Controls the launching and stepping of the agent-based system</h1>
 */
public class MasonUI extends SwingWorker <Integer, Integer> {
	
	private AgentsWithUI agentsWithUI;
	private Agent agent;
	private Console console;
	private int tick;
	private Double2D coordinates;
	
	/**
	 * Constructor for class MasonUI
	 */
	public MasonUI() {
		this.agentsWithUI = new AgentsWithUI();
		this.tick = 0;
	}
	
	/**
	 * Returns the local variable agentsWithUI
	 * @return Returns the agent in the AgentsWithUI class
	 */
	public AgentsWithUI getAgent() {
		return this.agentsWithUI;
	}
	
	/**
	 * Returns the local variable console
	 * @return AgentsWithUI returns the console in MASON Console class
	 */
	public Console getConsole() {
		return this.console;
	}

	/**
	 * Creates the UI
	 */
	public void launchUI() {
		this.console = new Console(agentsWithUI);
		console.setVisible(false);
	}
	
	/**
	 * Close the UI
	 */
	public void closeUI() {
		agentsWithUI.getFrame().setVisible(false);
		agentsWithUI.getFrame().dispose();
	}
	
	/**
	 * Steps the simulation once moving the agent
	 */
	public void stepOne(String note) {
		Agent.setCoordinates(note);
		console.pressPlay();
		console.setWhenShouldPause(tick);
		this.tick += 1;
	}
	
	@Override
	protected Integer doInBackground() throws Exception {
		launchUI();
		return null;
	}
}
