package jeu;

public class DeplacementEnnemis {

	private FenetrePrincipale lancement; /* Objet de type FenetrePrincipale */
	
	private int mvmtHautE1,mvmtBasE1,mvmtGaucheE1,mvmtDroiteE1,mvmtHautE2,mvmtBasE2,mvmtGaucheE2,mvmtDroiteE2,mvmtHautE3,mvmtBasE3,mvmtGaucheE3,mvmtDroiteE3;
	public DeplacementEnnemis(FenetrePrincipale lancement) {
		
		this.lancement = lancement; /* On initialise l'objet lancement avec celui fournit en argument */
	}
    
    public void mouvement(int valeur) {
    	
    	if (valeur==0) {
    		mvmtHautE1++;
    		mvmtBasE2++;
    		mvmtGaucheE3++;
			if (mvmtHautE1==35) mvmtHautE1=1;
			if (mvmtBasE2==35) mvmtBasE2=1;
			if (mvmtGaucheE3==35) mvmtGaucheE3=1;
    		lancement.getEnnemis1().deplacementHaut(mvmtHautE1);
    		lancement.getEnnemis2().deplacementBas(mvmtBasE2);
    		lancement.getEnnemis3().deplacementGauche(mvmtGaucheE3);
    	}
    	else if (valeur==1) {
    		mvmtDroiteE1++;
    		mvmtBasE3++;
    		mvmtGaucheE2++;
			if (mvmtDroiteE1==35) mvmtDroiteE1=1;
			if (mvmtBasE3==35) mvmtBasE3=1;
			if (mvmtGaucheE2==35) mvmtGaucheE2=1;
    		lancement.getEnnemis1().deplacementDroite(mvmtDroiteE1);
    		lancement.getEnnemis2().deplacementGauche(mvmtGaucheE2);
    		lancement.getEnnemis3().deplacementBas(mvmtBasE3);
    	}
    	else if (valeur==2) {
    		mvmtBasE2++;
    		mvmtBasE1++;
    		mvmtHautE3++;
			if (mvmtHautE3==35) mvmtHautE3=1;
			if (mvmtBasE2==35) mvmtBasE2=1;
			if (mvmtBasE1==35) mvmtBasE1=1;
    		lancement.getEnnemis1().deplacementBas(mvmtBasE1);
    		lancement.getEnnemis2().deplacementDroite(mvmtBasE2);
    		lancement.getEnnemis3().deplacementHaut(mvmtHautE3);
    	}
    	else {
    		mvmtHautE2++;
    		mvmtDroiteE3++;
    		mvmtGaucheE1++;
			if (mvmtHautE2==35) mvmtHautE2=1;
			if (mvmtDroiteE3==35) mvmtDroiteE3=1;
			if (mvmtGaucheE1==35) mvmtGaucheE1=1;
    		lancement.getEnnemis1().deplacementGauche(mvmtGaucheE1);
    		lancement.getEnnemis2().deplacementHaut(mvmtHautE2);
    		lancement.getEnnemis3().deplacementDroite(mvmtDroiteE3);
    	}
    } /* Ici, en fonction de la valeur fournie en argument dans la methode mouvement, celle-ci va demander aux 3 ennemis present dans l'objet lancement d'effectuer un mouvement dans une direction, un ennemi aura une direction differente des 2 autres ennemis */
}