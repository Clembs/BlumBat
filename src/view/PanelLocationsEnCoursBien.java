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

public class PanelLocationsEnCoursBien extends JPanel {
  private static final long serialVersionUID = 1L;

  public PanelLocationsEnCoursBien(FenBiens fenetre, Proprietaire proprietaire, BienLocatif bien) {
    this.setLayout(new BorderLayout());

    double loyerTotal = bien.getLocationsCourantes().stream().mapToDouble(location -> location.getLoyer()).sum();

    JLabel totalLoyerLabel = new JLabel("Loyer total : " + loyerTotal + " €", JLabel.CENTER);
    totalLoyerLabel.setFont(new Font("Arial", Font.BOLD, 16));
    this.add(totalLoyerLabel, BorderLayout.NORTH);

    DefaultTableModel tableModel = new DefaultTableModel(
        new Object[] { "ID", "Nom", "Date entrée", "Part du loyer" }, 0);
    JTable table = new JTable(tableModel);
    // on rend la table non-éditable
    table.setDefaultEditor(Object.class, null);

    for (Location location : bien.getLocationsCourantes()) {
      tableModel.addRow(new Object[] {
          location.getLocataire().getId(),
          location.getLocataire().getPrenom() + " " + location.getLocataire().getNom(),
          location.getDateEntree()
              .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
          location.getLoyer() + " €"
      });
    }

    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane tableScrollPane = new JScrollPane(table);
    this.add(tableScrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

    JButton louerButton = new JButton("Ajouter un locataire");
    buttonPanel.add(louerButton);

    JButton detailsButton = new JButton("Détails du locataire");
    buttonPanel.add(detailsButton);

    JButton suppButton = new JButton("Retirer de la colocation");
    suppButton.setBackground(Color.PINK);
    buttonPanel.add(suppButton);

    JButton modifierButton = new JButton("Modifier part du loyer");
    buttonPanel.add(modifierButton);

    this.add(buttonPanel, BorderLayout.SOUTH);
  }
}
