package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class FEN_CONSULTATION_LOCATAIRES extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel MainPane;
    private JTable tableBiens;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FEN_CONSULTATION_LOCATAIRES frame = new FEN_CONSULTATION_LOCATAIRES();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public FEN_CONSULTATION_LOCATAIRES() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        MainPane = new JPanel();
        MainPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        MainPane.setLayout(new BorderLayout(10, 10));
        setContentPane(MainPane);


        UIManager.put("Label.font", new Font("Rockwell", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Rockwell", Font.PLAIN, 14));

        JPanel TitlePanel = new JPanel();
        TitlePanel.setBackground(new Color(30, 136, 229));
        JLabel lblTitle = new JLabel("Locataires");
        lblTitle.setFont(new Font("Rockwell", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        TitlePanel.add(lblTitle);
        MainPane.add(TitlePanel, BorderLayout.NORTH);


        JPanel LeftPanel = new JPanel();
        LeftPanel.setBorder(new TitledBorder(new EtchedBorder(), "Liste des locataires", TitledBorder.CENTER, TitledBorder.TOP));
        LeftPanel.setLayout(new BorderLayout(5, 5));
        LeftPanel.setBackground(new Color(224, 247, 250)); 
        JList<String> list = new JList<>(new String[]{"Locataire 1", "Locataire 2", "Locataire 3"});
        list.setFont(new Font("Rockwell", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(list);
        LeftPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        ButtonPanel.setBackground(new Color(224, 247, 250));
        JButton btnModify = new JButton("Modifier");
        btnModify.setFont(new Font("Rockwell", Font.PLAIN, 12));
        btnModify.setBackground(new Color(0, 150, 136)); 
        JButton btnAdd = new JButton("Ajouter");
        btnAdd.setFont(new Font("Rockwell", Font.PLAIN, 12));
        btnAdd.setBackground(new Color(76, 175, 80)); 
        btnAdd.setForeground(Color.WHITE);
        ButtonPanel.add(btnModify);
        ButtonPanel.add(btnAdd);
        LeftPanel.add(ButtonPanel, BorderLayout.SOUTH);

        MainPane.add(LeftPanel, BorderLayout.WEST);

  
        JPanel RightPanel = new JPanel();
        RightPanel.setLayout(new BorderLayout(5, 5));

        JPanel InfoPanel = new JPanel();
        InfoPanel.setBorder(new TitledBorder(new EtchedBorder(), "Informations Personnelles", TitledBorder.CENTER, TitledBorder.TOP));
        InfoPanel.setLayout(new GridLayout(4, 2, 5, 5));
        InfoPanel.setBackground(Color.WHITE);

        JLabel lblNom = new JLabel("Nom:");
        lblNom.setFont(new Font("Rockwell", Font.PLAIN, 12));
        JTextField txtNom = new JTextField();
        txtNom.setFont(new Font("Rockwell", Font.PLAIN, 12));
        JLabel lblPrenom = new JLabel("Prenom:");
        lblPrenom.setFont(new Font("Rockwell", Font.PLAIN, 12));
        JTextField txtPrenom = new JTextField();
        txtPrenom.setFont(new Font("Rockwell", Font.PLAIN, 12));
        JLabel lblAdresse = new JLabel("Adresse:");
        lblAdresse.setFont(new Font("Rockwell", Font.PLAIN, 12));
        JTextField txtAdresse = new JTextField();
        txtAdresse.setFont(new Font("Rockwell", Font.PLAIN, 12));
        JLabel lblTelephone = new JLabel("Téléphone:");
        lblTelephone.setFont(new Font("Rockwell", Font.PLAIN, 12));
        JTextField txtTelephone = new JTextField();
        txtTelephone.setFont(new Font("Rockwell", Font.PLAIN, 12));

        InfoPanel.add(lblNom);
        InfoPanel.add(txtNom);
        InfoPanel.add(lblPrenom);
        InfoPanel.add(txtPrenom);
        InfoPanel.add(lblAdresse);
        InfoPanel.add(txtAdresse);
        InfoPanel.add(lblTelephone);
        InfoPanel.add(txtTelephone);

        RightPanel.add(InfoPanel, BorderLayout.NORTH);


        JPanel TablePanel = new JPanel();
        TablePanel.setBorder(new TitledBorder(new EtchedBorder(), "Liste des biens", TitledBorder.CENTER, TitledBorder.TOP));
        TablePanel.setLayout(new BorderLayout());
        TablePanel.setBackground(Color.WHITE);

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
        TablePanel.add(tableScrollPane, BorderLayout.CENTER);

        RightPanel.add(TablePanel, BorderLayout.CENTER);

        MainPane.add(RightPanel, BorderLayout.CENTER);


        JPanel BottomPanel = new JPanel();
        BottomPanel.setBackground(new Color(224, 247, 250));
        JButton btnClose = new JButton("Fermer");
        btnClose.setBackground(new Color(244, 67, 54)); 
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Rockwell", Font.PLAIN, 12));
        btnClose.addActionListener(e -> System.exit(0));
        BottomPanel.add(btnClose);
        MainPane.add(BottomPanel, BorderLayout.SOUTH);

  
        MainPane.setBackground(new Color(250, 250, 250));
    }
}
