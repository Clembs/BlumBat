package controller;

//import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;


import model.Locataire;
import model.Proprietaire;
//import model.Location;
import dao.locataireDAO;
import view.FEN_AJOUT_LOCATAIRE;

public class Controleur_Ajout_Locataire implements ActionListener {
    private FEN_AJOUT_LOCATAIRE fenetre;

    private String nom, prenom, email, telephone, id;
    private locataireDAO locataireDAO = new locataireDAO();

    public Controleur_Ajout_Locataire(Proprietaire P, FEN_AJOUT_LOCATAIRE fenetre) {
        this.fenetre = fenetre;
       // this.bienDAO = new BienDAO();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();

        // Vérifier si le bouton "Ajouter" est cliqué
        if (boutonClique.getText().equals("Ajouter")) {
           
            this.id = fenetre.getId();
            if (this.id == "") {
                fenetre.addErreur("L'id ne peut pas être vide.");
            }

            this.nom = fenetre.getNom();
            if (this.nom == "") {
                fenetre.addErreur("Le Nom ne peut pas être vide.");
            }
            this.prenom = fenetre.getPrenom();
            if (this.prenom == "") {
                fenetre.addErreur("Le prenom ne peut pas être vide.");
            }
            this.email = fenetre.getEmail();
            if (this.email == "") {
                fenetre.addErreur("L'email ne peut pas être vide.");
            }
            this.telephone = fenetre.getTelephone();
            if (this.telephone == "") {
                fenetre.addErreur("Le telephone ne peut pas être vide.");
            }
            
            Locataire locataire = new Locataire(
                this.id,
                this.nom,
                this.prenom,
                this.email,
                this.telephone);
            this.locataireDAO.create(locataire);
        }
        JOptionPane.showMessageDialog(this.fenetre, "Locataire ajouté avec succès");
    }

}

