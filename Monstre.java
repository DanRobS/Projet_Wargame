package wargame;

import wargame.ISoldat.TypesH;

public class Monstre extends Soldat{

	private final String NOM;
	private final TypesM TYPE;
	
	public Monstre(Carte carte, TypesM type, String nom, Position pos) {
		super(carte, type.getPoints(), type.getPortee(),type.getPuissance(), type.getTir(), pos);
		NOM = nom; 
		TYPE = type;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPortee() {
		// TODO Auto-generated method stub
		return 0;
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

	

}
