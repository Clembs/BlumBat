package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JTable;

import dao.BienDAO;
import model.BienImmobilier;
import model.Proprietaire;
import view.VueAjoutBien;
import view.VueBiens;
import view.VuelConsultationBien;

public class ControleurBiens implements ActionListener, MouseListener {
  private VueBiens fenetre;
  private Proprietaire proprietaire;
  private BienDAO bienDAO;
  private List<BienImmobilier> biens;

  public ControleurBiens(Proprietaire proprietaire, VueBiens fenetre) {
    this.proprietaire = proprietaire;
    this.fenetre = fenetre;
    this.bienDAO = new BienDAO();
    this.biens = bienDAO.getAllBiens(proprietaire);

    fenetre.setBiens(biens);
  }

  // Lorsque l'on clique sur le bouton "Ajouter"
  @Override
  public void actionPerformed(ActionEvent e) {
    VueAjoutBien fenetreAjoutBien = new VueAjoutBien(proprietaire);
    fenetreAjoutBien.setVisible(true);
  }

  // Lorsque l'on sélectionne un bien dans la liste
  @Override
  public void mouseClicked(MouseEvent e) {
    JTable tableBiens = (JTable) e.getSource();
    // On récupère la ligne sélectionnée
    int row = tableBiens.rowAtPoint(e.getPoint());

    // Si la ligne sélectionnée est valide
    if (row != -1) {
      BienImmobilier bien = biens.get(row);

      VuelConsultationBien menu = new VuelConsultationBien(fenetre, proprietaire, bien,
          VuelConsultationBien.Onglets.DÉTAILS);
      fenetre.setPanelCentral(menu);
      menu.setVisible(true);
    }
  }

  // On ne définit pas les autres méthodes de MouseListener car elles ne sont pas
  // utilisées
  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }
}
