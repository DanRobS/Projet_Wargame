package wargame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Case
{
	private Element elem;
	private int x,y;
	private Color couleurCase = Color.DARK_GRAY;
	private Color couleurTxt = Color.WHITE;
	private String nom = "";
	public boolean peutBouger, isVide;
	
	public Case()
	{
		peutBouger = false;
		isVide = true;
	}
	
	public void setPos(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
	
	public void setColor(Color _couleur)
	{
		couleurCase = _couleur;
	}
	
	public void setNom(String _nom)
	{
		nom = _nom;
	}
	
	public Element getElement() { return elem; }
	public void setElement(Element _elem) 
	{
		elem = _elem; 
		isVide = false;
	}
	
	public void seDessiner(Graphics g)
	{
		g.setColor(couleurCase);
		for(int i = 0; i < 50; i++)
		{
			g.drawLine(x+i, y, x+i, y+50);
		}
		g.setColor(couleurTxt);
		g.setFont((new Font("Courier New", Font.PLAIN, 40)));
		g.drawString(nom, x+10, y+40);
	}
}
