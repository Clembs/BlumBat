package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Locataire;
import model.Proprietaire;
import dao.LocataireDAO;
import view.FenLocataires;
import view.PanelConsultationLocataire;
import view.PanelModificationLocataire;

public class ControleurModificationLocataire implements ActionListener {
  private FenLocataires fenLocataires;
  private PanelModificationLocataire panel;
  private Locataire locataire;
  private Proprietaire proprietaire;
  private LocataireDAO locataireDAO;

  public ControleurModificationLocataire(
      FenLocataires fenLocataires,
      PanelModificationLocataire panel,
      Proprietaire proprietaire,
      Locataire locataire) {
    this.fenLocataires = fenLocataires;
    this.panel = panel;
    this.locataire = locataire;
    this.proprietaire = proprietaire;
    this.locataireDAO = new LocataireDAO();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton boutonClique = (JButton) e.getSource();
    String boutonTexte = boutonClique.getText();

    if (boutonTexte == "Enregistrer") {
      // Remettre à zéro les erreurs affichées
      panel.clearErreurs();

      // Récupérer les valeurs des champs de texte
      String nom = panel.getNom();
      String prenom = panel.getPrenom();
      String email = panel.getEmail();
      String telephone = panel.getTelephone();

      // Validation des champs
      if (nom.isEmpty()) {
        panel.addErreur("Le nom ne peut pas être vide.");
      }
      if (prenom.isEmpty()) {
        panel.addErreur("Le prénom ne peut pas être vide.");
      }
      if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
        panel.addErreur("L'email est invalide.");
      }
      if (!telephone.matches("^[0-9]{10}$")) {
        panel.addErreur("Le téléphone est invalide. Il doit contenir 10 chiffres.");
      }

      if (panel.hasErreurs()) {
        return;
      }

      // Modifier le locataire dans la base de données :
      Locataire nouveauLocataire = new Locataire(this.locataire.getId(), nom, prenom, email, telephone);

      locataireDAO.update(nouveauLocataire);

      JOptionPane.showMessageDialog(panel, "Locataire modifié avec succès.");

      this.fenLocataires.updateLocataire(nouveauLocataire);

      PanelConsultationLocataire panelConsultation = new PanelConsultationLocataire(fenLocataires, proprietaire,
          nouveauLocataire);

      this.fenLocataires.setPanelCentral(panelConsultation);
    } else if (boutonTexte == "Annuler") {
      PanelConsultationLocataire panelConsultation = new PanelConsultationLocataire(fenLocataires, proprietaire,
          locataire);

      this.fenLocataires.setPanelCentral(panelConsultation);
    }
  }
}
