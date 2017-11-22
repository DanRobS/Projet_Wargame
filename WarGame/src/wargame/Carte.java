package wargame;

import java.awt.Color;
import java.awt.Graphics;

import wargame.Obstacle.TypeObstacle;

public class Carte implements ICarte, IConfig
{
	private Element tabElem[][];
	private int nbHeros, nbMonstres;
	private double deplacementX, deplacementY;
	
	public Carte()
	{
		deplacementX = deplacementY = 0;
		tabElem = new Element[30][15];
		for(int i = 0; i<30;i++)
		{
			for(int j = 0; j < 15; j++)
			{
				tabElem[i][j] = new Element();
				tabElem[i][j].box.setColor(Color.BLUE);
				tabElem[i][j].box.setPos(i*51+1, j*52+1);
			}
		}
	}
	
	
	@Override
	public Element getElement(Position pos) 
	{
		return tabElem[pos.getX()][pos.getY()];
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
				tabElem[itForl][itForh].box.seDessiner(g);;
			}
		}
	}

	public int getNbHeros() { return nbHeros; }
	
	public int getNbMonstres() { return nbMonstres; }
	
	public void setDeplacementX(double x) { deplacementX = x; }
	
	public void setDeplacementY(double y) { deplacementY = y; }
}
