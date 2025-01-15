package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.*;

import components.Bouton;
import components.ChampSaisie;
import components.Layout;
import components.Libellé;
import components.Bouton.VarianteButton;
import components.Libellé.TypeLibellé;
import controller.ControleurAjoutBien;
import model.Proprietaire;
import model.TypeBien;

public class VueAjoutBien extends JFrame {
  private static final long serialVersionUID = 1L;
  private ChampSaisie idField;
  private JComboBox<String> typeComboBox;
  private ChampSaisie adresseField;
  private ChampSaisie villeField;
  private ChampSaisie codePostalField;
  private ChampSaisie surfaceField;
  private ChampSaisie nFiscalField;
  private ChampSaisie complementAdresseField;
  private ChampSaisie nbPiecesField;
  private JList<String> erreursList;
  private DefaultListModel<String> erreursListModel;

  public VueAjoutBien(VueBiens fenBiens, Proprietaire P) {
    this.setTitle("Ajouter un bien");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setBounds(100, 100, 600, 700);
    this.setResizable(false);

    JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(16, 16, 16, 16));
    contentPane.setBackground(Layout.COULEUR_FOND);
    contentPane.setLayout(new BorderLayout(16, 16));
    this.setContentPane(contentPane);

    Libellé lblTitle = new Libellé("Ajouter un bien", TypeLibellé.TITRE);
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(lblTitle, BorderLayout.NORTH);

    JPanel champsPanel = new JPanel();
    champsPanel.setLayout(new GridLayout(5, 2, 8, 12));
    contentPane.add(champsPanel, BorderLayout.CENTER);

    JPanel typePanel = new JPanel(new BorderLayout(0, 8));
    champsPanel.add(typePanel);

    Libellé lblType = new Libellé("Type de bien", TypeLibellé.CLEF);
    typePanel.add(lblType, BorderLayout.NORTH);

    typeComboBox = new JComboBox<>(
        Arrays.stream(TypeBien.values()).map(TypeBien::toString).toArray(String[]::new));

    typeComboBox.setFont(Layout.POLICE_REGULAR);
    typePanel.add(typeComboBox, BorderLayout.CENTER);

    idField = new ChampSaisie("Identifiant (unique)");
    champsPanel.add(idField);

    adresseField = new ChampSaisie("Adresse");
    champsPanel.add(adresseField);

    complementAdresseField = new ChampSaisie("Complément d'adresse (facultatif)");
    champsPanel.add(complementAdresseField);

    villeField = new ChampSaisie("Ville");
    champsPanel.add(villeField);

    codePostalField = new ChampSaisie("Code postal");
    champsPanel.add(codePostalField);

    surfaceField = new ChampSaisie("Surface (en m²)", new SpinnerNumberModel(0f, null, null, 1f));

    nFiscalField = new ChampSaisie("Numéro fiscal");

    nbPiecesField = new ChampSaisie("Nombre de pièces", new SpinnerNumberModel(1, null, null, 1));

    // affichage conditionnel des champs liés aux locations selon le type
    // sélectionné
    typeComboBox.addActionListener((ActionEvent e) -> {
      TypeBien selectedType = TypeBien.getTypeBien(this.getTypeBien());

      switch (selectedType) {
        case BATIMENT:
          // suppression des champs & leurs libellés
          champsPanel.remove(surfaceField);
          champsPanel.remove(nFiscalField);
          champsPanel.remove(nbPiecesField);
          // obligatoire pour rafraichir l'interface
          champsPanel.revalidate();
          champsPanel.repaint();
          break;
        case LOGEMENT:
        case GARAGE:
          // pareil mais on ajoute les champs
          champsPanel.add(surfaceField);
          champsPanel.add(nFiscalField);
          champsPanel.add(nbPiecesField);

          champsPanel.revalidate();
          champsPanel.repaint();
          break;
      }
    });

    JPanel bottomPanel = new JPanel(new BorderLayout());

    erreursListModel = new DefaultListModel<>();
    erreursList = new JList<>(erreursListModel);
    erreursList.setFont(Layout.POLICE_SMALL);
    erreursList.setForeground(Layout.COULEUR_DANGER);
    erreursList.setBackground(Layout.COULEUR_FOND);
    erreursList.setVisible(false);

    JScrollPane erreurScrollPane = new JScrollPane(erreursList);
    erreurScrollPane.setPreferredSize(new Dimension(200, 100));
    erreurScrollPane.setBorder(null);
    bottomPanel.add(erreurScrollPane, BorderLayout.NORTH);

    ControleurAjoutBien controleur = new ControleurAjoutBien(fenBiens, P, this);

    JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.TRAILING, 8, 8));

    Bouton btnSave = new Bouton("Ajouter");
    btnSave.addActionListener(controleur);
    panelBoutons.add(btnSave);

    Bouton btnCancel = new Bouton("Annuler", VarianteButton.SECONDAIRE);
    btnCancel.addActionListener(e -> this.dispose());
    panelBoutons.add(btnCancel);

    bottomPanel.add(panelBoutons, BorderLayout.SOUTH);
    contentPane.add(bottomPanel, BorderLayout.SOUTH);
  }

  public String getId() {
    return idField.getValue();
  }

  // Getters pour chaque champ
  public String getAdresse() {
    return adresseField.getValue();
  }

  public String getComplementAdresse() {
    return complementAdresseField.getValue();
  }

  public String getCodePostal() {
    return codePostalField.getValue();
  }

  public String getVille() {
    return villeField.getValue();
  }

  public float getSurface() {
    return surfaceField.getValue();
  }

  public String getNFiscal() {
    return nFiscalField.getValue();
  }

  public String getTypeBien() {
    return (String) typeComboBox.getSelectedItem();
  }

  public int getNbPieces() {
    return nbPiecesField.getValue();
  }

  public void addErreur(String erreur) {
    erreursList.setVisible(true);
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
