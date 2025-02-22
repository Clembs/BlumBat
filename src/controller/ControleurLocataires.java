package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.LocataireDAO;
import model.Locataire;
import model.Proprietaire;
import view.VueAjoutLocataire;
import view.VueAjoutLocation;
import view.VueLocataires;
import view.VueConsultationLocataire;

public class ControleurLocataires implements ActionListener, ListSelectionListener {
  private VueLocataires fenetre;
  private Proprietaire proprietaire;
  private LocataireDAO locataireDAO;
  private List<Locataire> locataires;
  private List<Locataire> locatairesSelectionnes;
  private VueAjoutLocation fenAjoutLocation;

  public ControleurLocataires(Proprietaire proprietaire, VueLocataires fenetre, VueAjoutLocation fenAjoutLocation) {
    this.proprietaire = proprietaire;
    this.fenetre = fenetre;
    this.locataireDAO = new LocataireDAO();
    this.locataires = locataireDAO.getAllLocataires(proprietaire);
    this.fenAjoutLocation = fenAjoutLocation;
    this.locatairesSelectionnes = new LinkedList<>();

    this.fenetre.setLocataires(this.locataires);
  }

  // Lorsque l'on clique sur le bouton "Ajouter"
  @Override
  public void actionPerformed(ActionEvent e) {
    JButton boutonClique = (JButton) e.getSource();
    String boutonTexte = boutonClique.getText();

    switch (boutonTexte) {
      case "Ajouter": {
        VueAjoutLocataire fenetreAjoutBien = new VueAjoutLocataire(this.fenetre, this.proprietaire);
        fenetreAjoutBien.setVisible(true);
        break;
      }
      case "Sélectionner": {
        this.fenAjoutLocation.setLocataires(this.locatairesSelectionnes);
        this.fenetre.dispose();
        break;
      }
    }
  }

  // Lorsque l'on sélectionne un locataire
  @Override
  public void valueChanged(ListSelectionEvent e) {
    // Si l'événement est en cours d'ajustement, ne rien faire pour éviter les
    // événements en double
    if (e.getValueIsAdjusting()) {
      return;
    }

    // on récupère la liste
    @SuppressWarnings("unchecked") // On sait que c'est une liste de String
    JList<String> list = (JList<String>) e.getSource();

    // Récupérer l'index du locataire sélectionné
    int indiceSelectionne = list.getSelectedIndex();

    if (indiceSelectionne != -1) {
      // Récupérer le locataire sélectionné
      Locataire locataire = this.locataires.get(indiceSelectionne);

      VueConsultationLocataire panel = new VueConsultationLocataire(this.fenetre, this.proprietaire,
          locataire);

      this.fenetre.setPanelCentral(panel);
    }

    // si on a plusieurs locataires sélectionnés, on les récupère
    int[] indicesSelectionnes = list.getSelectedIndices();

    // on vide la liste des locataires sélectionnés pour la remplir à nouveau
    this.locatairesSelectionnes.clear();

    for (int i : indicesSelectionnes) {
      this.locatairesSelectionnes.add(this.locataires.get(i));
    }
  }
}
