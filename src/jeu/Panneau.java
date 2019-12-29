package jeu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int largeurBackground = 1360;
	private static final int hauteurBackground  = 750;

	/* Largeur et Hauteur du JPanel */

	private int posX=400;
	private int posY=100;

	/* Position en x et y du commencement de creation du terrain */

	private Image background;
	private Image sol;
	private Image murIncassable;
	private Image murCassable;
	private Image bombe;
	private Image degradation;
	private Image degradationSol;
	private Image boom;

	/* Les objets Image, les differentes images qu'on va utiliser pour peindre le terrain et pour mettre un background */

	private int largeur = 40; /* Largeur d'un bloc du terrain */
	private Terrain terrain; /* L'objet terrain, la map qu'on va utiliser */

	public Panneau(Terrain terrain, String file) {

		super();
		this.terrain = terrain;
		this.setPreferredSize(new Dimension(largeurBackground,hauteurBackground));

		/* Initialise le terrain, et la taille du JPanel est definie */

		try {
			background = ImageIO.read(new File(file));
			sol = ImageIO.read(new File("images/Textures/sol.jpg"));
			murIncassable = ImageIO.read(new File("images/Textures/murIncassable.jpg"));
			murCassable = ImageIO.read(new File("images/Textures/murCassable.jpg"));
			bombe = ImageIO.read(new File("images/Textures/bombe.jpg"));
			degradation = ImageIO.read(new File("images/Textures/degradation.jpg"));
			degradationSol = ImageIO.read(new File("images/Textures/degradationSol.jpg"));
			boom = ImageIO.read(new File("images/Textures/boom.jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		} /* Charge les differentes images utilisees, si le chemin n'est pas valide ca lance une exception */
	}

	public void paintComponent(Graphics g) {

		g.drawImage(background,0,0,largeurBackground,hauteurBackground,this);
		for(int i=0;i<terrain.getMap().length;i++) {
			for(int j=0;j<terrain.getMap().length;j++) {
				g.drawImage(sol,posX,posY,largeur,largeur,this);
				if(terrain.getMap()[i][j]==1) {
					g.drawImage(murIncassable,posX,posY,largeur,largeur,this);
				}
				else if(terrain.getMap()[i][j]==2) {
					g.drawImage(murCassable,posX,posY,largeur,largeur,this);
				}
				else if(terrain.getMap()[i][j]==7 || terrain.getMap()[i][j]==8 || terrain.getMap()[i][j]==9) {
					g.drawImage(bombe,posX,posY,largeur,largeur,this);
				}
				else if(terrain.getMap()[i][j]==10) {
					g.drawImage(degradation,posX,posY,largeur,largeur,this);
				}
				else if(terrain.getMap()[i][j]==11) {
					g.drawImage(degradationSol,posX,posY,largeur,largeur,this);
				}
				else if(terrain.getMap()[i][j]==12) {
					g.drawImage(boom,posX,posY,largeur,largeur,this);
				}
				posX+=40;
			}
			posX=400;
			posY+=40;
		}
		posX=400;
		posY=100;
	} /* Va permettre de peindre le terrain et en soit de peindre le JPanel, peind du sol et soit un mur cassable ou soit un mur incassable par dessus ou rien en fonction de la valeur dans l'attribut terrain. Bombe/Degradation/Boom qui sont les images de bombes et d'explosion de bombe qu'on va faire afficher quand le personnage va en poser une ou quand elle va exploser */

	public int getPosX() {
		return posX;
	} /* Retourne la position en X */

	public int getPosY() {
		return posY;
	} /* Retourne la position en Y */

	public int getLargeur() {
		return largeur;
	} /* Retourne la largeur du JPanel */
}