package wargame;

public abstract class Soldat extends Element implements ISoldat{

	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
	private int pointsDeVie;
	private Carte carte;
	private Position pos;
	private TypesH t;
	
	Soldat(Carte carte, int pts, int portee, int puiss, int tir, Position pos) {
		POINTS_DE_VIE_MAX = pointsDeVie = pts;
		PORTEE_VISUELLE = portee; 
		PUISSANCE = puiss; 
		TIR = tir;
		this.carte = carte; 
		this.pos = pos;
	}

	
	@Override
	public abstract int getPoints();

	@Override
	public abstract int getTour();

	@Override
	public abstract int getPortee();

	@Override
	public abstract void joueTour(int tour);

	@Override
	public abstract void combat(Soldat soldat);

	@Override
	public abstract void seDeplace(Position newPos);

}
