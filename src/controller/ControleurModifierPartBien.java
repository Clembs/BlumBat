package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.BienLocatif;
import model.Locataire;
import model.Location;
import dao.LocationDAO;
import view.VueModifierPartBiens;

public class ControleurModifierPartBien implements ActionListener {
    private VueModifierPartBiens vue;
    private BienLocatif bien;
    private LocationDAO locationDAO;

    public ControleurModifierPartBien(VueModifierPartBiens vue, BienLocatif bien) {
        this.vue = vue;
        this.bien = bien;
        this.locationDAO = new LocationDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.getText().equals("Enregistrer")) {
            double nouveauLoyer = (double) vue.getSpinner().getValue();
            int selectedRow = vue.getOtherTenantsTable().getSelectedRow();
            if (selectedRow != -1) {
                Location ancienneLocation = bien.getLocationsCourantes().get(selectedRow);
                Location nouvelleLocation = new Location(
                        nouveauLoyer,
                        ancienneLocation.getDateEntree(),
                        ancienneLocation.getDateSortie(),
                        ancienneLocation.getBien(),
                        ancienneLocation.getLocataire()
                );
                bien.getLocationsCourantes().set(selectedRow, nouvelleLocation);
                locationDAO.update(nouvelleLocation);
                vue.rafraichirTable(bien);
                JOptionPane.showMessageDialog(vue, "Loyer mis à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un locataire.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}