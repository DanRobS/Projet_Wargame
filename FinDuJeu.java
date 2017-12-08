package wargame;

/**
 * 
 * Classe FinDuJeu qui herite d Exception
 * retourne si le joueur a gagner ou a perdu
 *
 */

public class FinDuJeu extends Exception 
{
	/**
	 * un unique serial version identifier
	 * @see Serializable#serialVersionUID
	 */
	private static final long serialVersionUID = 8045519042622070299L;
	public String nom;
	/**
	 * Constructeur de FinDuJeu
	 * 
	 * @param _gagnant
	 */
	
	public FinDuJeu(int _gagnant) 
	{
		super();
		if(_gagnant == 0) nom = "Le joueur gagne";
		else nom = "Le joueur perd";
	}
}
