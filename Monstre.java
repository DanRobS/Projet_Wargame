package wargame;

public class Monstre extends Soldat{
	public TypesM typeMonstre;
	public char nom;


	
	public Monstre(Position posSoldat, int porteeVisuelle, int puissanceFrappe, int puissanceTir, int nbrPointsVieMax, TypesM typeMons) {
		super(posSoldat, porteeVisuelle, puissanceFrappe, puissanceTir, nbrPointsVieMax);
		// TODO Auto-generated constructor stub
		this.typeMonstre=typeMons;
	}

	private TypesM getTypeMonstre() {
		return typeMonstre;
	}
	
	private char getNom() {
		return nom;
	}
	
	public int getNbrPointsVie() {
		return nbrPointsVie;
	}
	
	/** detail ode l'Hero   **/
	
	public String toString() {
		return " "+getTypeMonstre()+" "+getNom()+" ( "+getNbrPointsVie()+"PV/5)";
	}

	
	
    
}

