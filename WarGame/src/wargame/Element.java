package wargame;

import java.awt.Color;
import java.awt.event.MouseEvent;

public abstract class Element implements IConfig
{
	public Position pos;

	public abstract Position getPos();
	
}
