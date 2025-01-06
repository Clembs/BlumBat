package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dao.LocationDAO;
import model.BienImmobilier;
import model.Proprietaire;
import view.FenLocataires;
import view.FenAjoutLocation;

public class ControleurAjoutLocation implements ActionListener {
    private FenAjoutLocation fenetre;
    private LocationDAO LocationDAO;
    private BienImmobilier bien;
    private Proprietaire proprietaire;

    public ControleurAjoutLocation(FenAjoutLocation fenetre, BienImmobilier bien, Proprietaire prop) {
        this.bien = bien;
        this.fenetre = fenetre;
        this.proprietaire = prop;
        this.LocationDAO = new LocationDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();

        // Vérifier si le bouton "Louer" est cliqué
        if (boutonClique.getText().equals("Louer")) {
            // Récupérer les données depuis les champs de texte via les getters
            // this.loyer = fenetre.getLoyer();
            // try {
            // this.dateEntree = fenetre.getDateEntree();
            // } catch (ParseException error) {
            // }
            // try {
            // this.dateSortie = fenetre.getDateSortie();
            // } catch (ParseException error) {
            // }
            // this.locataire = fenetre.getLocataire();

            // Location location = new Location(this.loyer, this.dateEntree,
            // this.dateSortie, this.bien, this.locataire);
            // LocationDAO.create(location);

            // Afficher un message de confirmation
            // JOptionPane.showMessageDialog(this.fenetre, "Location ajouté avec succès!");

            // Fermer la fenêtre après ajout
            // this.fenetre.dispose();

        }
        // else if (boutonClique.getText().equals("Sélectionner")) {
        // FenLocataires nouvelleFenetre = new FenLocataires(proprietaire);
        // nouvelleFenetre.setVisible(true);
        // }
    }
}
