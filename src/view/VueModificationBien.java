package view;

import controller.ControleurModificationBien;
import model.BienImmobilier;
import model.BienLocatif;
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

public class VueModificationBien extends JPanel {
  private ChampSaisie adresseField;
  private ChampSaisie complémentAdresseField;
  private ChampSaisie villeField;
  private ChampSaisie codePostalField;
  private ChampSaisie surfaceField;
  private ChampSaisie nbFiscalField;
  private ChampSaisie nbPiecesField;
  private DefaultListModel<String> erreursListModel;
  private JList<String> erreursList;

  public VueModificationBien(VueBiens fenetre, Proprietaire proprietaire, BienImmobilier bien) {
    this.setLayout(new BorderLayout(0, 16));
    this.setBorder(new EmptyBorder(16, 16, 16, 16));

    erreursListModel = new DefaultListModel<>();
    erreursList = new JList<>(erreursListModel);

    Libellé lblTitle = new Libellé("Modifier " + bien.getId(), TypeLibellé.EN_TETE);
    this.add(lblTitle, BorderLayout.NORTH);

    JPanel formPanel = new JPanel(new GridLayout(5, 2, 8, 8));

    ChampSaisie idField = new ChampSaisie("Identifiant", bien.getId());
    idField.getChampSaisie().setEnabled(false);
    formPanel.add(idField);

    ChampSaisie typeField = new ChampSaisie("Type de bien", bien.getTypeBien().toString());
    typeField.getChampSaisie().setEnabled(false);
    formPanel.add(typeField);

    adresseField = new ChampSaisie("Adresse", bien.getAdresse());
    formPanel.add(adresseField);

    complémentAdresseField = new ChampSaisie("Complément d'adresse (facultatif)", bien.getComplementAdresse());
    formPanel.add(complémentAdresseField);

    villeField = new ChampSaisie("Ville", bien.getVille());
    formPanel.add(villeField);

    codePostalField = new ChampSaisie("Code postal", bien.getCodePostal());
    formPanel.add(codePostalField);

    if (bien instanceof BienLocatif) {
      BienLocatif bienL = (BienLocatif) bien;

      surfaceField = new ChampSaisie("Surface (en m²)",
          new SpinnerNumberModel(bienL.getSurface(), 1f, null, 1f));
      formPanel.add(surfaceField);

      nbFiscalField = new ChampSaisie("Numéro fiscal", bienL.getNumeroFiscal());
      formPanel.add(nbFiscalField);

      nbPiecesField = new ChampSaisie("Nombre de pièces",
          new SpinnerNumberModel(bienL.getNombrePieces(), 1, null, 1));
      formPanel.add(nbPiecesField);
    }

    add(formPanel, BorderLayout.CENTER);

    // Panneau contenant les erreurs et les boutons
    JPanel southPanel = new JPanel(new BorderLayout());

    // Liste des erreurs
    erreursList.setFont(Layout.POLICE_SMALL);
    erreursList.setForeground(Layout.COULEUR_DANGER);
    erreursList.setBackground(Layout.COULEUR_FOND);

    JScrollPane erreurScrollPane = new JScrollPane(erreursList);
    erreurScrollPane.setBorder(
        new TitledBorder(new EtchedBorder(), "Erreurs", TitledBorder.CENTER, TitledBorder.TOP));
    erreurScrollPane.setPreferredSize(new Dimension(200, 100));
    southPanel.add(erreurScrollPane, BorderLayout.NORTH);

    // Boutons
    JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.TRAILING, 8, 8));

    ControleurModificationBien controleur = new ControleurModificationBien(fenetre, this, proprietaire, bien);

    JButton btnModifier = new Bouton("Enregistrer");
    btnModifier.addActionListener(controleur);
    panelBoutons.add(btnModifier);

    JButton btnAnnuler = new Bouton("Annuler", VarianteButton.SECONDAIRE);
    btnAnnuler.addActionListener(controleur);
    panelBoutons.add(btnAnnuler);

    southPanel.add(panelBoutons, BorderLayout.SOUTH);
    add(southPanel, BorderLayout.SOUTH);
  }

  public String getAdresse() {
    return adresseField.getValue();
  }

  public String getComplementAdresse() {
    return complémentAdresseField.getValue();
  }

  public String getVille() {
    return villeField.getValue();
  }

  public String getCodePostal() {
    return codePostalField.getValue();
  }

  public float getSurface() {
    return surfaceField.getValue();
  }

  public String getNbFiscal() {
    return nbFiscalField.getValue();
  }

  public int getNbPieces() {
    return nbPiecesField.getValue();
  }

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