package wargame;

import java.awt.Color;
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
	}

}
