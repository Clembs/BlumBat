package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.LocataireDAO;
import dao.LocationDAO;
import model.BienLocatif;
import model.Locataire;
import model.Location;
import model.Proprietaire;
import view.VueAjoutLocation;
import view.VueBiens;
import view.VueConsultationLocataire;
import view.VueLocataires;
import view.VueLocationsEnCoursBien;
import view.VueModifierPartBiens;

public class ControleurLocationsEnCoursBien implements ActionListener {
    private VueBiens fenetre;
    private Proprietaire proprietaire;
    private BienLocatif bien;
    private VueLocationsEnCoursBien vue;
    private LocationDAO locationDAO;
    private LocataireDAO locataireDAO;
    private VueLocataires vueLocataires;
    private Locataire locataire;

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

                    VueLocataires fenLocataires = new VueLocataires(proprietaire, null);
                    VueConsultationLocataire vueConsultationLocataire = new VueConsultationLocataire(vueLocataires, proprietaire, locataire);

                    fenLocataires.setPanelCentral(vueConsultationLocataire);
                    fenLocataires.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un locataire.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Retirer de la colocation":
                int confirmation = JOptionPane.showConfirmDialog(fenetre,
                        "Voulez-vous vraiment retirer ce locataire ? Cela entraînera la suppression du locataire de cette location.",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (confirmation == JOptionPane.YES_OPTION) {
                    selectedRow = vue.getTable().getSelectedRow();
                    if (selectedRow != -1) {
                        Location location = bien.getLocationsCourantes().get(selectedRow);
                        locationDAO.delete(location);
                        bien.removeLocation(location);
                        rafraîchirTableLocationsEnCours();
                        JOptionPane.showMessageDialog(vue, "Locataire retiré avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un locataire à retirer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Modifier part du loyer":
                selectedRow = vue.getTable().getSelectedRow();
                if (selectedRow != -1) {
                    VueModifierPartBiens vueModifierPartBiens = new VueModifierPartBiens(bien, fenetre, proprietaire);
                    vueModifierPartBiens.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un locataire à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    public void rafraîchirTableLocationsEnCours() {
        List<Location> locations = locationDAO.getAllLocations(bien);
        bien.setLocations(locations);
        DefaultTableModel table = (DefaultTableModel) vue.getModelTable();
        table.setRowCount(0);
        for (Location location : bien.getLocationsCourantes()) {
          table.addRow(new Object[]{
                  location.getLocataire().getId(),
                  location.getLocataire().getPrenom() + " " + location.getLocataire().getNom(),
                  location.getDateEntree(),
                  location.getLoyer() + " €"
          });
        }
        double totalLoyer = bien.getLocationsCourantes().stream().mapToDouble(Location::getLoyer).sum();
        vue.setLblLoyerLabel(totalLoyer);
      }
}