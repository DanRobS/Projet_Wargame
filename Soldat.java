 package wargame;


public abstract class Soldat extends Element implements ISoldat{

	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
	private int pointsDeVie;
	private Carte carte;
	private Position pos;
	
	public Soldat(Carte carte, int pts, int portee, int puiss, int tir, Position pos) {
		this.pos = pos;
		POINTS_DE_VIE_MAX = pointsDeVie = pts;
		PORTEE_VISUELLE = portee; 
		PUISSANCE = puiss; 
		TIR = tir;
		this.carte = carte; 
		this.pos = pos;
	}
	
	

	public int getPoints(){
		return this.pointsDeVie;
	}

	
	public abstract int getTour();

	
	public int getPortee(){
		return this.PORTEE_VISUELLE;
	}
	
	
	//Tour de jeu IA
		public void joueTour(int tour)
		{
			
		}

	
	public void combat(Soldat soldat){
		if(this.pos.estVoisine(soldat.pos)){
			soldat.pointsDeVie = (int) (soldat.pointsDeVie - (this.PUISSANCE * Math.random()));
		}
		else {
			//combat à distance, à gérer plus tard
		}
	}

	
	public void seDeplace(Position newPos){
		setPos(newPos);
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

}