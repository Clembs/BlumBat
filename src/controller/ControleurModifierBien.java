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
        String action = boutonClique.getText();

        if (action.equals("Modifier")) {
            modifierBien();
            fenetre.closePanel();
        }
    }

    private void modifierBien() {
        fenetre.clearErreurs();

        // Récupérer les données des champs
        String idBien = fenetre.getIdBien();
        String adresse = fenetre.getAdresse();
        String complementAdresse = fenetre.getComplementAdresse();
        String ville = fenetre.getVille();
        String codePostal = fenetre.getCodePostal();
        TypeBien typeBien = fenetre.getTypeBien();
        float surface = fenetre.getSurface();
        String nbFiscal = fenetre.getNbFiscal();
        int nbPiece = fenetre.getNbPiece();

        boolean valide = true;

        // Vérification des champs vides
        if (idBien.isEmpty() || adresse.isEmpty() || ville.isEmpty() || codePostal.isEmpty() ||
                surface == 0 || nbFiscal.isEmpty() || nbPiece == 0) {
            valide = false;
            fenetre.addErreur("Veuillez remplir tous les champs.");
        }

        if (valide) {
            BienLocatif bien = new BienLocatif(idBien, typeBien, adresse, complementAdresse, codePostal, ville, nbFiscal, surface, nbPiece);
            bienDAO.modifierBienDao(bien);
            System.out.println(bien);
            fenetre.clearErreurs();
            JOptionPane.showMessageDialog(fenetre, "Votre bien a été modifié avec succès.");
        }
    }
}