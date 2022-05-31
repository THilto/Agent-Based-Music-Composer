package main.com.ted.dissertationproject;

import java.awt.EventQueue;

import main.com.ted.dissertationproject.music.musicalconstructs.Melody;
import main.com.ted.dissertationproject.ui.MainScreen;

public class Main {
	
	private static Melody melody;
	
	public static Melody getMelody() {
		return melody;
	}
	
	public static void setMelody(Melody melody) {
		Main.melody = melody;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
