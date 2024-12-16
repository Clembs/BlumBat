package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Locataire;
import dao.LocataireDAO;
import view.PopupModifierLocataire;

public class ControleurModifierLocataire implements ActionListener {

    private PopupModifierLocataire fenetre;
    private LocataireDAO locataireDao;

    public ControleurModifierLocataire(PopupModifierLocataire fenetre) {
        this.fenetre = fenetre;
        this.locataireDao = new LocataireDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();

        // Vérifier si le bouton "Modifier" est cliqué
        if (boutonClique.getText().equals("Modifier")) {

            // Remettre à zéro les erreurs affichées :
            fenetre.clearErreurs();

            // Récupérer les valeurs des champs de texte :
            String identifiant = fenetre.getIdentifiant();
            String nom = fenetre.getNom();
            String prenom = fenetre.getPrenom();
            String email = fenetre.getEmail();
            String telephone = fenetre.getTelephone();

            boolean valide = true;

            // Validation des champs
            if (identifiant.isEmpty()) {
                fenetre.addErreur("L'identifiant ne peut pas être vide.");
                valide = false;
            }

            if (nom.isEmpty()) {
                fenetre.addErreur("Le nom ne peut pas être vide.");
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

            if (telephone.isEmpty()) {
                fenetre.addErreur("Le téléphone ne peut pas être vide.");
                valide = false;
            } else if (telephone.length() != 10) {
                fenetre.addErreur("Le numéro de téléphone doit comporter 10 chiffres.");
                valide = false;
            }


            if (valide) {

                //Modifier le locataire dans la base de données :
                Locataire locataire = new Locataire(identifiant, nom, prenom, email, telephone);

                locataireDao.modifierLocataire(locataire);

                JOptionPane.showMessageDialog(fenetre, "Locataire modifié avec succès.");

                fenetre.dispose();
            }
        }
    }
}
