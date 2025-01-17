package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.TravailDAO;
import model.BienImmobilier;
import model.FactureTravaux;
import view.VueAjoutTravail;

public class ControleurAjoutTravaux implements ActionListener {

  private BienImmobilier bien;
  private TravailDAO travauxDAO;
  private VueAjoutTravail fenetre;
  private ControleurTravaux control;

  public ControleurAjoutTravaux(BienImmobilier bien, VueAjoutTravail fenetre, ControleurTravaux control) {
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
      double devis = this.fenetre.getMontantDevis();
      double montantFacture = this.fenetre.getMontantFacture();

      if (iD.isEmpty()) {
        this.fenetre.addErreur("Veuillez saisir un identifiant");
      }

      if (description.isEmpty()) {
        this.fenetre.addErreur("Veuillez saisir la description du travail");
      }

      if (entreprise.isEmpty()) {
        this.fenetre.addErreur("Veuillez saisir le nom de l'entreprise");
      }

      if (devis <= 0) {
        this.fenetre.addErreur("Le montant du devis doit être supérieur à 0");
      }

      if (montantFacture <= 0) {
        this.fenetre.addErreur("Le montant de la facture doit être supérieur à 0");
      }

      if (this.fenetre.hasErreurs()) {
        return;
      }

      FactureTravaux facture = new FactureTravaux(iD, montantFacture, description, devis, entreprise, this.bien);

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
