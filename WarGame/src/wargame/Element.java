package wargame;

/**
 * Classe Element
 */
public abstract class Element implements IConfig
{
	public Position pos;
	
	/**
	 * Methode abstraite get position
	 */
	public abstract Position getPos();
}
