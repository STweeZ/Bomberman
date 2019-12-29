package jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	private boolean hautJ1, basJ1, gaucheJ1, droiteJ1, espaceJ1; /* Les variables de direction du joueur 1 */
	private boolean hautJ2, basJ2, gaucheJ2, droiteJ2, espaceJ2; /* Les variables de direction du joueur 2 */

	public void keyTyped(KeyEvent e){} /* Les types de Key */

	public void keyPressed(KeyEvent e){
		
		if (e.getKeyCode() == KeyEvent.VK_Z) hautJ1=true;
		if (e.getKeyCode() == KeyEvent.VK_UP) hautJ2=true;
		if (e.getKeyCode() == KeyEvent.VK_S) basJ1=true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN) basJ2=true;
		if (e.getKeyCode() == KeyEvent.VK_Q) gaucheJ1=true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT) gaucheJ2=true;
		if (e.getKeyCode() == KeyEvent.VK_D) droiteJ1=true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) droiteJ2=true;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) espaceJ1=true;
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) espaceJ2=true;
    } /* Si les touches ZQSD sont pressees c'est au joueur 1 que les mouvements sont assignes, si c'est avec les touches directionnelles c'est au joueur 2 */

	public void keyReleased(KeyEvent e){

		if (e.getKeyCode() == KeyEvent.VK_Z) hautJ1=false;
		if (e.getKeyCode() == KeyEvent.VK_UP) hautJ2=false;
		if (e.getKeyCode() == KeyEvent.VK_S) basJ1=false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN) basJ2=false;
		if (e.getKeyCode() == KeyEvent.VK_Q) gaucheJ1=false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT) gaucheJ2=false;
		if (e.getKeyCode() == KeyEvent.VK_D) droiteJ1=false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) droiteJ2=false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) espaceJ1=false;
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) espaceJ2=false;
	} /* Si les touches ZQSD sont relachees c'est au joueur 1 que l'arret de l'appui des touches sont assignes, si c'est avec les touches directionnelles c'est au joueur 2 */

	public boolean estHautJ1(){

		return hautJ1;
	} /* Retourne si la touche Z est appuyee ou non */
	
	public boolean estHautJ2(){

		return hautJ2;
	} /* Retourne si la touche HAUT est appuyee ou non */

	public boolean estBasJ1(){

		return basJ1;
	} /* Retourne si la touche S est appuyee ou non */

	public boolean estBasJ2(){

		return basJ2;
	} /* Retourne si la touche BAS est appuyee ou non */

	public boolean estGaucheJ1(){

		return gaucheJ1;
	} /* Retourne si la touche Q est appuyee ou non */

	public boolean estGaucheJ2(){

		return gaucheJ2;
	} /* Retourne si la touche GAUCHE est appuyee ou non */

	public boolean estDroiteJ1(){

		return droiteJ1;
	} /* Retourne si la touche D est appuyee ou non */

	public boolean estDroiteJ2(){

		return droiteJ2;
	} /* Retourne si la touche DROITE est appuyee ou non */

	public boolean estEspaceJ1(){

		return espaceJ1;
	} /* Retourne si la touche ESPACE est appuyee ou non */

	public boolean estEspaceJ2(){

		return espaceJ2;
	} /* Retourne si la touche CTRL est appuyee ou non */

	public boolean toucheAppuiSolo() {
		if (estHautJ1()==true || estBasJ1()==true || estGaucheJ1()==true || estDroiteJ1()==true || estEspaceJ1()==true) return true;
		else return false;
	} /* Pour le solo, confere l'appui des touches au mouvement du joueur 1, le seul joueur present sur le terrain */
	
	public boolean toucheAppuiMulti() {
		if (estHautJ1()==true || estBasJ1()==true || estGaucheJ1()==true || estDroiteJ1()==true || estEspaceJ1()==true) return true;
		else if (estHautJ2()==true || estBasJ2()==true || estGaucheJ2()==true || estDroiteJ2()==true || estEspaceJ2()==true) return true;
		else return false;
	}
	
	public boolean toucheAppuiMultiJ1() {
		if (estHautJ1()==true || estBasJ1()==true || estGaucheJ1()==true || estDroiteJ1()==true || estEspaceJ1()==true) return true;
		else return false;
	}
	
	public boolean toucheAppuiMultiJ2() {
		if (estHautJ2()==true || estBasJ2()==true || estGaucheJ2()==true || estDroiteJ2()==true || estEspaceJ2()==true) return true;
		else return false;
	} /* Pour le multi, confere l'appui des touches ZQSD au mouvement du joueur 1, et l'appui des touches HAUT BAS GAUCHE DROITE au joueur 2 */
}