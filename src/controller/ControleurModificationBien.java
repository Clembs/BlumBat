package controller;

import dao.BienDAO;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;
import view.VueBiens;
import view.VueConsultationBien;
import view.VueModificationBien;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurModificationBien implements ActionListener {
  private VueBiens fenetre;
  private VueModificationBien panel;
  private BienImmobilier bien;
  private Proprietaire proprietaire;
  private BienDAO bienDAO;

  public ControleurModificationBien(VueBiens fenetre, VueModificationBien panel, Proprietaire proprietaire,
      BienImmobilier bien) {
    this.fenetre = fenetre;
    this.panel = panel;
    this.proprietaire = proprietaire;
    this.bien = bien;
    this.bienDAO = new BienDAO();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton boutonClique = (JButton) e.getSource();
    String boutonTexte = boutonClique.getText();

    switch (boutonTexte) {
      case "Enregistrer": {
        panel.clearErreurs();

        // Récupérer les données des champs
        String adresse = panel.getAdresse();
        String complementAdresse = panel.getComplementAdresse();
        String ville = panel.getVille();
        String codePostal = panel.getCodePostal();

        // déclaration anticipée pour pouvoir accéder au nouveau bien après
        BienImmobilier nouveauBien;

        // Vérification des champs vides
        if (adresse.isEmpty()) {
          panel.addErreur("L'adresse ne peut pas être vide.");
        }

        if (ville.isEmpty()) {
          panel.addErreur("La ville ne peut pas être vide.");
        }

        if (!codePostal.matches("\\d{5}") /* expression régulière pour 5 chiffres */) {
          panel.addErreur("Le code postal doit être composé de 5 chiffres.");
        }

        if (this.bien instanceof BienLocatif) {
          float surface = panel.getSurface();
          String nbFiscal = panel.getNbFiscal();
          int nbPieces = panel.getNbPieces();

          if (surface <= 0) {
            panel.addErreur("La surface doit être supérieure à 0m².");
          }

          if (nbPieces <= 0) {
            panel.addErreur("Le nombre de pièces doit être supérieur à 0.");
          }

          if (!nbFiscal.matches("\\d{12}") /* même chose pour 12 chiffres */) {
            panel.addErreur("Le numéro fiscal doit être composé de 12 chiffres.");
          }

          if (panel.hasErreurs()) {
            return;
          }

          nouveauBien = new BienLocatif(this.bien.getId(), this.bien.getTypeBien(), adresse,
              complementAdresse,
              codePostal, ville,
              nbFiscal, surface, nbPieces);
        } else {
          if (panel.hasErreurs()) {
            return;
          }

          nouveauBien = new BienImmobilier(this.bien.getId(), this.bien.getTypeBien(), adresse, complementAdresse,
              codePostal, ville);
        }

        bienDAO.update(nouveauBien);

        JOptionPane.showMessageDialog(panel, "Votre bien a été modifié avec succès.");

        this.fenetre.updateBien(nouveauBien);

        VueConsultationBien panelConsultation = new VueConsultationBien(fenetre, proprietaire, nouveauBien,
            VueConsultationBien.Onglets.DÉTAILS);

        fenetre.setPanelCentral(panelConsultation);
        break;
      }
      case "Annuler": {
        VueConsultationBien panelConsultation = new VueConsultationBien(fenetre, proprietaire, bien,
            VueConsultationBien.Onglets.DÉTAILS);

        fenetre.setPanelCentral(panelConsultation);
        break;
      }
    }
  }
}