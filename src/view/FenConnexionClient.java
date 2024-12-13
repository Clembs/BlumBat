package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import controller.ControleurConnexionClient;

import java.awt.Dialog.ModalExclusionType;

public class FenConnexionClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JTextField motDePasseField;

	public FenConnexionClient() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Gestion - Se connecter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));

		JLabel title = new JLabel("Gestion - Se connecter");
		title.setFont(new Font("Dialog", Font.BOLD, 24));
		contentPane.add(title, BorderLayout.NORTH);

		JPanel champs = new JPanel();
		contentPane.add(champs, BorderLayout.CENTER);
		champs.setLayout(new BorderLayout(0, 10));

		JPanel emailPanel = new JPanel();
		champs.add(emailPanel, BorderLayout.NORTH);
		emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblEmail = new JLabel("Email");
		emailPanel.add(lblEmail);

		emailField = new JTextField();
		emailPanel.add(emailField);
		emailField.setColumns(10);

		JPanel motDePassePanel = new JPanel();
		champs.add(motDePassePanel, BorderLayout.CENTER);
		motDePassePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblMotDePasse = new JLabel("Mot de passe");
		motDePassePanel.add(lblMotDePasse);

		motDePasseField = new JTextField();
		motDePasseField.setColumns(10);
		motDePassePanel.add(motDePasseField);

		JButton btnConnecter = new JButton("Se connecter");
		contentPane.add(btnConnecter, BorderLayout.SOUTH);

		ControleurConnexionClient controller = new ControleurConnexionClient(this);
		btnConnecter.addActionListener(controller);
	}

	public String getEmail() {
		return this.emailField.getText();
	}

	public String getMotDePasse() {
		return this.motDePasseField.getText();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenConnexionClient frame = new FenConnexionClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
