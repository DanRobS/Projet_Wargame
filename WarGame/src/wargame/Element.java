package wargame;

import java.awt.Color;

public class Element implements IConfig
{
	public Case box;
	public Position pos;
	
	public Element()
	{
		box = new Case();
	}
}
