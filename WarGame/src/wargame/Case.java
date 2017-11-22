package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Case implements Dessinable
{
	private int x,y;
	private Color couleur = Color.DARK_GRAY;
	
	public void setPos(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
	
	public void setColor(Color _couleur)
	{
		couleur = _couleur;
	}
	
	public void seDessiner(Graphics g)
	{
		g.setColor(couleur);
		for(int i = 0; i < 50; i++)
		{
			g.drawLine(x+i, y, x+i, y+50);
		}
	}
}
