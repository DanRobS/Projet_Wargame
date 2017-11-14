package wargame;


/**
 * Classe soldat
 * 
 *
 */

public class Soldat extends Element implements ISoldat{
	protected final char MONSTRE = 'M', HERO = 'H';/*Type de soldat*/
	protected int nbrPointsVie;
	protected final int PORTEE_VISUELLE;
	protected final int PUISSANCE_FRAPPE;
	protected final int PUISSANCE_TIR;
	protected final int NBR_POINTS_VIE_MAX;
	protected boolean enRepos = false;
	protected boolean estMort = false;
	protected int aJouer = 0;/*int car le prof à fait int*/
	
	/**
	 * Costructor soldat
	 * 
	 * @param posSoldat
	 * @param porteeVisuelle
	 * @param puissanceFrappe
	 * @param puissanceTir
	 * @param nbrPointsVieMax
	 */
	
	public Soldat(Position posSoldat, int porteeVisuelle, int puissanceFrappe, int puissanceTir,int nbrPointsVieMax ) {
		
		super(); /* super d'Element */
		this.position=posSoldat;
		this.PORTEE_VISUELLE=porteeVisuelle;
		this.PUISSANCE_FRAPPE=puissanceFrappe;
		this.PUISSANCE_TIR=puissanceTir;
		this.NBR_POINTS_VIE_MAX=nbrPointsVieMax;
		
	}


	/**
	 * Les Getters et le setters de soldat
	 * 
	 */
	
	public int getNbrPointsVie() {
		return nbrPointsVie;
	}



	public void setNbrPointsVie(int nbrPointsVie) {
		this.nbrPointsVie = nbrPointsVie;
	}



	public boolean isEnRepos() {
		return enRepos;
	}



	public void setEnRepos(boolean enRepos) {
		this.enRepos = enRepos;
	}



	public boolean isEstMort() {
		return estMort;
	}



	public void setEstMort(boolean estMort) {
		this.estMort = estMort;
	}



	public int getPORTEE_VISUELLE() {
		return PORTEE_VISUELLE;
	}



	public int getPUISSANCE_FRAPPE() {
		return PUISSANCE_FRAPPE;
	}



	public int getPUISSANCE_TIR() {
		return PUISSANCE_TIR;
	}

	/**
	 * getters et methode pour isoldat
	 */

	@Override
	public int getPoints() {
		return this.nbrPointsVie;
	}

	@Override
	public int getTour() {
		return this.aJouer;
	}

	@Override
	public int getPortee() {
		return this.PORTEE_VISUELLE;
	}

	@Override
	public void joueTour(int tour) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void combat(Soldat soldat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seDeplace(Position newPos) {
		// TODO Auto-generated method stub
		
	}

	/****** toString *******/
	
	/**
	 *Class toString pour la description du soldat
	 * @return Chaine de character décrit l'element
	 */
	
	public String toString() {
		String s = "Soldat";
		if(elementVisible)
			return s+="Points de vie"+this.nbrPointsVie+" "+this.getClass().getSimpleName();
		else
			return s+=" invisible";
		
	}

}
