package wargame;

public class FinDuJeu extends Exception 
{
	public String nom;
	public FinDuJeu(int _gagnant) 
	{
		super();
		if(_gagnant == 0) nom = "Le joueur gagne";
		else nom = "Le joueur perd";
	}
}
