package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import controller.ControleurConnexionClient;

public class FenConnexionClient extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField emailField;
	private JTextField motDePasseField;

	public FenConnexionClient() {
		setTitle("Se connecter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 20));

		JLabel title = new JLabel("Se connecter");
		title.setFont(new Font("Dialog", Font.BOLD, 24));
		mainPanel.add(title, BorderLayout.NORTH);

		JPanel champs = new JPanel();
		champs.setLayout(new GridLayout(2, 2, 10, 10));
		mainPanel.add(champs, BorderLayout.CENTER);

		JLabel lblEmail = new JLabel("Email");
		champs.add(lblEmail);

		emailField = new JTextField();
		champs.add(emailField);
		emailField.setColumns(10);

		JLabel lblMotDePasse = new JLabel("Mot de passe");
		champs.add(lblMotDePasse);

		motDePasseField = new JPasswordField();
		motDePasseField.setColumns(10);
		champs.add(motDePasseField);

		JButton btnConnecter = new JButton("Se connecter");
		mainPanel.add(btnConnecter, BorderLayout.SOUTH);

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
