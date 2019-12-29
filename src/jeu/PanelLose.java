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

public class PanelLose extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int largeur = 1360;
	private static final int hauteur  = 750;

	/* Largeur et Hauteur du JPanel PanelLose */

	private Image background; /* Objet background de type Image, ca va etre l'image d'arriere plan */
	
	private JButton rejouer;
	private JButton menuP;
	
	/* Les boutons qui vont etre affecte au JPanel PanelLose */

	private final Font fontEntered = new Font(Font.DIALOG, Font.ITALIC, 50); /* Le Font qui va etre utilise dans le JPabel PanelLose */

	public PanelLose(String file) {

		this.setLayout(null);

		/* Pas de Layout afin qu'on puisse positionner de maniere efficace les 2 differents boutons */

		rejouer=new JButton("Rejouer");
		rejouer.setBounds(350,500,250,100);
		rejouer.setOpaque(false);
		rejouer.setContentAreaFilled(false);
		rejouer.setBorderPainted(false);
		rejouer.setForeground(Color.WHITE);
		rejouer.setFont(fontEntered);

		menuP=new JButton("Menu");
		menuP.setBounds(750,500,250,100); /* L'emplacement et la taille */
		menuP.setOpaque(false); /* Bouton transparents */
		menuP.setContentAreaFilled(false); /* Pas de couleur interieure du bouton */
		menuP.setBorderPainted(false); /* Pas de bordure au bouton */
		menuP.setForeground(Color.WHITE); /* Couleur du texte */
		menuP.setFont(fontEntered); /* La Font du texte utilise */

		/* On initialise les 2 differents boutons avec leurs caracteristiques */

		this.add(menuP);
		this.add(rejouer);

		/* On affecte les 2 differents boutons au JPanel PanelLose  */

		try {
			background = ImageIO.read(new File(file));
		}
		catch(IOException e) {
			e.printStackTrace();
		} /* On initialise l'image d'arriere plan, si le chemin n'est pas valide ca envoi une exception */
	}

	public void paintComponent(Graphics g) {

		g.drawImage(background,0,0,largeur,hauteur,this);
	} /* Permet de peindre le JPanel et son contenu */

	public JButton getRejouer(){
		return rejouer;
	} /* menuPne le bouton Rejouer */

	public JButton getMenuP(){
		return menuP;
	} /* menuPne le bouton menuP */
}