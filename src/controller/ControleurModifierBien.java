package controller;

import dao.BienDAO;
import model.BienLocatif;
import model.TypeBien;
import view.PanelModifierUnBien;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurModifierBien implements ActionListener {
    private PanelModifierUnBien fenetre;
    private BienDAO bienDAO;

    public ControleurModifierBien(PanelModifierUnBien fenetre) {
        this.fenetre = fenetre;
        this.bienDAO = new BienDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();

        if (boutonClique.getText().equals("Modifier")) {
            fenetre.clearErreurs();  // Effacer les erreurs précédentes

            // Récupérer les valeurs des champs de texte
            String idBien = fenetre.getIdBien();
            String adresse = fenetre.getAdresse();
            String complementAdresse = fenetre.getComplementAdresse();
            String ville = fenetre.getVille();
            String codePostal = fenetre.getCodePostal();
            TypeBien typeBien = fenetre.getTypeBien();
            String surfaceStr = fenetre.getSurface();
            String nbFiscalStr = fenetre.getNbFiscal();
            String nbPieceStr = fenetre.getNbPiece();

            boolean valide = true;

            // Validation des champs vides
            if (idBien.isEmpty() || adresse.isEmpty() || ville.isEmpty() || codePostal.isEmpty() ||
                    surfaceStr.isEmpty() || nbFiscalStr.isEmpty() || nbPieceStr.isEmpty()) {
                valide = false;
                fenetre.addErreur("Veuillez remplir tous les champs.");
            }

            if (valide) {
                try {
                    float surface = Float.parseFloat(surfaceStr);
                    int nbFiscal = Integer.parseInt(nbFiscalStr);
                    int nbPiece = Integer.parseInt(nbPieceStr);

                    // Vérification de la validité des valeurs numériques
                    if (surface <= 0 || nbFiscal <= 0 || nbPiece <= 0) {
                        valide = false;
                        fenetre.addErreur("Les valeurs doivent être supérieures à zéro.");
                    } else {
                        // Créer l'objet BienLocatif et effectuer la modification dans la base de données
                        BienLocatif bien = new BienLocatif(idBien, typeBien, adresse, complementAdresse, codePostal,ville , nbFiscalStr, surface, nbPiece);
                        bienDAO.modifierBien(bien);

                        // Effacer les erreurs et informer l'utilisateur
                        fenetre.clearErreurs();
                        JOptionPane.showMessageDialog(fenetre, "Votre bien a été modifié avec succès.");
                    }
                } catch (NumberFormatException ex) {
                    valide = false;
                    fenetre.addErreur("Les valeurs numériques sont invalides.");
                }
            }
        }
    }
}
