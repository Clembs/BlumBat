package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.LocationDAO;
import model.BienLocatif;
import model.Locataire;
import model.Location;
import model.Proprietaire;
import view.VueLocataires;
import view.VueAjoutLocation;
import view.VueBiens;

public class ControleurAjoutLocation implements ActionListener {
  private BienLocatif bien;
  private VueAjoutLocation fenetre;
  private Proprietaire proprietaire;
  private VueBiens fenBiens;
  private LocationDAO locationDAO;

  public ControleurAjoutLocation(
      VueAjoutLocation fenetre, BienLocatif bien, Proprietaire proprietaire,
      VueBiens fenBiens) {
    this.bien = bien;
    this.fenetre = fenetre;
    this.proprietaire = proprietaire;
    this.fenBiens = fenBiens;
    this.locationDAO = new LocationDAO();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton boutonClique = (JButton) e.getSource();

    // Vérifier si le bouton "Louer" est cliqué
    if (boutonClique.getText().equals("Louer")) {
      // nettoyage préalable des erreurs
      this.fenetre.clearErreurs();

      double loyer = this.fenetre.getLoyer();
      String rawDateEntree = this.fenetre.getDateEntree();
      String rawDateSortie = this.fenetre.getDateSortie();
      LocalDate dateEntree = null;
      LocalDate dateSortie = null;

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

      if (loyer <= 0) {
        this.fenetre.addErreur("Le loyer doit être supérieur à 0");
      }

      try {
        dateEntree = LocalDate.parse(rawDateEntree, formatter);
      } catch (DateTimeParseException error) {
        this.fenetre.addErreur("Date d'entrée invalide. Utilisez le format dd/MM/yyyy.");
      }

      if (!rawDateSortie.isEmpty()) {
        try {
          dateSortie = LocalDate.parse(rawDateSortie, formatter);
        } catch (DateTimeParseException error) {
          this.fenetre.addErreur("Date de sortie invalide. Utilisez le format dd/MM/yyyy.");
        }

        if (dateEntree.isAfter(dateSortie)) {
          this.fenetre.addErreur("La date de sortie doit être après la date d'entrée");
        }
      }

      List<Locataire> locataires = this.fenetre.getLocataires();

      if (locataires == null || locataires.size() == 0) {
        this.fenetre.addErreur("Veuillez sélectionner au moins un locataire");
      }

      if (this.fenetre.hasErreurs()) {
        return;
      }

      // Pour chaque locataire sélectionné, créer le locataire & la location associée
      for (Locataire locataire : locataires) {
        Location location = new Location(
            loyer,
            dateEntree,
            dateSortie,
            this.bien,
            locataire);

        // Ajouter la location au bien et enregistrer dans la base de données
        bien.addLocation(location);
        locationDAO.create(location);
      }

      JOptionPane.showMessageDialog(this.fenetre, "Location ajoutée avec succès!");

      // on met à jour le bien dans la fenêtre des biens
      this.fenBiens.updateBien(bien);

      // Fermer la fenêtre après ajout
      this.fenetre.dispose();

    } else if (boutonClique.getText().equals("Sélectionner")) {
      VueLocataires nouvelleFenetre = new VueLocataires(proprietaire, fenetre);
      nouvelleFenetre.setVisible(true);
    }
  }
}
