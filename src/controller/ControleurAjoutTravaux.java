package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.TravauxDAO;
import model.BienImmobilier;
import model.FactureTravaux;
import view.VueAjoutTravaux;

public class ControleurAjoutTravaux implements ActionListener{

    private BienImmobilier bien;
    private TravauxDAO travauxDAO;
    private VueAjoutTravaux fenetre;
    private ControleurTravaux control;

    public ControleurAjoutTravaux(BienImmobilier bien, VueAjoutTravaux fenetre, ControleurTravaux control){
        this.bien = bien;
        this.fenetre = fenetre;
        this.travauxDAO = new TravauxDAO();
        this.control = control;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();
        

        if (boutonClique.getText().equals("Enregistrer")) {
            // nettoyage préalable des erreurs
            //this.fenetre.clearErreurs();
      
            String iD = this.fenetre.getID();
            String description = this.fenetre.getDescription();
            String entreprise = this.fenetre.getEntreprise();
            double devise = Double.valueOf(this.fenetre.getMontantDevise());
            double montantFacture = Double.valueOf(this.fenetre.getMontantFacture());
      
           
            FactureTravaux facture = new FactureTravaux(iD,this.bien,montantFacture,description,devise,entreprise);
      
            travauxDAO.create(facture,this.bien);
      
            JOptionPane.showMessageDialog(this.fenetre, "Travail ajoutée avec succès!");

            this.control.loadData();
      
            // Fermer la fenêtre après ajout
            this.fenetre.dispose();
      
          } else if (boutonClique.getText().equals("Annuler")) {
           
            this.fenetre.dispose();
          }
    }
    
}
