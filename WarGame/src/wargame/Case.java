package wargame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * 
 * Classe qui gere une case du tableau de jeu
 *
 */
public class Case implements IConfig
{
	private Element elem;
	private int x,y;
	private Color couleurCase = Color.DARK_GRAY;
	private Color couleurCaseSec = Color.DARK_GRAY;
	private Color couleurTxt = Color.WHITE;
	private String nom = "";
	public boolean peutBouger, isVide;
	
	/**
	 * Constructeur
	 * Initialise la case a vide
	 */
	public Case(int _x, int _y)
	{
		x = _x;
		y = _y;
		peutBouger = false;
		isVide = true;
		couleurCase = COULEUR_VIDE;
		couleurCaseSec = COULEUR_VIDE;
	}
	
	/**
	 * Set la position d'une case sur l'ecran
	 * @param _x _y La position a l'ecran
	 */
	public void setPos(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
	
	/**
	 * Set la couleur d'une Case
	 * @param _couleur la couleur que l'on veut set
	 * @param _elem, por savoir si on veut modifier la couleur principale ou secondaire
	 */
	public void setColor(Color _couleur, boolean _elem)
	{
		if(_elem)couleurCase = _couleur;
		else couleurCaseSec = _couleur;
		
	}
	
	/**
	 * Set le nom ecrit sur une Case
	 * @param _nom, le nom a inscrire
	 */
	public void setNom(String _nom)
	{
		nom = _nom;
	}
	
	/**
	 * get l'element present dans la case
	 * @return l'element present dans la case
	 */
	public Element getElement() { return elem; }
	
	/**
	 * Set l'element present dans la case
	 * @param _elem L'element a inserer
	 */
	public void setElement(Element _elem) 
	{
		elem = _elem; 
		isVide = false;
	}
	
	/**
	 * Dessine la Case
	 * @param g le Graphics
	 */
	public void seDessiner(Graphics g)
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
	
	/**
	 * get la couleur principale
	 * @return La couleur principale de la Case
	 */
	public Color getColor() { return couleurCase; }
	
	/**
	 * get la couleur secondaire
	 * @return La couleur secondaire de la Case
	 */
	public Color getColorSec() { return couleurCaseSec; }
	
	/**
	 * get la position de la case dans le tableau
	 * @return la position de la case dans le tableau
	 */
	public Position getPosTab() { return new Position(x/(NB_PIX_CASE+1),y/(NB_PIX_CASE+1)); }
	
	/**
	 * get la position de la case sur l'ecran
	 * @return la position de la case sur l'ecran
	 */
	public Position getPos() { return new Position(x,y); }
	
	/**
	 * Remet la case a 0
	 */
	public void reset()
	{
		elem = null;
		peutBouger = false;
		isVide = true;
		couleurCase = couleurCaseSec = COULEUR_VIDE;
	}
}
