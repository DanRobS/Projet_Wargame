package wargame;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FenetreJeu implements IConfig
{

	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Test");
		PanneauJeu panneau = new PanneauJeu(frame);
		//frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //IMPORTANT -> libérer mémoire
		frame.setSize(1920, 1080);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.add(panneau);
		frame.setVisible(true);
		panneau.setFocusable(true);
		panneau.requestFocus();
		panneau.setAutoscrolls(false);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorImage = tk.getImage("C:\\Users\\VOCAN\\git\\Projet_Wargame\\WarGame\\src\\Images\\Curseur.png");
		Cursor cursor = tk.createCustomCursor(cursorImage, new Point(0, 0), "Custom Cusor Not Paint");
		
		frame.setCursor(cursor);
	}

}
