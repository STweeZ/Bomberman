package jeu;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Bomberman {

	private static KeyInput clavier = new KeyInput(); /* L'attribut clavier = objet de la classe KeyInput que l'on a creee */
	private static FenetrePrincipale lancement; /* L'objet lancement, objet de la classe FenetrePrincipale */
	private static DeplacementPerso deplacementPerso; /* L'objet deplacementPerso, objet de la classe DeplacementPerso et qui va servir pour appliquer les deplacements du ou des joueurs dans le terrain */
	private static DeplacementEnnemis deplacementEnnemis; /* L'objet deplacementEnnemis, objet de la classe DeplacementEnnemis et qui va servir pour appliquer les deplacements des ennemis */
	private static int cptMouvementEnnemis=0; /* Compteur du mouvement des ennemis, a un certain montant leur direction va probablement changer */
	private static int bombeJ1AExploser=0;
	private static int bombeJ2AExploser=0;
	private static Bombes bombeJ1;
	private static Bombes bombeJ2;
	private static Bombes bombeE1;
	private static Bombes bombeE2;
	private static Bombes bombeE3;
	private static int cptBombeEnnemis;
	
	private static PlaySound boom;

	public static void main(String[] args) {

		lancement = new FenetrePrincipale(); /* On creer un objet lancement */
		lancement.getFenetreJeu().addKeyListener(clavier); /* On definit comme KeyListener notre objet clavier de type KeyInput */
		deplacementPerso = new DeplacementPerso(clavier,lancement); /* Creation de l'objet deplacementPerso */
		deplacementEnnemis = new DeplacementEnnemis(lancement); /* Creation de l'objet deplacementEnnemis */
		int valeur = (int)(Math.random()*4); /* Valeur random en int qui va permettre de donner une direction aux differents ennemis, si 0 1 2 ou 3 les ennemis se dirigeront dans une direction differentes */
		cptBombeEnnemis=400;
		
		boom = new PlaySound("musiques/boom.wav");

		while (lancement.getAppliEnRoute()) {

			if (deplacementPerso.getBombeSolo()==true && bombeJ1AExploser==0) {
				bombeJ1AExploser=200;
				bombeJ1=new Bombes(lancement.getPlayer1(),lancement.getTerrain());
				bombeJ1.perso1PoseBombe();
			}

			if (deplacementPerso.getBombeMultiJ1()==true && bombeJ1AExploser==0) {
				bombeJ1AExploser=200;
				bombeJ1=new Bombes(lancement.getPlayer1(),lancement.getTerrain());
				bombeJ1.perso1PoseBombe();
			}

			if (deplacementPerso.getBombeMultiJ2()==true && bombeJ2AExploser==0) {
				bombeJ2AExploser=200;
				bombeJ2=new Bombes(lancement.getPlayer2(),lancement.getTerrain());
				bombeJ2.perso2PoseBombe();
			}

			if (bombeJ1AExploser==40) {
				bombeJ1.degradationBombe();
				try {
					boom.musicLancement();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}

			if (bombeJ1AExploser!=0) {
				bombeJ1AExploser--;
			}

			if (bombeJ2AExploser==40) {
				bombeJ2.degradationBombe();
				try {
					boom.musicLancement();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}

			if (bombeJ2AExploser!=0) {
				bombeJ2AExploser--;
			}

			if (cptBombeEnnemis==200) {

				if (lancement.getMortEnnemis1()==false) {

					bombeE1=new Bombes(lancement.getEnnemis1(),lancement.getTerrain());
					bombeE1.ennemisPoseBombe();
				}

				if (lancement.getMortEnnemis2()==false) {

					bombeE2=new Bombes(lancement.getEnnemis2(),lancement.getTerrain());
					bombeE2.ennemisPoseBombe();
				}

				if (lancement.getMortEnnemis3()==false) {

					bombeE3=new Bombes(lancement.getEnnemis3(),lancement.getTerrain());
					bombeE3.ennemisPoseBombe();
				}
			}

			if (cptMouvementEnnemis==100) {

				cptMouvementEnnemis=0;
				valeur = (int)(Math.random()*4);
			} /* Compteur arrive a son terme(==100), changement probable de direction des ennemis et reinitialisation du compteur */

			cptMouvementEnnemis++; /* Le compteur evolu */

			if (lancement.getMulti()){
				deplacementPerso.caseMulti(); /* Si l'utilisateur a clique sur le mode multi -> Possibilite de jouer a deux joueurs sur le meme clavier */
				if (bombeJ1AExploser==1) {
					lancement.getPlayer1().bombeExplode(bombeJ1);
					if (bombeJ1.mortPerso(lancement.getPlayer1())==true) lancement.panelWinJ2();
					if (bombeJ1.mortPerso(lancement.getPlayer2())==true) lancement.panelWinJ1();
				}
				if (bombeJ2AExploser==1) {
					lancement.getPlayer2().bombeExplode(bombeJ2);
					if (bombeJ2.mortPerso(lancement.getPlayer1())==true) lancement.panelWinJ2();
					if (bombeJ2.mortPerso(lancement.getPlayer2())==true) lancement.panelWinJ1();
				}
				if (lancement.getPlayer1().getHitBoxPerso().intersects(lancement.getPlayer2().getHitBoxPerso())) lancement.panelEndLoseMulti(); /* Si on se percute en multi on meurt */
			}
			else if (lancement.getSolo()){
				deplacementPerso.caseSolo(); /* Sinon si l'utilisateur a clique sur le mode solo -> Possibilite de jouer de maniere solitaire */
				if (lancement.doEnnemisMove()) {
					deplacementEnnemis.mouvement(valeur); /* Si la partie s'est lancee, les ennemis se deplaceront */
					if (lancement.getPlayer1().getHitBoxPerso().intersects(lancement.getEnnemis1().getHitBoxEnnemis()) || lancement.getPlayer1().getHitBoxPerso().intersects(lancement.getEnnemis2().getHitBoxEnnemis()) || lancement.getPlayer1().getHitBoxPerso().intersects(lancement.getEnnemis3().getHitBoxEnnemis())) lancement.panelEndLoseSolo(); /* Si on touche un des 3 ennemis on meurt */
					if (bombeJ1AExploser==1) {
						lancement.getPlayer1().bombeExplode(bombeJ1);
						if (bombeJ1.mortPerso(lancement.getPlayer1())==true) lancement.panelEndLoseSolo();
						if (bombeJ1.mortEnnemis(lancement.getEnnemis1())==true) {
							lancement.mortEnnemis1();
							if (cptBombeEnnemis<200) bombeE1.exploseBombe();
						}
						if (bombeJ1.mortEnnemis(lancement.getEnnemis2())==true) {
							lancement.mortEnnemis2();
							if (cptBombeEnnemis<200) bombeE2.exploseBombe();
						}
						if (bombeJ1.mortEnnemis(lancement.getEnnemis3())==true) {
							lancement.mortEnnemis3();
							if (cptBombeEnnemis<200) bombeE3.exploseBombe();
						}
					}
					cptBombeEnnemis--;

					if (cptBombeEnnemis==40) {
						if (lancement.getMortEnnemis1()==false) {
							bombeE1.degradationBombe();
							try {
								boom.musicLancement();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
								e1.printStackTrace();
							}
						}
						if (lancement.getMortEnnemis2()==false) {
							bombeE2.degradationBombe();
							try {
								boom.musicLancement();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
								e1.printStackTrace();
							}
						}
						if (lancement.getMortEnnemis3()==false) {
							bombeE3.degradationBombe();
							try {
								boom.musicLancement();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
								e1.printStackTrace();
							}
						}
					}

					if (cptBombeEnnemis==0) {

						if (lancement.getMortEnnemis1()==false) bombeE1.exploseBombe();
						if (lancement.getMortEnnemis2()==false) bombeE2.exploseBombe();
						if (lancement.getMortEnnemis3()==false) bombeE3.exploseBombe();

						cptBombeEnnemis=400;
					}
					if (lancement.getMortEnnemis1()==true && lancement.getMortEnnemis2()==true && lancement.getMortEnnemis3()==true) lancement.panelEndWinSolo();
				}
			}
			lancement.getFenetreJeu().repaint();
			lancement.getFenetreJeu().revalidate();

			/* On remet a jour la fenetre avec le terrain et les placements des joueurs, ennemis, etc. */

			try{
				Thread.sleep(8);
			}
			catch(InterruptedException e){} /* On patiente legerement dans la boucle while afin que l'execution ne soit pas trop rapide sinon c'est injouable */
		}
	}
}