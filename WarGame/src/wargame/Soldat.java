package wargame;

public abstract class Soldat extends Element implements ISoldat
{
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
	private int pointsDeVie;
	private Carte carte;
	private Position pos;
	
	public Soldat(Carte carte, int pts, int portee, int puiss, int tir, Position pos) 
	{
		this.pos = pos;
		POINTS_DE_VIE_MAX = pointsDeVie = pts;
		PORTEE_VISUELLE = portee; 
		PUISSANCE = puiss; 
		TIR = tir;
		this.carte = carte; 
		this.pos = pos;
	}

	//Tour de jeu IA
	public void joueTour(int tour)
	{
		
	}

	
	/*Combat entre deux soldats*/
	public void combat(Soldat soldat) 
	{
		
	}

	/*Gère le déplacement d'un soldat*/
	public void seDeplace(Position newPos) 
	{
		pos = newPos;
	}
	
	public int getPoints() { return pointsDeVie; }
	public int getTour() { return carte.getTour(); }
	public int getPortee() { return PORTEE_VISUELLE; }
	public Position getPos() { return pos; }
}
