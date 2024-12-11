package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FEN_AJOUT_LOCATAIRE extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdentifiant;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtEmail;
    private JTextField txtTelephone;
    private JList<String> listLocataires;
    private JList<String> erreursList;
    private DefaultListModel<String> erreursListModel;

    /**
     * Create the frame.
     */
    public FEN_AJOUT_LOCATAIRE() {
        setTitle("Ajouter un Locataire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelHeader = new JPanel();
        contentPane.add(panelHeader, BorderLayout.NORTH);
        JLabel lblTitle = new JLabel("Ajout d'un Locataire");
        lblTitle.setFont(new Font("Rockwell", Font.BOLD, 28));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panelHeader.add(lblTitle);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(6, 2, 10, 10));
        contentPane.add(panelForm, BorderLayout.EAST);

        JLabel lblIdentifiant = new JLabel("Identifiant:");
        lblIdentifiant.setFont(new Font("Rockwell", Font.PLAIN, 14));
        lblIdentifiant.setHorizontalAlignment(SwingConstants.CENTER);
        txtIdentifiant = new JTextField();
        txtIdentifiant.setFont(new Font("Rockwell", Font.PLAIN, 14));
        panelForm.add(lblIdentifiant);
        panelForm.add(txtIdentifiant);

        JLabel lblNom = new JLabel("Nom:");
        lblNom.setFont(new Font("Rockwell", Font.PLAIN, 14));
        lblNom.setHorizontalAlignment(SwingConstants.CENTER);
        txtNom = new JTextField();
        txtNom.setFont(new Font("Rockwell", Font.PLAIN, 14));
        panelForm.add(lblNom);
        panelForm.add(txtNom);

        JLabel lblPrenom = new JLabel("Prénom:");
        lblPrenom.setFont(new Font("Rockwell", Font.PLAIN, 14));
        lblPrenom.setHorizontalAlignment(SwingConstants.CENTER);
        txtPrenom = new JTextField();
        txtPrenom.setFont(new Font("Rockwell", Font.PLAIN, 14));
        panelForm.add(lblPrenom);
        panelForm.add(txtPrenom);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Rockwell", Font.PLAIN, 14));
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Rockwell", Font.PLAIN, 14));
        panelForm.add(lblEmail);
        panelForm.add(txtEmail);

        JLabel lblTelephone = new JLabel("Téléphone:");
        lblTelephone.setFont(new Font("Rockwell", Font.PLAIN, 14));
        lblTelephone.setHorizontalAlignment(SwingConstants.CENTER);
        txtTelephone = new JTextField();
        txtTelephone.setFont(new Font("Rockwell", Font.PLAIN, 14));
        panelForm.add(lblTelephone);
        panelForm.add(txtTelephone);

        JPanel panelButton = new JPanel();
        contentPane.add(panelButton, BorderLayout.SOUTH);
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));

        erreursListModel = new DefaultListModel<String>();
        erreursList = new JList<>(erreursListModel);
        erreursList.setEnabled(false);
        erreursList.setFont(new Font("Rockwell", Font.PLAIN, 14));
        erreursList.setForeground(Color.RED);
        erreursList.setBorder(BorderFactory.createLineBorder(Color.RED));
        erreursList.setModel(erreursListModel);
        panelButton.add(erreursList);

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setFont(new Font("Rockwell", Font.PLAIN, 14));
        panelButton.add(btnAjouter);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Rockwell", Font.PLAIN, 14));
        panelButton.add(btnAnnuler);

        JPanel panelCenter = new JPanel();
        panelCenter.setBorder(
                new TitledBorder(new EtchedBorder(), "Liste des locataires", TitledBorder.CENTER, TitledBorder.TOP));
        panelCenter.setLayout(new BorderLayout(5, 5));
        contentPane.add(panelCenter, BorderLayout.CENTER);

        this.listLocataires = new JList<>(
                new String[] { "Locataire 1", "Locataire 2", "Locataire 3", "Locataire 4", "Locataire 5" });
        this.listLocataires.setFont(new Font("Rockwell", Font.PLAIN, 14));
        this.listLocataires.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(this.listLocataires);
        panelCenter.add(scrollPane, BorderLayout.CENTER);

    }

    public String getId() {
        return txtIdentifiant.getText();
    }

    public String getNom() {
        return txtNom.getText();
    }

    public String getPrenom() {
        return txtIdentifiant.getText();
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getTelephone() {
        return txtTelephone.getText();
    }

    public void addErreur(String erreur) {
        DefaultListModel<String> model = (DefaultListModel<String>) erreursList.getModel();
        model.addElement(erreur);
    }

    public void clearErreurs() {
        if (erreursListModel != null) {
            erreursListModel = new DefaultListModel<String>();
        }
        erreursListModel.clear();
        erreursList.setModel(erreursListModel);
    }

    public boolean hasErreurs() {
        return erreursListModel.size() > 0;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FEN_AJOUT_LOCATAIRE frame = new FEN_AJOUT_LOCATAIRE();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
