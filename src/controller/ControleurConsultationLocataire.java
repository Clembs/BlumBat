package controller;

import dao.LocataireDAO;
import model.Locataire;
import model.Proprietaire;
import view.FenLocataires;
import view.PanelConsultationLocataire;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurConsultationLocataire implements ActionListener {
  private FenLocataires fenetre;
  private PanelConsultationLocataire panel;
  private Locataire locataire;
  private Proprietaire proprietaire;
  private LocataireDAO locataireDAO;

  public ControleurConsultationLocataire(FenLocataires feneter, PanelConsultationLocataire panel,
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
      case "Modifier le locataire": {
        // TODO: aller to PanelModificationLocataire lorsqu'il sera implémenté
      }
      case "Supprimer le locataire": {
        // TODO: suppression d'un locataire
      }
    }
  }

  // TODO: double-clic sur un bien pour afficher ses informations avec
  // MouseListener
}
