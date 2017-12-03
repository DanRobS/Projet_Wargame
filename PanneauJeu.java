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
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanneauJeu extends JPanel implements IConfig
{
	private JButton btnFdT;
	private Carte carteJeu;
	private Box box;
	private JLabel labelHaut, labelBas;
	private JPanel tableau;
	
	private int xTab, yTab;
	
	public PanneauJeu()
	{
		carteJeu = new Carte();
		
		/*Organisation*/
		setLayout(new BorderLayout());
		
		/*Tableau de jeu*/
		tableau = new JPanel()
				{
					public void paintComponent(Graphics g)
					{	
						super.paintComponent(g);
						carteJeu.toutDessiner(g);
					}
				};
		tableau.setBackground(Color.BLACK);
		//tableau.setAutoscrolls(false);
		add("Center", tableau);
		
		/*Boutton Fin de tour*/
		btnFdT = new JButton("Fin de tour");
		btnFdT.setMinimumSize(new Dimension(200, 50));
		btnFdT.setMaximumSize(new Dimension(200, 50));
		btnFdT.setPreferredSize(new Dimension(200,50));
		btnFdT.setFont(new Font("Courier New", Font.ITALIC, 20));
		
		btnFdT.addActionListener(new ActionListener()
				{
					public void actionPerformed (ActionEvent e)
					{
						Toolkit.getDefaultToolkit().beep();
						
						repaint();
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
		
		
		/*Texte en bas*/
		labelBas = new JLabel();
		labelBas.setText("Description de l'unité sélectionnée");
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
						
						xTab = (int)e.getX()/51;
						yTab = (int)e.getY()/51;
						System.out.println(xTab+"      "+yTab);
					}
					
					public void mouseClicked(MouseEvent e)
					{
						
					}
					
					public void mouseReleased(MouseEvent e)
					{
						
					}
					
					public void mouseExited(MouseEvent e)
					{
						
					}
				});
		
		tableau.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				
				carteJeu.getElement(new Position(xTab,yTab)).setPos((int)e.getX()-25, (int)e.getY()-25);
				tableau.repaint();
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
            	tableau.getGraphics().translate(100, 100);
            	Toolkit.getDefaultToolkit().beep();
            	repaint();
            }
        });
		
		tableau.setFocusable(true);
		tableau.requestFocusInWindow();
	}
	
	public void paintComponent(Graphics g)
	{	
		super.paintComponent(g);
	}
}