package main.com.ted.dissertationproject.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.com.ted.dissertationproject.Main;
import main.com.ted.dissertationproject.music.Variables;
import main.com.ted.dissertationproject.music.abc4j.MusicSheet;
import main.com.ted.dissertationproject.music.musicalconstructs.BPMContainer;
import main.com.ted.dissertationproject.music.musicalconstructs.MasonUI;
import main.com.ted.dissertationproject.music.musicalconstructs.Melody;
import main.com.ted.dissertationproject.music.musicalconstructs.ScaleContainer;
import main.com.ted.dissertationproject.music.musicalconstructs.TimeSignatureContainer;

/**
 * Main Screen UI
 * This screen allows the user to input the specific time signature, bpm, scale and number of bars they wish to use
 */
public class MainScreen extends JFrame {

	private JPanel contentPane;
	private JTextField noOfBarstextField;
	private JButton playButton;
	private JTextField scaletextField;

	public MainScreen() {
		setTitle("Home Screen");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/** Time Signature label */
		JLabel timesignatureLabel = new JLabel("Time Signature:");
		timesignatureLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		timesignatureLabel.setBounds(19, 14, 87, 14);
		contentPane.add(timesignatureLabel);
		
		/** BPM label */
		JLabel bpmLabel = new JLabel("BPM:");
		bpmLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		bpmLabel.setBounds(78, 36, 28, 14);
		contentPane.add(bpmLabel);
		
		/** Scale label */
		JLabel scaleLabel = new JLabel("Scale:");
		scaleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		scaleLabel.setBounds(73, 58, 34, 14);
		contentPane.add(scaleLabel);
		
		/** Number of Bars label */
		JLabel noOfBarsLabel = new JLabel("Number of Bars:");
		noOfBarsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		noOfBarsLabel.setBounds(17, 80, 90, 14);
		contentPane.add(noOfBarsLabel);
		
		/** Time Signature Combination Box */
		String[] timeSignatureValues = {"2/4", "3/4", "4/4"};
		JComboBox<String> timeSignaturecomboBox = new JComboBox(timeSignatureValues);
		timeSignaturecomboBox.setSelectedItem(timeSignatureValues[2]); // Default value 4/4
		timeSignaturecomboBox.setBounds(119, 11, 120, 20);
		contentPane.add(timeSignaturecomboBox);
		
		/** BPM Text Field */
		JTextField BPMtextField = new JTextField();
		BPMtextField.setText("120");
		BPMtextField.setBounds(119, 33, 120, 20);
		contentPane.add(BPMtextField);
		BPMtextField.setColumns(10);
		
		/** Scale Text Field */
		scaletextField = new JTextField();
		scaletextField.setText("C");
		scaletextField.setColumns(10);
		scaletextField.setBounds(119, 55, 120, 20);
		contentPane.add(scaletextField);
		
		/** Number of bars Text Field */
		noOfBarstextField = new JTextField();
		noOfBarstextField.setText("4");
		noOfBarstextField.setColumns(10);
		noOfBarstextField.setBounds(119, 77, 120, 20);
		contentPane.add(noOfBarstextField);
		
		/** Play Button */
		playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() { // On button click
			public void actionPerformed(ActionEvent e) {
				
				String bpm = BPMtextField.getText();
				String key = scaletextField.getText().toUpperCase();
				/** Gets the first character of the Time Signature */
				int timeSignatureTop = Integer.parseInt(timeSignaturecomboBox.getSelectedItem().toString().substring(0, 1));
				String numberOfBars = noOfBarstextField.getText();
				
				UIConstraints ui = new UIConstraints();
				
				/** Constraints are checked on each of the inputs */
				if(!ui.bpmConstraint(bpm)) {
					ErrorDialog error = new ErrorDialog(Variables.BPM_ERROR_MESSAGE);
					error.setVisible(true);
				} else if(!ui.keyConstraint(key)) {
					ErrorDialog error = new ErrorDialog(Variables.KEY_ERROR_MESSAGE);
					error.setVisible(true);
				} else if(!ui.noBarsConstaint(numberOfBars)) {
					ErrorDialog error = new ErrorDialog(Variables.NO_OF_BARS_ERROR_MESSAGE);
					error.setVisible(true);
				} else {
					int bpmInt = Integer.parseInt(bpm);
					int noOfBarsInt = Integer.parseInt(numberOfBars);
					Main.setMelody(new Melody(
							new BPMContainer(bpmInt), 
							new ScaleContainer(key), 
							new TimeSignatureContainer(timeSignatureTop, noOfBarsInt), 
							new MusicSheet("Random ", timeSignatureTop, key, bpmInt),
							new MasonUI()));
					
					Main.getMelody().execute();
					
					disposeMainScreen();
				}
			}
		});
		playButton.setBounds(10, 108, 229, 23);
		playButton.setFocusPainted(false); // Removes weird outline around button text
		contentPane.add(playButton);
	}
	
	/**
	 * Disposes of the Main Screen appropriately
	 */
	private void disposeMainScreen()  {
		setVisible(false);
		dispose();
	}
}
