package view;

import java.awt.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import components.*;
import components.Bouton.VarianteButton;
import components.Libellé.TypeLibellé;

import controller.ControleurAjoutTravail;
import controller.ControleurTravaux;
import model.BienImmobilier;

public class VueAjoutTravail extends JFrame {
  private static final long serialVersionUID = 1L;
  private ChampSaisie idField;
  private ChampSaisie descriptionField;
  private ChampSaisie entrepriseField;
  private ChampSaisie montantDevisField;
  private ChampSaisie montantFactureField;
  private ChampSaisie dateField;
  private BienImmobilier bien;
  private JList<String> erreursList;
  private DefaultListModel<String> erreursListModel;

  public VueAjoutTravail(BienImmobilier bien, ControleurTravaux control) {
    this.bien = bien;

    this.setTitle("Ajouter un travail");
    this.setBounds(100, 100, 400, 750);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setResizable(false);

    JPanel contentPane = new JPanel(new BorderLayout(0, 0));
    contentPane.setBorder(new EmptyBorder(16, 16, 16, 16));

    this.setContentPane(contentPane);

    Libellé titre = new Libellé("Ajouter un travail", TypeLibellé.TITRE);
    titre.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(titre, BorderLayout.NORTH);

    JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

    idField = new ChampSaisie("Identifiant");
    formPanel.add(idField);

    descriptionField = new ChampSaisie("Description du travail effectué");
    formPanel.add(descriptionField);

    entrepriseField = new ChampSaisie("Entreprise prestataire");
    formPanel.add(entrepriseField);

    montantDevisField = new ChampSaisie("Montant du devis (en €)",
        new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.1));
    formPanel.add(montantDevisField);

    montantFactureField = new ChampSaisie("Montant de la facture (en €)",
        new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.1));
    formPanel.add(montantFactureField);

    dateField = new ChampSaisie("Date de la facture (jj/MM/aaaa)", new SpinnerDateModel());
    JSpinner dateSpinner = (JSpinner) dateField.getChampSaisie();
    dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
    formPanel.add(dateField);

    contentPane.add(formPanel, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel(new BorderLayout());

    JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.TRAILING, 8, 8));

    erreursListModel = new DefaultListModel<>();
    erreursList = new JList<>(erreursListModel);

    erreursList.setFont(Layout.POLICE_SMALL);
    erreursList.setForeground(Layout.COULEUR_DANGER);
    erreursList.setBackground(Layout.COULEUR_FOND);

    JScrollPane erreurScrollPane = new JScrollPane(erreursList);
    erreurScrollPane
        .setBorder(new TitledBorder(new EtchedBorder(), "Erreurs", TitledBorder.CENTER, TitledBorder.TOP));
    erreurScrollPane.setPreferredSize(new Dimension(200, 100));

    bottomPanel.add(erreurScrollPane, BorderLayout.CENTER);

    ControleurAjoutTravail controleur = new ControleurAjoutTravail(this.bien, this, control);

    Bouton btnEnregistrer = new Bouton("Enregistrer");
    btnEnregistrer.addActionListener(controleur);
    panelBoutons.add(btnEnregistrer);

    Bouton btnAnnuler = new Bouton("Annuler", VarianteButton.SECONDAIRE);
    btnAnnuler.addActionListener(controleur);
    panelBoutons.add(btnAnnuler);

    bottomPanel.add(panelBoutons, BorderLayout.SOUTH);
    contentPane.add(bottomPanel, BorderLayout.SOUTH);
  }

  public String getID() {
    return idField.getValue();
  }

  public String getDescription() {
    return descriptionField.getValue();
  }

  public String getEntreprise() {
    return entrepriseField.getValue();
  }

  public double getMontantDevis() {
    return montantDevisField.getValue();
  }

  public double getMontantFacture() {
    return montantFactureField.getValue();
  }

  public Date getDate() {
    return dateField.getValue();
  }

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
