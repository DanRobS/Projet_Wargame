package wargame;

public class Heros extends Soldat {
	
	private final String NOM;
	private final TypesH TYPE;
	
	public Heros(Carte carte, TypesH type, String nom, Position pos) {
		super(carte, type.getPoints(), type.getPortee(),type.getPuissance(), type.getTir(), pos);
		NOM = nom; 
		TYPE = type;
	}

	@Override
	public int getTour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void joueTour(int tour) {
		// TODO Auto-generated method stub
		
	}


	
	
}
