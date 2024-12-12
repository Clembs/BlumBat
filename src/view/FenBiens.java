package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class FenBiens extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel MainPane;

	public FenBiens() {
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

		JList<String> list = new JList<>(new String[] { "Bien 1", "Bien 2", "Bien 3" });
		list.setForeground(Color.BLACK);
		list.setBackground(Color.LIGHT_GRAY);
		list.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane(list);
		LeftPanel.add(scrollPane, BorderLayout.CENTER);

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

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FenBiens frame = new FenBiens();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
