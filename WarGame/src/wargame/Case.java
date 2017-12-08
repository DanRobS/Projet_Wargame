package wargame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Case implements IConfig
{
	private Element elem;
	private int x,y;
	private Color couleurCase = Color.DARK_GRAY;
	private Color couleurCaseSec = Color.DARK_GRAY;
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
		couleurCaseSec = COULEUR_VIDE;
	}
	
	public void setPos(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
	
	public void setColor(Color _couleur, boolean _elem)
	{
		if(_elem)couleurCase = _couleur;
		else couleurCaseSec = _couleur;
		
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
	
	public void seDessiner(Graphics g, JPanel panneau)
	{
		
		g.setColor(couleurCase);
		for(int i = 0; i < NB_PIX_CASE/2; i++)
		{
			g.drawLine(x+i, y, x+i, y+NB_PIX_CASE);
		}
		g.setColor(couleurCaseSec);
		for(int i = NB_PIX_CASE/2; i < NB_PIX_CASE; i++)
		{
			g.drawLine(x+i, y, x+i, y+NB_PIX_CASE);
		}
		g.setColor(couleurTxt);
		g.setFont((new Font("Courier New", Font.PLAIN, 40)));
		g.drawString(nom, x+10, y+40);
	}
	
	public Color getColor() { return couleurCase; }
	public Color getColorSec() { return couleurCaseSec; }
	
	public Position getPosTab() { return new Position(x/(NB_PIX_CASE+1),y/(NB_PIX_CASE+1)); }
	public Position getPos() { return new Position(x,y); }
	public void reset()
	{
		elem = null;
		peutBouger = false;
		isVide = true;
		couleurCase = couleurCaseSec = COULEUR_VIDE;
	}
}
