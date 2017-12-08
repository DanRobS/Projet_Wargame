package wargame;

/**
 * Classe FinDuJeu, gere l'exception lancee lors de la fin du jeu
 */
public class FinDuJeu extends Exception 
{
	private static final long serialVersionUID = 8045519042622070299L;
	public String nom;
	public FinDuJeu(int _gagnant) 
	{
		super();
		if(_gagnant == 0) nom = "Le joueur gagne";
		else nom = "Le joueur perd";
	}
}
