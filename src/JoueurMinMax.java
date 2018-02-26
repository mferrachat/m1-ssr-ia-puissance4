
public class JoueurMinMax implements Joueur {
	private int m_depth;
	private FonctionEvaluation m_funct;
	
	public JoueurMinMax(int depth, FonctionEvaluation funct){
		this.m_depth = depth;
		this.m_funct = funct;
	}

	@Override
	public Resultat coup(Grille grille, int joueur) {
		int coup = grille.getCoupAleatoire(); //Permet de s'assurer de jouer dans une colonne valide si aucun coup n'est évalué à plus de MIN
		double val = FonctionEvaluation.MIN, val_tmp = FonctionEvaluation.MIN;
		Grille g_tmp;
		for(int i : grille.generateurCoups()){
			g_tmp = new Grille(grille);
			g_tmp.joueEn(joueur, i);
			val_tmp = this.minimax(g_tmp, m_depth, joueur, true);
			if(val_tmp > val){
				val = val_tmp;
				coup = i;
			}
		}
		return new Resultat(coup, 0);
	}
	
	private double minimax(Grille g, int depth, int joueur, boolean hisTurn){
		double result = 0.0;
		if((depth == 0) || g.estPleine())
			result = m_funct.evaluation(g, joueur);
		else{
			Grille tmp;
			result = hisTurn ? FonctionEvaluation.MIN : FonctionEvaluation.MAX;
			for(int i : g.generateurCoups()){
				tmp = new Grille(g);
				tmp.joueEn(joueur, i);
				result = hisTurn ? Math.max(result, this.minimax(tmp, depth-1, Grille.joueurSuivant(joueur), !hisTurn)) : Math.min(result, this.minimax(tmp, depth-1, Grille.joueurSuivant(joueur), !hisTurn));
			}
		}
		return result;
	}
}
