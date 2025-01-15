package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControleurTravaux;
import model.BienImmobilier;
import model.TypeBien;

public class PanelTravaux extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel PrixTotalText;

	/**
	 * Create the panel.
	 */
	public PanelTravaux(BienImmobilier bien) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
		
		JPanel panel_2 = new JPanel();
		panel_8.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel PrixTotal = new JLabel("Prix Total des Travaux :");
		panel_2.add(PrixTotal);
		
		this.PrixTotalText = new JLabel("20.000€");
		panel_2.add(PrixTotalText);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
		    new Object[][] {
		        {"Changer robinet", "Abdel&Co", "400\u20AC", "3000\u20AC"},
		    },
		    new String[] {
		        "Description", "Entreprise", "Montant Devise", "Montant Facture"
		    }
		));

		// Encapsulation de la table dans un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		panel_3.add(scrollPane, BorderLayout.CENTER);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.EAST);

		ControleurTravaux controleur = new ControleurTravaux(this, bien);
		
		JButton ButtonAjouter = new JButton("Ajouter Nouveaux Travaux");
		ButtonAjouter.setBackground(new Color(192, 192, 192));
		panel_6.add(ButtonAjouter);
		ButtonAjouter.addActionListener(controleur);
		
		JButton ButtonSuprimer = new JButton("Supprimer");
		ButtonSuprimer.setBackground(new Color(255, 128, 128));
		panel_6.add(ButtonSuprimer);
		ButtonSuprimer.addActionListener(controleur);
		
		JButton ButtonModifier = new JButton("Modifier");
		ButtonModifier.setBackground(new Color(128, 255, 255));
		panel_6.add(ButtonModifier);
		ButtonModifier.addActionListener(controleur);
	}
	
	public JTable getTable() {
		return table;
	}
	
	public void setPrixTotal(String prixTotal) {
		PrixTotalText.setText(prixTotal);
	}
	
	
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        // Création du JFrame
	        javax.swing.JFrame frame = new javax.swing.JFrame("Travaux");
	        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	        
	        // Ajout du JPanel PanelAPropos au JFrame
	        PanelTravaux Travaux = new PanelTravaux(new BienImmobilier("12345",TypeBien.BATIMENT,"rue compte","ap4","36000","Toulouse"));
	        frame.getContentPane().add(Travaux);

	        // Ajustement de la taille et affichage
	        frame.pack(); // Ajuste la taille en fonction du contenu
	        frame.setLocationRelativeTo(null); // Centre la fenêtre à l'écran
	        frame.setSize(700, 500);
	        frame.setVisible(true);
	    });
	}

}
