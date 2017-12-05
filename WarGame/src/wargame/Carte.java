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
		tour = 1;
		
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

	//Trouve le héro le plus près de cette position
	public Heros trouveHeros(Position pos) 
	{
		int itFor;
		Heros hr;
		int distance;
		int h = 0;
		
		distance = hero.get(0).getPos().distance(pos);
		
		for(itFor = 1; itFor < hero.size(); itFor++)
		{
			if(distance > hero.get(itFor).getPos().distance(pos)) 
			{
				distance = hero.get(itFor).getPos().distance(pos); 
				h = itFor;
			}
		}
		return hero.get(h);
	}

	@Override
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mort(Soldat perso) 
	{
		tabCase[perso.getPos().getX()][perso.getPos().getY()].reset();
		hero.remove(perso);
		monstre.remove(perso);
		
		if(hero.isEmpty()) 
		{
			//C'est perdu
		}
		else if(monstre.isEmpty())
		{
			//C'est gagne
		}
	}

	@Override
	public boolean actionHeros(Position pos, Position pos2) {
		// TODO Auto-generated method stub
		return false;
	}

	//IA
	public void jouerSoldats(PanneauJeu pj) 
	{
		int itFor;
		Heros hr;
		Position depl, avant;
		
		for(itFor = 0; itFor < monstre.size(); itFor++)
		{
			hr = trouveHeros(monstre.get(itFor).getPos());
			avant = monstre.get(itFor).getPos();
			//Attaque si possible
			if(hr.getPos().distance(monstre.get(itFor).getPos())<= monstre.get(itFor).getPortee())
			{
				monstre.get(itFor).combat(hr);
			}
			else
			{
				//Déplacement aléatoire de 1 case
				depl = getRandom(monstre.get(itFor).getPos().getX()-monstre.get(itFor).getPorteeDepl(), monstre.get(itFor).getPos().getY()-monstre.get(itFor).getPorteeDepl(), monstre.get(itFor).getPos().getX()+monstre.get(itFor).getPorteeDepl(), monstre.get(itFor).getPos().getY()+monstre.get(itFor).getPorteeDepl());
				
				//System.out.println("X : "+depl.getX()+" | Y : "+depl.getY());
				
				getElement(depl).setElement(getElement(monstre.get(itFor).getPos()).getElement());
				
				getElement(depl).setColor(COULEUR_MONSTRES);
				
				monstre.get(itFor).seDeplace(depl);
				
				getElement(avant).reset();
				
				
				
				
				//System.out.println(xTab + " | " + yTab + " | " + carteJeu.getElement(new Position(xTab,yTab)).getPosTab() + " | " + carteJeu.getElement(new Position(xTab,yTab)).getPos());
				
			}
		}
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
		for(itFor = 0; itFor < NB_HEROS; itFor++)
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
			if(_xMin < 0) _xMin = 0;
			if(_xMax >= LARGEUR_CARTE) _xMax = LARGEUR_CARTE-1;
			if(_yMin < 0) _yMin = 0;
			if(_yMax >= HAUTEUR_CARTE) _yMax = HAUTEUR_CARTE-1;
			
			xRand = random.nextInt(_xMax - _xMin) + _xMin;
			yRand = random.nextInt(_yMax - _yMin) + _yMin;
			
			pos = new Position(xRand, yRand);
		
			for(itFor = 0; itFor < obstacle.size(); itFor++) if(obstacle.get(itFor).getPos().getX() == xRand && obstacle.get(itFor).getPos().getY() == yRand) continue debut;
			
			for(itFor = 0; itFor < hero.size(); itFor++) if(hero.get(itFor).getPos().getX() == xRand && hero.get(itFor).getPos().getY() == yRand) continue debut;
			
			for(itFor = 0; itFor < monstre.size(); itFor++) if(monstre.get(itFor).getPos().getX() == xRand && monstre.get(itFor).getPos().getY() == yRand) continue debut;
			
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


	public void addTour() 
	{
		int itFor;
		
		for(itFor = 0; itFor < hero.size(); itFor++)
		{
			((Heros)tabCase[hero.get(itFor).getPos().getX()][hero.get(itFor).getPos().getY()].getElement()).peutBouger = true;
			((Heros)tabCase[hero.get(itFor).getPos().getX()][hero.get(itFor).getPos().getY()].getElement()).peutAttaquer = true;
		}
		
		tour++; 
	}
}
