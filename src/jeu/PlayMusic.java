package jeu;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayMusic{
	
	private long clipTimePosition; /* Position de la musique */
	private File musicPath; /* Chemin de la musique */
	private Clip clip; /* Notre clip musical */
	private boolean estEnRoute; /* Si la musique est en route ou pas */
	private int dejaEteAllume; /* Si la musique a deja ete en route ou pas */
	
	public PlayMusic(String filepath) {
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
		clip.loop(Clip.LOOP_CONTINUOUSLY); /* La musique se relance du debut une fois terminee */
		estEnRoute=true; /* La musique est maintenant en route */
		dejaEteAllume=1; /* La musique a deja ete allumee */
	}
	
	public void musicPause() {
		clipTimePosition = clip.getMicrosecondPosition(); /* On recupere le moment ou on a arrete la musique */
		estEnRoute=false; /* La musique n'est plus en route */
		clip.stop(); /* Stop la musique */
	}
	
	public void musicPlay() {
		clip.setMicrosecondPosition(clipTimePosition); /* On applique le moment ou on avait arrete la musique */
		estEnRoute=true; /* La musique est en route */
		clip.start(); /* Demarre la musique */
	}
	
	public void musicArret() {
		clip.stop(); /* Stop la musique */
		clip.flush(); /* Arrete definitivement le clip */
		estEnRoute=false; /* La musique n'est plus en route */
		dejaEteAllume=0; /* La musique n'a pas deja ete allumee et donc on ne peut pas la redemarree ou elle s'etait probablement arretee */
	}
	
	public boolean getEstEnRoute() {
		return estEnRoute; /* Savoir si la musique est en route ou non */
	}
	
	public int getDejaEteAllume() {
		return dejaEteAllume; /* Savoir si la musique a deja ete ete allumee ou non et donc savoir si on peut la remettre en route */
	}
}