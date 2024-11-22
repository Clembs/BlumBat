package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class FEN_CONNEXION_CLIENT extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public FEN_CONNEXION_CLIENT() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_1.add(lblNewLabel_2);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FEN_CONNEXION_CLIENT frame = new FEN_CONNEXION_CLIENT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
