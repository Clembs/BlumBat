package view;

import controller.ControleurConsultationLocataires;
import model.Locataire;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class FenConsultationLocataires extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel MainPane;
    private JTable tableBiens;
    private JTextField txtNom, txtPrenom, txtAdresse, txtTelephone;
    private JList<String> locatairesList;
    private ControleurConsultationLocataires controleur;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FenConsultationLocataires frame = new FenConsultationLocataires();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public FenConsultationLocataires() {
        this.setTitle("Consultation des locataires");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 720, 500);
        MainPane = new JPanel(new BorderLayout(10, 10));
        MainPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(MainPane);

        // Title Panel
        JPanel TitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        TitlePanel.setBackground(Color.DARK_GRAY);
        JLabel lblTitle = new JLabel("Gestion des locataires");
        lblTitle.setFont(new Font("Rockwell", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        TitlePanel.add(lblTitle);
        MainPane.add(TitlePanel, BorderLayout.NORTH);

        // Panel (Liste des locataires)
        JPanel LeftPanel = new JPanel(new BorderLayout(5, 5));
        LeftPanel.setBorder(new TitledBorder(new EtchedBorder(), "Liste des locataires", TitledBorder.CENTER, TitledBorder.TOP));

        locatairesList = new JList<>();
        locatairesList.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        locatairesList.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(locatairesList);
        LeftPanel.add(scrollPane, BorderLayout.CENTER);



        // Panel (Bouttons)
        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnModify = new JButton("Modifier");
        JButton btnAdd = new JButton("Ajouter");
        btnModify.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        btnModify.setBackground(new Color(255, 99, 71));
        btnModify.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        btnAdd.setBackground(new Color(46, 139, 87));
        btnAdd.setForeground(Color.WHITE);
        ButtonPanel.add(btnModify);
        ButtonPanel.add(btnAdd);
        LeftPanel.add(ButtonPanel, BorderLayout.SOUTH);
        MainPane.add(LeftPanel, BorderLayout.WEST);

        // Panel  (Informations & Table)
        JPanel RightPanel = new JPanel(new BorderLayout(5, 5));

        // InfoPanel
        JPanel InfoPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        InfoPanel.setBorder(new TitledBorder(new EtchedBorder(), "Informations Personnelles", TitledBorder.CENTER, TitledBorder.TOP));
        InfoPanel.setBackground(Color.LIGHT_GRAY);

        JLabel lblNom = new JLabel("Nom:", SwingConstants.CENTER);
        txtNom = new JTextField();
        txtNom.setFont(new Font("Rockwell", Font.PLAIN, 12));
        txtNom.setEditable(false);

        JLabel lblPrenom = new JLabel("Prénom:", SwingConstants.CENTER);
        txtPrenom = new JTextField();
        txtPrenom.setFont(new Font("Rockwell", Font.PLAIN, 12));
        txtPrenom.setEditable(false);

        JLabel lblAdresse = new JLabel("Adresse:", SwingConstants.CENTER);
        txtAdresse = new JTextField();
        txtAdresse.setFont(new Font("Rockwell", Font.PLAIN, 12));
        txtAdresse.setEditable(false);

        JLabel lblTelephone = new JLabel("Téléphone:", SwingConstants.CENTER);
        txtTelephone = new JTextField();
        txtTelephone.setFont(new Font("Rockwell", Font.PLAIN, 12));
        txtTelephone.setEditable(false);

        InfoPanel.add(lblNom);
        InfoPanel.add(txtNom);
        InfoPanel.add(lblPrenom);
        InfoPanel.add(txtPrenom);
        InfoPanel.add(lblAdresse);
        InfoPanel.add(txtAdresse);
        InfoPanel.add(lblTelephone);
        InfoPanel.add(txtTelephone);

        RightPanel.add(InfoPanel, BorderLayout.NORTH);

        // Panel (Liste des biens)
        JPanel TablePanel = new JPanel(new BorderLayout());
        TablePanel.setBackground(Color.LIGHT_GRAY);
        TablePanel.setBorder(new TitledBorder(new EtchedBorder(), "Liste des biens", TitledBorder.CENTER, TitledBorder.TOP));

        tableBiens = new JTable(new DefaultTableModel(
                new Object[][] {
                        {"Bien 1", "Adresse 1", "Type 1"},
                        {"Bien 2", "Adresse 2", "Type 2"}
                },
                new String[] {"Nom du Bien", "Adresse", "Type"}
        ));
        tableBiens.setFont(new Font("Rockwell", Font.PLAIN, 12));
        JScrollPane tableScrollPane = new JScrollPane(tableBiens);
        TablePanel.add(tableScrollPane, BorderLayout.CENTER);
        RightPanel.add(TablePanel, BorderLayout.CENTER);

        MainPane.add(RightPanel, BorderLayout.CENTER);

        controleur = new ControleurConsultationLocataires(this);
    }


    // Mettre à jour la liste des locataires
    public void setLocatairesList(List<Locataire> locataires) {
        String[] locataireArray = new String[locataires.size()];
        for (int i = 0; i < locataires.size(); i++) {
            Locataire locataire = locataires.get(i);
            locataireArray[i] = "• " + locataire.getNom() + " " + locataire.getPrenom();
        }
        locatairesList.setListData(locataireArray);
    }

    // Retourne la liste des locataires
    public JList<String> getLocatairesList() {
        return locatairesList;
    }

    // Mise à jour des informations du locataire
    public void updateDetails(Locataire locataire) {
        txtNom.setText(locataire.getNom());
        txtPrenom.setText(locataire.getPrenom());
        txtAdresse.setText(locataire.getEmail());
        txtTelephone.setText(locataire.getTelephone());
    }
}
