package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.BienDAO;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;
import model.TypeBien;
import view.VueAjoutBien;
import view.VueBiens;
import view.VueConsultationBien;

public class ControleurAjoutBien implements ActionListener {
  private VueBiens fenBiens;
  private VueAjoutBien fenetre;
  private Proprietaire proprietaire;
  private BienDAO bienDAO;

  public ControleurAjoutBien(VueBiens fenBiens, Proprietaire P, VueAjoutBien fenetre) {
    this.fenBiens = fenBiens;
    this.proprietaire = P;
    this.fenetre = fenetre;
    this.bienDAO = new BienDAO();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton boutonClique = (JButton) e.getSource();

    // Vérifier si le bouton "Ajouter" est cliqué
    if (boutonClique.getText().equals("Ajouter")) {
      // on nettoie les erreurs pour éviter les doublons
      fenetre.clearErreurs();

      // Récupérer les données des champs de texte
      String id = fenetre.getId();
      TypeBien type = TypeBien.getTypeBien(fenetre.getTypeBien());
      String adresse = fenetre.getAdresse();
      String complementAdresse = fenetre.getComplementAdresse();
      String codePostal = fenetre.getCodePostal();
      String ville = fenetre.getVille();

      List<BienImmobilier> biens = this.fenBiens.getBiens();
      // List<BienImmobilier> biens = this.bienDAO.getAllBiens(proprietaire);

      // déclaration anticipée pour pouvoir accéder au nouveau bien après
      BienImmobilier nouveauBien;

      if (id.isEmpty()) {
        fenetre.addErreur("L'identifiant ne peut pas être vide.");
      }

      if (biens.stream().anyMatch(b -> b.getId().equals(id))) {
        fenetre.addErreur("L'identifiant est déjà utilisé.");
      }

      if (adresse.isEmpty()) {
        fenetre.addErreur("L'adresse ne peut pas être vide.");
      }

      if (!codePostal.matches("\\d{5}") /* expression régulière pour 5 chiffres */) {
        fenetre.addErreur("Le code postal doit être composé de 5 chiffres.");
      }

      if (ville.isEmpty()) {
        fenetre.addErreur("La ville ne peut pas être vide.");
      }

      if (type != TypeBien.BATIMENT) {
        float surface = fenetre.getSurface();
        String nbFiscal = fenetre.getNFiscal();
        int nbPieces = fenetre.getNbPieces();

        if (surface <= 0) {
          fenetre.addErreur("La surface doit être supérieure à 0m².");
        }

        if (nbPieces <= 0) {
          fenetre.addErreur("Le nombre de pièces doit être supérieur à 0.");
        }

        if (!nbFiscal.matches("\\d{12}") /* même chose pour 12 chiffres */) {
          fenetre.addErreur("Le numéro fiscal doit être composé de 12 chiffres.");
        }

        if (fenetre.hasErreurs()) {
          return;
        }

        nouveauBien = new BienLocatif(
            id, type,
            adresse, complementAdresse,
            codePostal, ville,
            nbFiscal, surface, nbPieces);
      } else {
        if (fenetre.hasErreurs()) {
          return;
        }

        nouveauBien = new BienImmobilier(
            id, type,
            adresse, complementAdresse,
            codePostal, ville);
      }

      // Ajouter ce bien à la liste des biens du propriétaire
      this.bienDAO.create(nouveauBien, proprietaire);

      // Afficher un message de confirmation
      JOptionPane.showMessageDialog(this.fenetre, "Bien ajouté avec succès!", "Succès",
          JOptionPane.INFORMATION_MESSAGE);

      this.fenetre.dispose();

      biens.add(nouveauBien);
      this.fenBiens.setBiens(biens);

      VueConsultationBien vueConsultationBien = new VueConsultationBien(this.fenBiens, proprietaire, nouveauBien,
          VueConsultationBien.Onglets.DÉTAILS);
      this.fenBiens.setPanelCentral(vueConsultationBien);

    }
  }

}
