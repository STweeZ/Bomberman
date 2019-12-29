package jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int largeur = 1360;
	private static final int hauteur  = 750;

	/* Largeur et Hauteur du JPanel menu */

	private Image background;

	private JButton jouer;
	private JButton options;
	private JButton quitter;

	/* 3 Objets de type JButton pour la navigation dans le menu  */

	private final Font fontEntered = new Font(Font.DIALOG, Font.ITALIC, 50); /* Font qu'on va appliquer pour la police du menu */

	public Menu() {

		this.setLayout(null); /* Pas de layout afin de pouvoir positionner convenablement les 3 differents boutons */

		jouer=new JButton("Jouer");
		options=new JButton("Options");
		quitter=new JButton("Quitter");

		/* Initialisation des 3 boutons avec leur appellation */

		jouer.setBounds(250,350,250,100);
		options.setBounds(550,350,250,100);
		quitter.setBounds(850,350,250,100);

		/* On definit leur emplacement et leur taille */

		jouer.setOpaque(false);
		options.setOpaque(false);
		quitter.setOpaque(false);

		/* Sont transparents */

		jouer.setContentAreaFilled(false);
		options.setContentAreaFilled(false);
		quitter.setContentAreaFilled(false);

		/* Pas de couleur de contenu des 3 differents boutons */

		jouer.setBorderPainted(false);
		options.setBorderPainted(false);
		quitter.setBorderPainted(false);

		/* Pas de bordures pour les 3 differents boutons */

		jouer.setForeground(Color.WHITE);
		options.setForeground(Color.WHITE);
		quitter.setForeground(Color.WHITE);

		/* Couleur des 3 boutons qui est blanche */

		jouer.setFont(fontEntered);
		options.setFont(fontEntered);
		quitter.setFont(fontEntered);

		/* Application de la Font a la police du texte des boutons */

		this.add(jouer);
		this.add(options);
		this.add(quitter);

		/* On affecte les boutons au JPanel Menu */

		try {
			background = ImageIO.read(new File("images/Textures/menu.jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		} /* On charge l'image de background, si chemin introuvable ca lance une exception */
	}

	public void paintComponent(Graphics g) {

		g.drawImage(background,0,0,largeur,hauteur,this);
	} /* Permet de peindre le JPanel et son contenu */

	public JButton getJouer(){
		return jouer;
	} /* Retourne le JButton Jouer */

	public JButton getOptions(){
		return options;
	} /* Retourne le JButton Options */

	public JButton getQuitter(){
		return quitter;
	} /* Retourne le JButton Quitter */
}