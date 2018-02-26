
public class JoueurAlphaBeta implements Joueur {
	private int m_depth;
	private FonctionEvaluation m_funct;
	
	public JoueurAlphaBeta(int depth, FonctionEvaluation funct){
		this.m_depth = depth;
		this.m_funct = funct;
	}

	@Override
	public Resultat coup(Grille grille, int joueur) {
		int coup = 0;
		double val = FonctionEvaluation.MIN, val_tmp = FonctionEvaluation.MIN;
		Grille g_tmp;
		for(int i : grille.generateurCoups()){
			g_tmp = new Grille(grille);
			g_tmp.joueEn(joueur, i);
			val_tmp = this.alphabeta(g_tmp, FonctionEvaluation.MIN, FonctionEvaluation.MAX, m_depth, joueur, true);
			if(val_tmp > val){
				val = val_tmp;
				coup = i;
			}
		}
		return new Resultat(coup, 0);
	}
	
	private double alphabeta(Grille g, double alpha, double beta, int depth, int joueur, boolean hisTurn){
		double result = 0.0;
		if((depth == 0) || g.estPleine())
			result = m_funct.evaluation(g, joueur);
		else{
			Grille tmp;
			result = hisTurn ? FonctionEvaluation.MIN : FonctionEvaluation.MAX;
			for(int i : g.generateurCoups()){
				tmp = new Grille(g);
				tmp.joueEn(joueur, i); 
				if(hisTurn){
					result = Math.max(result, this.alphabeta(tmp, alpha, beta, depth-1, Grille.joueurSuivant(joueur), !hisTurn));
					if(alpha >= result)
						return result;
					beta = Math.min(beta, result);
				}
				else{
					result = Math.min(result, this.alphabeta(tmp, alpha, beta, depth-1, Grille.joueurSuivant(joueur), !hisTurn));
					if(beta <= result)
						return result;
					alpha = Math.max(alpha, result);
				}
			}
		}
		return result;
	}
}
