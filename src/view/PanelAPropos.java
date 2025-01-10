package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PanelAPropos extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelAPropos() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_14, BorderLayout.SOUTH);
		panel_14.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		
		JButton btnNewButton = new JButton("Louer");
		btnNewButton.setBackground(new Color(192, 192, 192));
		panel_14.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setBackground(new Color(255, 128, 128));
		panel_14.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Modifier");
		btnNewButton_2.setBackground(new Color(128, 255, 255));
		panel_14.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 5));
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Identifiant");
		panel_7.add(lblNewLabel);
		
		JLabel lblNewLabel_7 = new JLabel("Bien 1");
		panel_7.add(lblNewLabel_7);
		lblNewLabel_7.setForeground(Color.GRAY);
		
		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8);
		panel_8.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Type de bien");
		panel_8.add(lblNewLabel_1);
		
		JLabel lblNewLabel_8 = new JLabel("Logement");
		panel_8.add(lblNewLabel_8);
		lblNewLabel_8.setForeground(Color.GRAY);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 5));
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		panel_9.setLayout(new GridLayout(3, 0, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Adresse");
		panel_9.add(lblNewLabel_2);
		
		JLabel lblNewLabel_9 = new JLabel("12 rue des Trucs");
		panel_9.add(lblNewLabel_9);
		lblNewLabel_9.setForeground(Color.GRAY);
		
		JLabel lblNewLabel_10 = new JLabel("31400 Toulouse");
		panel_9.add(lblNewLabel_10);
		lblNewLabel_10.setForeground(Color.GRAY);
		
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 5));
		
		JPanel panel_10 = new JPanel();
		panel_5.add(panel_10);
		panel_10.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Surface");
		panel_10.add(lblNewLabel_3);
		
		JLabel lblNewLabel_11 = new JLabel("24.09m²");
		panel_10.add(lblNewLabel_11);
		lblNewLabel_11.setForeground(Color.GRAY);
		
		JPanel panel_11 = new JPanel();
		panel_5.add(panel_11);
		panel_11.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Numéro fiscal");
		panel_11.add(lblNewLabel_4);
		
		JLabel lblNewLabel_12 = new JLabel("1234567890");
		panel_11.add(lblNewLabel_12);
		lblNewLabel_12.setForeground(Color.GRAY);
		
		JPanel panel_12 = new JPanel();
		panel_5.add(panel_12);
		panel_12.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Nombre de pièces");
		panel_12.add(lblNewLabel_5);
		
		JLabel lblNewLabel_13 = new JLabel("34");
		panel_12.add(lblNewLabel_13);
		lblNewLabel_13.setForeground(Color.GRAY);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 5));
		
		JPanel panel_13 = new JPanel();
		panel_6.add(panel_13);
		panel_13.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Disponibilité");
		panel_13.add(lblNewLabel_6);
		
		JLabel lblNewLabel_14 = new JLabel("En location depuis le 25 novembre 2024");
		panel_13.add(lblNewLabel_14);
		lblNewLabel_14.setForeground(Color.GRAY);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 5));
		
		JButton btnNewButton_3 = new JButton("Voir les locataires");
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		panel_2.add(btnNewButton_3);

	}
	
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        // Création du JFrame
	        javax.swing.JFrame frame = new javax.swing.JFrame("À Propos");
	        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	        
	        // Ajout du JPanel PanelAPropos au JFrame
	        PanelAPropos aPropos = new PanelAPropos();
	        frame.getContentPane().add(aPropos);

	        // Ajustement de la taille et affichage
	        frame.pack(); // Ajuste la taille en fonction du contenu
	        frame.setLocationRelativeTo(null); // Centre la fenêtre à l'écran
	        frame.setSize(400, 600);
	        frame.setVisible(true);
	    });
	}


}
