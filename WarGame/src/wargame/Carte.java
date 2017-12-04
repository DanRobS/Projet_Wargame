package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import wargame.ISoldat.TypesH;
import wargame.ISoldat.TypesM;
import wargame.Obstacle.TypeObstacle;

public class Carte implements ICarte, IConfig
{
	private Case[][] tabCase;
	private ArrayList<Heros> hero;
	private ArrayList<Monstre> monstre;
	private ArrayList<Obstacle> obstacle;
	
	private int tour;
	private double deplacementX, deplacementY;
	
	public Carte()
	{
		tour = 0;
		
		//Initialisation du tableau
		tabCase = new Case[LARGEUR_CARTE][HAUTEUR_CARTE];
		for(int i = 0; i<LARGEUR_CARTE;i++)
		{
			for(int j = 0; j < HAUTEUR_CARTE; j++)
			{
				tabCase[i][j] = new Case(i*(NB_PIX_CASE+1)+1, j*(NB_PIX_CASE+2)+1);
			}
		}
		
		//Initialisation listes
		hero = new ArrayList<Heros>();
		monstre = new ArrayList<Monstre>();
		obstacle = new ArrayList<Obstacle>();
		initObstacle();
		initHero();
		initMonstre();
		
		//Génération de la carte
		deplacementX = deplacementY = 0;
		
		
	}
	
	
	@Override
	public Case getElement(Position pos) 
	{
		return tabCase[pos.getX()][pos.getY()];
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
		
		for(itForl = 0; itForl < LARGEUR_CARTE; itForl++)
		{
			for(itForh = 0; itForh < HAUTEUR_CARTE; itForh++)
			{
				if(!hero.contains(tabCase[itForl][itForh].getElement())) tabCase[itForl][itForh].seDessiner(g);
			}
		}
		
		for(itForl = 0; itForl < hero.size(); itForl++)
		{
			tabCase[hero.get(itForl).getPos().getX()][hero.get(itForl).getPos().getY()].setColor(COULEUR_HEROS);
			tabCase[hero.get(itForl).getPos().getX()][hero.get(itForl).getPos().getY()].seDessiner(g);
		}
	}

	public int getNbHeros() { return hero.size(); }
	
	public int getNbMonstres() { return monstre.size(); }
	
	public void setDeplacementX(double x) { deplacementX = x; }
	
	public void setDeplacementY(double y) { deplacementY = y; }
	
	public int getTour() { return tour; }
	
	private void initHero()
	{
		//Declarations
		int itFor;
		TypesH h;
		Position pos;
		Heros hr;
						
		//Instructions
		for(itFor = 0; itFor < NB_MONSTRES; itFor++)
		{
			h = TypesH.getTypeHAlea();
			pos = getRandom(0,0,(int)(LARGEUR_CARTE/4),HAUTEUR_CARTE);
			hr = new Heros(this, h, "", pos);
			hero.add(hr);
			tabCase[pos.getX()][pos.getY()].setElement(hr);
			tabCase[pos.getX()][pos.getY()].setColor(COULEUR_HEROS);
			tabCase[pos.getX()][pos.getY()].peutBouger = true;
		}
	}
	
	private void initMonstre()
	{
		//Declarations
		int itFor;
		TypesM m;
		Position pos;
		Monstre mstr;
				
		//Instructions
		for(itFor = 0; itFor < NB_MONSTRES; itFor++)
		{
			m = TypesM.getTypeMAlea();
			pos = getRandom((int)(LARGEUR_CARTE-(LARGEUR_CARTE/4)),0,LARGEUR_CARTE,HAUTEUR_CARTE);
			mstr = new Monstre(this, m, "", pos);
			monstre.add(mstr);
			tabCase[pos.getX()][pos.getY()].setElement(mstr);
			tabCase[pos.getX()][pos.getY()].setColor(COULEUR_MONSTRES);
		}
	}
	
	private void initObstacle()
	{
		//Declarations
		int itFor;
		TypeObstacle o;
		Position pos;
		Obstacle obs;
		
		//Instructions
		for(itFor = 0; itFor < NB_OBSTACLES; itFor++)
		{
			o = TypeObstacle.getObstacleAlea();
			pos = getRandom(0,0,LARGEUR_CARTE,HAUTEUR_CARTE);
			obs = new Obstacle(o, pos);
			obstacle.add(obs);
			tabCase[pos.getX()][pos.getY()].setElement(obs);
			tabCase[pos.getX()][pos.getY()].setColor(o.getColor());
		}
	}
	
	//Return une Position vide
	private Position getRandom(int _xMin, int _yMin, int _xMax, int _yMax)
	{
		//Declarations
		Position pos;
		Random random;
		boolean isGood;
		int xRand, yRand, itFor;
		
		//Initializations
		random = new Random();
		isGood = false;
		
		//Instructions
		debut : do
		{
			xRand = random.nextInt(_xMax - _xMin) + _xMin;
			yRand = random.nextInt(_yMax - _yMin) + _yMin;
			
			pos = new Position(xRand, yRand);
			for(itFor = 0; itFor < obstacle.size(); itFor++)
			{
				if(obstacle.get(itFor).getPos() == pos) continue debut;
			}
			
			for(itFor = 0; itFor < hero.size(); itFor++)
			{
				if(hero.get(itFor).getPos() == pos) continue debut;
			}
			
			for(itFor = 0; itFor < monstre.size(); itFor++)
			{
				if(monstre.get(itFor).getPos() == pos) continue debut;
			}
			
			isGood = true;
			
		}while(!isGood);
		
		return pos;
	}
	
	public void resetCase(Case _case)
	{
		tabCase[_case.getPosTab().getX()][_case.getPosTab().getY()] = new Case(_case.getPos().getX(), _case.getPos().getY());
	}
	
	public void setHero(Heros _hero, Case _case)
	{
		_hero.seDeplace(_case.getPosTab());
		tabCase[_case.getPosTab().getX()][_case.getPosTab().getY()].setElement(_hero);
		_case.peutBouger = true;
		_case.setColor(COULEUR_HEROS);
	}
}
