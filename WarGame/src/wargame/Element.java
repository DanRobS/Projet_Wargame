package wargame;

/**
 * Class Element
 */
public abstract class Element implements IConfig
{
	public Position pos;
	
	/**
	 * Methode abstraite get position
	 * @return la Position de l'element
	 */
	public abstract Position getPos();
}
