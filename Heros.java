package wargame;

public class Heros extends Soldat {
	
	public TypesH typeHero;
	public char nom;

	public Heros(Position posSoldat, int porteeVisuelle, int puissanceFrappe, int puissanceTir, int nbrPointsVieMax, TypesH typeHero) {
		super(posSoldat, porteeVisuelle, puissanceFrappe, puissanceTir, nbrPointsVieMax);
		this.typeHero=typeHero;		
	}
	
	private TypesH getTypeHero() {
		return typeHero;
	}
	
	private char getNom() {
		return nom;
	}
	
	public int getNbrPointsVie() {
		return nbrPointsVie;
	}
	
	/** detail ode l'Hero   **/
	
	public String toString() {
		return " "+getTypeHero()+" "+getNom()+" ( "+getNbrPointsVie()+"PV/5)";
	}

	
	
    
}
