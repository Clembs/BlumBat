package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.LocationDAO;
import model.BienImmobilier;
import model.Locataire;
import model.Location;
import view.FEN_ACCUEIL;
import view.FEN_CONSULTATION_LOCATAIRES;
import view.POPUP_LOUER;

public class Controleur_Ajout_Location implements ActionListener {
    private POPUP_LOUER fenetre;
    private LocationDAO LocationDAO;
    private double loyer;
    private Date dateEntree;
    private Date dateSortie;
    private BienImmobilier bien;
    private List<Locataire> locataires;

    public Controleur_Ajout_Location(POPUP_LOUER fenetre) {
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
            this.bien = fenetre.getBien();
            this.locataires = fenetre.getLocataires();

            Location location = new Location(this.loyer, this.dateEntree, this.dateSortie, this.bien, this.locataires);
            LocationDAO.create(location);

        } else if (boutonClique.getText().equals("Sélectionner")) {
            FEN_CONSULTATION_LOCATAIRES nouvelleFenetre = new FEN_CONSULTATION_LOCATAIRES();
            nouvelleFenetre.setVisible(true);
        }

        // Afficher un message de confirmation
        JOptionPane.showMessageDialog(this.fenetre, "Location ajouté avec succès!");

        // Fermer la fenêtre après ajout
        FEN_ACCUEIL nouvelleFenetre = new FEN_ACCUEIL();
        nouvelleFenetre.setVisible(true);
        this.fenetre.dispose();
    }
}
