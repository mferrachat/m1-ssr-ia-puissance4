

/*
 * TP 3 : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Programme principal du jeu du puissance 4.
 * 
 */
public class Puissance4 {

	public static void main(String[] args) {
		//cr�ation des joueurs et appel de la fonction jouer
		FonctionEvaluation funct1 = new FonctionEvaluationProf();
		FonctionEvaluation funct2 = new FonctionEvaluationPerso();
		Joueur joueur1 = new JoueurAlphaBeta(0, funct2);
		Joueur joueur2 = new JoueurAlphaBeta(6, funct1);
		
		jouer(joueur1, joueur2);
	}
	
	
	/**
	 * Fonction qui effectue la boucle de jeu.
	 * 
	 * @param joueur1 : le premier joueur.
	 * @param joueur2 : le second joueur.
	 */
	public static void jouer(Joueur joueur1, Joueur joueur2){
		Resultat res;
		Grille grille = new Grille();
		
		Joueur joueurCour = joueur1;
		int numJoueur = Grille.JOUEUR1; //le joueur 1 commence à jouer
		
		int vainqueur = 0;//match nul
		boolean jeuFini = false;
		
		
		//boucle de jeu
		while(!jeuFini){
			//affichage de la grille 
			//System.out.println(grille);
			//faire jouer le joueur courant et passer au suivant
			res = joueurCour.coup(grille, numJoueur);
			if(grille.coupGagnant(numJoueur, res.getColonne())){
				jeuFini = true;
				vainqueur = numJoueur;
			}
			grille.joueEn(numJoueur, res.getColonne());
			
			if(grille.estPleine())
				jeuFini = true;
			
			numJoueur = Grille.joueurSuivant(numJoueur);
			joueurCour = (numJoueur == Grille.JOUEUR1) ? joueur1 : joueur2;
		}//while - boucle de jeu
		
		//affichage de la grille 
		System.out.println(grille);
		
		//affichage du vainqueur
		switch(vainqueur){
			case Grille.JOUEUR1:
				System.out.println("Victoire du joueur 1");
				break;
			case Grille.JOUEUR2:
				System.out.println("Victoire du joueur 2");
				break;
			default:
				System.out.println("Match nul");
		}
	}

}
