package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Proprietaire;
import view.FEN_Biens;
import view.FEN_ACCUEIL;

public class Controleur_ACCUEIL implements ActionListener {
	private FEN_ACCUEIL fenetre;
	private Proprietaire P;

	public Controleur_ACCUEIL(FEN_ACCUEIL fenetre, Proprietaire P) {
		this.fenetre = fenetre;
		this.P = P;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton boutonClique = (JButton) e.getSource();
		if (boutonClique.getText() == "Mes Biens") {
			FEN_Biens nouvelleFenetre = new FEN_Biens(this.P);
			nouvelleFenetre.setVisible(true);
			this.fenetre.dispose();
		}
	}

	public void rafraichirDonnees() {
		int loyersImpayes = 3;
		int charges = 5;
		int locatairesDepart = 2;

		this.fenetre.setLabelLoyersImpayes(loyersImpayes + " loyers impayés");
		this.fenetre.setLabelCharges(charges + " charges en attente");
		this.fenetre.setLabelLocatairesDepart(locatairesDepart + " locataires en départ");
	}
}
