package controller;

import dao.BienDAO;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;
import model.TypeBien;
import view.FenBiens;
import view.PanelModificationBien;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurModificationBien implements ActionListener {
    private FenBiens fenetre;
    private PanelModificationBien panel;
    private BienImmobilier bien;
    private Proprietaire proprietaire;
    private BienDAO bienDAO;

    public ControleurModificationBien(FenBiens fenetre, PanelModificationBien panel, Proprietaire proprietaire,
            BienImmobilier bien) {
        this.fenetre = fenetre;
        this.panel = panel;
        this.proprietaire = proprietaire;
        this.bien = bien;
        this.bienDAO = new BienDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();
        String boutonTexte = boutonClique.getText();

        if (boutonTexte.equals("Enregistrer")) {
            panel.clearErreurs();

            // Récupérer les données des champs
            String idBien = panel.getIdBien();
            String adresse = panel.getAdresse();
            String complementAdresse = panel.getComplementAdresse();
            String ville = panel.getVille();
            String codePostal = panel.getCodePostal();
            TypeBien typeBien = panel.getTypeBien();
            float surface = panel.getSurface();
            String nbFiscal = panel.getNbFiscal();
            int nbPiece = panel.getNbPiece();

            boolean valide = true;

            // Vérification des champs vides
            if (idBien.isEmpty() || adresse.isEmpty() || ville.isEmpty() || codePostal.isEmpty() ||
                    surface == 0 || nbFiscal.isEmpty() || nbPiece == 0) {
                valide = false;
                panel.addErreur("Veuillez remplir tous les champs.");
            }

            if (valide) {
                BienLocatif bien = new BienLocatif(idBien, typeBien, adresse, complementAdresse, codePostal, ville,
                        nbFiscal, surface, nbPiece);
                bienDAO.modifierBienDao(bien);
                System.out.println(bien);
                panel.clearErreurs();
                JOptionPane.showMessageDialog(panel, "Votre bien a été modifié avec succès.");
            }

            panel.closePanel();
        }
    }
}