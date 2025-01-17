package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.TravailDAO;
import model.BienImmobilier;
import model.FactureTravaux;
import view.VueAjoutTravail;

public class ControleurAjoutTravail implements ActionListener {

  private BienImmobilier bien;
  private TravailDAO travauxDAO;
  private VueAjoutTravail fenetre;
  private ControleurTravaux control;

  public ControleurAjoutTravail(BienImmobilier bien, VueAjoutTravail fenetre, ControleurTravaux control) {
    this.bien = bien;
    this.fenetre = fenetre;
    this.travauxDAO = new TravailDAO();
    this.control = control;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton boutonClique = (JButton) e.getSource();

    if (boutonClique.getText().equals("Enregistrer")) {
      // nettoyage préalable des erreurs
      this.fenetre.clearErreurs();

      String iD = this.fenetre.getID();
      String description = this.fenetre.getDescription();
      String entreprise = this.fenetre.getEntreprise();
      double montantDevis = this.fenetre.getMontantDevis();
      double montantFacture = this.fenetre.getMontantFacture();
      Date date = this.fenetre.getDate();

      if (iD.isEmpty()) {
        this.fenetre.addErreur("Veuillez saisir un identifiant");
      }

      if (description.isEmpty()) {
        this.fenetre.addErreur("Veuillez saisir la description du travail");
      }

      if (entreprise.isEmpty()) {
        this.fenetre.addErreur("Veuillez saisir le nom de l'entreprise");
      }

      if (montantDevis <= 0) {
        this.fenetre.addErreur("Le montant du devis doit être supérieur à 0");
      }

      if (montantFacture <= 0) {
        this.fenetre.addErreur("Le montant de la facture doit être supérieur à 0");
      }

      LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

      if (localDate.isAfter(LocalDate.now())) {
        this.fenetre.addErreur("La date ne peut pas être dans le futur");
      }

      if (this.fenetre.hasErreurs()) {
        return;
      }

      FactureTravaux facture = new FactureTravaux(iD, description, montantDevis, montantFacture, entreprise, localDate,
          this.bien);

      travauxDAO.create(facture, this.bien);

      JOptionPane.showMessageDialog(this.fenetre, "Travail ajouté avec succès !");

      this.control.loadData();

      // Fermer la fenêtre après ajout
      this.fenetre.dispose();

    } else if (boutonClique.getText().equals("Annuler")) {
      this.fenetre.dispose();
    }
  }

}
