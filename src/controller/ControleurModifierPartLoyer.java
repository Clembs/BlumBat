package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import components.Tableau;
import dao.LocationDAO;
import model.BienLocatif;
import model.Location;
import model.Proprietaire;
import view.VueBiens;
import view.VueConsultationBien;
import view.VueModifierPartLoyer;

public class ControleurModifierPartLoyer implements ActionListener, ChangeListener {
  private VueModifierPartLoyer vue;
  private VueBiens vueBiens;
  private Proprietaire proprietaire;
  private BienLocatif bien;
  private Location location;
  private LocationDAO locationDAO;

  public ControleurModifierPartLoyer(VueModifierPartLoyer vue, VueBiens vueBiens, Proprietaire proprietaire,
      BienLocatif bien, Location location) {
    this.vue = vue;
    this.vueBiens = vueBiens;
    this.proprietaire = proprietaire;
    this.bien = bien;
    this.location = location;
    this.locationDAO = new LocationDAO();

    this.rafraîchirDonnées();
  }

  // Quand le spinner change de valeur
  @Override
  public void stateChanged(ChangeEvent e) {
    if (this.vue.loyerAutresLocatairesRecalculé()) {
      this.recalculTousLocataires();
    } else {
      this.rafraîchirDonnées();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // La source peut être une case à cocher ou un bouton
    Object source = e.getSource();

    if (source instanceof JButton) {
      String boutonTexte = ((JButton) source).getText();

      if (boutonTexte.equals("Enregistrer")) {

        Tableau table = this.vue.getTableAutresLocataires();
        // on récupère la liste des locations courantes pour pouvoir la muter
        List<Location> locations = bien.getLocationsCourantes();

        // on récupère les colonnes de la table
        final int idTableColonneIndex = 0;
        final int loyerTableColonneIndex = 2;

        // pour chaque colonne de la table, on met à jour le loyer de la location
        for (int i = 0; i < table.getRowCount(); i++) {
          double loyer = 0d;

          try {
            String celluleLoyer = (String) table.getValueAt(i, loyerTableColonneIndex);

            loyer = Double.parseDouble(celluleLoyer.replace("€", ""));

            if (loyer <= 0) {
              JOptionPane.showMessageDialog(this.vue, "Le loyer d'un des locataires est égal ou en dessous de 0.",
                  "Erreur",
                  JOptionPane.ERROR_MESSAGE);
              return;
            }
          } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this.vue, "Le loyer d'un des locataires est invalide.", "Erreur",
                JOptionPane.ERROR_MESSAGE);
            return;
          }

          String celluleIdLocataire = (String) table.getValueAt(i, idTableColonneIndex);

          // on récupère la location dans la liste des locations à partir de l'id du
          // locataire
          Location ancienneLocation = locations.stream()
              .filter(l -> l.getLocataire().getId().equals(celluleIdLocataire))
              .findFirst()
              .orElse(null);

          Location nouvelleLocation = new Location(loyer,
              ancienneLocation.getDateEntree(),
              ancienneLocation.getDateSortie(),
              ancienneLocation.getBien(),
              ancienneLocation.getLocataire());

          // mise à jour dans la base de données
          locationDAO.update(nouvelleLocation);

          // on mute la liste des locations avec la nouvelle location
          locations.set(locations.indexOf(ancienneLocation), nouvelleLocation);
        }

        // mise à jour de la liste des locations du bien & de la vueBiens
        bien.setLocations(locations);
        vueBiens.updateBien(bien);

        VueConsultationBien vueConsultationBien = new VueConsultationBien(vueBiens, proprietaire, bien,
            VueConsultationBien.Onglets.LOCATIONS_EN_COURS);
        vueBiens.setPanelCentral(vueConsultationBien);

        JOptionPane.showMessageDialog(this.vue, "Loyer mis à jour avec succès.", "Succès",
            JOptionPane.INFORMATION_MESSAGE);
        this.vue.dispose();
      } else if (boutonTexte.equals("Annuler")) {
        this.vue.dispose();
      }
    } else if (source instanceof JCheckBox) {
      if (this.vue.loyerAutresLocatairesRecalculé()) {
        this.recalculTousLocataires();
      } else {
        this.rafraîchirDonnées();
      }
    }
  }

  // Rafraîchissement des données de la table et du libellé total en incrémentant
  // le loyer total et en gardant la part du loyer payée par chaque locataire
  public void rafraîchirDonnées() {
    Tableau table = this.vue.getTableAutresLocataires();

    // on nettoie d'abord la table
    table.clear();

    double totalLoyer = 0d;

    for (Location locationCourante : bien.getLocationsCourantes()) {
      // On regarde si la location est celle que l'on édite
      boolean estLocataireEnModification = locationCourante.getBien().getId() == location.getBien().getId()
          && locationCourante.getLocataire().getId() == location.getLocataire().getId();

      double loyer =
          // Si c'est la location qu'on modifie, on met la valeur entrée plutôt que la
          // valeur actuellement en base de données
          estLocataireEnModification ? this.vue.getLoyer() : locationCourante.getLoyer();

      table.addRow(
          locationCourante.getLocataire().getId(),
          locationCourante.getLocataire().getNomComplet(),
          String.format("%.2f€", loyer));

      totalLoyer += loyer;
    }

    table.revalidate();
    table.repaint();

    this.vue.setLblTotalAmount(String.format("%.2f€", totalLoyer));
  }

  // Rafraîchissement des données de table et du libellé total en se basant sur le
  // loyer total et le nouveau loyer de la location comme de valeurs fixes. La
  // part du loyer des autres locataires et automatiquement recalculée pour garder
  // le même loyer total.
  public void recalculTousLocataires() {
    Tableau table = this.vue.getTableAutresLocataires();

    // on nettoie d'abord la table
    table.clear();

    // loyer total
    final double totalLoyerFixe = bien.getLocationsCourantes().stream().mapToDouble(Location::getLoyer).sum();
    // le loyer en cours de modification
    final double nouveauLoyer = this.vue.getLoyer();

    for (Location locationCourante : bien.getLocationsCourantes()) {
      // On regarde si la location est celle que l'on édite
      final boolean estLocataireEnModification = locationCourante.getBien().getId() == location.getBien().getId()
          && locationCourante.getLocataire().getId() == location.getLocataire().getId();

      if (estLocataireEnModification) {
        table.addRow(
            locationCourante.getLocataire().getId(),
            locationCourante.getLocataire().getNomComplet(),
            String.format("%.2f€", nouveauLoyer));
      } else {
        double totalLoyersSansLocataireModification = totalLoyerFixe - location.getLoyer();
        double facteurMultiplication = (totalLoyerFixe - nouveauLoyer)
            / totalLoyersSansLocataireModification;

        table.addRow(
            locationCourante.getLocataire().getId(),
            locationCourante.getLocataire().getNomComplet(),
            String.format("%.2f€", locationCourante.getLoyer() * facteurMultiplication));
      }
    }

    table.revalidate();
    table.repaint();

    this.vue.setLblTotalAmount(String.format("%.2f€", totalLoyerFixe));
  }
}