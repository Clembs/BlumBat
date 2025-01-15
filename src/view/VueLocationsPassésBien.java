package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

    DefaultTableModel tableModel = new DefaultTableModel(
        new Object[] {
            "ID", "Nom", "Date d'entrée", "Date de sortie", "Dernier loyer payé"
        }, 0);

    JTable table = new JTable(tableModel);
    // on rend la table non-éditable
    table.setDefaultEditor(Object.class, null);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    for (Location location : bien.getLocationsPassees()) {
      tableModel.addRow(new Object[] {
          location.getLocataire().getId(),
          location.getLocataire().getPrenom() + " " + location.getLocataire().getNom(),
          location.getDateEntree()
              .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
          location.getDateSortie()
              .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
          location.getLoyer() + " €"
      });
    }

    JScrollPane tableScrollPane = new JScrollPane(table);
    this.add(tableScrollPane, BorderLayout.CENTER);
  }
}
