package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dao.BienDAO;
import model.BienImmobilier;
import model.Proprietaire;
import view.FenBiens;
import view.PanelConsultationBien;
import view.FenAjoutLocation;

public class ControleurConsultationBien implements ActionListener {
  private FenBiens fenetre;
  private PanelConsultationBien panel;
  private BienImmobilier bien;
  private Proprietaire proprietaire;
  private BienDAO bienDAO;

  public ControleurConsultationBien(FenBiens fenetre, PanelConsultationBien panel, Proprietaire proprietaire,
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
      case "Modifier le bien": {
        // TODO: aller to PanelModificationBien lorsqu'il sera implémenté
      }
      case "Supprimer le bien": {
        // TODO: suppression d'un bien
      }
      case "Louer": {
        FenAjoutLocation fenAjoutLocation = new FenAjoutLocation(fenetre, bien, proprietaire);
        fenAjoutLocation.setVisible(true);
      }
    }
  }

  // TODO: double-clic sur un locataire pour afficher ses informations avec
  // MouseListener
}
