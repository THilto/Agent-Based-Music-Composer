package main.com.ted.dissertationproject.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Error Dialog UI
 * Screen is dynamic and generates the error label text based on the string passed into it
 */
public class ErrorDialog extends JDialog {

	/**
	 * Create the dialog.
	 * @param errorText - The text to be displayed on the error
	 */
	public ErrorDialog(String errorText) {
		setBounds(100, 100, 286, 119);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 43, 269, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				/** Cancel Button*/
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				cancelButton.setFocusPainted(false);
				buttonPane.add(cancelButton);
			}
		}
		
		/** Error Label*/
		JLabel errorLabel = new JLabel(errorText);
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setBounds(10, 11, 270, 27);
		getContentPane().add(errorLabel);
	}
}
