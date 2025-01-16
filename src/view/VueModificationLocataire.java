package view;

import controller.ControleurModificationLocataire;
import model.Locataire;
import model.Proprietaire;

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

public class VueModificationLocataire extends JPanel {
  private ChampSaisie nomField;
  private ChampSaisie prenomField;
  private ChampSaisie emailField;
  private ChampSaisie telephoneField;
  private DefaultListModel<String> erreursListModel;
  private JList<String> erreursList;

  public VueModificationLocataire(VueLocataires fenLocataires, Proprietaire proprietaire, Locataire locataire) {
    this.setLayout(new BorderLayout(8, 8));
    this.setBorder(new EmptyBorder(0, 16, 16, 16));

    // Titre
    Libellé lblTitle = new Libellé("Modifier " + locataire.getPrenom() + " " + locataire.getNom(), TypeLibellé.EN_TETE);
    this.add(lblTitle, BorderLayout.NORTH);

    // Panneau de formulaire
    JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

    // Champs de formulaire
    ChampSaisie idField = new ChampSaisie("Identifiant", locataire.getId());
    idField.getChampSaisie().setEnabled(false);
    formPanel.add(idField);

    prenomField = new ChampSaisie("Prénom", locataire.getPrenom());
    formPanel.add(prenomField);

    nomField = new ChampSaisie("Nom", locataire.getNom());
    formPanel.add(nomField);

    emailField = new ChampSaisie("Adresse email", locataire.getEmail());
    formPanel.add(emailField);

    telephoneField = new ChampSaisie("Téléphone", locataire.getTelephone());
    formPanel.add(telephoneField);

    this.add(formPanel, BorderLayout.CENTER);

    // Panneau des erreurs et des boutons
    JPanel bottomPanel = new JPanel(new BorderLayout());

    // Initialisation du modèle de liste des erreurs
    erreursListModel = new DefaultListModel<>();
    erreursList = new JList<>(erreursListModel);
    erreursList.setFont(Layout.POLICE_SMALL);
    erreursList.setForeground(Layout.COULEUR_DANGER);
    erreursList.setBackground(Layout.COULEUR_FOND);

    // Panneau des erreurs
    JScrollPane erreurScrollPane = new JScrollPane(erreursList);
    erreurScrollPane.setBorder(
        new TitledBorder(new EtchedBorder(), "Erreurs", TitledBorder.CENTER, TitledBorder.TOP));
    erreurScrollPane.setPreferredSize(new Dimension(200, 100));
    bottomPanel.add(erreurScrollPane, BorderLayout.NORTH);

    // Panneau des boutons
    ControleurModificationLocataire controleur = new ControleurModificationLocataire(fenLocataires, this, proprietaire,
        locataire);

    JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.TRAILING, 8, 8));

    Bouton btnModifier = new Bouton("Enregistrer");
    btnModifier.addActionListener(controleur);
    panelBoutons.add(btnModifier);

    Bouton btnAnnuler = new Bouton("Annuler", VarianteButton.SECONDAIRE);
    btnAnnuler.addActionListener(controleur);
    panelBoutons.add(btnAnnuler);

    bottomPanel.add(panelBoutons, BorderLayout.SOUTH);
    this.add(bottomPanel, BorderLayout.SOUTH);
  }

  // Méthodes pour récupérer les valeurs
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
    erreursListModel.addElement(erreur);
  }

  public void clearErreurs() {
    erreursListModel.clear();
  }

  public boolean hasErreurs() {
    return erreursListModel.size() > 0;
  }
}
