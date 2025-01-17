package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import components.Bouton;
import components.ChampSaisie;
import components.Libellé;
import components.Tableau;
import components.Bouton.VarianteButton;
import components.Libellé.TypeLibellé;
import controller.ControleurModifierPartLoyer;
import model.BienLocatif;
import model.Location;
import model.Proprietaire;

public class VueModifierPartLoyer extends JFrame {

  private static final long serialVersionUID = 1L;
  private Tableau tableAutresLocataires;
  private ChampSaisie loyerField;
  private Libellé lblTotalAmount;
  private JCheckBox modifAutresCheckBox;

  public VueModifierPartLoyer(VueBiens vueBiens, Proprietaire proprietaire, BienLocatif bien, Location location) {
    this.setTitle("Modifier part du loyer pour " +
        location.getLocataire().getNomComplet());
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setBounds(100, 100, 800, 400);

    JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(16, 16, 16, 16));
    contentPane.setLayout(new BorderLayout(16, 16));
    setContentPane(contentPane);

    // Titre
    JLabel lblTitle = new Libellé("Modifier part du loyer pour" +
        location.getLocataire().getNomComplet(), TypeLibellé.TITRE);
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(lblTitle, BorderLayout.NORTH);

    // Panneau de gauche
    JPanel leftPanel = new JPanel(new BorderLayout(20, 20));
    contentPane.add(leftPanel, BorderLayout.WEST);

    // Formulaire
    this.loyerField = new ChampSaisie(
        "Loyer de " + location.getLocataire().getNomComplet() + " (en €)",
        new SpinnerNumberModel(location.getLoyer(), 0d, Double.MAX_VALUE, 1d));

    leftPanel.add(loyerField, BorderLayout.NORTH);

    this.modifAutresCheckBox = new JCheckBox("Modifier la répartition du loyer total pour les autres locataires");
    leftPanel.add(modifAutresCheckBox, BorderLayout.SOUTH);

    // Panneau central (à droite)
    JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
    contentPane.add(centerPanel, BorderLayout.CENTER);

    // Panel de tous les locataires
    this.tableAutresLocataires = new Tableau("ID", "Nom", "Loyer");

    JScrollPane tableAutresLocatairesScrollPane = new JScrollPane(tableAutresLocataires);
    tableAutresLocatairesScrollPane.setPreferredSize(new Dimension(600, 100));
    tableAutresLocatairesScrollPane.setBorder(
        new TitledBorder(new EtchedBorder(), "Aperçu de la nouvelle répartition", TitledBorder.CENTER,
            TitledBorder.TOP));

    centerPanel.add(tableAutresLocatairesScrollPane, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel(new BorderLayout());

    // Total du loyer
    JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    bottomPanel.add(totalPanel, BorderLayout.NORTH);

    JLabel lblTotalLoyer = new Libellé("Loyer total :");
    totalPanel.add(lblTotalLoyer);

    this.lblTotalAmount = new Libellé("0 €");
    totalPanel.add(lblTotalAmount);

    // Contrôleur
    ControleurModifierPartLoyer controleur = new ControleurModifierPartLoyer(this, vueBiens, proprietaire, bien,
        location);

    // Quand le spinner change de valeur
    ((JSpinner) loyerField.getChampSaisie()).addChangeListener(controleur);
    this.modifAutresCheckBox.addActionListener(controleur);

    // Boutons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 8, 8));

    Bouton btnAnnuler = new Bouton("Annuler", VarianteButton.SECONDAIRE);
    btnAnnuler.addActionListener(controleur);
    buttonPanel.add(btnAnnuler);

    Bouton btnEnregistrer = new Bouton("Enregistrer");
    btnEnregistrer.addActionListener(controleur);
    buttonPanel.add(btnEnregistrer);

    bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
    contentPane.add(bottomPanel, BorderLayout.SOUTH);
  }

  public double getLoyer() {
    return this.loyerField.getValue();
  }

  public Tableau getTableAutresLocataires() {
    return this.tableAutresLocataires;
  }

  public boolean loyerAutresLocatairesRecalculé() {
    return this.modifAutresCheckBox.isSelected();
  }

  public void setLblTotalAmount(String totalLoyer) {
    this.lblTotalAmount.setText(totalLoyer);
  }
}
