package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.LocationDAO;
import model.BienImmobilier;
import model.Locataire;
import model.Location;
import view.PopupAjoutLocation;

public class ControleurAjoutLocation implements ActionListener {
    private PopupAjoutLocation fenetre;
    private LocationDAO LocationDAO;
    private double loyer;
    private LocalDate dateEntree;
    private LocalDate dateSortie;
    private BienImmobilier bien;
    private Locataire locataires;

    public ControleurAjoutLocation(PopupAjoutLocation fenetre, BienImmobilier bien) {
        this.bien = bien;
        this.fenetre = fenetre;
        this.LocationDAO = new LocationDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();

        // Vérifier si le bouton "Ajouter" est cliqué
        if (boutonClique.getText().equals("Louer")) {
            // Récupérer les données depuis les champs de texte via les getters
            this.loyer = fenetre.getLoyer();
            try {
                this.dateEntree = fenetre.getDateEntree();
            } catch (ParseException error) {
            }
            try {
                this.dateSortie = fenetre.getDateSortie();
            } catch (ParseException error) {
            }
            //this.locataires = fenetre.getLocataires();

            Location location = new Location(this.loyer, this.dateEntree, this.dateSortie, this.bien, this.locataires);
            LocationDAO.create(location);

            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(this.fenetre, "Location ajouté avec succès!");

            // Fermer la fenêtre après ajout
            this.fenetre.dispose();

        } //else if (boutonClique.getText().equals("Sélectionner")) {
            //FEN_CONSULTATION_LOCATAIRES nouvelleFenetre = new FEN_CONSULTATION_LOCATAIRES();
            //nouvelleFenetre.setVisible(true);
        //}

    }
}
