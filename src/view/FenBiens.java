package view;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import controller.ControleurBiens;
import model.BienImmobilier;
import model.Proprietaire;

public class FenBiens extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable tableBiens;
	private ControleurBiens controleur;
	private List<BienImmobilier> biens;

	public FenBiens(Proprietaire proprietaire) {
		setTitle("Gestion des biens");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new BorderLayout(10, 10));
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
				if (!bien.estLoué()) {
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
				if (bien.estLoué()) {
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
		// On définit la largeur des colonnes
		tableBiens.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableBiens.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableBiens.getColumnModel().getColumn(2).setPreferredWidth(10);
		tableBiens.getColumnModel().getColumn(3).setPreferredWidth(10);
		tableBiens.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableBiens.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));

		// On définit une JScrollPane pour pouvoir défiler dans la table
		JScrollPane tableScrollPane = new JScrollPane(tableBiens);
		tableScrollPane.setPreferredSize(new Dimension(400, 0));
		sidePanel.add(tableScrollPane, BorderLayout.CENTER);

		// Initialisation du contrôleur
		controleur = new ControleurBiens(proprietaire, this);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		// ButtonPanel.setBackground(new Color(224, 247, 250));
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnAdd.setBackground(new Color(46, 139, 87));
		btnAdd.setForeground(Color.WHITE);
		buttonPanel.add(btnAdd);
		sidePanel.add(buttonPanel, BorderLayout.SOUTH);

		mainPanel.add(sidePanel, BorderLayout.WEST);
		mainPanel.setBackground(new Color(250, 250, 250));
	}

	// Setter utilisé par le contrôleur
	public void setBiens(List<BienImmobilier> biens) {
		this.biens = biens;

		rafraîchirTableBiens(biens);
	}

	public void rafraîchirTableBiens(List<BienImmobilier> biens) {
		// Vider la table
		DefaultTableModel model = (DefaultTableModel) tableBiens.getModel();
		model.setRowCount(0);

		// Pour chaque bien, ajouter une ligne dans la table
		for (BienImmobilier bien : biens) {
			model.addRow(new Object[] {
					bien.getId(),
					bien.getVille(),
					bien.getTypeBien().toString(),
					bien.estLoué() ? "🔴 Loué" : "🟢 Disponible"
			});
		}

		// Rafraîchir la table
		tableBiens.revalidate();
		tableBiens.repaint();
	}

	public static void main(String[] args) {
		Proprietaire proprietaire = new Proprietaire("1", "VOISIN", "Clément", new LinkedList<BienImmobilier>());

		EventQueue.invokeLater(() -> {
			try {
				FenBiens frame = new FenBiens(proprietaire);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
