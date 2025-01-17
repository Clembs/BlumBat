package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import components.Tableau;
import dao.TravailDAO;
import model.BienImmobilier;
import model.FactureTravaux;
import view.VueAjoutTravail;
import view.VueTravaux;

public class ControleurTravaux implements ActionListener {

  private VueTravaux view;
  private TravailDAO travauxDAO;
  private BienImmobilier bien;
  List<FactureTravaux> travaux;

  public ControleurTravaux(VueTravaux view, BienImmobilier bien) {
    this.view = view;
    this.bien = bien;
    this.travauxDAO = new TravailDAO();

    this.loadData();
  }

  public void loadData() {
    travaux = travauxDAO.getAllTravaux(bien);
    Tableau table = view.getTable();
    table.clear(); // Réinitialiser le tableau

    double totalPrix = 0;

    for (FactureTravaux travail : travaux) {
      table.addRow(
          travail.getId(),
          travail.getDescription(),
          travail.getEntreprise(),
          travail.getDate()
              .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
          String.format("%.2f€", travail.getMontantDevis()),
          String.format("%.2f€", travail.getMontantFacture()));

      totalPrix += travail.getMontantFacture();
    }

    // Mettre à jour le prix total dans la vue
    view.setPrixTotal(String.format("%.2f€", totalPrix));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton boutonClique = (JButton) e.getSource();
    String boutonTexte = boutonClique.getText();

    switch (boutonTexte) {
      case "Ajouter":
        // Controleur passé pour l'utilisation du methode de loadData() dans le
        // controleur de la fenetre ajoutTravaux pour mis a jour le tableau des travaux
        VueAjoutTravail nouvelleFenetre = new VueAjoutTravail(this.bien, this);
        nouvelleFenetre.setVisible(true);
        break;
      case "Supprimer":
        int entrée = JOptionPane.showConfirmDialog(boutonClique,
            "Voulez-vous vraiment supprimer ce travail ?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (entrée == JOptionPane.YES_OPTION) {
          int selectedRow = view.getTable().getSelectedRow();
          if (selectedRow >= 0) {
            DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
            String id = (String) tableModel.getValueAt(selectedRow, 0);

            FactureTravaux travailÀSupprimer = travaux.stream()
                .filter(travail -> travail.getId().equals(id))
                .findFirst()
                .orElse(null);

            if (travailÀSupprimer != null) {
              travauxDAO.delete(travailÀSupprimer);
              loadData(); // rafraîchir les données après supression
              JOptionPane.showMessageDialog(view, "Travail supprimé avec succès !");
            }
          } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un travail à supprimer.");
          }
        }
        break;
    }
  }
}
