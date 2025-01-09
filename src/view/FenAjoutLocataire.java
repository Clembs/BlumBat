package view;

import javax.swing.*;
import java.awt.*;
import controller.ControleurAjoutLocataire;
import model.Proprietaire;

public class FenAjoutLocataire extends JFrame {
  private JTextField textIdentifiant;
  private JTextField textNom;
  private JTextField textPrenom;
  private JTextField textEmail;
  private JTextField textTelephone;
  private DefaultListModel<String> erreursListModel;
  private JList<String> erreursList;

  public FenAjoutLocataire(FenLocataires fenLocataires, Proprietaire proprietaire) {
    // Configuration de la fenêtre
    setTitle("Ajouter un locataire");
    setBounds(100, 100, 600, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(10, 10));
    getContentPane().setBackground(new Color(40, 40, 40));

    // Initialisation du modèle de liste des erreurs
    erreursListModel = new DefaultListModel<>();
    erreursList = new JList<>(erreursListModel);

    // Titre
    JLabel lblTitle = new JLabel("Ajouter un locataire", SwingConstants.CENTER);
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
    textIdentifiant = new JTextField();
    addField(formPanel, "Identifiant :", textIdentifiant);

    textNom = new JTextField();
    addField(formPanel, "Nom :", textNom);

    textPrenom = new JTextField();
    addField(formPanel, "Prénom : ", textPrenom);

    textEmail = new JTextField();
    addField(formPanel, "Email :", textEmail);

    textTelephone = new JTextField();
    addField(formPanel, "Téléphone :", textTelephone);

    getContentPane().add(formPanel, BorderLayout.CENTER);

    // Panneau des erreurs et des boutons
    JPanel southPanel = new JPanel(new BorderLayout());
    southPanel.setBackground(new Color(40, 40, 40));

    // Panneau des erreurs
    erreursList.setFont(new Font("SansSerif", Font.PLAIN, 12));
    erreursList.setBackground(new Color(60, 60, 60));
    erreursList.setForeground(Color.RED);

    JScrollPane erreurScrollPane = new JScrollPane(erreursList);
    erreurScrollPane.setPreferredSize(new Dimension(200, 100));
    southPanel.add(erreurScrollPane, BorderLayout.NORTH);

    // Panneau des boutons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    buttonPanel.setBackground(new Color(40, 40, 40));

    JButton btnAjouter = new JButton("Ajouter");
    btnAjouter.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnAjouter.setBackground(new Color(0, 170, 85));
    btnAjouter.setForeground(Color.WHITE);
    btnAjouter.setFocusPainted(false);
    btnAjouter.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    buttonPanel.add(btnAjouter);

    ControleurAjoutLocataire controleur = new ControleurAjoutLocataire(this, proprietaire, fenLocataires);
    btnAjouter.addActionListener(controleur);

    JButton btnAnnuler = new JButton("Annuler");
    btnAnnuler.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnAnnuler.setBackground(new Color(200, 50, 50));
    btnAnnuler.setForeground(Color.WHITE);
    btnAnnuler.setFocusPainted(false);
    btnAnnuler.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    buttonPanel.add(btnAnnuler);

    btnAnnuler.addActionListener(e -> dispose());

    southPanel.add(buttonPanel, BorderLayout.SOUTH);
    getContentPane().add(southPanel, BorderLayout.SOUTH);
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

  public boolean hasErreurs() {
    return erreursListModel.size() > 0;
  }

  public void clearErreurs() {
    erreursListModel.clear();
  }
}
