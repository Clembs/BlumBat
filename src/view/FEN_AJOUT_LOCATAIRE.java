package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FEN_AJOUT_LOCATAIRE extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdentifiant;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtEmail;
    private JTextField txtTelephone;

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
        contentPane.add(panelForm, BorderLayout.CENTER);

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

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setFont(new Font("Rockwell", Font.PLAIN, 14));
        panelButton.add(btnAjouter);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Rockwell", Font.PLAIN, 14));
        panelButton.add(btnAnnuler);
    }
}
