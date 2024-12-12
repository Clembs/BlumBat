package view;

import java.awt.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import controller.ControleurBiens;
import model.BienImmobilier;
import model.Locataire;
import model.Location;
import model.Proprietaire;
import model.TypeBien;

public class FenBiens extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable tableBiens;
	private ControleurBiens controleur;

	public FenBiens(Proprietaire proprietaire) {
		setTitle("Gestion des biens");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);

		JPanel MainPane = new JPanel();
		MainPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		MainPane.setLayout(new BorderLayout(10, 10));
		setContentPane(MainPane);

		JPanel TitlePanel = new JPanel();
		TitlePanel.setBackground(Color.DARK_GRAY);
		JLabel lblTitle = new JLabel("Gestion des biens");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		TitlePanel.add(lblTitle);
		MainPane.add(TitlePanel, BorderLayout.NORTH);

		// Panel de gauche, contenant la liste des biens et un bouton "Ajouter"
		JPanel LeftPanel = new JPanel();
		LeftPanel.setBorder(new TitledBorder(new EtchedBorder(), "Vos biens", TitledBorder.CENTER, TitledBorder.TOP));
		LeftPanel.setLayout(new BorderLayout(5, 5));
		LeftPanel.setBackground(Color.LIGHT_GRAY);

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

		JScrollPane tableScrollPane = new JScrollPane(tableBiens);
		tableScrollPane.setPreferredSize(new Dimension(400, 300));
		// JScrollPane scrollPane = new JScrollPane(tableBiens);
		LeftPanel.add(tableScrollPane, BorderLayout.CENTER);

		// Initialisation du contrôleur (appelle setListeBiens())
		controleur = new ControleurBiens(proprietaire, this);

		JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		ButtonPanel.setBackground(new Color(224, 247, 250));
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnAdd.setBackground(new Color(46, 139, 87));
		btnAdd.setForeground(Color.WHITE);
		ButtonPanel.add(btnAdd);
		LeftPanel.add(ButtonPanel, BorderLayout.SOUTH);

		MainPane.add(LeftPanel, BorderLayout.WEST);
		MainPane.setBackground(new Color(250, 250, 250));
	}

	public void setListeBiens(List<BienImmobilier> biens) {
		// Vider la table
		DefaultTableModel model = (DefaultTableModel) tableBiens.getModel();
		model.setRowCount(0);

		// Pour chaque bien, ajouter une ligne dans la table
		for (BienImmobilier bien : biens) {
			// Si il n'y a aucune location ou s'il n'y en a aucune sans date de sortie
			boolean estLoué = !bien.getLocations().isEmpty()
					|| !bien.getLocations().stream()
							.allMatch(location -> location.getDateSortie() == null);

			((DefaultTableModel) tableBiens.getModel())
					.addRow(new Object[] {
							bien.getId(),
							bien.getVille(),
							bien.getTypeBien().toString(),
							estLoué ? "🔴 Occupé" : "🟢 Disponible"
					});
		}

		// Rafraîchir la table
		tableBiens.revalidate();
		tableBiens.repaint();
	}

	public static void main(String[] args) {
		List<BienImmobilier> biens = new LinkedList<BienImmobilier>();

		for (int i = 0; i < 100; i++) {
			TypeBien randomType = TypeBien.values()[(int) (Math.random() * TypeBien.values().length)];

			BienImmobilier bien1 = new BienImmobilier(String.valueOf(i + 1), randomType, "30 avenue Truc", null, "11000",
					"Carcassonne");

			if (i % 2 == 0) {
				Locataire locataire = new Locataire("clement-voisin", "Voisin", "Clément", "clembs@clembs.com",
						"+33 6 64 57 15 69",
						null);
				List<Locataire> locataires = new LinkedList<Locataire>();
				locataires.add(locataire);
				Location location = new Location(150f, new Date(), null, bien1, locataires);
				bien1.addLocation(location);
			}

			biens.add(bien1);
		}

		Proprietaire proprietaire = new Proprietaire("1", "VOISIN", "Clément", biens);

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
