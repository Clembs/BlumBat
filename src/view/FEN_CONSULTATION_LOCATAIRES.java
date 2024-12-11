package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class FEN_CONSULTATION_LOCATAIRES extends JFrame {

    private static final long serialVersionUID = 1L;
private JPanel MainPane;
private JDesktopPane desktopPane;
private JTable tableBiens;
    private JTextField textPrenom;

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
desktopPane = new JDesktopPane();
MainPane.add(desktopPane, BorderLayout.CENTER);

        UIManager.put("Label.font", new Font("Rockwell", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Rockwell", Font.PLAIN, 14));

        JPanel TitlePanel = new JPanel();
        TitlePanel.setBackground(Color.DARK_GRAY);
        JLabel lblTitle = new JLabel("Gestion des locataires");
        lblTitle.setFont(new Font("Rockwell", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        TitlePanel.add(lblTitle);
        MainPane.add(TitlePanel, BorderLayout.NORTH);


        JPanel LeftPanel = new JPanel();
        LeftPanel.setBorder(new TitledBorder(new EtchedBorder(), "Liste des locataires", TitledBorder.CENTER, TitledBorder.TOP));
        LeftPanel.setLayout(new BorderLayout(5, 5));
        LeftPanel.setBackground(Color.LIGHT_GRAY);
        JList<String> list = new JList<>(new String[]{"Locataire 1", "Locataire 2", "Locataire 3"});
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
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FEN_AJOUTER_LOCATAIRE fenAjouterLocataire = new FEN_AJOUTER_LOCATAIRE();
                getLayeredPane().add(fenAjouterLocataire);
                getLayeredPane().moveToFront(fenAjouterLocataire);
                fenAjouterLocataire.setLocation(50, 50);
                fenAjouterLocataire.setVisible(true);


            }
        });


        MainPane.add(LeftPanel, BorderLayout.WEST);


        JPanel RightPanel = new JPanel();
        RightPanel.setLayout(new BorderLayout(5, 5));

        JPanel InfoPanel = new JPanel();
        InfoPanel.setBorder(new TitledBorder(new EtchedBorder(), "Informations Personnelles", TitledBorder.CENTER, TitledBorder.TOP));
        InfoPanel.setBackground(Color.LIGHT_GRAY);
        GridBagLayout gbl_InfoPanel = new GridBagLayout();
        gbl_InfoPanel.columnWidths = new int[] {250, 40, 0};
        gbl_InfoPanel.rowHeights = new int[]{21, 21, 21, 21, 0, 0};
        gbl_InfoPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_InfoPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        InfoPanel.setLayout(gbl_InfoPanel);
        
                JLabel lblNom = new JLabel("Nom:");
                lblNom.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
                
        GridBagConstraints gbc_lblNom = new GridBagConstraints();
        gbc_lblNom.fill = GridBagConstraints.VERTICAL;
        gbc_lblNom.insets = new Insets(0, 0, 5, 5);
        gbc_lblNom.gridx = 0;
        gbc_lblNom.gridy = 0;
        InfoPanel.add(lblNom, gbc_lblNom);
        
        textPrenom = new JTextField();
        textPrenom.setFont(new Font("Rockwell", Font.PLAIN, 12));
        textPrenom.setEnabled(false);
        textPrenom.setEditable(false);
        GridBagConstraints gbc_textPrenom = new GridBagConstraints();
        gbc_textPrenom.insets = new Insets(0, 0, 5, 0);
        gbc_textPrenom.fill = GridBagConstraints.HORIZONTAL;
        gbc_textPrenom.gridx = 1;
        gbc_textPrenom.gridy = 0;
        InfoPanel.add(textPrenom, gbc_textPrenom);
        JLabel lblPrenom = new JLabel("Prenom:");
        lblPrenom.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrenom.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        GridBagConstraints gbc_lblPrenom = new GridBagConstraints();
        gbc_lblPrenom.fill = GridBagConstraints.VERTICAL;
        gbc_lblPrenom.insets = new Insets(0, 0, 5, 5);
        gbc_lblPrenom.gridx = 0;
        gbc_lblPrenom.gridy = 1;
        InfoPanel.add(lblPrenom, gbc_lblPrenom);
        JTextField txtPrenom = new JTextField();
        txtPrenom.setEnabled(false);
        txtPrenom.setEditable(false);
        txtPrenom.setFont(new Font("Rockwell", Font.PLAIN, 12));
        GridBagConstraints gbc_txtPrenom = new GridBagConstraints();
        gbc_txtPrenom.fill = GridBagConstraints.BOTH;
        gbc_txtPrenom.insets = new Insets(0, 0, 5, 0);
        gbc_txtPrenom.gridx = 1;
        gbc_txtPrenom.gridy = 1;
        InfoPanel.add(txtPrenom, gbc_txtPrenom);
        JLabel lblAdresse = new JLabel("Adresse:");
        lblAdresse.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        GridBagConstraints gbc_lblAdresse = new GridBagConstraints();
        gbc_lblAdresse.fill = GridBagConstraints.VERTICAL;
        gbc_lblAdresse.insets = new Insets(0, 0, 5, 5);
        gbc_lblAdresse.gridx = 0;
        gbc_lblAdresse.gridy = 2;
        InfoPanel.add(lblAdresse, gbc_lblAdresse);
        JTextField txtAdresse = new JTextField();
        txtAdresse.setEnabled(false);
        txtAdresse.setEditable(false);
        txtAdresse.setFont(new Font("Rockwell", Font.PLAIN, 12));
        GridBagConstraints gbc_txtAdresse = new GridBagConstraints();
        gbc_txtAdresse.fill = GridBagConstraints.BOTH;
        gbc_txtAdresse.insets = new Insets(0, 0, 5, 0);
        gbc_txtAdresse.gridx = 1;
        gbc_txtAdresse.gridy = 2;
        InfoPanel.add(txtAdresse, gbc_txtAdresse);
        JLabel lblTelephone = new JLabel("Téléphone:");
        lblTelephone.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        GridBagConstraints gbc_lblTelephone = new GridBagConstraints();
        gbc_lblTelephone.fill = GridBagConstraints.VERTICAL;
        gbc_lblTelephone.insets = new Insets(0, 0, 5, 5);
        gbc_lblTelephone.gridx = 0;
        gbc_lblTelephone.gridy = 3;
        InfoPanel.add(lblTelephone, gbc_lblTelephone);
        JTextField txtTelephone = new JTextField();
        txtTelephone.setEnabled(false);
        txtTelephone.setEditable(false);
        txtTelephone.setFont(new Font("Rockwell", Font.PLAIN, 12));
        GridBagConstraints gbc_txtTelephone = new GridBagConstraints();
        gbc_txtTelephone.insets = new Insets(0, 0, 5, 0);
        gbc_txtTelephone.fill = GridBagConstraints.BOTH;
        gbc_txtTelephone.gridx = 1;
        gbc_txtTelephone.gridy = 3;
        InfoPanel.add(txtTelephone, gbc_txtTelephone);

        RightPanel.add(InfoPanel, BorderLayout.NORTH);


        JPanel TablePanel = new JPanel();
        TablePanel.setBorder(new TitledBorder(new EtchedBorder(), "Liste des biens", TitledBorder.CENTER, TitledBorder.TOP));
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

MainPane.add(RightPanel, BorderLayout.EAST);


     
        MainPane.setBackground(new Color(250, 250, 250));
    }
}
