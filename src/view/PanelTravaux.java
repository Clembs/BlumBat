package oui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class PanelTravaux extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelTravaux() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		
		JPanel panel_2 = new JPanel();
		panel_8.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel NomTravaux = new JLabel("Travaux Cuisine");
		NomTravaux.setFont(new Font("Tahoma", Font.PLAIN, 21));
		NomTravaux.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_2.add(NomTravaux);
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 5));
		
		JPanel panel_3 = new JPanel();
		panel_7.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 0, 3, 0));
		
		JLabel Description = new JLabel("Description");
		Description.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_3.add(Description);
		
		JLabel DescriptionText = new JLabel("Changer le robinet");
		DescriptionText.setForeground(Color.GRAY);
		panel_3.add(DescriptionText);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 5));
		
		JPanel panel_4 = new JPanel();
		panel_9.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 0, 3, 0));
		
		JLabel Entreprise = new JLabel("Entreprise");
		Entreprise.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_4.add(Entreprise);
		
		JLabel EntrepriseText = new JLabel("Abdel&Co");
		EntrepriseText.setForeground(Color.GRAY);
		panel_4.add(EntrepriseText);
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 5));
		
		JPanel panel_5 = new JPanel();
		panel_10.add(panel_5);
		panel_5.setLayout(new GridLayout(2, 0, 3, 0));
		
		JLabel MontantDevis = new JLabel("Montant Devis");
		MontantDevis.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_5.add(MontantDevis);
		
		JLabel MontantDevisText = new JLabel("14.000€");
		MontantDevisText.setForeground(Color.GRAY);
		panel_5.add(MontantDevisText);
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11);
		panel_11.setLayout(new GridLayout(2, 0, 3, 0));
		
		JLabel MontantFacture = new JLabel("Montant Facture");
		panel_11.add(MontantFacture);
		
		JLabel MontantFactureText = new JLabel("20.000€");
		MontantFactureText.setForeground(Color.GRAY);
		panel_11.add(MontantFactureText);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("Ajouter Nouvele Travaux");
		btnNewButton.setBackground(new Color(192, 192, 192));
		panel_6.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setBackground(new Color(255, 128, 128));
		panel_6.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Modifier");
		btnNewButton_2.setBackground(new Color(128, 255, 255));
		panel_6.add(btnNewButton_2);

	}
	
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        // Création du JFrame
	        javax.swing.JFrame frame = new javax.swing.JFrame("Travaux");
	        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	        
	        // Ajout du JPanel PanelAPropos au JFrame
	        PanelTravaux Travaux = new PanelTravaux();
	        frame.getContentPane().add(Travaux);

	        // Ajustement de la taille et affichage
	        frame.pack(); // Ajuste la taille en fonction du contenu
	        frame.setLocationRelativeTo(null); // Centre la fenêtre à l'écran
	        frame.setSize(700, 500);
	        frame.setVisible(true);
	    });
	}

}
