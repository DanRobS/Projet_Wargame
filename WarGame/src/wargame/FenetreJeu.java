package wargame;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Classe fenetre, gere la fenetre du projet
 */
public class FenetreJeu implements IConfig
{
	/**
	 * Main du projet, construit la fenetre et l'affiche
	 */
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("WarGame");
		PanneauJeu panneau = new PanneauJeu(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //IMPORTANT -> libérer mémoire
		frame.setSize(1920, 1080);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.add(panneau);
		frame.setVisible(true);
		panneau.setFocusable(true);
		panneau.requestFocus();
		panneau.setAutoscrolls(false);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorImage = tk.getImage(tk.getClass().getResource("/wargame/Images/Curseur.png"));
		Cursor cursor = tk.createCustomCursor(cursorImage, new Point(0, 0), "Custom Cusor Not Paint");
		
		frame.setCursor(cursor);
	}

}
