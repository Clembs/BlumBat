package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Locataire;
import model.Proprietaire;
import dao.LocataireDAO;
import view.FenAjoutLocataire;
import view.FenLocataires;
import view.PanelConsultationLocataire;

public class ControleurAjoutLocataire implements ActionListener {
  private FenAjoutLocataire fenetre;
  private Proprietaire proprietaire;
  private FenLocataires fenLocataires;
  private LocataireDAO locataireDAO;

  public ControleurAjoutLocataire(
      FenAjoutLocataire fenetre,
      Proprietaire proprietaire,
      FenLocataires fenLocataires) {
    this.fenetre = fenetre;
    this.proprietaire = proprietaire;
    this.fenLocataires = fenLocataires;
    this.locataireDAO = new LocataireDAO();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton boutonClique = (JButton) e.getSource();

    // Vérifier si le bouton "Ajouter" est cliqué
    if (boutonClique.getText().equals("Ajouter")) {
      fenetre.clearErreurs();

      // Récupérer les valeurs des champs de texte
      String id = fenetre.getIdentifiant();
      String nom = fenetre.getNom();
      String prenom = fenetre.getPrenom();
      String email = fenetre.getEmail();
      String telephone = fenetre.getTelephone();

      // Validation des champs
      if (id.isEmpty()) {
        fenetre.addErreur("L'identifiant ne peut pas être vide.");
      }
      if (nom.isEmpty()) {
        fenetre.addErreur("Le nom ne peut pas être vide.");
      }
      if (prenom.isEmpty()) {
        fenetre.addErreur("Le prénom ne peut pas être vide.");
      }
      if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
        fenetre.addErreur("L'email est invalide.");
      }
      if (!telephone.matches("^[0-9]{10}$")) {
        fenetre.addErreur("Le téléphone est invalide. Il doit contenir 10 chiffres.");
      }

      if (fenetre.hasErreurs()) {
        return;
      }

      // Création de l'objet Locataire si tout est valide
      Locataire locataire = new Locataire(id, nom, prenom, email, telephone);
      this.locataireDAO.create(locataire, this.proprietaire);

      JOptionPane.showMessageDialog(this.fenetre, "Locataire ajouté avec succès");

      // on récupère l'ancienne liste des locataires
      List<Locataire> locataires = this.fenLocataires.getLocataires();
      locataires.add(locataire);

      // on met à jour la liste des locataires
      this.fenLocataires.setLocataires(locataires);

      PanelConsultationLocataire panel = new PanelConsultationLocataire(fenLocataires, proprietaire, locataire);
      this.fenLocataires.setPanelCentral(panel);

      // on ferme la fenêtre d'ajout
      this.fenetre.dispose();
    }
  }
}