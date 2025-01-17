package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Locataire;
import model.Proprietaire;
import dao.LocataireDAO;
import view.VueAjoutLocataire;
import view.VueLocataires;
import view.VueBiens;
import view.VueConsultationLocataire;

public class ControleurAjoutLocataire implements ActionListener {
  private VueAjoutLocataire fenetre;
  private Proprietaire proprietaire;
  private VueLocataires fenLocataires;
  private VueBiens fenBiens;
  private LocataireDAO locataireDAO;

  public ControleurAjoutLocataire(VueAjoutLocataire fenetre, Proprietaire proprietaire, VueLocataires fenLocataires) {
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
      String id = fenetre.getId();
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

      if (this.fenLocataires != null) {
        // on récupère l'ancienne liste des locataires
        List<Locataire> locataires = this.fenLocataires.getLocataires();
        locataires.add(locataire);

        // on met à jour la liste des locataires
        this.fenLocataires.setLocataires(locataires);

        VueConsultationLocataire panel = new VueConsultationLocataire(fenLocataires, proprietaire, locataire);
        this.fenLocataires.setPanelCentral(panel);
      }

      // on ferme la fenêtre d'ajout
      this.fenetre.dispose();
    }
  }
}