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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;
import javax.tools.Tool;


public class PanneauJeu extends JPanel implements IConfig
{
	/**
	 * 
	 */
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
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void paintComponent(Graphics g)
					{	
						super.paintComponent(g);
						g.translate(deplX, deplY);
						carteJeu.toutDessiner(g, this);
					}
				};
		tableau.setBackground(Color.BLACK);
		//tableau.setAutoscrolls(false);
		add("Center", tableau);
		
		
		/*Boutton Fin de tour*/
		btnFdT = new JButton(new ImageIcon("C:\\Users\\VOCAN\\git\\Projet_Wargame\\WarGame\\src\\Images\\test.jpg"));
		btnFdT.setFocusable(false);
		btnFdT.setMinimumSize(new Dimension(300, 50));
		btnFdT.setMaximumSize(new Dimension(300, 50));
		btnFdT.setPreferredSize(new Dimension(300,50));
		
		btnFdT.addActionListener(new ActionListener()
				{
					
					public void actionPerformed (ActionEvent e)
					{
						/*try {
		                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("C:\\Users\\VOCAN\\git\\Projet_Wargame\\WarGame\\src\\Musiques\\fichier.wav"));
		                    // Get a sound clip resource.
		                    Clip clip = AudioSystem.getClip();
		                    // Open audio clip and load samples from the audio input stream.
		                    clip.open(audioIn);
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
		labelHaut.setText("Il reste " + carteJeu.getNbHeros() + " H�ros et " + carteJeu.getNbMonstres() + " Monstres");
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
		labelBas.setText("Description de l'unit� s�lectionn�e");
		labelBas.setFont(new Font("Courier New", Font.ITALIC, 20));
		labelBas.setOpaque(true);
		labelBas.setBackground(Color.LIGHT_GRAY);
		labelBas.setPreferredSize(new Dimension(0, 30));
		add("South", labelBas);
		
		
		/*Gestion clic*/
		tableau.addMouseListener(new MouseListener()
				{
					public void mouseEntered(MouseEvent e)
					{
						
					}
			
					public void mousePressed(MouseEvent e)
					{
						Toolkit.getDefaultToolkit().beep();
						
						xTab = (int)e.getX()/(NB_PIX_CASE+1);
						yTab = (int)e.getY()/(NB_PIX_CASE+1);
						//System.out.println(xTab+"      "+yTab);
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
					
					public void mouseClicked(MouseEvent e)
					{
						
					}
					
					public void mouseReleased(MouseEvent e)
					{
						int nX,nY;
						
						//D�placement
						if(enDeplacement && peutJouer)
						{
							nX = (int)e.getX()/(NB_PIX_CASE+1);
							nY = (int)e.getY()/(NB_PIX_CASE+1);
							
							carteJeu.getElement(new Position(xTab,yTab)).setPos(avDeplacement.getX(), avDeplacement.getY());
							
							if(nX < LARGEUR_CARTE && nY < HAUTEUR_CARTE && carteJeu.getElement(new Position(nX,nY)).getColor() == Color.pink)
							{
								((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutBouger = false;
								//System.out.println("OK" + xTab + " | " + yTab);
								repeindreVide(carteJeu.getElement(new Position(xTab,yTab)), Color.white, ((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).getPortee());
								((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).seDeplace(new Position(nX,nY));
								carteJeu.getElement(new Position(nX,nY)).setElement(carteJeu.getElement(new Position(xTab,yTab)).getElement());
								//System.out.println(xTab + " | " + yTab + " | " + carteJeu.getElement(new Position(xTab,yTab)).getPosTab() + " | " + carteJeu.getElement(new Position(xTab,yTab)).getPos());
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
							nX = (int)e.getX()/(NB_PIX_CASE+1);
							nY = (int)e.getY()/(NB_PIX_CASE+1);
							
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
								labelHaut.setText("Il reste " + carteJeu.getNbHeros() + " H�ros et " + carteJeu.getNbMonstres() + " Monstres");
								((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutAttaquer = false;
							}
							repeindreVide(carteJeu.getElement(new Position(xTab,yTab)), Color.white, ((Soldat) carteJeu.getElement(new Position(xTab,yTab)).getElement()).getPorteeDepl());
							enAttaque = false;
						}
					}
					
					public void mouseExited(MouseEvent e)
					{
						
					}
				});
		
		tableau.addMouseMotionListener(new MouseMotionListener()
		{
			public void mouseDragged(MouseEvent e)
			{
				/*
				if(xTab < LARGEUR_CARTE && yTab < HAUTEUR_CARTE && carteJeu.getElement(new Position(xTab,yTab)).peutBouger == true)
				{
					carteJeu.getElement(new Position(xTab,yTab)).setPos((int)e.getX()-25, (int)e.getY()-25);
					tableau.repaint();
				}*/
				
				if(enDeplacement && ((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutBouger == true)
				{
					carteJeu.getElement(new Position(xTab,yTab)).setPos((int)e.getX()-25, (int)e.getY()-25);
					tableau.repaint();
				}
				else if(enAttaque && ((Heros)carteJeu.getElement(new Position(xTab,yTab)).getElement()).peutAttaquer == true)
				{
					carteJeu.getElement(new Position(xTab,yTab)).setPos((int)e.getX()-25, (int)e.getY()-25);
					tableau.repaint();
				} 
			}

			@Override
			public void mouseMoved(MouseEvent e) 
			{
				int posX,posY;
				String labelTxt;
				Color coulSelect;
				Soldat soldat;
				
				posX = (int)e.getX()/(NB_PIX_CASE+1);
				posY = (int)e.getY()/(NB_PIX_CASE+1);
				if(posX < LARGEUR_CARTE && posY < HAUTEUR_CARTE)
				{
					
		
					
					
					
					labelTxt = "X : "+posX+" | Y : "+posY;
					coulSelect = carteJeu.getElement(new Position(posX,posY)).getColor();
					if(coulSelect == COULEUR_HEROS)
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
		
		this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) 
            {
            	char key = e.getKeyChar();
            	switch(key)
            	{
            	case 'z':
            		deplY-=10;
            		tableau.repaint();
            		break;
            	case 'q':
            		deplX-=10;
            		tableau.repaint();
            		break;
            	case 's':
            		deplY+=10;
            		tableau.repaint();
            		break;
            	case 'd':
            		deplX+=10;
            		tableau.repaint();
            		break;
            	}
            }
        });
		
		tableau.requestFocusInWindow();
	}
	
	public void paintComponent(Graphics g)
	{	
		super.paintComponent(g);
		//carteJeu.toutDessiner(g);
	}
	
	public void repeindreVide(Case _case, Color _couleur, int _rayon)//((Soldat) _case.getElement()).getPorteeDepl()
	{
		int itForl, itForh;
		
		for(itForl = _case.getElement().getPos().getX()-_rayon; itForl <= _case.getElement().getPos().getX()+_rayon; itForl++)
		{
			for(itForh = _case.getElement().getPos().getY()-_rayon; itForh <= _case.getElement().getPos().getY()+_rayon; itForh++)
			{
				if(itForl >= 0 && itForl < LARGEUR_CARTE && itForh >= 0 && itForh < HAUTEUR_CARTE && carteJeu.getElement(new Position(itForl, itForh)).isVide)
				{
					carteJeu.getElement(new Position(itForl, itForh)).setColor(_couleur);
				}
			}
		}
		repaint();
	}
	
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
		labelHaut.setText("Il reste " + carteJeu.getNbHeros() + " H�ros et " + carteJeu.getNbMonstres() + " Monstres");
		peutJouer = true;
		
	}
}
