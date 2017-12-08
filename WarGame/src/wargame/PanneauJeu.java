package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * Class PanneauJeu, gere l'affichage
 *
 */
public class PanneauJeu extends JPanel implements IConfig
{
	private static final long serialVersionUID = 1L;
	private JButton btnFdT;
	private Carte carteJeu;
	private Box box;
	private JLabel labelHaut, labelBas;
	private JPanel tableau;
	private boolean enDeplacement = false;
	private boolean enAttaque = false;
	private int xTab, yTab;
	private Position avDeplacement;
	private boolean peutJouer;
	private JFrame frame;
	private int deplX, deplY;
	
	/**
	 * Constructeur
	 * @param _frame la fenetre
	 */
	public PanneauJeu(JFrame _frame)
	{
		deplX = deplY = 0;		
		frame = _frame;
		peutJouer = true;
		
		carteJeu = new Carte();
		
		/*Organisation*/
		setLayout(new BorderLayout());
		
		/*Tableau de jeu*/
		tableau = new JPanel()
				{
					private static final long serialVersionUID = 1L;

					public void paintComponent(Graphics g)
					{	
						super.paintComponent(g);
						g.translate(deplX, deplY);
						carteJeu.toutDessiner(g, this);
					}
				};
		tableau.setBackground(Color.BLACK);
		add("Center", tableau);
		
		
		/*Boutton Fin de tour*/
		btnFdT = new JButton(new ImageIcon(this.getClass().getResource("/wargame/Images/bouton.png")));
		btnFdT.setFocusable(false);
		btnFdT.setMinimumSize(new Dimension(300, 50));
		btnFdT.setMaximumSize(new Dimension(300, 50));
		btnFdT.setPreferredSize(new Dimension(300,50));
		
		btnFdT.addActionListener(new ActionListener()
				{
					
					public void actionPerformed (ActionEvent e)
					{
						/*try {
		                    InputStream is = getClass().getResourceAsStream("/wargame/Musiques/test2.wav");
		                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(is);
		                    
							Clip clip = AudioSystem.getClip();
		                    clip.open(audioIn);
		                    clip.start();
		                } catch (UnsupportedAudioFileException e1) {
		                    e1.printStackTrace();
		                } catch (IOException e1) {
		                    e1.printStackTrace();
		                } catch (LineUnavailableException e1) {
		                    e1.printStackTrace();
		                }*/
						
						//Lancer IA
						jouerIA();
						
						//Debloquer joueur et ajouter tour
						carteJeu.addTour();
					}
				});
		
		
		/*Texte avec bouton*/
		labelHaut = new JLabel();
		labelHaut.setText("Il reste " + carteJeu.getNbHeros() + " Héros et " + carteJeu.getNbMonstres() + " Monstres");
		labelHaut.setFont(new Font("Courier New", Font.ITALIC, 20));
		
		
		/*Box bar*/
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());
		box.add(btnFdT);
		box.add(labelHaut);
		box.add(Box.createHorizontalGlue());
		box.setOpaque(true);
		box.setBackground(Color.LIGHT_GRAY);
		box.setPreferredSize(new Dimension(0, 60));
		add("North", box);
		btnFdT.setToolTipText("Boutton fin du tour");
		
		/*Texte en bas*/
		labelBas = new JLabel();
		labelBas.setText("Description de l'unité sélectionnée");
		labelBas.setFont(new Font("Courier New", Font.ITALIC, 20));
		labelBas.setOpaque(true);
		labelBas.setBackground(Color.LIGHT_GRAY);
		labelBas.setPreferredSize(new Dimension(0, 30));
		add("South", labelBas);
		
		
		/*Gestion clic*/
		tableau.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						Toolkit.getDefaultToolkit().beep();
						
