package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.LocationDAO;
import model.BienLocatif;
import model.Location;
import model.Proprietaire;
import view.VueBiens;
import view.VueConsultationBien;
import view.VueModifierPartBiens;

public class ControleurModifierPartBien implements ActionListener {
    private VueModifierPartBiens vue;
    private BienLocatif bien;
    private LocationDAO locationDAO;
    private VueBiens vueBiens;
    private Proprietaire proprietaire;

    public ControleurModifierPartBien(VueModifierPartBiens vue, Proprietaire proprietaire, BienLocatif bien, VueBiens vueBiens) {
        this.vue = vue;
        this.bien = bien;
        this.locationDAO = new LocationDAO();
        this.vueBiens = vueBiens;
        this.proprietaire = proprietaire;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.getText().equals("Enregistrer")) {
            double nouveauLoyer = (double)this.vue.getSpinner().getValue();
            int selectedRow = vue.getOtherTenantsTable().getSelectedRow();
            if (selectedRow != -1) {
                Location ancienneLocation = this.bien.getLocationsCourantes().get(selectedRow);
                Location nouvelleLocation = new Location(
                        nouveauLoyer,
                        ancienneLocation.getDateEntree(),
                        ancienneLocation.getDateSortie(),
                        ancienneLocation.getBien(),
                        ancienneLocation.getLocataire()
                );
                bien.getLocationsCourantes().set(selectedRow, nouvelleLocation);
                locationDAO.update(nouvelleLocation);
                rafraichirTable();
                JOptionPane.showMessageDialog(this.vue, "Loyer mis à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                this.vue.dispose();
            } else {
                JOptionPane.showMessageDialog(this.vue, "Veuillez sélectionner un locataire.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void rafraichirTable() {
        List<Location> locations = locationDAO.getAllLocations(bien);
        bien.setLocations(locations);
        DefaultTableModel model = (DefaultTableModel) this.vue.getOtherTenantsTable().getModel();
        model.setRowCount(0);
        double totalLoyer = 0;
        for (Location location : bien.getLocationsCourantes()) {
            model.addRow(new Object[]{
                    location.getLocataire().getNom() + location.getLocataire().getPrenom(),
                    location.getLoyer() + " €",
                    location.getDateEntree(),
                    location.getDateSortie()
            });
            totalLoyer += location.getLoyer();
        }
        this.vue.setLblTotalAmount(totalLoyer + " €");
        vueBiens.updateBien(bien);

        VueConsultationBien vueConsultationBien = new VueConsultationBien(vueBiens, proprietaire, bien, VueConsultationBien.Onglets.LOCATIONS_EN_COURS);
        vueBiens.setPanelCentral(vueConsultationBien);
    }

    public void mettreAJourSpinnerAvecLoyer() {
        int selectedRow = vue.getOtherTenantsTable().getSelectedRow();
        if (selectedRow != -1) {
            // Récupérer le loyer de la ligne sélectionnée
            double loyer = bien.getLocationsCourantes().get(selectedRow).getLoyer();
            // Mettre à jour la valeur du JSpinner
            vue.getSpinner().setValue(loyer);
        }
    }
    

}