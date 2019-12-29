package jeu;

import java.io.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Bombes extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int largeurBackground=1360;
	private static final int hauteurBackground=750;
	private int x;
	private int y;
	private Terrain terrain;
	private int longueurFlamme;
	private Rectangle hitBoxBombe;

	public Bombes(Personnage player, Terrain terrain){

		this.x=player.getPx();
		this.y=player.getPy();
		this.terrain=terrain;
		this.longueurFlamme=player.getLongueurFlamme();
		this.setPreferredSize(new Dimension(largeurBackground,hauteurBackground));
		this.hitBoxBombe=new Rectangle(x,y,30,30); /* Creation de la hitbox de la bombe */
	}

	public Bombes(Ennemis ennemis, Terrain terrain){

		this.x=ennemis.getPx();
		this.y=ennemis.getPy();
		this.terrain=terrain;
		this.longueurFlamme=1;
		this.setPreferredSize(new Dimension(largeurBackground,hauteurBackground));
		this.hitBoxBombe=new Rectangle(x,y,30,30); /* Creation de la hitbox de la bombe */
	}

	public boolean mortPerso(Personnage player) {

		int blocX=((x-400)/40);
		int blocY=((y-100)/40)+1;

		for(int i=1;i<longueurFlamme+1;i++) {

			hitBoxBombe.setLocation((blocX*40)+400,(blocY*40)+101);
			if(player.getHitBoxPerso().intersects(hitBoxBombe)) return true;
			hitBoxBombe.setLocation((blocX*40)+400,((blocY+i)*40)+101);
			if(player.getHitBoxPerso().intersects(hitBoxBombe)) return true;
			hitBoxBombe.setLocation((blocX*40)+400,((blocY-i)*40)+101);
			if(player.getHitBoxPerso().intersects(hitBoxBombe)) return true;
			hitBoxBombe.setLocation(((blocX+i)*40)+400,((blocY)*40)+101);
			if(player.getHitBoxPerso().intersects(hitBoxBombe)) return true;
			hitBoxBombe.setLocation(((blocX-i)*40)+400,((blocY)*40)+101);
			if(player.getHitBoxPerso().intersects(hitBoxBombe)) return true;
		}
		return false;
	}

	public boolean mortEnnemis(Ennemis ennemis) {

		int blocX=((x-400)/40);
		int blocY=((y-100)/40)+1;

		for(int i=1;i<longueurFlamme+1;i++) {

			hitBoxBombe.setLocation((blocX*40)+400,(blocY*40)+101);
			if(ennemis.getHitBoxEnnemis().intersects(hitBoxBombe)) return true;
			hitBoxBombe.setLocation((blocX*40)+400,((blocY+i)*40)+101);
			if(ennemis.getHitBoxEnnemis().intersects(hitBoxBombe)) return true;
			hitBoxBombe.setLocation((blocX*40)+400,((blocY-i)*40)+101);
			if(ennemis.getHitBoxEnnemis().intersects(hitBoxBombe)) return true;
			hitBoxBombe.setLocation(((blocX+i)*40)+400,((blocY)*40)+101);
			if(ennemis.getHitBoxEnnemis().intersects(hitBoxBombe)) return true;
			hitBoxBombe.setLocation(((blocX-i)*40)+400,((blocY)*40)+101);
			if(ennemis.getHitBoxEnnemis().intersects(hitBoxBombe)) return true;
		}
		return false;
	}

	public void degradationBombe() {

		int blocX=((x-400)/40);
		int blocY=((y-100)/40)+1;

		for(int i=1;i<longueurFlamme+1;i++) {
			
			if(terrain.getMap()[blocY+i][blocX]==2) terrain.setMap(blocY+i,blocX,10);
			if(terrain.getMap()[blocY-i][blocX]==2) terrain.setMap(blocY-i,blocX,10);
			if(terrain.getMap()[blocY][blocX+i]==2) terrain.setMap(blocY,blocX+i,10);
			if(terrain.getMap()[blocY][blocX-i]==2) terrain.setMap(blocY,blocX-i,10);

			if(terrain.getMap()[blocY+i][blocX]==0) terrain.setMap(blocY+i,blocX,11);
			if(terrain.getMap()[blocY-i][blocX]==0) terrain.setMap(blocY-i,blocX,11);
			if(terrain.getMap()[blocY][blocX+i]==0) terrain.setMap(blocY,blocX+i,11);
			if(terrain.getMap()[blocY][blocX-i]==0) terrain.setMap(blocY,blocX-i,11);
		}
		terrain.setMap(blocY,blocX,12);
	}

	public void exploseBombe() {

		int blocX=((x-400)/40);
		int blocY=((y-100)/40)+1;

		for(int i=1;i<longueurFlamme+1;i++) {

			if(terrain.getMap()[blocY+i][blocX]==10 || terrain.getMap()[blocY+i][blocX]==11) terrain.setMap(blocY+i,blocX,0);
			if(terrain.getMap()[blocY-i][blocX]==10 || terrain.getMap()[blocY-i][blocX]==11) terrain.setMap(blocY-i,blocX,0);
			if(terrain.getMap()[blocY][blocX+i]==10 || terrain.getMap()[blocY][blocX+i]==11) terrain.setMap(blocY,blocX+i,0);
			if(terrain.getMap()[blocY][blocX-i]==10 || terrain.getMap()[blocY][blocX-i]==11) terrain.setMap(blocY,blocX-i,0);
		}

		terrain.setMap(blocY,blocX,0);
	}

	public void perso1PoseBombe(){

		int blocX=((x-400)/40);
		int blocY=((y-100)/40)+1;

		terrain.setMap(blocY,blocX,7);
	}

	public void perso2PoseBombe(){

		int blocX=((x-400)/40);
		int blocY=((y-100)/40)+1;

		terrain.setMap(blocY,blocX,8);
	}

	public void ennemisPoseBombe(){

		int blocX=((x-400)/40);
		int blocY=((y-100)/40)+1;

		terrain.setMap(blocY,blocX,9);
	}

	public void updateExplosion() {

		int blocX=(x-400)/40;
		int blocY=(y-100)/40;

		for(int i=0;i<longueurFlamme;i++) {

			if(terrain.getMap()[blocY+i][blocX]==7) terrain.getMap()[blocY+i][blocX]=0;
			if(terrain.getMap()[blocY-i][blocX]==7) terrain.getMap()[blocY-i][blocX]=0;
			if(terrain.getMap()[blocY][blocX+i]==7) terrain.getMap()[blocY][blocX+i]=0;
			if(terrain.getMap()[blocY][blocX-i]==7) terrain.getMap()[blocY][blocX-i]=0;
		}
	}
}