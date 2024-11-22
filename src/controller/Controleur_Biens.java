package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import model.BienImmobilier;
import model.Proprietaire;
import view.FEN_Ajout_Biens;
import view.FEN_Biens;
//import view.FEN_Locataire;

public class Controleur_Biens implements ActionListener {
	private FEN_Biens fenetre;
	private Proprietaire P;
	private JList<String> listBiens;

	public Controleur_Biens(FEN_Biens fenetre, Proprietaire P, JList<String> listBiens) {
		this.fenetre = fenetre;
		this.P = P;
		this.listBiens = listBiens;

		// Mettre à jour la JList avec les biens du locataire
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (BienImmobilier bien : P.getBiens()) {
			listModel.addElement(bien.toString()); // Utiliser une représentation adéquate de BienImmobilier
		}
		this.listBiens.setModel(listModel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton boutonClique = (JButton) e.getSource();
		// Vérifier si un bien est sélectionné dans la liste
		String selectedBien = this.listBiens.getSelectedValue();

		if (boutonClique.getText() == "Valider") {
			if (selectedBien != null) {
				//FEN_Locataire nouvelleFenetre = new FEN_Locataire();
				//nouvelleFenetre.setVisible(true);
				this.fenetre.dispose();
			} else {
				// Afficher un message d'erreur si aucun bien n'est sélectionné
				JOptionPane.showMessageDialog(this.fenetre, "Veuillez sélectionner un bien dans la liste.", "Erreur",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (boutonClique.getText() == "Ajouter") {
			FEN_Ajout_Biens nouvelleFenetre = new FEN_Ajout_Biens(this.P);
			nouvelleFenetre.setVisible(true);
			this.fenetre.dispose();
		}
	}
}
