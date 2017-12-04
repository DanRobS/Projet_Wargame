package wargame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Case implements IConfig
{
	private Element elem;
	private int x,y;
	private Color couleurCase = Color.DARK_GRAY;
	private Color couleurTxt = Color.WHITE;
	private String nom = "";
	public boolean peutBouger, isVide;
	
	public Case(int _x, int _y)
	{
		x = _x;
		y = _y;
		peutBouger = false;
		isVide = true;
		couleurCase = COULEUR_VIDE;
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
	
	public Color getColor() { return couleurCase; }
	
	public Position getPosTab() { return new Position(x/(NB_PIX_CASE+1),y/(NB_PIX_CASE+1)); }
	public Position getPos() { return new Position(x,y); }
	public void reset()
	{
		elem = null;
		peutBouger = false;
		isVide = true;
		couleurCase = COULEUR_VIDE;
	}
}
