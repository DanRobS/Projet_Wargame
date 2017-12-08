package wargame;

import java.awt.Graphics;

import javax.swing.JPanel;

public interface ICarte 
{
	Case getElement(Position pos);
	void mort(Soldat perso) throws FinDuJeu;
	void jouerSoldats(PanneauJeu pj) throws FinDuJeu;
	void toutDessiner(Graphics g, JPanel panneau);
}