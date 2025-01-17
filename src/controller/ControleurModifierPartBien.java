package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.LocationDAO;
import model.BienLocatif;
import model.Location;
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
                rafraichirTable(bien);
                JOptionPane.showMessageDialog(vue, "Loyer mis à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un locataire.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String rafraichirTable(BienLocatif bien) {
        DefaultTableModel model = (DefaultTableModel) vue.getOtherTenantsTable().getModel();
        model.setRowCount(0);
        double totalLoyer = 0;
        for (Location location : bien.getLocationsCourantes()) {
            model.addRow(new Object[]{
                    location.getLocataire().getNom(),
                    location.getLoyer() + " €",
                    location.getDateEntree(),
                    location.getDateSortie()
            });
            totalLoyer += location.getLoyer();
        }
        return (totalLoyer + " €");
    }
}