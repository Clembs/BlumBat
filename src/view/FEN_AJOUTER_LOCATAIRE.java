package view;

import model.BienImmobilier;
import model.TypeBien;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class FEN_AJOUTER_LOCATAIRE extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textNom;
    private JTextField textPrenom;
    private JTextField textAdresse;
    private JTextField textTelephone;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FEN_AJOUTER_LOCATAIRE frame = new FEN_AJOUTER_LOCATAIRE();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public FEN_AJOUTER_LOCATAIRE() {

        setTitle("Ajouter un Locataire");
        setBounds(100, 100, 500, 350);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(40, 40, 40));


        JLabel lblTitle = new JLabel("Ajouter un Locataire", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(240, 240, 240));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(60, 60, 60));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(lblTitle, BorderLayout.NORTH);


        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(new Color(50, 50, 50));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));


        JLabel lblNom = new JLabel("Nom:");
        lblNom.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblNom.setForeground(new Color(230, 230, 230));
        formPanel.add(lblNom);
        textNom = new JTextField();
        textNom.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textNom.setBackground(new Color(60, 60, 60));
        textNom.setForeground(Color.WHITE);
        textNom.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        formPanel.add(textNom);


        JLabel lblPrenom = new JLabel("Prénom:");
        lblPrenom.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPrenom.setForeground(new Color(230, 230, 230));
        formPanel.add(lblPrenom);
        textPrenom = new JTextField();
        textPrenom.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textPrenom.setBackground(new Color(60, 60, 60));
        textPrenom.setForeground(Color.WHITE);
        textPrenom.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        formPanel.add(textPrenom);


        JLabel lblAdresse = new JLabel("Adresse:");
        lblAdresse.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblAdresse.setForeground(new Color(230, 230, 230));
        formPanel.add(lblAdresse);
        textAdresse = new JTextField();
        textAdresse.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textAdresse.setBackground(new Color(60, 60, 60));
        textAdresse.setForeground(Color.WHITE);
        textAdresse.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        formPanel.add(textAdresse);


        JLabel lblTelephone = new JLabel("Téléphone:");
        lblTelephone.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblTelephone.setForeground(new Color(230, 230, 230));
        formPanel.add(lblTelephone);
        textTelephone = new JTextField();
        textTelephone.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textTelephone.setBackground(new Color(60, 60, 60));
        textTelephone.setForeground(Color.WHITE);
        textTelephone.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        formPanel.add(textTelephone);

        getContentPane().add(formPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(40, 40, 40));

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAjouter.setBackground(new Color(0, 170, 85));
        btnAjouter.setForeground(Color.WHITE);
        btnAjouter.setFocusPainted(false);
        btnAjouter.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(btnAjouter);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAnnuler.setBackground(new Color(200, 50, 50));
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setFocusPainted(false);
        btnAnnuler.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(btnAnnuler);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}
public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
        try {
            JFrame frame = new JFrame("Test POPUP_LOUER");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.getContentPane().setLayout(new BorderLayout());

            JDesktopPane desktopPane = new JDesktopPane();
            frame.getContentPane().add(desktopPane, BorderLayout.CENTER);

            POPUP_LOUER popup = new POPUP_LOUER(
                    new BienImmobilier("3344", TypeBien.BATIMENT, "Rue ta mere", "ap4", 81000, "Toulouse"));
            desktopPane.add(popup);
            popup.setVisible(true);

            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}