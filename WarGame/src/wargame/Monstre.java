package wargame;

/**
 * Class Monstre
 */
public class Monstre extends Soldat
{
	private final String NOM;
	private final TypesM TYPE;
	
	/**
	 * Constructeur
	 * @param carte Carte
	 * @param type Type
	 * @param nom Nom
	 * @param pos Position
	 */
	public Monstre(Carte carte, TypesM type, String nom, Position pos)
	{
		super(carte, type.getPoints(), type.getPortee(),type.getPuissance(), type.getTir(), type.getPorteeDepl(), pos);
		NOM = nom; 
		TYPE = type;
	}
	
	/**
	 * get le type du monstre
	 * @return le type de monstre
	 */
	public TypesM getType() {return TYPE;}
	
	/**
	 * get le nom du monstre
	 * @return le nom du monstre
	 */
	public String getNom() {return NOM;}
}
