package controller;

import dao.LocataireDAO;
import model.Locataire;
import model.Proprietaire;
import view.VueLocataires;
import view.VueConsultationLocataire;
import view.VueModificationLocataire;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurConsultationLocataire implements ActionListener {
  private VueLocataires fenetre;
  private VueConsultationLocataire panel;
  private Locataire locataire;
  private Proprietaire proprietaire;
  private LocataireDAO locataireDAO;

  public ControleurConsultationLocataire(VueLocataires fenetre, VueConsultationLocataire panel,
      Proprietaire proprietaire,
      Locataire locataire) {
    // Initialisation de la vue et des DAO
    this.fenetre = fenetre;
    this.panel = panel;
    this.proprietaire = proprietaire;
    this.locataire = locataire;
    this.locataireDAO = new LocataireDAO();
  }

  // Lorsque l'on clique sur le bouton "Ajouter"
  @Override
  public void actionPerformed(ActionEvent e) {
    JButton boutonClique = (JButton) e.getSource();
    String boutonTexte = boutonClique.getText();

    switch (boutonTexte) {
      case "Modifier": {
        VueModificationLocataire panelModificationLocataire = new VueModificationLocataire(
            this.fenetre,
            this.proprietaire,
            this.locataire);

        this.fenetre.setPanelCentral(panelModificationLocataire);
        break;
      }
      case "Supprimer": {
        // TODO: suppression d'un locataire
      }
    }
  }

  // TODO: double-clic sur un bien pour afficher ses informations avec
  // MouseListener
}
