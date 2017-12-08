package wargame;

/**
 * Classe Soldat
 */
public abstract class Soldat extends Element implements ISoldat
{
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE, PORTEE_DEPLACEMENT;
	private int pointsDeVie;
	private Carte carte;
	private Position pos;
	
	/**
	 * Constructeur
	 */
	public Soldat(Carte carte, int pts, int portee, int puiss, int tir, int porteeDepl, Position pos) 
	{
		this.pos = pos;
		POINTS_DE_VIE_MAX = pointsDeVie = pts;
		PORTEE_VISUELLE = portee; 
		PUISSANCE = puiss; 
		TIR = tir;
		PORTEE_DEPLACEMENT = porteeDepl;
		this.carte = carte; 
		this.pos = pos;
	}

	/**
	 * Combat entre deux soldats
	 * @param soldat Soldat attaque
	 */
	public void combat(Soldat soldat) throws FinDuJeu 
	{
		if(getPos().estVoisine(soldat.getPos()))	//Corps à corps
		{
			soldat.setDegat(getPuissance());
		}
		else	//Distance
		{
			soldat.setDegat(getTir());
		}
	}

	/**
	 * Gere le deplacement d'un soldat
	 * @param newPos la nouvelle position du soldat
	 */
	public void seDeplace(Position newPos) 
	{
		pos = newPos;
	}
	
	/**
	 * Set des degats a un soldat
	 * @param _degat nombre de PV a retirer au soldat
	 */
	public void setDegat(int _degat) throws FinDuJeu
	{
		pointsDeVie -= _degat;
		if(pointsDeVie <= 0) carte.mort(this);
	}
	
	/**
	 * Get le nombre de points de vie
	 * @return le nombre de points de vie
	 */
	public int getPoints() { return pointsDeVie; }
	
	/**
	 * Get le nombre de points de vie max
	 * @return le nombre de points de vie max
	 */
	public int getPointsMax() { return POINTS_DE_VIE_MAX; }
	
	/**
	 * Get la puissance du soldat
	 * @return la puissance du soldat
	 */
	public int getPuissance() { return PUISSANCE; }
	
	/**
	 * Get la puissance de tir du soldat
	 * @return la puissance de tir du soldat
	 */
	public int getTir() { return TIR; }
	
	/**
	 * Get le numero du tour
	 */
	public int getTour() { return carte.getTour(); }
	
	/**
	 * Get la portee visuelle du soldat
	 * @return la portee visuelle du soldat
	 */
	public int getPortee() { return PORTEE_VISUELLE; }
	
	/**
	 * Get la portee de deplacement du soldat
	 * @return la portee de deplacement du soldat
	 */
	public int getPorteeDepl() { return PORTEE_DEPLACEMENT; }
	
	/**
	 * Get la position du soldat
	 * @return la position du soldat
	 */
	public Position getPos() { return pos; }
}
