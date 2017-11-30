package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import wargame.Obstacle.TypeObstacle;

public class Carte implements ICarte, IConfig
{
	private ArrayList<ArrayList<Case>> tabCase; //A remplacer par arraylist
	private ArrayList<Case> ligne;
	
	private int nbHeros, nbMonstres, tour;
	private double deplacementX, deplacementY;
	
	public Carte()
	{
		tour = 0;
		
		
		//Génération de la carte
		deplacementX = deplacementY = 0;
		tabCase = new ArrayList<ArrayList<Case>>();
		ligne = new ArrayList<Case>();
		for(int i = 0; i<30;i++)
		{
			for(int j = 0; j < 15; j++)
			{
				ligne.add(new Case());
				ligne.get(j).setColor(Color.BLUE);
				ligne.get(j).setPos(i*51+1, j*52+1);
			}
			tabCase.add(ligne);
			ligne = new ArrayList<Case>();
		}
	}
	
	
	@Override
	public Case getElement(Position pos) 
	{
		return tabCase.get(pos.getX()).get(pos.getY());
	}

	@Override
	public Position trouvePositionVide() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position trouvePositionVide(Position pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Heros trouveHeros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Heros trouveHeros(Position pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mort(Soldat perso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean actionHeros(Position pos, Position pos2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void jouerSoldats(PanneauJeu pj) {
		// TODO Auto-generated method stub
		
	}

	//Utile pour afficher le tableau de jeu
	public void toutDessiner(Graphics g) 
	{
		int itForl, itForh;
		
		for(itForl = 0; itForl < 30; itForl++)
		{
			for(itForh = 0; itForh < 15; itForh++)
			{
				tabCase.get(itForl).get(itForh).seDessiner(g);
			}
		}
	}

	public int getNbHeros() { return nbHeros; }
	
	public int getNbMonstres() { return nbMonstres; }
	
	public void setDeplacementX(double x) { deplacementX = x; }
	
	public void setDeplacementY(double y) { deplacementY = y; }
	
	public int getTour() { return tour; }
}
