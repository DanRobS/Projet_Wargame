package wargame;

import java.awt.Color;

public interface IConfig 
{
	int LARGEUR_CARTE = 30; // en nombre de cases
	int HAUTEUR_CARTE = 15; // en nombre de cases
	int NB_PIX_CASE = 50;
	
	int POSITION_X = 100; // Position de la fen�tre
	int POSITION_Y = 50; // Position de la fen�tre
	
	int NB_HEROS = 6;
	int NB_MONSTRES = 1;
	int NB_OBSTACLES = 20;
	
	Color COULEUR_VIDE = Color.white;
	Color COULEUR_INCONNU = Color.lightGray;
	Color COULEUR_TEXTE = Color.black;
	Color COULEUR_MONSTRES = Color.orange;
	Color COULEUR_HEROS = Color.red;
	Color COULEUR_HEROS_DEJA_JOUE = Color.pink;
	Color COULEUR_EAU = Color.blue;
	Color COULEUR_FORET = Color.green;
	Color COULEUR_ROCHER = Color.gray;
}