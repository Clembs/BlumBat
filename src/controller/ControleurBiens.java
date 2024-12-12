package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.BienDAO;
import model.Proprietaire;
import view.FenBiens;

public class ControleurBiens implements ActionListener {
  private FenBiens fenetre;
  private Proprietaire proprietaire;
  private BienDAO bienDAO;

  public ControleurBiens(Proprietaire proprietaire, FenBiens fenetre) {
    this.proprietaire = proprietaire;
    this.fenetre = fenetre;
    this.bienDAO = new BienDAO();

    fenetre.setListeBiens(bienDAO.getAllBiens(proprietaire));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Montrer la fenêtre d'ajout de bien

  }
}
