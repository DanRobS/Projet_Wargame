package wargame;

import java.awt.Color;

/**
 * Classe element qui renvoie et modife
 * La position la couleur et la visibiliter
 * 
 */

public abstract class Element implements IConfig {

	protected Position position;
	protected boolean elementVisible;
	protected Color couleur;
	
	/******* les getters *******/
	
	/**
	 * getters de position, couleur et elementVisible
	 * @return position, couleur et elelntVisible 
	 */
	
	public Position getPosition() {
		return this.position;
	}
	
	public Color getCouleur() {
		return this.couleur;
	}
	
	public boolean getElementVisible() {
		return this.elementVisible;
	}
	
	/******* Les setters *******/
	
	/**
	 * setters de position, couleur et elementVisible
	 * @param position, couleur et elementVisible
	 */
	
	public void setPosition(Position position) {
		this.position=position;
	}
	
	public void setColor(Color couleur) {
		this.couleur=couleur;
	}
	
	public void setElementVisible(boolean elementVisible) {
		this.elementVisible=elementVisible;
	}
	
	/******* toString peut etre utile pour decrire l'element  *******/
	
	/**
	 * Class toString pour la description de l'elemnt
	 * @return Chaine de character décrit l'element
	 */
	
	public String toString() {
		String s = "Element";
		if(elementVisible)
			return s+=""+this.getClass().getSimpleName();
		else
			return s+=" invisible";
		
	}


}
