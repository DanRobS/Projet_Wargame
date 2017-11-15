 package wargame;


public abstract class Soldat extends Element implements ISoldat{

	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
	private int pointsDeVie;
	private Carte carte;
	private Position pos;
	private TypesH t;
	
	public Soldat(Carte carte, int pts, int portee, int puiss, int tir, Position pos) {
		this.pos = pos;
		POINTS_DE_VIE_MAX = pointsDeVie = pts;
		PORTEE_VISUELLE = portee; 
		PUISSANCE = puiss; 
		TIR = tir;
		this.carte = carte; 
		this.pos = pos;
	}

	public abstract int getPoints();

	
	public abstract int getTour();

	
	public abstract int getPortee();
	
	
	public abstract void joueTour(int tour);

	
	public abstract void combat(Soldat soldat);

	
	public abstract void seDeplace(Position newPos);

}