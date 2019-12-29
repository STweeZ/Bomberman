package jeu;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlaySound{
	
	private File musicPath; /* Chemin de la musique */
	private Clip clip; /* Notre clip musical */
	
	public PlaySound(String filepath) {
		try {
			musicPath = new File(filepath);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	} /* Constructeur qui permet d'initialiser la musique avec le chemin fourni prealablement, si chemin incorrect -> exception */
	
	public void musicLancement() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
		clip = AudioSystem.getClip();
		clip.open(audioInput);
		
		/* Permet d'initialiser la musique et l'ouvre */
		
		clip.start(); /* Demarre la musique */
	}
}