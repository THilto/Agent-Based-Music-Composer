package main.com.ted.dissertationproject.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
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
 * Evaluation Screen UI
 * This screen allows the user to evaluate which bar they wish to change within the melody
 */
public class EvaluationScreen extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField barToChangetextField;

	/**
	 * Launch the Evaluation Screen.
	 */
	public void launchUI() {
		try {
			EvaluationScreen evaluationScreen = new EvaluationScreen();
			evaluationScreen.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvaluationScreen() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 262, 110);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		/** Bar to change Text Field */
		barToChangetextField = new JTextField();
		barToChangetextField.setBounds(132, 11, 106, 20);
		contentPanel.add(barToChangetextField);
		barToChangetextField.setColumns(10);
		
		/** Bar to change Label*/
		JLabel barsToChangeLabel = new JLabel("Bars to re-evaluate:");
		barsToChangeLabel.setBounds(10, 14, 125, 14);
		contentPanel.add(barsToChangeLabel);
		
		/** Re-evaluate Button*/
		JButton reevaluationButton = new JButton("Re-evaluate");
		reevaluationButton.addActionListener(new ActionListener() { // On button click
			public void actionPerformed(ActionEvent e) {
				
				Melody mainMelody = Main.getMelody();
				
				int bpm = mainMelody.getBPMContainer().getBPM();
				int timeSignatureTop = mainMelody.getTimeSignatureContainer().getTimeSignatureTop();
				int numberOfBars = mainMelody.getTimeSignatureContainer().getNumberOfBars();
				String key = mainMelody.getScaleContainer().getKey().toUpperCase();
				
				UIConstraints ui = new UIConstraints();
				int totalNumberOfBars = mainMelody.getTimeSignatureContainer().getNumberOfBars();
				String barToRevaluate = barToChangetextField.getText();
				
				/** Checks the bars to Re-Evaluate input against the constraints */
				if(!ui.reevaluateConstraint(barToRevaluate, totalNumberOfBars)) {
					ErrorDialog error = new ErrorDialog(Variables.RE_EVALUATE_ERROR_MESSAGE + " " + totalNumberOfBars);
					error.setVisible(true);
				} else {
					Variables.RE_EVALUATE = true;
					
					/** Creates a new melody based with the bar changes */
					Melody melody = new Melody(
							new BPMContainer(bpm), 
							new ScaleContainer(key), 
							new TimeSignatureContainer(timeSignatureTop, numberOfBars), 
							new MusicSheet("Random name", timeSignatureTop, key, bpm),
							new MasonUI(),
							mainMelody.getMelodyList());
					
					int intBarToRevaluate = Integer.parseInt(barToRevaluate) - 1;
					mainMelody.getMusicSheet().disposeMusicSheet();
					mainMelody.getMelodyList().get(intBarToRevaluate).clear();
					mainMelody.getMelodyList().get(intBarToRevaluate).add(null);
					
					disposeEvaluationScreen();
					
					Main.setMelody(melody);
					Main.getMelody().execute();
				}
			}
		});
		
		reevaluationButton.setBounds(132, 39, 107, 23);
		reevaluationButton.setFocusable(false);
		contentPanel.add(reevaluationButton);
		
		/** Reset Button */
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				disposeEvaluationScreen();
				Main.getMelody().getMusicSheet().disposeMusicSheet();
				
				MainScreen frame = new MainScreen();
				frame.setVisible(true);
			}
		});
		resetButton.setFocusable(false);
		resetButton.setBounds(10, 39, 70, 23);
		contentPanel.add(resetButton);
	}
	
	/**
	 * Disposes of the Evaluation Screen appropriately
	 */
	private void disposeEvaluationScreen() {
		setVisible(false);
		dispose();
	}
}
