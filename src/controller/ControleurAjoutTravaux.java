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
            this.fenetre.clearErreurs();
      
            String iD = this.fenetre.getID();
            String description = this.fenetre.getDescription();
            String entreprise = this.fenetre.getEntreprise();
            double devise = this.fenetre.getMontantDevise();
            double montantFacture = this.fenetre.getMontantFacture();
            
            if (iD.isEmpty()) {
              this.fenetre.addErreur("Il faut mettre un ID");
            }

            if (description.isEmpty()) {
              this.fenetre.addErreur("Il faut mettre une description");
            }

            if (entreprise.isEmpty()) {
              this.fenetre.addErreur("Il faut mettre une entreprise");
            }

            if (devise <= 0) {
              this.fenetre.addErreur("Le devise doit être supérieur à 0");
            }

            if (montantFacture <= 0) {
              this.fenetre.addErreur("Le montant facture doit être supérieur à 0");
            }

            if (this.fenetre.hasErreurs()) {
              return;
            }
           
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
