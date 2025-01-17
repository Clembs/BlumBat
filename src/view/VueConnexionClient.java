package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import components.Bouton;
import components.ChampSaisie;
import components.Libellé;
import components.ChampSaisie.TypeChamp;
import components.Libellé.TypeLibellé;
import controller.ControleurConnexionClient;

public class VueConnexionClient extends JFrame {
	private static final long serialVersionUID = 1L;
	private ChampSaisie emailField;
	private ChampSaisie motDePasseField;

	public VueConnexionClient() {
		this.setTitle("Connexion à Blumbat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 350);
		this.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		mainPanel.setLayout(new BorderLayout(10, 20));
		setContentPane(mainPanel);

		JLabel lblTitre = new Libellé("Connexion à Blumbat", TypeLibellé.EN_TETE);
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblTitre, BorderLayout.NORTH);

		JPanel panelChamps = new JPanel(new GridLayout(2, 2, 40, 20));
		mainPanel.add(panelChamps, BorderLayout.CENTER);

		emailField = new ChampSaisie("Email");
		panelChamps.add(emailField);

		motDePasseField = new ChampSaisie("Mot de passe", TypeChamp.MOT_DE_PASSE);
		panelChamps.add(motDePasseField);

		JPanel panelBoutons = new JPanel();

		ControleurConnexionClient controller = new ControleurConnexionClient(this);
		Bouton btnConnecter = new Bouton("Se connecter");
		btnConnecter.addActionListener(controller);

		panelBoutons.add(btnConnecter);
		mainPanel.add(panelBoutons, BorderLayout.SOUTH);
	}

	public String getEmail() {
		return this.emailField.getValue();
	}

	public String getMotDePasse() {
		return this.motDePasseField.getValue();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				VueConnexionClient frame = new VueConnexionClient();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
