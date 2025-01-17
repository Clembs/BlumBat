package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import javax.swing.border.*;

import components.Bouton;
import components.Layout;
import components.Libellé;
import components.Tableau;
import controller.ControleurBiens;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;

public class VueBiens extends JFrame {
	private static final long serialVersionUID = 1L;
	private Tableau tableBiens;
	private ControleurBiens controleur;
	private List<BienImmobilier> biens;
	private JPanel panelCentralCourant;
	private Proprietaire proprietaire;

	public VueBiens(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;

		this.setTitle("Gestion des biens");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1200, 700);
		this.setBackground(Layout.COULEUR_FOND);
		this.setLayout(new BorderLayout(0, 0));

		// Panel latéral, contenant la liste des biens et un bouton "Ajouter"
		JPanel sidePanel = new JPanel();
		sidePanel.setBorder(new TitledBorder(new EtchedBorder(), "Vos biens", TitledBorder.CENTER, TitledBorder.TOP));
		sidePanel.setLayout(new BorderLayout(0, 8));

		// On définit un panel et un ButtonGroup pour les filtres
		JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
		ButtonGroup filtres = new ButtonGroup();

		JRadioButton btnTous = new JRadioButton("Tous");
		btnTous.setFont(Layout.POLICE_SMALL);
		btnTous.setSelected(true);
		filtres.add(btnTous);
		filterPanel.add(btnTous);

		// Afficher tous les biens
		btnTous.addActionListener((e) -> {
			rafraîchirTableBiens(this.biens);
		});

		JRadioButton btnDispo = new JRadioButton("Disponibles");
		btnDispo.setFont(Layout.POLICE_SMALL);
		filtres.add(btnDispo);
		filterPanel.add(btnDispo);

		// Afficher les biens disponibles
		btnDispo.addActionListener((e) -> {
			List<BienImmobilier> biensDispo = new LinkedList<>();

			for (BienImmobilier bien : this.biens) {
				if (bien instanceof BienLocatif && !((BienLocatif) bien).estLoué()) {
					biensDispo.add(bien);
				}
			}

			rafraîchirTableBiens(biensDispo);
		});

		JRadioButton btnLoues = new JRadioButton("Loués");
		btnLoues.setFont(Layout.POLICE_SMALL);
		filtres.add(btnLoues);
		filterPanel.add(btnLoues);

		// Afficher les biens loués
		btnLoues.addActionListener((e) -> {
			List<BienImmobilier> biensDispo = new LinkedList<>();

			for (BienImmobilier bien : this.biens) {
				if (bien instanceof BienLocatif && ((BienLocatif) bien).estLoué()) {
					biensDispo.add(bien);
				}
			}

			rafraîchirTableBiens(biensDispo);
		});

		sidePanel.add(filterPanel, BorderLayout.NORTH);

		// Création de la table des biens
		tableBiens = new Tableau("Identifiant", "Ville", "Type", "Disponibilité");
		tableBiens.setRowSelectionAllowed(true);
		tableBiens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// On définit la largeur des colonnes
		tableBiens.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableBiens.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableBiens.getColumnModel().getColumn(2).setPreferredWidth(10);
		tableBiens.getColumnModel().getColumn(3).setPreferredWidth(10);

		// Initialisation du contrôleur
		controleur = new ControleurBiens(proprietaire, this);
		// On ajoute un écouteur pour la sélection d'une ligne
		tableBiens.addMouseListener(controleur);

		// On définit une JScrollPane pour pouvoir défiler dans la table
		JScrollPane tableScrollPane = new JScrollPane(tableBiens);
		tableScrollPane.setPreferredSize(new Dimension(500, 0));
		sidePanel.add(tableScrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		Bouton btnAdd = new Bouton("Ajouter");
		buttonPanel.add(btnAdd);
		sidePanel.add(buttonPanel, BorderLayout.SOUTH);
		// Lorsque l'on clique sur le bouton "Ajouter"
		btnAdd.addActionListener(controleur);

		this.add(sidePanel, BorderLayout.WEST);

		resetPanelCentral();
	}

	public List<BienImmobilier> getBiens() {
		return this.biens;
	}

	// mise à jour d'un bien
	public void updateBien(BienImmobilier nouveauBien) {
		this.biens = this.biens.stream()
				.map(bienCourant -> bienCourant.getId().equals(nouveauBien.getId()) ? nouveauBien : bienCourant)
				.collect(Collectors.toList());

		// rafraîchir la liste des biens
		this.setBiens(biens);

		// rafraîchir le panel courant (s'il est en consultation) en le remplaçant
		if (this.panelCentralCourant instanceof VueConsultationBien) {
			VueConsultationBien panel = new VueConsultationBien(this, this.proprietaire, nouveauBien,
					VueConsultationBien.Onglets.DÉTAILS);

			this.setPanelCentral(panel);
		}
	}

	// Setter utilisé par le contrôleur
	public void setBiens(List<BienImmobilier> biens) {
		this.biens = biens;

		this.rafraîchirTableBiens(biens);
	}

	public void rafraîchirTableBiens(List<BienImmobilier> biens) {
		// Vider la table
		this.tableBiens.clear();

		// Pour chaque bien, ajouter une ligne dans la table
		for (BienImmobilier bien : biens) {
			this.tableBiens.addRow(
					bien.getId(),
					bien.getVille(),
					bien.getTypeBien().toString(),
					bien instanceof BienLocatif ? ((BienLocatif) bien).estLoué()
							? "🏳️ Loué"
							: "✅ Disponible"
							: "N/A");
		}

		// Rafraîchir la table
		this.tableBiens.revalidate();
		this.tableBiens.repaint();
	}

	// Changer le panel central
	public void setPanelCentral(JPanel panel) {
		if (this.panelCentralCourant != null) {
			this.panelCentralCourant.setVisible(false);
			this.remove(this.panelCentralCourant);
		}

		this.panelCentralCourant = panel;
		this.add(this.panelCentralCourant, BorderLayout.CENTER);
		this.panelCentralCourant.setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Proprietaire proprietaire = new Proprietaire(1, "Voisin", "Clément", "clembs@clembs.com", "truc");
				VueBiens frame = new VueBiens(proprietaire);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void resetPanelCentral() {
		// Panel central par défaut, affiché lorsqu'aucun bien n'est sélectionné
		JPanel panel = new JPanel(new GridLayout());
		Libellé lblChoix = new Libellé("Cliquez sur un bien pour afficher ses détails");
		lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoix.setVerticalAlignment(SwingConstants.CENTER);
		panel.add(lblChoix);

		setPanelCentral(panel);
	}
}
