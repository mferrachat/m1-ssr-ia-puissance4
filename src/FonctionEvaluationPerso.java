/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neofaucheur
 */
public class FonctionEvaluationPerso implements FonctionEvaluation {
	@Override
	public double evaluation(Grille grille, int joueur) {
		double res = 0.0;
		int d1 = 0, d2 = 0, h = 0, v = 0, l;
		
		// On vérifie si la grille est gagnante pour le joueur
		for(int  i = 0; (i < Grille.NB_COLONNES) && (d1 < 4) && (d2 < 4) && (h < 4) && (v < 4); i++){
			l = grille.getLigneLibre(i);
			if(l < 1)
				l = 1;
			d1 = grille.getNbCasesDiagonale1(joueur, l-1, i);
			d2 = grille.getNbCasesDiagonale2(joueur, l-1, i);
			h = grille.getNbCasesHorizontale(joueur, l-1, i);
			v = grille.getNbCasesVerticale(joueur, l-1, i);
		}
		if((d1 >= 4) || (d2 >= 4) || (h >= 4) || (v >= 4))
			res = Double.POSITIVE_INFINITY;
		else{
			boolean gagnant = false;
			int adversaire = Grille.joueurSuivant(joueur);
			for(int i : grille.generateurCoups()){
				gagnant |= grille.coupGagnant(adversaire, i);
			}
			if(gagnant)
				res = Double.NEGATIVE_INFINITY;
		}
		return res;
	}
}
