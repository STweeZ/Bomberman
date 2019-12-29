package jeu;

import java.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.awt.Dimension;

public class Personnage extends JPanel {

	private static final long serialVersionUID = 1L;

 	private String nom; /* Nom du joueur */

	private int score; /* Score du joueur */

    private int nbBombes; /* Nombre de bombes qu'il peut poser */

    private int bombesPosees; /* Nombre de bombes qu'il a posees */

    private int longueurFlamme; /* Longueur des flammes du joueur */

    private int px,py; /* Position du joueur sur le terrain */

	private int nbSpeed; /* Vitesse du joueur */

    private Terrain terrain; /* Terrain actuel */

    private Image player;

	private static final int largeurBackground=1360;

	private static final int hauteurBackground=750;

	private int numPerso; /* Numero du personnage */

	private Rectangle hitBoxPerso; /* Rectangle pour la hitbox du personnage */

    public Personnage(String nom, Terrain terrain) {

        this.nom = nom; /* Nom du joueur */
        this.longueurFlamme = 1; /* Initialisation de la longueur des flammes a 1 */
        this.score = 0; /* Initialisation du score a 0 */
        this.nbBombes = 1; /* Initialisation du nombre de bombes transportables a 1 */
        this.bombesPosees = 0; /* Initialisation du nombre de bombes posees a 0 */
        this.terrain = terrain; /* Initialisation du terrain avec celui fourni */
		this.nbSpeed = 1; /* Initialisation de la vitesse 1 */
        int[] position = terrain.addPerso(); /* On recupere les coordonnees en pixel du joueur a partir du nombre de joueur deja present sur le terrain */
        this.numPerso = terrain.getCptPersonnages(); /* On lui donne un numero de joueur */
        this.px = position[0]; /* On initialise les coordonnees x du joueur grace a la variable position qu'on a recuperee */
        this.py = position[1]; /* On initialise les coordonnees y du joueur grace a la variable position qu'on a recuperee */
		this.setPreferredSize(new Dimension(largeurBackground,hauteurBackground)); /* Definition de la taille du panel personnage */
		this.hitBoxPerso=new Rectangle(px,py,30,35); /* Creation de la hitbox du personnage */

        try {
			player = ImageIO.read(new File("images/SpriteSheet/SPBas.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		} /* Charge l'image du joueur dans la variable player, si chemin non valide -> exception */
    }

    public int getLongueurFlamme(){
        return longueurFlamme;
    } /* Retourne la longueur des flammes */

    public void plusFlammes(int x){
        if((longueurFlamme+x)>=1) longueurFlamme+=x;
    } /* Augmente la longueur des flammes d'un point */

    public void undrop_bomb(){
        if(bombesPosees>0) bombesPosees--;
    } /* Redonne une bombe au joueur */

    public void give_bombs(int  n){
        if((nbBombes+n)>=1) nbBombes+=n;
    } /* Donne n bombes au joueur */

    public boolean deplacement() {

		int posX = 400; /* C'est le x ou la creation de la map commence  */
		int posY = 100; /* C'est le y ou la creation de la map commence  */

		for(int i=0;i<terrain.getMap().length;i++) {
			for(int j=0;j<terrain.getMap().length;j++) {
				if(terrain.getMap()[i][j]==1 || terrain.getMap()[i][j]==2 || terrain.getMap()[i][j]==9 || (numPerso==1 && terrain.getMap()[i][j]==8) || (numPerso==2 && terrain.getMap()[i][j]==7)) {
					Rectangle hitBoxCase = new Rectangle(posX,posY-7-((numPerso-1)*5),40,40); /* Hibtox des blocs cassables et incassables */
					if (hitBoxPerso.intersects(hitBoxCase)) return false;
				}
				posX+=40;
			}
			posX=400;
			posY+=40;
		}
		posX=400;
		posY=100;
		return true;
	} /* Deplacement du joueur sur le terrain, creation des hitbox des blocs cassables et incassables pour voir s'il peut bouger ou non */
    
    public void deplacementBas(int nb) {

		py+=nbSpeed;
		if (nb==1) {
			try {
				player = ImageIO.read(new File("images/SpriteSheet/PB.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if (nb==18) {
			try {
				player = ImageIO.read(new File("images/SpriteSheet/SPB.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		hitBoxPerso.setLocation(px,py);
    	if (this.deplacement()==false) {
    		py-=nbSpeed;
    		hitBoxPerso.setLocation(px,py);
    	}
	} /* Descend la hitbox du joueur de 1 pixel afin de verifier si elle ne s'intersecte pas avec la hitbox de la map, si intersection -> Remet a la place d'origine */


	public void deplacementHaut(int nb) {

		py-=nbSpeed;
		if (nb==1) {
			try {
				player = ImageIO.read(new File("images/SpriteSheet/PH.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if (nb==18) {
			try {
				player = ImageIO.read(new File("images/SpriteSheet/SPH.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		hitBoxPerso.setLocation(px,py);
		if (this.deplacement()==false) {
			py+=nbSpeed;
			hitBoxPerso.setLocation(px,py);
    	}
	} /* Monte la hitbox du joueur de 1 pixel afin de verifier si elle ne s'intersecte pas avec la hitbox de la map, si intersection -> Remet a la place d'origine */


	public void deplacementDroite(int nb) {

		px+=nbSpeed;
		if (nb==1) {
			try {
				player = ImageIO.read(new File("images/SpriteSheet/PD.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if (nb==18) {
			try {
				player = ImageIO.read(new File("images/SpriteSheet/SPD.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		hitBoxPerso.setLocation(px,py);
		if (this.deplacement()==false) {
			px-=nbSpeed;
    		hitBoxPerso.setLocation(px,py);
    	}
	} /* Decale a droite la hitbox du joueur de 1 pixel afin de verifier si elle ne s'intersecte pas avec la hitbox de la map, si intersection -> Remet a la place d'origine */


	public void deplacementGauche(int nb) {


		px-=nbSpeed;
		if (nb==1) {
			try {
				player = ImageIO.read(new File("images/SpriteSheet/PG.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if (nb==18) {
			try {
				player = ImageIO.read(new File("images/SpriteSheet/SPG.png"));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		hitBoxPerso.setLocation(px,py);
		if (this.deplacement()==false) {
    		px+=nbSpeed;
    		hitBoxPerso.setLocation(px,py);
    	}
	} /* Decale a gauche la hitbox du joueur de 1 pixel afin de verifier si elle ne s'intersecte pas avec la hitbox de la map, si intersection -> Remet a la place d'origine */

	public void bombeExplode(Bombes bombe) {

		bombe.exploseBombe();
	} /* Appel d'une methode d'explosion de bombe dans la classe bombe. Methode qui va servir a faire exploser les bombes */

	public void paintComponent(Graphics g) {

		g.drawImage(player,px,py,35,40,this);
	} /* Permet de peindre sur le terrain le panel contenant le joueur */

	public int getNbSpeed(){
		return nbSpeed;
	} /* Recuperer la vitesse du personnage */

	public void setNbSpeed(int nbSpeed){
		this.nbSpeed=nbSpeed;
	} /* Permet d'augmenter ou de diminuer la vitesse du joueur d'autant de pixel */

	public int getPx(){
		return px;
	} /* Recuperer la position x du joueur */

	public int getPy(){
		return py;
	} /* Recuperer la position y du joueur */

	public Rectangle getHitBoxPerso() {
		return hitBoxPerso;
	} /* Retourne la hitbox du personnage */

	public Terrain getTerrain() {
		return terrain;
	} /* Retourne le terrain dans lequel le personnage joue */
}