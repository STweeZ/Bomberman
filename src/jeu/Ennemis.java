package jeu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Ennemis extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int px,py; /* Position en pixel du personnage sur le terrain */
   
    private Terrain terrain; /* Terrain actuel */

    private Image ennemis;

	private static final int largeurBackground=1360;

	private static final int hauteurBackground=750;

	private Rectangle hitBoxEnnemis;

    public Ennemis(Terrain terrain) {
    	
        this.terrain = terrain;
        int[] position = terrain.addEnnemis(); /* Position en pixel de l'ennemi sur le terrain en fonction du nombre d'ennemis deja present sur celui-ci */
        this.px = position[0]; /* Position en x pixel */
        this.py = position[1]; /* Position en y pixel */
		this.setPreferredSize(new Dimension(largeurBackground,hauteurBackground)); /* Definition de la taille du panel ennemi */
		this.hitBoxEnnemis=new Rectangle(px,py,30,35); /* Creation de la hitbox de l'ennemi */

        try {
			ennemis = ImageIO.read(new File("images/SpriteSheet/sprite_00.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		} /* Charge l'image de l'ennemi dans la variable ennemis, si chemin non valide -> exception */
    }
    
    public boolean deplacement() {

		int posX = 400; /* C'est le x ou la creation de la map commence  */
		int posY = 100; /* C'est le y ou la creation de la map commence  */

		for(int i=0;i<terrain.getMap().length;i++) {
			for(int j=0;j<terrain.getMap().length;j++) {
				if(terrain.getMap()[i][j]==1 || terrain.getMap()[i][j]==2 || terrain.getMap()[i][j]==7) {
					Rectangle hitBoxCase = new Rectangle(posX,posY-6*(terrain.getCptEnnemis())+2,40,40); /* Hibtox des blocs cassables et incassables */
					if (hitBoxEnnemis.intersects(hitBoxCase)) return false;
				}
				posX+=40;
			}
			posX=400;
			posY+=40;
		}
		posX=400;
		posY=100;
		return true;
	} /* Deplacement de l'ennemi sur le terrain, creation des hitbox des blocs cassables et incassables pour voir s'il peut bouger ou non */

    public void deplacementBas(int nb) {

		py+=1;
		if (nb==1) {
			try {
				ennemis = ImageIO.read(new File("images/SpriteSheet/sprite_01.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if (nb==18) {
			try {
				ennemis = ImageIO.read(new File("images/SpriteSheet/sprite_02.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		hitBoxEnnemis.setLocation(px,py);
    	if (this.deplacement()==false) {
    		py-=1;
    		hitBoxEnnemis.setLocation(px,py);
    	}
	} /* Descend la hitbox de l'ennemi de 1 pixel afin de verifier si elle ne s'intersecte pas avec la hitbox de la map, si intersection -> Remet a la place d'origine */


	public void deplacementHaut(int nb) {

		py-=1;
		if (nb==1) {
			try {
				ennemis = ImageIO.read(new File("images/SpriteSheet/sprite_10.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if (nb==18) {
			try {
				ennemis = ImageIO.read(new File("images/SpriteSheet/sprite_11.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		hitBoxEnnemis.setLocation(px,py);
		if (this.deplacement()==false) {
			py+=1;
			hitBoxEnnemis.setLocation(px,py);
    	}
	} /* Monte la hitbox de l'ennemi de 1 pixel afin de verifier si elle ne s'intersecte pas avec la hitbox de la map, si intersection -> Remet a la place d'origine */


	public void deplacementDroite(int nb) {

		px+=1;
		if (nb==1) {
			try {
				ennemis = ImageIO.read(new File("images/SpriteSheet/sprite_07.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if (nb==18) {
			try {
				ennemis = ImageIO.read(new File("images/SpriteSheet/sprite_08.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		hitBoxEnnemis.setLocation(px,py);
		if (this.deplacement()==false) {
			px-=1;
    		hitBoxEnnemis.setLocation(px,py);
    	}
	} /* Decale a droite la hitbox de l'ennemi de 1 pixel afin de verifier si elle ne s'intersecte pas avec la hitbox de la map, si intersection -> Remet a la place d'origine */


	public void deplacementGauche(int nb) {


		px-=1;
		if (nb==1) {
			try {
				ennemis = ImageIO.read(new File("images/SpriteSheet/sprite_05.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if (nb==18) {
			try {
				ennemis = ImageIO.read(new File("images/SpriteSheet/sprite_04.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		hitBoxEnnemis.setLocation(px,py);
		if (this.deplacement()==false) {
    		px+=1;
    		hitBoxEnnemis.setLocation(px,py);
    	}
	} /* Decale a gauche la hitbox de l'ennemi de 1 pixel afin de verifier si elle ne s'intersecte pas avec la hitbox de la map, si intersection -> Remet a la place d'origine */


	public void paintComponent(Graphics g) {

		g.drawImage(ennemis,px,py,35,40,this);
	} /* Paint le panel l'image de l'ennemi sur le terrain */

	public int getPx(){
		return px;
	} /* Retourne la position x en pixel de l'ennemi */

	public int getPy(){
		return py;
	} /* Retourne la position y en pixel de l'ennemi */

	public void setPx(int px){
		this.px=px;
	} /* Retourne la position x en pixel de l'ennemi */

	public void setPy(int py){
		this.py=py;
	} /* Retourne la position y en pixel de l'ennemi */
	
	public Rectangle getHitBoxEnnemis() {
		return hitBoxEnnemis;
	}
}