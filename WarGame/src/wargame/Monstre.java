package wargame;

public class Monstre extends Soldat
{
	private final String NOM;
	private final TypesM TYPE;
	
	public Monstre(Carte carte, TypesM type, String nom, Position pos) {
		super(carte, type.getPoints(), type.getPortee(),type.getPuissance(), type.getTir(), type.getPorteeDepl(), pos);
		NOM = nom; 
		TYPE = type;
	}

	@Override
	public int getTour() {return 0;}
	public TypesM getType() {return TYPE;}
	public String getNom() {return NOM;}

	@Override
	public void joueTour(int tour) 
	{
		
	}
	
}
