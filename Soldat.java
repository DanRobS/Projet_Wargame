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
	
	public int getaJouer() {
		return aJouer;
	}

	public void setaJouer(int aJouer) {
		this.aJouer = aJouer;
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
	
	
	/**
	 * Methode joueTour
	 * On change la couleur du soldat s'il a déjà jouer
	 */
	

	@Override
	public void joueTour(int tour) {
		this.aJouer=tour;
		
		if(this.aJouer == 1) 
			couleur=COULEUR_HEROS_DEJA_JOUE; /* COULEUR_HEROS_DEJA_JOUE de IConfi */
		
		else if(this.aJouer == 0)
			couleur=COULEUR_HEROS;
		
	}

	
	
	/**
	 * Methode combat implemente le drelement d'un combat
	 * 
	 * 
	 * 
	 */
	
	
	@Override
	public void combat(Soldat soldat) {
		int perdPoint=0;
		int caseAdjacentes=0;
		int nbrPointVie=0;
		
		caseAdjacentes = soldat.getPosition().distance(this.getPosition());//distance a definir dans position
		
		/* Cas cases adjacentes ( Corps-à-corps )*/
		
		if (caseAdjacentes==1) {
			
			perdPoint = (int)(Math.random()*(getPUISSANCE_FRAPPE()-0));
			nbrPointVie=soldat.getNbrPointsVie()-perdPoint;
			
			//actualiser le nbr de points
			soldat.setNbrPointsVie(nbrPointVie);
			this.setaJouer(1);
						
		}	
		
		/* Cas combat à distance */
		
		else {
			
			/*Tester d'abord qu'il y a pas d'opstacle */
			if(!Carte.obstacle()) {  // obstacle a definir sur la carte obstacle
				
				/*Tester la puissance du tire*/
				if(this.getPUISSANCE_TIR()>=caseAdjacentes) {
					perdPoint=this.getPUISSANCE_TIR();
					nbrPointVie=soldat.getNbrPointsVie()-perdPoint;
				
					//actualiser le nbr de points
					soldat.setNbrPointsVie(nbrPointVie);
					this.setaJouer(1);
				}
				
				//si l'adversaire est loin
				else {
					System.out.println("Advairsaire loin");//a ameliorer par une methode dans carte
				}				
			}
			
			//s'il ya un obstacle
			else {
				System.out.println("Existance d'un obstacle entre les deux adversaire");//aussi a ameliorer par une methode dans carte
			}
			
			
		}
		//appel de la methode mort(Soldat soldat) de Carte
		
		if (soldat.getNbrPointsVie()<=0) {
			this.estMort=true;
			Carte.mort(soldat);//Mort aussi a definir dans carte
		}
		
		
	}
	

	

	/**
	 * Methode seDeplasse : met a jour l'objet Soldat
	 * 
	 */
	
	
	@Override
	public void seDeplace(Position newPos) {
		
		this.position=newPos;
		
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
