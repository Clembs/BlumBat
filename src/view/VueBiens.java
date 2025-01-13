package view;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import controller.ControleurBiens;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;

public class VueBiens extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable tableBiens;
	private ControleurBiens controleur;
	private List<BienImmobilier> biens;
	private JPanel panelCentralCourant;
	private Proprietaire proprietaire;

	public VueBiens(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;

		this.setTitle("Gestion des biens");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1200, 700);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBackground(new Color(250, 250, 250));
		setContentPane(mainPanel);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.DARK_GRAY);
		JLabel lblTitle = new JLabel("Gestion des biens");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		titlePanel.add(lblTitle);
		mainPanel.add(titlePanel, BorderLayout.NORTH);

		// Panel latéral, contenant la liste des biens et un bouton "Ajouter"
		JPanel sidePanel = new JPanel();
		sidePanel.setBorder(new TitledBorder(new EtchedBorder(), "Vos biens", TitledBorder.CENTER, TitledBorder.TOP));
		sidePanel.setLayout(new BorderLayout(5, 5));
		sidePanel.setBackground(Color.LIGHT_GRAY);

		// On définit un panel et un ButtonGroup pour les filtres
		JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		ButtonGroup filtres = new ButtonGroup();

		JRadioButton btnTous = new JRadioButton("Tous");
		btnTous.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnTous.setSelected(true);
		filtres.add(btnTous);
		filterPanel.add(btnTous);

		// Afficher tous les biens
		btnTous.addActionListener((e) -> {
			rafraîchirTableBiens(this.biens);
		});

		JRadioButton btnDispo = new JRadioButton("Disponibles");
		btnDispo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
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
		btnLoues.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
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
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[] { "ID", "Ville", "Type", "Disponibilité" },
				0);
		tableBiens = new JTable(tableModel);
		tableBiens.setRowSelectionAllowed(true);
		tableBiens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// On rend la table non-éditable
		tableBiens.setDefaultEditor(Object.class, null);
		// On définit la largeur des colonnes
		tableBiens.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableBiens.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableBiens.getColumnModel().getColumn(2).setPreferredWidth(10);
		tableBiens.getColumnModel().getColumn(3).setPreferredWidth(10);
		tableBiens.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableBiens.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));

		// Initialisation du contrôleur
		controleur = new ControleurBiens(proprietaire, this);
		// On ajoute un écouteur pour la sélection d'une ligne
		tableBiens.addMouseListener(controleur);

		// On définit une JScrollPane pour pouvoir défiler dans la table
		JScrollPane tableScrollPane = new JScrollPane(tableBiens);
		tableScrollPane.setPreferredSize(new Dimension(400, 0));
		sidePanel.add(tableScrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnAdd.setBackground(new Color(46, 139, 87));
		btnAdd.setForeground(Color.WHITE);
		buttonPanel.add(btnAdd);
		sidePanel.add(buttonPanel, BorderLayout.SOUTH);
		// Lorsque l'on clique sur le bouton "Ajouter"
		btnAdd.addActionListener(controleur);

		mainPanel.add(sidePanel, BorderLayout.WEST);

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
		if (this.panelCentralCourant instanceof VuelConsultationBien) {
			VuelConsultationBien panel = new VuelConsultationBien(this, this.proprietaire, nouveauBien,
					VuelConsultationBien.Onglets.DÉTAILS);

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
		DefaultTableModel model = (DefaultTableModel) this.tableBiens.getModel();
		model.setRowCount(0);

		// Pour chaque bien, ajouter une ligne dans la table
		for (BienImmobilier bien : biens) {
			model.addRow(new Object[] {
					bien.getId(),
					bien.getVille(),
					bien.getTypeBien().toString(),
					bien instanceof BienLocatif ? ((BienLocatif) bien).estLoué()
							? "🔴 Loué"
							: "🟢 Disponible"
							: "N/A"
			});
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
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		JLabel lblChoix = new JLabel("Choisissez un bien pour afficher ses détails");
		lblChoix.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblChoix);

		setPanelCentral(panel);
	}
}
