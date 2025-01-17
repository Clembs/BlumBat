package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import components.Tableau;
import dao.LocationDAO;
import model.BienLocatif;
import model.Locataire;
import model.Location;
import model.Proprietaire;
import view.VueAjoutLocation;
import view.VueBiens;
import view.VueConsultationBien;
import view.VueConsultationLocataire;
import view.VueLocataires;
import view.VueLocationsEnCoursBien;
import view.VueModifierPartLoyer;

public class ControleurLocationsEnCoursBien implements ActionListener, MouseListener {
  private VueBiens vueBiens;
  private VueLocationsEnCoursBien vue;
  private Proprietaire proprietaire;
  private BienLocatif bien;
  private LocationDAO locationDAO;
  private Location locationSélectionnée;

  public ControleurLocationsEnCoursBien(VueBiens vueBiens, Proprietaire proprietaire, BienLocatif bien,
      VueLocationsEnCoursBien vue) {
    this.vueBiens = vueBiens;
    this.proprietaire = proprietaire;
    this.bien = bien;
    this.locationDAO = new LocationDAO();
    this.vue = vue;

    this.rafraîchirTable();
  }

  public void rafraîchirTable() {
    List<Location> locations = locationDAO.getAllLocations(bien);
    bien.setLocations(locations);

    Tableau table = vue.getTable();
    table.clear();

    for (Location location : bien.getLocationsCourantes()) {
      table.addRow(
          location.getLocataire().getId(),
          location.getLocataire().getPrenom() + " " + location.getLocataire().getNom(),
          location.getDateEntree()
              .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
          String.format("%.2f€", location.getLoyer()));
    }

    double totalLoyer = bien.getLocationsCourantes().stream().mapToDouble(Location::getLoyer).sum();
    vue.setLblLoyerLabel(totalLoyer);
  }

  // Lorsque l'on sélectionne une location dans la liste, on récupère la location
  // dans locationSélectionnée
  @Override
  public void mouseClicked(MouseEvent e) {
    // On récupère la ligne sélectionnée
    int ligneSélectionnée = vue.getTable().rowAtPoint(e.getPoint());

    // Si la ligne sélectionnée est valide
    if (ligneSélectionnée != -1) {
      locationSélectionnée = bien.getLocationsCourantes().get(ligneSélectionnée);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton boutonClique = (JButton) e.getSource();
    String boutonTexte = boutonClique.getText();

    switch (boutonTexte) {
      case "Ajouter un locataire":
        VueAjoutLocation vueAjoutLocation = new VueAjoutLocation(vueBiens, bien, proprietaire);
        vueAjoutLocation.setVisible(true);
        break;
      case "Détails du locataire":
        if (locationSélectionnée != null) {
          Locataire locataire = locationSélectionnée.getLocataire();

          VueLocataires vueLocataires = new VueLocataires(proprietaire, null);
          VueConsultationLocataire vueConsultationLocataire = new VueConsultationLocataire(vueLocataires, proprietaire,
              locataire);

          vueLocataires.setPanelCentral(vueConsultationLocataire);
          vueLocataires.setVisible(true);
        } else {
          JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un locataire.", "Erreur",
              JOptionPane.ERROR_MESSAGE);
        }
        break;
      case "Retirer de la colocation":
        int confirmation = JOptionPane.showConfirmDialog(vueBiens,
            "Voulez-vous vraiment retirer ce locataire de la location ? Cela ne supprimera pas le locataire de vos locataires (voir l'onglet \"Locataires\").",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirmation == JOptionPane.YES_OPTION) {
          if (locationSélectionnée != null) {
            locationDAO.delete(locationSélectionnée);
            bien.removeLocation(locationSélectionnée);

            vueBiens.updateBien(bien);

            VueConsultationBien vueConsultationBien = new VueConsultationBien(vueBiens, proprietaire, bien,
                VueConsultationBien.Onglets.LOCATIONS_EN_COURS);

            vueBiens.setPanelCentral(vueConsultationBien);

            JOptionPane.showMessageDialog(vue, "Locataire retiré avec succès.", "Succès",
                JOptionPane.INFORMATION_MESSAGE);
          } else {
            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un locataire à retirer.", "Erreur",
                JOptionPane.ERROR_MESSAGE);
          }
        }

        break;
      case "Modifier part du loyer":
        if (locationSélectionnée != null) {
          VueModifierPartLoyer vueModifierPartBiens = new VueModifierPartLoyer(vueBiens, proprietaire, bien,
              locationSélectionnée);
          vueModifierPartBiens.setVisible(true);
        } else {
          JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un locataire à modifier.", "Erreur",
              JOptionPane.ERROR_MESSAGE);
        }
        break;
    }
  }

  // On ne définit pas les autres méthodes de MouseListener car elles ne sont pas
  // utilisées
  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }
}