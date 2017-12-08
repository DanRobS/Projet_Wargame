package wargame;

/**
 * Class Heros
 */
public class Heros extends Soldat
{
	private final String NOM;
	private final TypesH TYPE;
	public boolean peutAttaquer, peutBouger;
	
	/**
	 * Constructeur
	 */
	public Heros(Carte carte, TypesH type, String nom, Position pos) 
	{
		super(carte, type.getPoints(), type.getPortee(),type.getPuissance(), type.getTir(), type.getPorteeDepl(), pos);
		NOM = nom; 
		TYPE = type;
		peutAttaquer = peutBouger = true;
	}
	
	/**
	 * get le type du heros
	 * @return le type de heros
	 */
	public TypesH getType() {return TYPE;}
	
	/**
	 * get le nom du heros
	 * @return le nom du heros
	 */
	public String getNom() {return NOM;}
}
