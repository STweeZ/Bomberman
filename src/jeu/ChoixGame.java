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

public class ChoixGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int largeur = 1360; /* Largeur qu'on va appliquer au background et donc au panel */
	private static final int hauteur  = 750;; /* Hauteur qu'on va appliquer au background et donc au panel */
	private Image background; /* Image d'arriere plan */
	private JButton joueurChoix1;
	private JButton joueurChoix2;
	private JButton retour;
	private final Font fontEntered = new Font(Font.DIALOG, Font.ITALIC, 50); /* Font du Bomberman */
	private final Font fontEntered2 = new Font(Font.DIALOG, Font.ITALIC, 40); /* Meme Font mais plus petite que la premiere */

	public ChoixGame() {

		this.setLayout(null); /* Il n'y a pas de Layout, necessaire pour le positionnement des boutons */

		joueurChoix1=new JButton("1 joueur");
		joueurChoix2=new JButton("2 joueurs");
		retour=new JButton("Retour");
		
		/* Definition des boutons de la fenetre de choix de partie, 1 joueur, 2 joueur, ou bien retour */

		joueurChoix1.setBounds(250,450,250,100);
		joueurChoix2.setBounds(850,450,250,100);
		retour.setBounds(550,550,250,100);
		
		/* Position puis largeur des 3 boutons */
		
		joueurChoix1.setOpaque(false);
		joueurChoix2.setOpaque(false);
		retour.setOpaque(false);
		
		/* Transparence des 3 boutons */
		
		joueurChoix1.setContentAreaFilled(false);
		joueurChoix2.setContentAreaFilled(false);
		retour.setContentAreaFilled(false);
		
		/* Pas de couleur dans le contenu des boutons */
		
		joueurChoix1.setBorderPainted(false);
		joueurChoix2.setBorderPainted(false);
		retour.setBorderPainted(false);
		
		/* Pas de couleurs de bordures des boutons */
		
		joueurChoix1.setForeground(Color.WHITE);
		joueurChoix2.setForeground(Color.WHITE);
		retour.setForeground(Color.WHITE);
		
		/* Couleur du texte de base des boutons */
		
		joueurChoix1.setFont(fontEntered2);
		joueurChoix2.setFont(fontEntered2);
		retour.setFont(fontEntered);
		
		/* Application des Font aux boutons */
		
		this.add(joueurChoix1);
		this.add(joueurChoix2);
		this.add(retour);
		
		/* Application des boutons au Panel ChoixGame */
		
		try {
			background = ImageIO.read(new File("images/Textures/choixPerso.jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		} /* Charge l'image d'arriere plan dans la variable background, si chemin non valide -> exception */
	}

	public JButton getJoueurChoix1(){
		return joueurChoix1;
	} /* Retourne le bouton 1 joueur */

	public JButton getJoueurChoix2(){
		return joueurChoix2;
	} /* Retourne le bouton 2 joueurs */

	public JButton getRetour(){
		return retour;
	} /* Retourne le bouton retour */

	public void paintComponent(Graphics g) {

		g.drawImage(background,0,0,largeur,hauteur,this);
	} /* Applique le panel contenant l'image et l'ensemble */
}