						xTab = ((int)e.getX()-deplX)/(NB_PIX_CASE+1);
						yTab = ((int)e.getY()-deplY)/(NB_PIX_CASE+1);
						if(e.getButton() == MouseEvent.BUTTON1 && xTab < LARGEUR_CARTE && yTab < HAUTEUR_CARTE && carteJeu.getElement(new Position(xTab,yTab)).peutBouger == true && ((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutBouger == true)
						{
							avDeplacement = new Position(carteJeu.getElement(new Position(xTab,yTab)).getPos().getX(),carteJeu.getElement(new Position(xTab,yTab)).getPos().getY());
							enDeplacement = true;
							repeindreVide(carteJeu.getElement(new Position(xTab,yTab)), Color.pink, ((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).getPortee());
						}
						else if(e.getButton() == MouseEvent.BUTTON3 && xTab < LARGEUR_CARTE && yTab < HAUTEUR_CARTE && carteJeu.getElement(new Position(xTab,yTab)).peutBouger == true && ((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutAttaquer == true)
						{
							avDeplacement = new Position(carteJeu.getElement(new Position(xTab,yTab)).getPos().getX(),carteJeu.getElement(new Position(xTab,yTab)).getPos().getY());
							enAttaque = true;
							repeindreVide(carteJeu.getElement(new Position(xTab,yTab)), Color.yellow, ((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).getPorteeDepl());
						}
					}
					
					public void mouseReleased(MouseEvent e)
					{
						int nX,nY;
						
						//Déplacement
						if(enDeplacement && peutJouer)
						{
							
							nX = ((int)e.getX()-deplX)/(NB_PIX_CASE+1);
							nY = ((int)e.getY()-deplY)/(NB_PIX_CASE+1);
							
							carteJeu.getElement(new Position(xTab,yTab)).setPos(avDeplacement.getX(), avDeplacement.getY());
							
							if(nX < LARGEUR_CARTE && nY < HAUTEUR_CARTE && carteJeu.getElement(new Position(nX,nY)).getColor() == Color.pink)
							{
								((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutBouger = false;
								repeindreVide(carteJeu.getElement(new Position(xTab,yTab)), Color.white, ((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).getPortee());
								((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).seDeplace(new Position(nX,nY));
								carteJeu.getElement(new Position(nX,nY)).setElement(carteJeu.getElement(new Position(xTab,yTab)).getElement());
								carteJeu.getElement(new Position(nX,nY)).setColor(Color.cyan,true);
								if(((Heros)carteJeu.getElement(new Position(nX,nY)).getElement()).peutAttaquer == false) carteJeu.getElement(new Position(nX,nY)).setColor(Color.magenta,false);
								else carteJeu.getElement(new Position(nX,nY)).setColor(COULEUR_HEROS,false);
								carteJeu.getElement(new Position(nX,nY)).isVide = false;
								carteJeu.getElement(new Position(nX,nY)).peutBouger = true;
								carteJeu.getElement(new Position(xTab,yTab)).reset();
								
							}
							else
							{
								repeindreVide(carteJeu.getElement(new Position(xTab,yTab)), Color.white, ((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).getPortee());
							}
							enDeplacement = false;
						}
						
						//Attaque
						if(enAttaque && peutJouer)
						{
							nX = ((int)e.getX()-deplX)/(NB_PIX_CASE+1);
							nY = ((int)e.getY()-deplY)/(NB_PIX_CASE+1);
							
							carteJeu.getElement(new Position(xTab,yTab)).setPos(avDeplacement.getX(), avDeplacement.getY());
							
							if(Math.abs(nX-xTab) <= ((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).getPorteeDepl() && Math.abs(nY-yTab) <= ((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).getPorteeDepl() && carteJeu.getElement(new Position(nX,nY)).getColor() == COULEUR_MONSTRES)
							{
								((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutAttaquer = false;
								try {
									((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).combat(((Soldat) carteJeu.getElement(new Position(nX,nY)).getElement()));
								} catch (FinDuJeu e1) {
									JOptionPane.showMessageDialog(frame,"Vous avez gagne");
									frame.dispose();
								}
								labelHaut.setText("Il reste " + carteJeu.getNbHeros() + " Héros et " + carteJeu.getNbMonstres() + " Monstres");
								carteJeu.getElement(new Position(xTab,yTab)).setColor(Color.magenta,false);
								((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutAttaquer = false;
							}
							repeindreVide(carteJeu.getElement(new Position(xTab,yTab)), Color.white, ((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).getPorteeDepl());
							enAttaque = false;
						}
					}
				});
		
		tableau.addMouseMotionListener(new MouseMotionListener()
		{
			public void mouseDragged(MouseEvent e)
			{
				if(enDeplacement && ((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutBouger == true)
				{
					carteJeu.getElement(new Position(xTab,yTab)).setPos((int)e.getX()-deplX-25, (int)e.getY()-deplY-25);
					tableau.repaint();
				}
				else if(enAttaque && ((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutAttaquer == true)
				{
					carteJeu.getElement(new Position(xTab,yTab)).setPos((int)e.getX()-deplX-25, (int)e.getY()-deplY-25);
					tableau.repaint();
				} 
			}

			public void mouseMoved(MouseEvent e) 
			{
				int posX,posY;
				String labelTxt;
				Color coulSelect;
				Soldat soldat;
				
				posX = ((int)e.getX()-deplX)/(NB_PIX_CASE+1);
				posY = ((int)e.getY()-deplY)/(NB_PIX_CASE+2);
				if(posX < LARGEUR_CARTE && posY < HAUTEUR_CARTE)
				{
					labelTxt = "X : "+posX+" | Y : "+posY;
					coulSelect = carteJeu.getElement(new Position(posX,posY)).getColor();
					if(coulSelect == COULEUR_HEROS || coulSelect == Color.cyan || coulSelect == Color.magenta)
					{
						soldat = (Soldat) carteJeu.getElement(new Position(posX,posY)).getElement();
						labelTxt = labelTxt.concat("     Hero : "+ ((Heros) soldat).getType() + " | PV=" + soldat.getPoints()+"/"+soldat.getPointsMax()+" | Puissance="+soldat.getPuissance()+" | Tir="+soldat.getTir());
					}
					else if(coulSelect == COULEUR_MONSTRES)
					{
						soldat = (Soldat) carteJeu.getElement(new Position(posX,posY)).getElement();
						labelTxt = labelTxt.concat("     Monstre : "+ ((Monstre) soldat).getType() + " |PV=" + soldat.getPoints()+"/"+soldat.getPointsMax()+" | Puissance="+soldat.getPuissance()+" | Tir="+soldat.getTir());
					}
					else if(coulSelect == COULEUR_EAU || coulSelect == COULEUR_ROCHER || coulSelect == COULEUR_FORET)
					{
						labelTxt = labelTxt.concat("     "+(Obstacle) carteJeu.getElement(new Position(posX,posY)).getElement());
					}
					
					labelBas.setText(labelTxt);
				}
			}
			
		});
		
		this.addKeyListener(new KeyAdapter() 
		{
            public void keyPressed(KeyEvent e) 
            {
            	char key = e.getKeyChar();
            	switch(key)
            	{
            	case 'z':
            		deplY-=NB_PIX_CASE+1;
            		tableau.repaint();
            		break;
            	case 'q':
            		deplX-=NB_PIX_CASE+1;
            		tableau.repaint();
            		break;
            	case 's':
            		deplY+=NB_PIX_CASE+1;
            		tableau.repaint();
            		break;
            	case 'd':
            		deplX+=NB_PIX_CASE+1;
            		tableau.repaint();
            		break;
            	}
            }
        });
		
		tableau.requestFocusInWindow();
	}
	
	/**
	 * Peint la fenetre
	 * @param g le Graphics
	 */
	public void paintComponent(Graphics g)
	{	
		super.paintComponent(g);
	}
	
	/**
	 * Repeint toutes les cases vides autour de la case passee en parametre
	 * @param _case la case centrale
	 * @param _couleur la nouvelle couleur des cases
	 * @param le rayon sur lequel on repeint
	 */
	public void repeindreVide(Case _case, Color _couleur, int _rayon)
	{
		int itForl, itForh;
		
		for(itForl = _case.getElement().getPos().getX()-_rayon; itForl <= _case.getElement().getPos().getX()+_rayon; itForl++)
		{
			for(itForh = _case.getElement().getPos().getY()-_rayon; itForh <= _case.getElement().getPos().getY()+_rayon; itForh++)
			{
				if(itForl >= 0 && itForl < LARGEUR_CARTE && itForh >= 0 && itForh < HAUTEUR_CARTE && carteJeu.getElement(new Position(itForl, itForh)).isVide)
				{
					carteJeu.getElement(new Position(itForl, itForh)).setColor(_couleur,true);
					carteJeu.getElement(new Position(itForl, itForh)).setColor(_couleur,false);
				}
			}
		}
		repaint();
	}
	
	/**
	 * Lance l'IA
	 */
	public void jouerIA()
	{
		peutJouer = false;
		try {
			carteJeu.jouerSoldats(this);
		} catch (FinDuJeu e) 
		{
			JOptionPane.showMessageDialog(this,"Les Monstres Gagnent la partie");
			frame.dispose();
		}
		repaint();
		labelHaut.setText("Il reste " + carteJeu.getNbHeros() + " Héros et " + carteJeu.getNbMonstres() + " Monstres");
		peutJouer = true;
	}
}
