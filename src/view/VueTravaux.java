package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import components.*;
import components.Bouton.VarianteButton;
import components.Libellé.TypeLibellé;
import controller.ControleurTravaux;
import model.BienImmobilier;

public class VueTravaux extends JPanel {
	private static final long serialVersionUID = 1L;
	private Tableau table;
	private Libellé lblPrixTotalValue;

	public VueTravaux(BienImmobilier bien) {
		this.setLayout(new BorderLayout(0, 0));

		JPanel prixTotalPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		prixTotalPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		this.add(prixTotalPanel, BorderLayout.NORTH);

		Libellé lblPrixTotalKey = new Libellé("Prix total des travaux :", TypeLibellé.CLEF);
		prixTotalPanel.add(lblPrixTotalKey);

		this.lblPrixTotalValue = new Libellé("20.000€");
		prixTotalPanel.add(this.lblPrixTotalValue);

		table = new Tableau("ID", "Description", "Entreprise", "Date", "Montant Devis", "Montant Facture");

		// Encapsulation de la table dans un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new EmptyBorder(8, 8, 8, 8));
		this.add(scrollPane, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout(0, 0));

		JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.TRAILING, 8, 8));
		panelBoutons.setBorder(
				new CompoundBorder(new MatteBorder(1, 0, 0, 0, Layout.COULEUR_SOUS_TEXTE), new EmptyBorder(0, 8, 0, 8)));

		ControleurTravaux controleur = new ControleurTravaux(this, bien);

		Bouton btnSupprimer = new Bouton("Supprimer", VarianteButton.DANGER);
		btnSupprimer.addActionListener(controleur);
		panelBoutons.add(btnSupprimer);

		Bouton btnAjouter = new Bouton("Ajouter");
		btnAjouter.addActionListener(controleur);
		panelBoutons.add(btnAjouter);

		bottomPanel.add(panelBoutons, BorderLayout.SOUTH);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	public Tableau getTable() {
		return this.table;
	}

	public void setPrixTotal(String prixTotal) {
		lblPrixTotalValue.setText(prixTotal);
	}
}
