package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.LocationDAO;
import model.Location;
import model.Proprietaire;
import view.FEN_AJOUT_LOCATION;
import model.BienImmobilier;
import model.Locataire;

public class Controleur_Ajout_Location implements ActionListener {
    private FEN_AJOUT_LOCATION fenetre;
    private Proprietaire proprio;
    private LocationDAO LocationDAO;
    private double loyer;
    private Date dateEntree;
    private Date dateSortie;
    private BienImmobilier bien;
    private List<Locataire> locataires;

    public Controleur_Ajout_Location(Proprietaire P, FEN_AJOUT_LOCATION fenetre) {
        this.proprio = P;
        this.fenetre = fenetre;
        this.LocationDAO = new LocationDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();

        // Vérifier si le bouton "Ajouter" est cliqué
        if (boutonClique.getText().equals("Ajouter")) {
            // Récupérer les données depuis les champs de texte via les getters
            this.loyer = fenetre.getLoyer();
            this.dateEntree = fenetre.getDateEntree();
            this.dateSortie = fenetre.getDateSortie();
            this.bien = fenetre.getBien();
            this.locataires = fenetre.getLocatires();

            Location location = new Location(this.loyer, this.dateEntree, this.dateSortie, this.bien, this.locataires); 
            LocationDAO.create(location);

            }

            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(this.fenetre, "Location ajouté avec succès!");

            // Fermer la fenêtre après ajout
            FEN_AJOUT_BIEN nouvelleFenetre = new FEN_AJOUT_BIEN(this.proprio);
            nouvelleFenetre.setVisible(true);
            this.fenetre.dispose();
        }
    }

}
