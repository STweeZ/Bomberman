package jeu;

import java.awt.Rectangle;

public class Terrain {
	
	/* 0 = Rien - 1 = Mur incassable - 2 = Mur cassable - 3,4 = Joueur */
	
	private int[][] map = {
			
		    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
		    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
		    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
		    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
		    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
		    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		    { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
		    { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
		    
	}; /* Tableau de la map */
	
	private int[][] positionsPossiblesPersonnages = {{1, 1},{13, 13}}; /* Coordonnees possibles de spawn des joueurs a partir du tableau map */
	
	private int[][] positionsPossiblesEnnemis = {{1,13},{13,1},{13,13}}; /* Coordonnees possibles de spawn des ennemis a partir du tableau map */
	
	private int cptPersonnages = 0; /* Compteur des personnages present sur le terrain */
	
	private int cptEnnemis = 0; /* Compteur des ennemis present sur le terrain */
	
	public Terrain() {
		spawnCassable();
	} /* Constructeur qui initialise les blocs cassables */
	
	public int[][] getMap(){
		return map;
	} /* Retourne le terrain sous forme de tableau */
	
	public void spawnCassable() {
		
		double pourcentageSpawn = 0.6;
		
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++){
				if(map[i][j] == 0 && Math.random() < pourcentageSpawn) {
					map[i][j] = 2;
				}
			}
		}
	} /* Permet d'initialiser dans le tableau map de maniere aleatoire les blocs cassables, avec un taux de 60% */
	
	public void setMap(int y, int x, int valeur) {
		map[y][x]=valeur;
	}
	
	public int[] addPerso() {
		
		if(cptPersonnages >= 2) {
			return null;
		} /* Maximum de 2 joueurs sur le terrain */
		
		int px = 440+(positionsPossiblesPersonnages[cptPersonnages][0])*40-35; /* Position du joueur en x en pixel */
		int py = 100+(positionsPossiblesPersonnages[cptPersonnages][1])*40-2*(cptPersonnages*5+1); /* Position du joueur en y en pixel */
		
		int x = positionsPossiblesPersonnages[cptPersonnages][0]; /* Position du joueur en x via le tableau map */
		int y = positionsPossiblesPersonnages[cptPersonnages][1]; /* Position du joueur en y via le tableau map */
		
		if (cptPersonnages==0) {
			map[y][x] = 3;
		} /* Le premier joueur obtient l'id 3 */
		
		else if (cptPersonnages==1) {
			map[y][x] = 4;
		} /* Le second joueur obtient l'id 4 */
		
		if(map[y + 1][x] != 1) {
			map[y + 1][x] = 0;
		}
		
		if(map[y - 1][x] != 1) {
			map[y - 1][x] = 0;
		}
		
		if(map[y][x + 1] != 1) {
			map[y][x + 1] = 0;
		}
		
		if(map[y][x - 1] != 1) {
			map[y][x - 1] = 0;
		}
		
		/* Si a cote du personnage il y a des blocs cassables a 1 bloc de distance on les enleves */
		
		cptPersonnages++;
		return new int[] {px, py};
	} /* Ajoute un personnage sur la map, retourne la position du personnage sur la map */
	
	public int[] addEnnemis(){
		
		int px = 440+(positionsPossiblesEnnemis[cptEnnemis][0])*40-35; /* Position du joueur en x en pixel */
		int py = 100+(positionsPossiblesEnnemis[cptEnnemis][1])*40-5*(cptEnnemis)-10; /* Position du joueur en y en pixel */
		
		int x = positionsPossiblesEnnemis[cptEnnemis][0]; /* Position du joueur en x via le tableau map */
		int y = positionsPossiblesEnnemis[cptEnnemis][1]; /* Position du joueur en y via le tableau map */
		
		if (cptEnnemis==0) {
			map[y][x] = 5;
		}
		
		else if (cptEnnemis==1) {
			map[y][x] = 5;
		}
		
		else if (cptEnnemis==2) {
			map[y][x] = 5;
		}
		
		/* Les ennemis obtiennent tous l'id 5 */
		
		if(map[y + 1][x] != 1) {
			map[y + 1][x] = 0;
		}
		
		if(map[y - 1][x] != 1) {
			map[y - 1][x] = 0;
		}
		
		if(map[y][x + 1] != 1) {
			map[y][x + 1] = 0;
		}
		
		if(map[y][x - 1] != 1) {
			map[y][x - 1] = 0;
		}
		
		/* Si a cote de l'ennemi il y a des blocs cassables a 1 bloc de distance on les enleves */
		
		cptEnnemis++;
		return new int[] {px, py};
	} /* Ajoute un ennemi sur la map, retourne la position du personnage sur la map */
	
	public int getCptPersonnages() {
		return cptPersonnages;
	} /* Retourne le nombre de personnages presents sur le terrain */
	
	public int getCptEnnemis() {
		return cptEnnemis;
	} /* Retourne le nombre d'ennemis presents sur le terrain */
}