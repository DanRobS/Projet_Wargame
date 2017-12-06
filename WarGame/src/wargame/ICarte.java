package wargame;

import java.awt.Graphics;

import javax.swing.JPanel;

public interface ICarte 
{
	Case getElement(Position pos);
	Position trouvePositionVide(); // Trouve aléatoirement une position vide sur la carte
	Position trouvePositionVide(Position pos); // Trouve une position vide choisie aléatoirement parmi les 8 positions adjacentes de pos
	Heros trouveHeros(); // Trouve aléatoirement un héros sur la carte
	Heros trouveHeros(Position pos); // Trouve un héros choisi aléatoirement
									 // parmi les 8 positions adjacentes de pos
	boolean deplaceSoldat(Position pos, Soldat soldat);
	void mort(Soldat perso) throws FinDuJeu;
	boolean actionHeros(Position pos, Position pos2);
	void jouerSoldats(PanneauJeu pj) throws FinDuJeu;
	void toutDessiner(Graphics g, JPanel panneau);
}