package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import components.Bouton;
import components.Libellé;
import components.Tableau;
import components.Bouton.VarianteButton;
import components.Libellé.TypeLibellé;
import controller.ControleurLocationsEnCoursBien;
import model.*;

import java.awt.*;

public class VueLocationsEnCoursBien extends JPanel {
  private static final long serialVersionUID = 1L;
  private Tableau table;
  private JLabel totalLoyerLabel;

  public VueLocationsEnCoursBien(VueBiens vueBiens, Proprietaire proprietaire, BienLocatif bien) {
    this.setLayout(new BorderLayout());

    double loyerTotal = bien.getLocationsCourantes().stream().mapToDouble(Location::getLoyer).sum();

    totalLoyerLabel = new Libellé("Loyer total : " + loyerTotal + " €", TypeLibellé.TITRE);
    totalLoyerLabel.setBorder(new EmptyBorder(16, 16, 16, 16));
    this.add(totalLoyerLabel, BorderLayout.NORTH);

    table = new Tableau("ID", "Nom", "Date entrée", "Part du loyer");

    ControleurLocationsEnCoursBien controleur = new ControleurLocationsEnCoursBien(vueBiens, proprietaire, bien, this);

    table.addMouseListener(controleur);

    JScrollPane tableScrollPane = new JScrollPane(table);
    tableScrollPane.setBorder(new EmptyBorder(16, 16, 16, 16));
    this.add(tableScrollPane, BorderLayout.CENTER);

    JPanel panelBoutons = new JPanel();
    panelBoutons.setLayout(new FlowLayout(FlowLayout.TRAILING, 8, 8));

    Bouton louerButton = new Bouton("Ajouter un locataire");
    louerButton.addActionListener(controleur);
    panelBoutons.add(louerButton);

    Bouton detailsButton = new Bouton("Détails du locataire", VarianteButton.SECONDAIRE);
    detailsButton.addActionListener(controleur);
    panelBoutons.add(detailsButton);

    Bouton suppButton = new Bouton("Retirer de la colocation", VarianteButton.DANGER);
    suppButton.addActionListener(controleur);
    panelBoutons.add(suppButton);

    Bouton modifierButton = new Bouton("Modifier part du loyer", VarianteButton.SECONDAIRE);
    modifierButton.addActionListener(controleur);
    panelBoutons.add(modifierButton);

    this.add(panelBoutons, BorderLayout.SOUTH);
  }

  public Tableau getTable() {
    return this.table;
  }

  public void setLblLoyerLabel(double totalLoyer) {
    this.totalLoyerLabel.setText("Loyer total : " + totalLoyer + " €");
  }
}