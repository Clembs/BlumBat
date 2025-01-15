package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.TravauxDAO;
import model.BienImmobilier;
import model.FactureTravaux;
import view.VueAjoutTravaux;
import view.VueTravaux;


public class ControleurTravaux implements ActionListener{

    private VueTravaux view;
    private TravauxDAO travauxDAO;
    private BienImmobilier bien;

    public ControleurTravaux(VueTravaux view, BienImmobilier bien) {
        this.view = view;
        this.bien = bien;
        this.travauxDAO = new TravauxDAO();
    
        this.loadData();
    }

    public void loadData() {
        List<FactureTravaux> factures = travauxDAO.getAllFacture(bien);
        DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
        tableModel.setRowCount(0); // Réinitialiser le tableau

        double totalPrix = 0;

        for (FactureTravaux facture : factures) {
            tableModel.addRow(new Object[]{
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
            case "Ajouter Nouveaux Travaux":
                VueAjoutTravaux nouvelleFenetre = new VueAjoutTravaux(this.bien, this); //Controleur passé pour l'utilisation du methode de loadData() dans le controleur de la fenetre ajoutTravaux pour mis a jour le tableau des travaux
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
    
            FactureTravaux factureToDelete = travauxDAO.getFactureByDescription(description, bien);
    
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
