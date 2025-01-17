package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import components.Bouton;
import components.ChampSaisie;
import components.Layout;
import components.Libellé;
import components.Bouton.VarianteButton;
import components.Libellé.TypeLibellé;

import java.awt.*;
import controller.ControleurAjoutLocataire;
import model.Proprietaire;

public class VueAjoutLocataire extends JFrame {
  private ChampSaisie idField;
  private ChampSaisie nomField;
  private ChampSaisie prenomField;
  private ChampSaisie emailField;
  private ChampSaisie telephoneField;

  private JList<String> erreursList;
  private DefaultListModel<String> erreursListModel;

  public VueAjoutLocataire(VueLocataires fenLocataires, Proprietaire proprietaire) {
    // Configuration de la fenêtre
    this.setTitle("Ajouter un locataire");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setBounds(100, 100, 600, 700);
    this.setResizable(false);

    JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(16, 16, 16, 16));
    contentPane.setLayout(new BorderLayout(10, 10));
    this.setContentPane(contentPane);

    // Titre
    Libellé lblTitle = new Libellé("Ajouter un locataire", TypeLibellé.TITRE);
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(lblTitle, BorderLayout.NORTH);

    // Panneau de formulaire
    JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

    idField = new ChampSaisie("Identifiant");
    formPanel.add(idField);

    prenomField = new ChampSaisie("Prénom");
    formPanel.add(prenomField);

    nomField = new ChampSaisie("Nom");
    formPanel.add(nomField);

    emailField = new ChampSaisie("Email");
    formPanel.add(emailField);

    telephoneField = new ChampSaisie("Téléphone");
    formPanel.add(telephoneField);

    contentPane.add(formPanel, BorderLayout.CENTER);

    // Panneau des erreurs et des boutons
    JPanel bottomPanel = new JPanel(new BorderLayout());

    // Panneau des erreurs
    erreursListModel = new DefaultListModel<>();
    erreursList = new JList<>(erreursListModel);

    erreursList.setFont(Layout.POLICE_SMALL);
    erreursList.setForeground(Layout.COULEUR_DANGER);
    erreursList.setBackground(Layout.COULEUR_FOND);

    JScrollPane erreurScrollPane = new JScrollPane(erreursList);
    erreurScrollPane.setPreferredSize(new Dimension(200, 100));
    erreurScrollPane
        .setBorder(new TitledBorder(new EtchedBorder(), "Erreurs", TitledBorder.CENTER, TitledBorder.TOP));
    bottomPanel.add(erreurScrollPane, BorderLayout.NORTH);

    // Panneau des boutons
    ControleurAjoutLocataire controleur = new ControleurAjoutLocataire(this, proprietaire, fenLocataires);

    JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.TRAILING, 8, 8));

    Bouton btnAjouter = new Bouton("Ajouter");
    btnAjouter.addActionListener(controleur);
    panelBoutons.add(btnAjouter);

    Bouton btnAnnuler = new Bouton("Annuler", VarianteButton.SECONDAIRE);
    btnAnnuler.addActionListener(e -> this.dispose());
    panelBoutons.add(btnAnnuler);

    bottomPanel.add(panelBoutons, BorderLayout.SOUTH);
    contentPane.add(bottomPanel, BorderLayout.SOUTH);
  }

  // Méthodes pour récupérer les valeurs
  public String getId() {
    return idField.getValue();
  }

  public String getNom() {
    return nomField.getValue();
  }

  public String getPrenom() {
    return prenomField.getValue();
  }

  public String getEmail() {
    return emailField.getValue();
  }

  public String getTelephone() {
    return telephoneField.getValue();
  }

  // Méthodes pour gérer les erreurs
  public void addErreur(String erreur) {
    erreursList.setVisible(true);
    erreursListModel.addElement(erreur);
  }

  public boolean hasErreurs() {
    return erreursListModel.size() > 0;
  }

  public void clearErreurs() {
    erreursListModel.clear();
  }
}
