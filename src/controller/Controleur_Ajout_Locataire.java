package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.BienDAO;
import model.Locataire;
import model.Proprietaire;
import dao.LocataireDAO;
import view.FEN_AJOUTER_LOCATAIRE;


public class Controleur_Ajout_Locataire implements ActionListener {
    private FEN_AJOUTER_LOCATAIRE fenetre;
    private LocataireDAO locataireDao;
    private LocataireDAO LocataireDAO;

    private String nom, prenom, email, telephone, id;

    public Controleur_Ajout_Locataire(Proprietaire P, FEN_AJOUTER_LOCATAIRE fenetre) {
        this.fenetre = fenetre;
        this.locataireDao = new LocataireDAO();
        this.LocataireDAO = new LocataireDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();

        // Vérifier si le bouton "Ajouter" est cliqué
        if (boutonClique.getText().equals("Ajouter")) {
            fenetre.clearErreurs(); 

            // Récupérer les valeurs des champs de texte
            String id = fenetre.getIdentifiant();
            String nom = fenetre.getNom();
            String prenom = fenetre.getPrenom();
            String email = fenetre.getEmail();
            String telephone = fenetre.getTelephone();
            
            boolean valide = true ;

            // Validation des champs
            if (id.isEmpty()) {
                fenetre.addErreur("L'id ne peut pas être vide.");
                valide=false;
            }
            if (nom.isEmpty()) {
                fenetre.addErreur("Le Nom ne peut pas être vide.");
                valide = false;
            }
            if (prenom.isEmpty()) {
                fenetre.addErreur("Le prénom ne peut pas être vide.");
                valide = false;
            }
            if (email.isEmpty()) {
                fenetre.addErreur("L'email ne peut pas être vide.");
                valide = false;
            } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                fenetre.addErreur("L'email est invalide.");
                valide = false;
            }
            if (telephone.isEmpty() || telephone.length() != 10) {
                fenetre.addErreur("Le téléphone est invalide. Il doit contenir 10 chiffres.");
                valide = false;
            }

            // Création de l'objet Locataire si tout est valide
            if (valide) {
                Locataire locataire = new Locataire(id, nom, prenom, email, telephone);
                this.LocataireDAO.create(locataire);
                JOptionPane.showMessageDialog(this.fenetre, "Locataire ajouté avec succès");
            }
        }
    }
}