package view;

import controller.ControleurModificationLocataire;
import model.Locataire;
import model.Proprietaire;

import javax.swing.*;
import java.awt.*;

public class PanelModificationLocataire extends JPanel {
  private JTextField nomField;
  private JTextField prenomField;
  private JTextField emailField;
  private JTextField telephoneField;
  private DefaultListModel<String> erreursListModel;
  private JList<String> erreursList;

  public PanelModificationLocataire(FenLocataires fenLocataires, Proprietaire proprietaire, Locataire locataire) {
    this.setLayout(new BorderLayout(10, 10));
    this.setBackground(new Color(40, 40, 40));

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
    this.add(lblTitle, BorderLayout.NORTH);

    // Panneau de formulaire
    JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
    formPanel.setBackground(new Color(50, 50, 50));
    formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    // Champs de formulaire
    JTextField textIdentifiant = new JTextField(locataire.getId());
    textIdentifiant.setEditable(false);
    addField(formPanel, "Identifiant :", textIdentifiant);

    nomField = new JTextField(locataire.getNom());
    addField(formPanel, "Nom :", nomField);

    prenomField = new JTextField(locataire.getPrenom());
    addField(formPanel, "Prénom :", prenomField);

    emailField = new JTextField(locataire.getEmail());
    addField(formPanel, "Email :", emailField);

    telephoneField = new JTextField(locataire.getTelephone());
    addField(formPanel, "Téléphone :", telephoneField);

    this.add(formPanel, BorderLayout.CENTER);

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

    JButton btnModifier = new JButton("Enregistrer");
    btnModifier.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnModifier.setBackground(new Color(0, 170, 85));
    btnModifier.setForeground(Color.WHITE);
    btnModifier.setFocusPainted(false);
    btnModifier.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    buttonPanel.add(btnModifier);

    ControleurModificationLocataire controleur = new ControleurModificationLocataire(fenLocataires, this, proprietaire,
        locataire);
    btnModifier.addActionListener(controleur);

    JButton btnAnnuler = new JButton("Annuler");
    btnAnnuler.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnAnnuler.setBackground(new Color(200, 50, 50));
    btnAnnuler.setForeground(Color.WHITE);
    btnAnnuler.setFocusPainted(false);
    btnAnnuler.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    buttonPanel.add(btnAnnuler);

    btnAnnuler.addActionListener(controleur);

    southPanel.add(buttonPanel, BorderLayout.SOUTH);
    this.add(southPanel, BorderLayout.SOUTH);
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
  public String getNom() {
    return nomField.getText();
  }

  public String getPrenom() {
    return prenomField.getText();
  }

  public String getEmail() {
    return emailField.getText();
  }

  public String getTelephone() {
    return telephoneField.getText();
  }

  // Méthodes pour gérer les erreurs
  public void addErreur(String erreur) {
    erreursListModel.addElement(erreur);
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
}
