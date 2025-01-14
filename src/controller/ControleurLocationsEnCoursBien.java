package controller;

import dao.LocataireDAO;
import model.BienLocatif;
import model.Locataire;
import model.Location;
import model.Proprietaire;
import dao.LocationDAO;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurLocationsEnCoursBien implements ActionListener {
    private VueBiens fenetre;
    private Proprietaire proprietaire;
    private BienLocatif bien;
    private VueLocationsEnCoursBien vue;
    private LocationDAO locationDAO;
    private LocataireDAO locataireDAO;
    private VueLocataires vueLocataires;

    public ControleurLocationsEnCoursBien(VueBiens fenetre, Proprietaire proprietaire, BienLocatif bien, VueLocationsEnCoursBien vue) {
        this.fenetre = fenetre;
        this.proprietaire = proprietaire;
        this.bien = bien;
        this.locationDAO = new LocationDAO();
        this.locataireDAO = new LocataireDAO();
        this.vue = vue;
        this.vueLocataires = new VueLocataires(proprietaire, new VueAjoutLocation(fenetre, bien, proprietaire));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();
        String boutonTexte = boutonClique.getText();

        switch (boutonTexte) {
            case "Ajouter un locataire":
                VueAjoutLocation vueAjoutLocation = new VueAjoutLocation(fenetre, bien, proprietaire);
                vueAjoutLocation.setVisible(true);
                break;
            case "Détails du locataire":
                int selectedRow = vue.getTable().getSelectedRow();
                if (selectedRow != -1) {
                    Location location = bien.getLocationsCourantes().get(selectedRow);
                    Locataire locataire = location.getLocataire();
                    VueConsultationLocataire vueConsultationLocataire = new VueConsultationLocataire(vueLocataires, proprietaire, locataire);
                    fenetre.setPanelCentral(vueConsultationLocataire);
                } else {
                    JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un locataire.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Retirer de la colocation":
                selectedRow = vue.getTable().getSelectedRow();
                if (selectedRow != -1) {
                    Location location = bien.getLocationsCourantes().get(selectedRow);
                    locationDAO.delete(location);
                    bien.removeLocation(location);
                    vue.rafraîchirTableLocationsEnCours(bien);
                    JOptionPane.showMessageDialog(vue, "Locataire retiré avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un locataire à retirer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Modifier part du loyer":
                // TODO  à faire quand la feneêtre de modification de part de loyer sera créée
                break;
        }
        fenetre.updateBien(bien);
    }
}