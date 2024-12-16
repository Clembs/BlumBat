package view;

import controller.ControleurAjoutBien;
import controller.ControleurModifierLocataire;

import javax.swing.*;
import java.awt.*;

public class PopupModifierLocataire extends JInternalFrame {

    private JTextField textIdentifiant;
    private JTextField textNom;
    private JTextField textPrenom;
    private JTextField textEmail;
    private JTextField textTelephone;
    private DefaultListModel<String> erreursListModel;
    private JList<String> erreursList;

    public PopupModifierLocataire(String identifiant, String nom, String prenom, String email, String telephone) {

        setTitle("Modifier un Locataire");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(40, 40, 40));

        // Initialisation du modèle de liste des erreurs
        erreursListModel = new DefaultListModel<>();
        erreursList = new JList<>(erreursListModel);

        // Titre
        JLabel lblTitle = new JLabel("Modifier un Locataire", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(240, 240, 240));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(60, 60, 60));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(lblTitle, BorderLayout.NORTH);

        // Panneau de formulaire
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBackground(new Color(50, 50, 50));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Champs de formulaire
        textIdentifiant = new JTextField(identifiant);
        addField(formPanel, "Identifiant:*", textIdentifiant);

        textNom = new JTextField(nom);
        addField(formPanel, "Nom:* ", textNom);

        textPrenom = new JTextField(prenom);
        addField(formPanel, "Prénom:* ", textPrenom);

        textEmail = new JTextField(email);
        addField(formPanel, "Email:*", textEmail);

        textTelephone = new JTextField(telephone);
        addField(formPanel, "Téléphone:*", textTelephone);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        // Panneau des erreurs
        Panel erreurPanel = new Panel(new BorderLayout());
        erreurPanel.setBackground(new Color(40, 40, 40));

        erreursList.setFont(new Font("SansSerif", Font.PLAIN, 12));
        erreursList.setBackground(new Color(60, 60, 60));
        erreursList.setForeground(Color.RED);

        JScrollPane erreurScrollPane = new JScrollPane(erreursList);
        erreurScrollPane.setPreferredSize(new Dimension(200, 100));

        erreurPanel.add(erreurScrollPane, BorderLayout.CENTER);
        getContentPane().add(erreurPanel, BorderLayout.WEST);

        // Panneau des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(40, 40, 40));

        JButton btnModifier = new JButton("Modifier");
        btnModifier.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnModifier.setBackground(new Color(0, 170, 85));
        btnModifier.setForeground(Color.WHITE);
        btnModifier.setFocusPainted(false);
        btnModifier.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(btnModifier);

        ControleurModifierLocataire controleur = new ControleurModifierLocataire(this);
        btnModifier.addActionListener(controleur);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAnnuler.setBackground(new Color(200, 50, 50));
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setFocusPainted(false);
        btnAnnuler.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(btnAnnuler);

        btnAnnuler.addActionListener(e -> {
            dispose();
        });

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    // Méthodes utilitaires pour les champs
    private void addField(JPanel panel, String label, JTextField textField) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl.setForeground(new Color(230, 230, 230));
        panel.add(lbl);

        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.setBackground(new Color(60, 60, 60));
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        panel.add(textField);
    }

    // Méthodes pour récupérer les valeurs
    public String getIdentifiant() {
        return textIdentifiant.getText();
    }

    public String getNom() {
        return textNom.getText();
    }

    public String getPrenom() {
        return textPrenom.getText();
    }

    public String getEmail() {
        return textEmail.getText();
    }

    public String getTelephone() {
        return textTelephone.getText();
    }

    // Méthodes pour gérer les erreurs
    public void addErreur(String erreur) {
        erreursListModel.addElement(erreur);
    }

    public void clearErreurs() {
        erreursListModel.clear();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Modifier un Locataire");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.getContentPane().setLayout(new BorderLayout());

                JDesktopPane desktopPane = new JDesktopPane();
                frame.getContentPane().add(desktopPane, BorderLayout.CENTER);

                String identifiant = "123";
                String nom = "Dupont";
                String prenom = "Jean";
                String email = "jean.dupont@example.com";
                String telephone = "0123456789";

                // Création de la fenêtre PopupModifierLocataire
                PopupModifierLocataire fenetre = new PopupModifierLocataire(identifiant, nom, prenom, email, telephone);

                desktopPane.add(fenetre);
                fenetre.setVisible(true);

                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
