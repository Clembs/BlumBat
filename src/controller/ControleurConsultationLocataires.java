package controller;

import dao.LocataireDAO;
import model.Locataire;
import view.FenConsultationLocataires;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;


public class ControleurConsultationLocataires implements ListSelectionListener {
    private FenConsultationLocataires fenetre;
    private LocataireDAO locataireDAO;
    private List<Locataire> locataireList;


    public ControleurConsultationLocataires(FenConsultationLocataires fenetre) {
        // Initialisation de la vue et du DAO
        this.fenetre = fenetre;
        this.locataireDAO = new LocataireDAO();

        // Initialisation de la liste des locataires
        List<Locataire> locataires = locataireDAO.getAllLocataires();

        // Envoyer la liste des locataires à la vue
        fenetre.setLocatairesList(locataires);


        // Ajout du listerner sur chaque elements de la liste des locataires
        fenetre.getLocatairesList().addListSelectionListener(this);


    }

    // Sélectionne un locataire et affiche directement ses informations dans la vue
    @Override
    public void valueChanged(ListSelectionEvent e) {
        List<Locataire> locataireList = locataireDAO.getAllLocataires();
        int i = fenetre.getLocatairesList().getSelectedIndex();

        if(i >= 0) {
            Locataire locataire = locataireList.get(i);
            fenetre.updateDetails(locataire);
        }else {
            System.out.println("Aucun locataire trouvée ");
        }
    }


}
