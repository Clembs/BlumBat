package view;

import javax.swing.*;

import components.Tableau;
import model.BienLocatif;
import model.Location;
import model.Proprietaire;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class VueLocationsPassésBien extends JPanel {
  private static final long serialVersionUID = 1L;

  public VueLocationsPassésBien(VueBiens fenetre, Proprietaire proprietaire, BienLocatif bien) {
    this.setLayout(new BorderLayout());

    Tableau tableau = new Tableau(
        "ID", "Nom", "Date d'entrée", "Date de sortie", "Dernier loyer payé");
    // on rend la table non-éditable
    tableau.setDefaultEditor(Object.class, null);
    tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    for (Location location : bien.getLocationsPassees()) {
      tableau.addRow(
          location.getLocataire().getId(),
          location.getLocataire().getPrenom() + " " + location.getLocataire().getNom(),
          location.getDateEntree()
              .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
          location.getDateSortie()
              .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
          location.getLoyer() + " €");
    }

    JScrollPane tableScrollPane = new JScrollPane(tableau);
    this.add(tableScrollPane, BorderLayout.CENTER);
  }
}
