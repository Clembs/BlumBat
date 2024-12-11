package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class FEN_CONSULTATION_BIENS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel MainPane;
	private JTable tableBiens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FEN_CONSULTATION_BIENS frame = new FEN_CONSULTATION_BIENS();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FEN_CONSULTATION_BIENS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		MainPane = new JPanel();
		MainPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		MainPane.setLayout(new BorderLayout(10, 10));
		setContentPane(MainPane);

		UIManager.put("Label.font", new Font("Rockwell", Font.PLAIN, 14));
		UIManager.put("Button.font", new Font("Rockwell", Font.PLAIN, 14));

		JPanel TitlePanel = new JPanel();
		TitlePanel.setBackground(Color.DARK_GRAY);
		JLabel lblTitle = new JLabel("Gestion des biens");
		lblTitle.setFont(new Font("Rockwell", Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		TitlePanel.add(lblTitle);
		MainPane.add(TitlePanel, BorderLayout.NORTH);

		JPanel LeftPanel = new JPanel();
		LeftPanel.setBorder(new TitledBorder(new EtchedBorder(), "Liste des biens", TitledBorder.CENTER, TitledBorder.TOP));
		LeftPanel.setLayout(new BorderLayout(5, 5));
		LeftPanel.setBackground(Color.LIGHT_GRAY);

		JList<String> list = new JList<>(new String[]{"Bien 1", "Bien 2", "Bien 3"});
		list.setForeground(Color.BLACK);
		list.setBackground(Color.LIGHT_GRAY);
		list.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane(list);
		LeftPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		ButtonPanel.setBackground(new Color(224, 247, 250));
		JButton btnModify = new JButton("Modifier");
		btnModify.setForeground(Color.WHITE);
		btnModify.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnModify.setBackground(new Color(255, 99, 71));
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnAdd.setBackground(new Color(46, 139, 87));
		btnAdd.setForeground(Color.WHITE);
		ButtonPanel.add(btnModify);
		ButtonPanel.add(btnAdd);
		LeftPanel.add(ButtonPanel, BorderLayout.SOUTH);

		MainPane.add(LeftPanel, BorderLayout.WEST);

		JPanel RightPanel = new JPanel();
		RightPanel.setLayout(new BorderLayout(5, 5));

		JPanel TablePanel = new JPanel();
		TablePanel.setBorder(new TitledBorder(new EtchedBorder(), "Détails des biens", TitledBorder.CENTER, TitledBorder.TOP));
		TablePanel.setLayout(new BorderLayout());
		TablePanel.setBackground(Color.LIGHT_GRAY);

		tableBiens = new JTable(new DefaultTableModel(
				new Object[][]{
						{"Bien 1", "Adresse 1", "Type 1"},
						{"Bien 2", "Adresse 2", "Type 2"}
				},
				new String[]{"Nom du Bien", "Adresse", "Type"}
		));
		tableBiens.setFont(new Font("Rockwell", Font.PLAIN, 12));
		tableBiens.setBackground(new Color(240, 248, 255));
		tableBiens.setForeground(Color.BLACK);
		tableBiens.setGridColor(new Color(224, 224, 224));

		JScrollPane tableScrollPane = new JScrollPane(tableBiens);
		tableScrollPane.setFont(new Font("Arial", Font.BOLD, 13));
		tableScrollPane.setBackground(Color.DARK_GRAY);
		TablePanel.add(tableScrollPane, BorderLayout.CENTER);

		RightPanel.add(TablePanel, BorderLayout.CENTER);

		MainPane.add(RightPanel, BorderLayout.CENTER);

		MainPane.setBackground(new Color(250, 250, 250));
	}
}
