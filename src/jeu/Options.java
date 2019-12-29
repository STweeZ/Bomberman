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

public class Options extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int largeur = 1360;
	private static final int hauteur  = 750;

	/* Largeur et Hauteur du JPanel Options */

	private Image background; /* Objet background de type Image, ca va etre l'image d'arriere plan */

	private JButton retour;
	private JButton j1;
	private JButton j2;

	/* Les boutons qui vont etre affecte au JPanel Options */

	private final Font fontEntered = new Font(Font.DIALOG, Font.ITALIC, 50); /* Le Font qui va etre utilise dans le JPabel Options */

	public Options(String file) {

		this.setLayout(null);

		/* Pas de Layout afin qu'on puisse positionner de maniere efficace les 3 differents boutons */

		retour=new JButton("Retour");
		retour.setBounds(550,600,250,100); /* L'emplacement et la taille */
		retour.setOpaque(false); /* Bouton transparents */
		retour.setContentAreaFilled(false); /* Pas de couleur interieure du bouton */
		retour.setBorderPainted(false); /* Pas de bordure au bouton */
		retour.setForeground(Color.WHITE); /* Couleur du texte */
		retour.setFont(fontEntered); /* La Font du texte utilise */

		j1=new JButton("");
		j1.setBounds(30,315,250,100);
		j1.setOpaque(false);
		j1.setContentAreaFilled(false);
		j1.setBorderPainted(false);
		j1.setForeground(Color.WHITE);
		j1.setFont(fontEntered);

		j2=new JButton("");
		j2.setBounds(1080,315,250,100);
		j2.setOpaque(false);
		j2.setContentAreaFilled(false);
		j2.setBorderPainted(false);
		j2.setForeground(Color.WHITE);
		j2.setFont(fontEntered);

		/* On initialise les 3 differents boutons avec leurs caracteristiques */

		this.add(retour);
		this.add(j1);
		this.add(j2);

		/* On affecte les 3 differents boutons au JPanel Options  */

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

	public JButton getJ1(){
		return j1;
	} /* Retourne le bouton Joueur 1 */

	public JButton getJ2(){
		return j2;
	} /* Retourne le bouton Joueur 2 */

	public JButton getRetour(){
		return retour;
	} /* Retourne le bouton Retour */
}