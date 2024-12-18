package controller;

import dao.BienDAO;
import dao.LocataireDAO;
import model.BienLocatif;
import model.Locataire;
import model.Proprietaire;
import view.FenConsultationLocataires;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class ControleurConsultationLocataires implements ListSelectionListener {
    private FenConsultationLocataires fenetre;
    private LocataireDAO locataireDAO;
    private BienDAO bienDAO;
    private List<Locataire> locataires; // Liste locale des locataires

    public ControleurConsultationLocataires(FenConsultationLocataires fenetre) {
        // Initialisation de la vue et des DAO
        this.fenetre = fenetre;
        this.locataireDAO = new LocataireDAO();
        this.bienDAO = new BienDAO();

        // Initialisation de la liste des locataires
        this.locataires = locataireDAO.getAllLocataires();

        // Envoyer la liste des locataires à la vue
        fenetre.setLocatairesList(locataires);

        // Ajout du listener à la liste des locataires
        fenetre.getLocatairesList().addListSelectionListener(this);
    }


     // Récupère les biens associés à un locataire.

    public List<BienLocatif> getBiensByLocataire(Locataire locataire) {
        return bienDAO.getBiensByLocataire(locataire.getId());
    }


     //Gestion des événements de sélection dans la liste des locataires.

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = fenetre.getLocatairesList().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Récupérer le locataire sélectionné
            Locataire selectedLocataire = locataires.get(selectedIndex);
            fenetre.updateDetails(selectedLocataire);

            // Récupérer les biens associés et mettre à jour la table
            List<BienLocatif> biens = getBiensByLocataire(selectedLocataire);
            fenetre.updateTableBiens(biens);

            System.out.println("Locataire sélectionné : " + selectedLocataire.getNom());
            System.out.println("Biens récupérés : " + biens);
        } else {
            System.out.println("Aucun locataire sélectionné.");
        }
    }
}
