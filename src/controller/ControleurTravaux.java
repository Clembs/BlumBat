package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.TravailDAO;
import model.BienImmobilier;
import model.FactureTravaux;
import view.VueAjoutTravail;
import view.VueTravaux;

public class ControleurTravaux implements ActionListener {

    private VueTravaux view;
    private TravailDAO travauxDAO;
    private BienImmobilier bien;

    public ControleurTravaux(VueTravaux view, BienImmobilier bien) {
        this.view = view;
        this.bien = bien;
        this.travauxDAO = new TravailDAO();

        this.loadData();
    }

    public void loadData() {
        List<FactureTravaux> factures = travauxDAO.getAllTravaux(bien);
        DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
        tableModel.setRowCount(0); // Réinitialiser le tableau

        double totalPrix = 0;

        for (FactureTravaux facture : factures) {
            tableModel.addRow(new Object[] {
                    facture.getDescription(),
                    facture.getEntreprise(),
                    facture.getMontantDevis() + "€",
                    facture.getMontantFacture() + "€"
            });
            totalPrix += facture.getMontantFacture();
        }

        // Mettre à jour le prix total dans la vue
        view.setPrixTotal(totalPrix + "€");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();
        String boutonTexte = boutonClique.getText();

        switch (boutonTexte) {
            case "Ajouter":
                // Controleur passé pour l'utilisation du methode de loadData() dans le
                // controleur de la fenetre ajoutTravaux pour mis a jour le tableau des travaux
                VueAjoutTravail nouvelleFenetre = new VueAjoutTravail(this.bien, this);
                nouvelleFenetre.setVisible(true);
                break;
            case "Supprimer":
                supprimerTravaux();
                break;
        }

    }

    private void supprimerTravaux() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
            String description = (String) tableModel.getValueAt(selectedRow, 0);

            FactureTravaux factureToDelete = travauxDAO.getTravailByDescription(description, bien);

            if (factureToDelete != null) {
                travauxDAO.delete(factureToDelete);
                loadData(); // Reload la Data apres supression
                JOptionPane.showMessageDialog(view, "Travail supprimé avec succès !");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un travail à supprimer.");
        }
    }

}
