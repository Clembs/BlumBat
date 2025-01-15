package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import controller.ControleurConnexionClient;

public class VueConnexionClient extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField emailField;
	private JPasswordField motDePasseField;

	public VueConnexionClient() {
		setTitle("Se connecter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 320);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		mainPanel.setLayout(new BorderLayout(10, 20));
		mainPanel.setBackground(new Color(245, 245, 250));
		setContentPane(mainPanel);

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.DARK_GRAY);
		JLabel titleLabel = new JLabel("Connexion Client");
		titleLabel.setFont(new Font("Rockwell", Font.BOLD, 26));
		titleLabel.setForeground(Color.WHITE);
		titlePanel.add(titleLabel);
		mainPanel.add(titlePanel, BorderLayout.NORTH);

		JPanel inputPanel = new JPanel(new GridLayout(2, 2, -40, 20));
		inputPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Identifiants",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		inputPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JLabel emailLabel = new JLabel("Email :");
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		inputPanel.add(emailLabel);
		emailField = new JTextField();
		emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		emailField.setColumns(10);
		emailField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(0, 120, 215), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		inputPanel.add(emailField);

		JLabel passwordLabel = new JLabel("Mot de passe :");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		inputPanel.add(passwordLabel);
		motDePasseField = new JPasswordField();
		motDePasseField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		motDePasseField.setColumns(10);
		motDePasseField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(0, 120, 215), 1),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		inputPanel.add(motDePasseField);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(245, 245, 250));
		JButton btnConnecter = new JButton("Se connecter");
		btnConnecter.setFont(new Font("Rockwell", Font.BOLD, 14));
		btnConnecter.setForeground(Color.WHITE);
		btnConnecter.setBackground(new Color(39, 174, 96));
		btnConnecter.setFocusPainted(false);
		btnConnecter.setBorder(new EmptyBorder(10, 30, 10, 30));
		btnConnecter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnConnecter.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnConnecter.setBackground(new Color(46, 204, 113));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnConnecter.setBackground(new Color(39, 174, 96));
			}
		});

		buttonPanel.add(btnConnecter);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		ControleurConnexionClient controller = new ControleurConnexionClient(this);
		btnConnecter.addActionListener(controller);
	}

	public String getEmail() {
		return this.emailField.getText();
	}

	public String getMotDePasse() {
		return new String(this.motDePasseField.getPassword());
	}

	/**
	 * Launch the application.
	 */
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
