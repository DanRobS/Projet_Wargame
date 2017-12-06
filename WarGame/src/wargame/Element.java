package wargame;

public abstract class Element implements IConfig
{
	public Position pos;

	public abstract Position getPos();
	
}
