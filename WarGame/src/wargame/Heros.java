package wargame;

public class Heros extends Soldat
{
	private final String NOM;
	private final TypesH TYPE;
	public boolean peutAttaquer, peutBouger;
	
	public Heros(Carte carte, TypesH type, String nom, Position pos) {
		super(carte, type.getPoints(), type.getPortee(),type.getPuissance(), type.getTir(), type.getPorteeDepl(), pos);
		NOM = nom; 
		TYPE = type;
		peutAttaquer = peutBouger = true;
	}

	public int getTour() {return 0;}
	public TypesH getType() {return TYPE;}
	public String getNom() {return NOM;}
}
