package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import controller.Controleur_Biens;
import model.BienImmobilier;
import model.Proprietaire;

public class FEN_Biens extends JFrame {

	private static final long serialVersionUID = 1L;
	private JList<String> listBiens;
	private JButton btnValider;
	private Controleur_Biens C;

	public FEN_Biens(Proprietaire P) {
		this.setTitle("Mes Biens");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 600, 400);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(10, 10));
		this.setContentPane(contentPane);

		JPanel panelHeader = new JPanel(new BorderLayout(10, 10));
		panelHeader.setBackground(new Color(240, 240, 240));
		contentPane.add(panelHeader, BorderLayout.NORTH);

		JLabel lblTitre = new JLabel("Mes Biens");
		lblTitre.setFont(new Font("Rockwell", Font.BOLD, 24));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		panelHeader.add(lblTitre, BorderLayout.CENTER);

		JLabel iconDeconnexion = new JLabel();
		iconDeconnexion.setIcon(new ImageIcon("C:\\Users\\abdel\\Downloads\\se-deconnecter (2).png"));
		iconDeconnexion.setHorizontalAlignment(SwingConstants.RIGHT);
		panelHeader.add(iconDeconnexion, BorderLayout.EAST);

		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout(10, 10));
		contentPane.add(panelCenter, BorderLayout.CENTER);

		JPanel panelFilter = new JPanel();
		panelFilter.setLayout(new BorderLayout(10, 10));
		panelFilter.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelCenter.add(panelFilter, BorderLayout.NORTH);

		JLabel lblSearch = new JLabel("Filtre :");
		lblSearch.setFont(new Font("Rockwell", Font.BOLD, 14));
		panelFilter.add(lblSearch, BorderLayout.WEST);

		JTextField txtSearch = new JTextField();
		txtSearch.setFont(new Font("Rockwell", Font.PLAIN, 14));
		txtSearch.setPreferredSize(new Dimension(100, 20));
		panelFilter.add(txtSearch, BorderLayout.CENTER);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setPreferredSize(new Dimension(100, 23));
		btnAjouter.setFont(new Font("Rockwell", Font.PLAIN, 14));
		panelFilter.add(btnAjouter, BorderLayout.EAST);

		this.listBiens = new JList<>(new String[] { "Bien 1", "Bien 2", "Bien 3", "Bien 4", "Bien 5" });
		this.listBiens.setFont(new Font("Rockwell", Font.PLAIN, 14));
		this.listBiens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(this.listBiens);
		panelCenter.add(scrollPane, BorderLayout.CENTER);

		JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		contentPane.add(panelFooter, BorderLayout.SOUTH);

		this.btnValider = new JButton("Valider");
		this.btnValider.setFont(new Font("Rockwell", Font.PLAIN, 14));
		this.btnValider.setPreferredSize(new Dimension(120, 30));
		panelFooter.add(this.btnValider);

		// Initialiser le contrôleur
		this.C = new Controleur_Biens(this, P, this.listBiens);

		btnAjouter.addActionListener(this.C);
		this.btnValider.addActionListener(this.C);

	}
	

}
