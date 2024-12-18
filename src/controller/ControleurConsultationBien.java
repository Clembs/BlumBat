package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dao.BienDAO;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;
import view.FenBiens;
import view.PanelConsultationBien;
import view.PanelModifierUnBien;

public class ControleurConsultationBien implements ActionListener {
  private FenBiens fenetre;
  private PanelModifierUnBien fenetreModify;
  private PanelConsultationBien panel;
  private BienLocatif bien;
  private Proprietaire proprietaire;
  private BienDAO bienDAO;

  public ControleurConsultationBien(FenBiens fenetre, PanelConsultationBien panel, Proprietaire proprietaire,
      BienLocatif bien) {
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
      case "Modifier le bien": {
        PanelModifierUnBien panelModification = new PanelModifierUnBien(
                fenetreModify.getTypeBien().toString(),
                fenetreModify.getIdBien(),
                fenetreModify.getAdresse(),
                fenetreModify.getComplementAdresse(),
                fenetreModify.getVille(),
                fenetreModify.getDepartement(),
                fenetreModify.getCodePostal(),
                fenetreModify.getSurface(),
                fenetreModify.getNbFiscal(),
                fenetreModify.getNbPiece()
        );

        fenetre.setPanelCentral(panelModification);
        break;
      }

      case "Supprimer le bien": {
        // TODO: suppression d'un bien
      }
      case "Louer": {
        // TODO: louer un bien
      }
    }
  }

  // TODO: double-clic sur un locataire pour afficher ses informations avec
  // MouseListener
}
