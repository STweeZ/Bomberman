package jeu;

public class DeplacementPerso {
	
	private KeyInput clavier; /* Objet de type KeyInput */
	private FenetrePrincipale lancement; /* Objet de type FenetrePrincipale */
	private boolean bombeSolo;
	private boolean bombeMultiJ1;
	private boolean bombeMultiJ2;
	private int mvmtHaut1,mvmtBas1,mvmtGauche1,mvmtDroite1;
	private int mvmtHaut2,mvmtBas2,mvmtGauche2,mvmtDroite2;
	
	public DeplacementPerso(KeyInput clavier, FenetrePrincipale lancement) {
		
		this.clavier = clavier; /* On initialise l'objet clavier avec celui fournit en argument, va servir pour les controles du ou des personnages */
		this.lancement = lancement; /* On initialise l'objet lancement avec celui fournit en argument */
		bombeSolo=false;
		bombeMultiJ1=false;
		bombeMultiJ2=false;
		mvmtHaut1=0; mvmtBas1=0; mvmtGauche1=0; mvmtDroite1=0;
		mvmtHaut2=0; mvmtBas2=0; mvmtGauche2=0; mvmtDroite2=0;
	}
	
	public void caseMulti() {
		
		if (clavier.toucheAppuiMulti()==true) {
			if (clavier.toucheAppuiMultiJ1()==true) {
				if (clavier.estHautJ1()==true){
					mvmtHaut1++;
					if (mvmtHaut1==35) mvmtHaut1=1;
					lancement.getPlayer1().deplacementHaut(mvmtHaut1);
					bombeMultiJ1=false;
					mvmtBas1=0; mvmtGauche1=0; mvmtDroite1=0;
				}
				if (clavier.estBasJ1()==true){
					mvmtBas1++;
					if (mvmtBas1==35) mvmtBas1=1;
					lancement.getPlayer1().deplacementBas(mvmtBas1);
					bombeMultiJ1=false;
					mvmtHaut1=0; mvmtGauche1=0; mvmtDroite1=0;
				}
				if (clavier.estGaucheJ1()==true){
					mvmtGauche1++;
					if (mvmtGauche1==35) mvmtGauche1=1;
					lancement.getPlayer1().deplacementGauche(mvmtGauche1);
					bombeMultiJ1=false;
					mvmtHaut1=0; mvmtBas1=0; mvmtDroite1=0;
				}
				if (clavier.estDroiteJ1()==true){
					mvmtDroite1++;
					if (mvmtDroite1==35) mvmtDroite1=1;
					lancement.getPlayer1().deplacementDroite(mvmtDroite1);
					bombeMultiJ1=false;
					mvmtHaut1=0; mvmtBas1=0; mvmtGauche1=0;
				}
				if (clavier.estEspaceJ1()==true){
					bombeMultiJ1=true;
				}
			}
			if (clavier.toucheAppuiMultiJ2()==true) {
				if (clavier.estHautJ2()==true){
					mvmtHaut2++;
					if (mvmtHaut2==35) mvmtHaut2=1;
					lancement.getPlayer2().deplacementHaut(mvmtHaut2);
					bombeMultiJ2=false;
					mvmtBas2=0; mvmtGauche2=0; mvmtDroite2=0;
				}
				if (clavier.estBasJ2()==true){
					mvmtBas2++;
					if (mvmtBas2==35) mvmtBas2=1;
					lancement.getPlayer2().deplacementBas(mvmtBas2);
					bombeMultiJ2=false;
					mvmtHaut2=0; mvmtGauche2=0; mvmtDroite2=0;
				}
				if (clavier.estGaucheJ2()==true){
					mvmtGauche2++;
					if (mvmtGauche2==35) mvmtGauche2=1;
					lancement.getPlayer2().deplacementGauche(mvmtGauche2);
					bombeMultiJ2=false;
					mvmtHaut2=0; mvmtBas2=0; mvmtDroite2=0;
				}
				if (clavier.estDroiteJ2()==true){
					mvmtDroite2++;
					if (mvmtDroite2==35) mvmtDroite2=1;
					lancement.getPlayer2().deplacementDroite(mvmtDroite2);
					bombeMultiJ2=false;
					mvmtHaut2=0; mvmtBas2=0; mvmtGauche2=0;
				}
				if (clavier.estEspaceJ2()==true){
					bombeMultiJ2=true;
				}
			}
		}
	} /* Dans le cas du multijoueur, avec les getTouches du clavier si on a une valeur true c'est qu'une touche a ete pressee. Si c'est ZQSD l'objet player1 present dans l'objet lancement va subir un deplacement. Si c'est HAUT BAS GAUCHE DROITE c'est l'objet player2 de l'objet lancement qui va subir les deplacements voulus */
	
	public void caseSolo() {
		
		if (clavier.toucheAppuiSolo()==true) {
			if (clavier.estHautJ1()==true){
				mvmtHaut1++;
				if (mvmtHaut1==35) mvmtHaut1=1;
				lancement.getPlayer1().deplacementHaut(mvmtHaut1);
				bombeSolo=false;
				mvmtBas1=0; mvmtGauche1=0; mvmtDroite1=0;
			}
			if (clavier.estBasJ1()==true){
				mvmtBas1++;
				if (mvmtBas1==35) mvmtBas1=1;
				lancement.getPlayer1().deplacementBas(mvmtBas1);
				bombeSolo=false;
				mvmtHaut1=0; mvmtGauche1=0; mvmtDroite1=0;
			}
			if (clavier.estGaucheJ1()==true){
				mvmtGauche1++;
				if (mvmtGauche1==35) mvmtGauche1=1;
				lancement.getPlayer1().deplacementGauche(mvmtGauche1);
				bombeSolo=false;
				mvmtHaut1=0; mvmtBas1=0; mvmtDroite1=0;
			}
			if (clavier.estDroiteJ1()==true){
				mvmtDroite1++;
				if (mvmtDroite1==35) mvmtDroite1=1;
				lancement.getPlayer1().deplacementDroite(mvmtDroite1);
				bombeSolo=false;
				mvmtHaut1=0; mvmtBas1=0; mvmtGauche1=0;
			}
			if (clavier.estEspaceJ1()==true){
				bombeSolo=true;
			}
		}
	} /* Dans le cas du mode solo, avec les getTouches du clavier si on a une valeur true c'est qu'une touche a ete pressee. Si c'est ZQSD c'est l'objet player1, le seul player de l'objet lancement qui va subir les deplacements voulus */
	
	public boolean getBombeSolo() {
		
		return bombeSolo;
	}
	
	public boolean getBombeMultiJ1() {
		return bombeMultiJ1;
	}
	
	public boolean getBombeMultiJ2() {
		return bombeMultiJ2;
	}
}