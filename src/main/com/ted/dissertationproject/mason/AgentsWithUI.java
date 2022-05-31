package main.com.ted.dissertationproject.mason;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;

/**
 * <h1>Agent class which handles when the agent is stepped</h1>
 * <p>More Info: https://cs.gmu.edu/~eclab/projects/mason/manual.pdf
 */
public class AgentsWithUI extends GUIState {
	
	/** 2D Display which can display several fields at once, displays use their own JFrames */
	private Display2D display;
	/** JFrame which holds the display inside of */
	private JFrame displayFrame;
	
	/**  Continues Portrayal which allows displaying of a continues grid for the agent*/
	ContinuousPortrayal2D portrayal = new ContinuousPortrayal2D(); 
	
	/**
	 * Constructor for AgentsWithUI
	 * Passing the seed as the time in milliseconds
	 */
	public AgentsWithUI() { 
		super(new Agents(System.currentTimeMillis())); 
	}
	
	/**
	 * Gets the 2D display
	 * @return The 2D display
	 */
	public Display2D getDisplay() {
		return display;
	}
	
	/**
	 * Gets the JFrame
	 * @return The JFrame
	 */
	public JFrame getFrame() {
		return displayFrame;
	}
	
	/**
	 * Starts the simulation and sets up the simulation portrayals
	 * @return The display
	 */
	public void start() {
		super.start();
		setupPortrayals();
	}
	
	/**
	 * Sets up the portrayals in the JFrame and displays them
	 */
	private void setupPortrayals() {
		Agents agents = (Agents) state;
		
		portrayal.setField(agents.agentSpace);
		portrayal.setPortrayalForAll(new OvalPortrayal2D() {
			public void draw(Object object, Graphics2D graphics, DrawInfo2D info)
			{
				Agent agent = (Agent) object;
				paint = Color.WHITE;
				super.draw(object, graphics, info);
			}

		});
		
		java.awt.Image image = new ImageIcon(getClass().getResource("/Background.png")).getImage();
		BufferedImage b = display.getGraphicsConfiguration().createCompatibleImage(image.getWidth(null), image.getHeight(null));
		Graphics g = b.getGraphics();
		g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null),null);
		g.dispose();
		display.setBackdrop(new TexturePaint(b, new Rectangle( 0, 0, image.getWidth(null), image.getHeight(null))));
		
		display.reset();
		
		display.repaint();
	}
	
	/**
	 * Called when the GUI is inilitialy created
	 * Uses Display2D to attach the Grid and Continues Portryals to it
	 * @param c - The console controller
	 */
	public void init(Controller c) {
		super.init(c);
		
		display = new Display2D(400, 300, this); // Creates display size of 600 x 600
		
		display.setClipping(false); // Removes the clipping of the field height and width (500 x 500)
		display.remove(display.header); // Removes the header at the top
		display.setBackdrop(Color.white);
		display.attach(portrayal, "Portrayal"); // Attached the display to the 2D Portrayal
		
		displayFrame = display.createFrame(); // Creates a new frame
		displayFrame.setTitle("Frame Display"); // Sets a title for the new frame
		displayFrame.setVisible(true);
		displayFrame.setResizable(false);
		c.registerFrame(displayFrame); // so the frame appears in the "Display" list
		
	}
	
}
