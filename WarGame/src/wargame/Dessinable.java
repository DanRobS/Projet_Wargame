package wargame;

import java.awt.Color;
import java.awt.Graphics;

public interface Dessinable 
{
	public static final int WIDTH_MAX = 1920;
	public static final int HEIGHT_MAX = 1080;
	public static final int TAILLE_MAX = 1000;
	public static final Color COULEUR = Color.RED;
	
	public abstract void seDessiner(Graphics g);
}
