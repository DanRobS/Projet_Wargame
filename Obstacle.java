package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends Element {
	
	private static Color COULEUR_ROCHER;
	private static Color COULEUR_FORET;
	private static Color COULEUR_EAU;
	
	private Position pos;
	
	public enum TypeObstacle {
		ROCHER (COULEUR_ROCHER), FORET (COULEUR_FORET), EAU (COULEUR_EAU);
		private final Color COULEUR;
		
		TypeObstacle(Color couleur){ 
			COULEUR = couleur; 
		}
		
		public static TypeObstacle getObstacleAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
	
	private TypeObstacle TYPE;
	
	Obstacle(TypeObstacle type, Position pos){
		TYPE = type; this.pos = pos; 
	}
	
	public String toString(){ 
		return ""+TYPE; 
	}

	/*
	public static void main(String[] args){
		TypeObstacle t = TypeObstacle.EAU;
		Obstacle o = new Obstacle(t,new Position(1,2));
	}*/

}

