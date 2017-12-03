package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class FenetreJeu extends JFrame {

	private JPanel contentPane;
	private PanneauJeu game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreJeu frame = new FenetreJeu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetreJeu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1565, 1000);
		setTitle("War Game");
		
		game = new PanneauJeu();
		game.setBounds(12, 24, 1531, 871);
		game.setBackground(Color.DARK_GRAY);
		
		contentPane = new JPanel();
		contentPane.setBounds(20, 20, 10, 10);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(game);
		setContentPane(contentPane);
		
		JButton btnPasserLeTour = new JButton("Passer le tour");
		btnPasserLeTour.setBounds(112, 907, 158, 33);
		contentPane.add(btnPasserLeTour);
	}
}