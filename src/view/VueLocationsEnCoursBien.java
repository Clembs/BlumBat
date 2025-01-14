package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ControleurLocationsEnCoursBien;
import model.*;

import java.awt.*;

public class VueLocationsEnCoursBien extends JPanel {
  private static final long serialVersionUID = 1L;
  private JButton louerButton;
  private JButton detailsButton;
  private JButton suppButton;
  private JButton modifierButton;
  private JTable table;
  private DefaultTableModel tableModel;
  private JLabel totalLoyerLabel;
  private VueBiens vueBiens;

  public VueLocationsEnCoursBien(VueBiens fenetre, Proprietaire proprietaire, BienLocatif bien) {
    this.setLayout(new BorderLayout());
    this.vueBiens = fenetre;

    double loyerTotal = bien.getLocationsCourantes().stream().mapToDouble(Location::getLoyer).sum();

    totalLoyerLabel = new JLabel("Loyer total : " + loyerTotal + " €", JLabel.CENTER);
    totalLoyerLabel.setFont(new Font("Arial", Font.BOLD, 16));
    this.add(totalLoyerLabel, BorderLayout.NORTH);

    tableModel = new DefaultTableModel(
            new Object[]{"ID", "Nom", "Date entrée", "Part du loyer"}, 0);
    table = new JTable(tableModel);
    // on rend la table non-éditable
    table.setDefaultEditor(Object.class, null);

    rafraîchirTableLocationsEnCours(bien);

    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane tableScrollPane = new JScrollPane(table);
    this.add(tableScrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
    ControleurLocationsEnCoursBien controleur = new ControleurLocationsEnCoursBien(fenetre, proprietaire, bien, this);
    louerButton = new JButton("Ajouter un locataire");
    louerButton.addActionListener(controleur);
    buttonPanel.add(louerButton);

    detailsButton = new JButton("Détails du locataire");
    detailsButton.addActionListener(controleur);
    buttonPanel.add(detailsButton);

    suppButton = new JButton("Retirer de la colocation");
    suppButton.setBackground(Color.PINK);
    suppButton.addActionListener(controleur);
    buttonPanel.add(suppButton);

    modifierButton = new JButton("Modifier part du loyer");
    modifierButton.addActionListener(controleur);
    buttonPanel.add(modifierButton);

    this.add(buttonPanel, BorderLayout.SOUTH);
  }

  public void rafraîchirTableLocationsEnCours(BienLocatif bien) {
    tableModel.setRowCount(0);

    for (Location location : bien.getLocationsCourantes()) {
      tableModel.addRow(new Object[]{
              location.getLocataire().getId(),
              location.getLocataire().getPrenom() + " " + location.getLocataire().getNom(),
              location.getDateEntree(),
              location.getLoyer() + " €"
      });
    }
    totalLoyerLabel.setText("Loyer total : " + bien.getLocationsCourantes().stream().mapToDouble(Location::getLoyer).sum() + " €");
    table.revalidate();
    table.repaint();
  }

  public JTable getTable() {
    return table;
  }
}