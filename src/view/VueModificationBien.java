package view;

import controller.ControleurModificationBien;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;

import javax.swing.*;
import java.awt.*;

public class VueModificationBien extends JPanel {
  private JTextField adresseField;
  private JTextField comlementAdresseField;
  private JTextField villeField;
  private JTextField codePostalField;
  private JSpinner surfaceSpinner;
  private JTextField nbFiscalField;
  private JSpinner nbPiecesSpinner;
  private DefaultListModel<String> erreursListModel;
  private JList<String> erreursList;

  public VueModificationBien(VueBiens fenetre, Proprietaire proprietaire, BienImmobilier bien) {
    setLayout(new BorderLayout(10, 10));
    setBackground(new Color(40, 40, 40));

    erreursListModel = new DefaultListModel<>();
    erreursList = new JList<>(erreursListModel);

    JLabel lblTitle = new JLabel("Modifier un bien", SwingConstants.CENTER);
    lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
    lblTitle.setForeground(new Color(240, 240, 240));
    lblTitle.setOpaque(true);
    lblTitle.setBackground(new Color(60, 60, 60));
    lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    add(lblTitle, BorderLayout.NORTH);

    JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
    formPanel.setBackground(new Color(50, 50, 50));
    formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    JTextField textTypeBien = new JTextField(bien.getTypeBien().toString());
    textTypeBien.setEditable(false);
    addField(formPanel, "Type :", textTypeBien);

    JTextField textIdBien = new JTextField(bien.getId());
    textIdBien.setEditable(false);
    addField(formPanel, "Identifiant :", textIdBien);

    adresseField = new JTextField(bien.getAdresse());
    addField(formPanel, "Adresse :", adresseField);

    comlementAdresseField = new JTextField(bien.getComplementAdresse());
    addField(formPanel, "Complément d'adresse (facultatif) :", comlementAdresseField);

    villeField = new JTextField(bien.getVille());
    addField(formPanel, "Ville :", villeField);

    codePostalField = new JTextField(bien.getCodePostal());
    addField(formPanel, "Code postal :", codePostalField);

    if (bien instanceof BienLocatif) {
      BienLocatif bienL = (BienLocatif) bien;

      surfaceSpinner = new JSpinner();
      surfaceSpinner.setModel(new SpinnerNumberModel(bienL.getSurface(), null, null, Float.valueOf(1)));
      addSpinnerField(formPanel, "Surface (en m²) :", surfaceSpinner);

      nbFiscalField = new JTextField(bienL.getNumeroFiscal());
      addField(formPanel, "Numéro fiscal:*", nbFiscalField);

      nbPiecesSpinner = new JSpinner();
      nbPiecesSpinner.setModel(new SpinnerNumberModel(bienL.getNombrePieces(), null, null, Integer.valueOf(1)));
      addSpinnerField(formPanel, "Nombre de pièces:*", nbPiecesSpinner);
    }

    add(formPanel, BorderLayout.CENTER);

    // Panneau contenant les erreurs et les boutons
    JPanel southPanel = new JPanel(new BorderLayout());
    southPanel.setBackground(new Color(40, 40, 40));

    // Liste des erreurs
    erreursList.setFont(new Font("SansSerif", Font.PLAIN, 12));
    erreursList.setBackground(new Color(60, 60, 60));
    erreursList.setForeground(Color.RED);

    JScrollPane erreurScrollPane = new JScrollPane(erreursList);
    erreurScrollPane.setPreferredSize(new Dimension(200, 100));
    southPanel.add(erreurScrollPane, BorderLayout.NORTH);

    // Boutons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    buttonPanel.setBackground(new Color(40, 40, 40));

    JButton btnModifier = new JButton("Enregistrer");
    btnModifier.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnModifier.setBackground(new Color(0, 170, 85));
    btnModifier.setForeground(Color.WHITE);
    btnModifier.setFocusPainted(false);
    btnModifier.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    buttonPanel.add(btnModifier);

    ControleurModificationBien controleur = new ControleurModificationBien(fenetre, this, proprietaire, bien);
    btnModifier.addActionListener(controleur);

    JButton btnAnnuler = new JButton("Annuler");
    btnAnnuler.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnAnnuler.setBackground(new Color(200, 50, 50));
    btnAnnuler.setForeground(Color.WHITE);
    btnAnnuler.setFocusPainted(false);
    btnAnnuler.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    btnAnnuler.addActionListener(controleur);
    buttonPanel.add(btnAnnuler);

    southPanel.add(buttonPanel, BorderLayout.SOUTH);
    add(southPanel, BorderLayout.SOUTH);
  }

  private void addField(JPanel panel, String label, JComponent field) {
    JLabel lbl = new JLabel(label);
    lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
    lbl.setForeground(new Color(230, 230, 230));
    panel.add(lbl);

    field.setFont(new Font("SansSerif", Font.PLAIN, 14));
    field.setBackground(new Color(60, 60, 60));
    field.setForeground(Color.WHITE);
    field.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    panel.add(field);
  }

  private void addSpinnerField(JPanel panel, String label, JSpinner spinner) {
    JLabel lbl = new JLabel(label);
    lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
    lbl.setForeground(new Color(230, 230, 230));
    panel.add(lbl);

    JComponent editor = spinner.getEditor();
    if (editor instanceof JSpinner.DefaultEditor) {
      JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
      spinnerEditor.getTextField().setFont(new Font("SansSerif", Font.PLAIN, 14));
      spinnerEditor.getTextField().setBackground(new Color(60, 60, 60));
      spinnerEditor.getTextField().setForeground(Color.WHITE);
      spinnerEditor.getTextField().setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    }

    spinner.setFont(new Font("SansSerif", Font.PLAIN, 14));
    spinner.setBackground(new Color(60, 60, 60));
    spinner.setForeground(Color.WHITE);
    spinner.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    panel.add(spinner);
  }

  public String getAdresse() {
    return adresseField.getText();
  }

  public String getComplementAdresse() {
    return comlementAdresseField.getText();
  }

  public String getVille() {
    return villeField.getText();
  }

  public String getCodePostal() {
    return codePostalField.getText();
  }

  public float getSurface() {
    return (float) surfaceSpinner.getValue();
  }

  public String getNbFiscal() {
    return nbFiscalField.getText();
  }

  public int getNbPieces() {
    return (int) nbPiecesSpinner.getValue();
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