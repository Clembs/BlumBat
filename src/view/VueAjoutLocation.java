package view;

import java.awt.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import components.Bouton;
import components.ChampSaisie;
import components.Layout;
import components.Libellé;
import components.Tableau;
import components.Bouton.VarianteButton;
import components.Libellé.TypeLibellé;
import controller.ControleurAjoutLocation;
import model.BienLocatif;
import model.Locataire;
import model.Proprietaire;

public class VueAjoutLocation extends JFrame {
  private static final long serialVersionUID = 1L;
  private ChampSaisie loyerField;
  private ChampSaisie dateEntreeField;
  private JCheckBox dateSortieCheckbox;
  private ChampSaisie dateSortieField;
  private List<Locataire> locataires;
  private Tableau tableLocatairesSelectionnes;
  private JList<String> erreursList;
  private DefaultListModel<String> erreursListModel;

  public VueAjoutLocation(VueBiens fenBiens, BienLocatif bien, Proprietaire proprietaire) {
    ControleurAjoutLocation controleur = new ControleurAjoutLocation(this, bien, proprietaire, fenBiens);

    this.setBounds(100, 100, 450, 750);
    this.setTitle("Louer");
    this.setResizable(false);

    JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(16, 16, 16, 16));
    contentPane.setLayout(new BorderLayout(15, 15));
    this.setContentPane(contentPane);

    Libellé lblTitre = new Libellé("Louer", TypeLibellé.TITRE);
    lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(lblTitre, BorderLayout.NORTH);

    JPanel panelCenter = new JPanel(new GridLayout(5, 1, 8, 8));
    contentPane.add(panelCenter, BorderLayout.CENTER);

    loyerField = new ChampSaisie("Loyer (en €)", new SpinnerNumberModel(0d, null, null, 1d));

    panelCenter.add(loyerField);

    dateEntreeField = new ChampSaisie("Date d'entrée (jj/MM/aaaa)", new SpinnerDateModel());
    JSpinner entreeSpinner = (JSpinner) dateEntreeField.getChampSaisie();
    entreeSpinner.setEditor(new JSpinner.DateEditor(entreeSpinner, "dd/MM/yyyy"));
    panelCenter.add(dateEntreeField);

    // Case à cocher pour activer/désactiver le champ de saisie de la date de sortie
    dateSortieCheckbox = new JCheckBox("La location s'est déjà finie (ajout d'une location passée)");
    dateSortieCheckbox.setFont(Layout.POLICE_REGULAR);
    panelCenter.add(dateSortieCheckbox);

    SpinnerDateModel sortieModel = new SpinnerDateModel();
    dateSortieField = new ChampSaisie("Date de sortie (jj/MM/aaaa) (facultatif)", sortieModel);
    JSpinner sortieSpinner = (JSpinner) dateSortieField.getChampSaisie();
    sortieSpinner.setEditor(new JSpinner.DateEditor(sortieSpinner, "dd/MM/yyyy"));
    // le champ est désactivé par défaut
    dateSortieField.getChampSaisie().setEnabled(false);
    panelCenter.add(dateSortieField);

    // Activer le champ de saisie lorsque la case est cochée
    dateSortieCheckbox.addActionListener(e -> {
      dateSortieField.getChampSaisie().setEnabled(dateSortieCheckbox.isSelected());
    });

    JPanel selectionLocatairesPanel = new JPanel(new BorderLayout(0, 8));
    panelCenter.add(selectionLocatairesPanel);

    Libellé lblLocataires = new Libellé("Locataires", TypeLibellé.CLEF);
    selectionLocatairesPanel.add(lblLocataires, BorderLayout.NORTH);

    Bouton btnSelectionner = new Bouton("Sélectionner", VarianteButton.SECONDAIRE);
    btnSelectionner.addActionListener(controleur);
    selectionLocatairesPanel.add(btnSelectionner, BorderLayout.SOUTH);

    // Panneau des locataires, des erreurs et des boutons
    JPanel bottomPanel = new JPanel(new BorderLayout());

    tableLocatairesSelectionnes = new Tableau("ID", "Nom");

    JScrollPane locatairesScrollPane = new JScrollPane(tableLocatairesSelectionnes);
    locatairesScrollPane.setPreferredSize(new Dimension(400, 100));
    locatairesScrollPane
        .setBorder(
            new TitledBorder(new EtchedBorder(), "Locataires sélectionnés", TitledBorder.CENTER, TitledBorder.TOP));
    bottomPanel.add(locatairesScrollPane, BorderLayout.NORTH);

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
    bottomPanel.add(erreurScrollPane, BorderLayout.CENTER);

    JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.TRAILING, 8, 8));

    Bouton btnLouer = new Bouton("Louer");
    btnLouer.addActionListener(controleur);
    panelBoutons.add(btnLouer);

    Bouton btnAnnuler = new Bouton("Annuler", VarianteButton.SECONDAIRE);
    btnAnnuler.addActionListener(e -> this.dispose());
    panelBoutons.add(btnAnnuler);

    bottomPanel.add(panelBoutons, BorderLayout.SOUTH);
    contentPane.add(bottomPanel, BorderLayout.SOUTH);
  }

  public List<Locataire> getLocataires() {
    return this.locataires;
  }

  public void setLocataires(List<Locataire> locataires) {
    this.locataires = locataires;

    // Vider la table
    tableLocatairesSelectionnes.clear();

    // Pour chaque locataire, ajouter une ligne dans la table
    for (Locataire locataire : locataires) {
      tableLocatairesSelectionnes.addRow(
          locataire.getId(),
          locataire.getPrenom() + " " + locataire.getNom());
    }

    // Rafraîchir la table
    tableLocatairesSelectionnes.revalidate();
    tableLocatairesSelectionnes.repaint();
  }

  public double getLoyer() {
    return (double) this.loyerField.getValue();
  }

  public Date getDateEntree() {
    return this.dateEntreeField.getValue();
  }

  public Date getDateSortie() {
    return dateSortieCheckbox.isSelected() ? this.dateSortieField.getValue() : null;
  }

  public void addErreur(String erreur) {
    erreursListModel.addElement(erreur);
    erreursList.setModel(erreursListModel);
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
