package controller;

import dao.LocataireDAO;
import model.Locataire;
import view.FenBiens;
import view.FenConsultationLocataires;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ControleurConsultationLocataires implements ActionListener {
    private FenConsultationLocataires fenetre;
    private LocataireDAO locataireDAO;

    public ControleurConsultationLocataires(FenConsultationLocataires fenetre) {
        // Initialisation de la vue et du DAO
        this.fenetre = fenetre;
        this.locataireDAO = new LocataireDAO();

        // Initialisation de la liste des locataires
        List<Locataire> locataires = locataireDAO.getAllLocataires();

        // Envoyer la liste des locataires à la vue
        fenetre.setLocatairesList(locataires);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
