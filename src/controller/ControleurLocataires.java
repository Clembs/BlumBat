package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.LocataireDAO;
import model.Locataire;
import model.Proprietaire;
import view.FenAjoutBien;
import view.FenLocataires;
import view.PanelConsultationLocataire;

public class ControleurLocataires implements ActionListener, ListSelectionListener {
  private FenLocataires fenetre;
  private Proprietaire proprietaire;
  private LocataireDAO locataireDAO;
  private List<Locataire> locataires;

  public ControleurLocataires(Proprietaire proprietaire, FenLocataires fenetre) {
    this.proprietaire = proprietaire;
    this.fenetre = fenetre;
    this.locataireDAO = new LocataireDAO();
    this.locataires = locataireDAO.getAllLocataires(proprietaire);

    fenetre.setLocataires(locataires);
  }

  // Lorsque l'on clique sur le bouton "Ajouter"
  @Override
  public void actionPerformed(ActionEvent e) {
    FenAjoutBien fenetreAjoutBien = new FenAjoutBien(proprietaire);
    fenetreAjoutBien.setVisible(true);
    this.fenetre.dispose();
  }

  // Lorsque l'on sélectionne un locataire
  @Override
  public void valueChanged(ListSelectionEvent e) {
    // Récupérer l'index du locataire sélectionné
    int selectedIndex = e.getFirstIndex();

    if (selectedIndex != -1) {
      // Récupérer le locataire sélectionné
      Locataire locataire = locataires.get(selectedIndex);

      PanelConsultationLocataire panel = new PanelConsultationLocataire(fenetre, proprietaire, locataire);

      fenetre.setPanelCentral(panel);
    }
  }
}
