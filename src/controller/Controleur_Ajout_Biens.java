package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.BienDAO;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;
import model.TypeBien;
import view.FEN_Ajout_Biens;
import view.FEN_Biens;

public class Controleur_Ajout_Biens implements ActionListener {
    private FEN_Ajout_Biens fenetre;
    private Proprietaire proprio;
    private String adresse, ville, complementAdresse, id;
    private float surface;
    private String codePostal;
    private int Npieces;
    private String NFiscal;
    private TypeBien type;
    private BienDAO bienDAO;

    public Controleur_Ajout_Biens(Proprietaire P, FEN_Ajout_Biens fenetre) {
        this.proprio = P;
        this.fenetre = fenetre;
        this.bienDAO = new BienDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();
        // on nettoie les erreurs pour éviter les doublons
        fenetre.clearErreurs();

        // Vérifier si le bouton "Ajouter" est cliqué
        if (boutonClique.getText().equals("Ajouter")) {
            // Récupérer les données depuis les champs de texte via les getters
            this.id = fenetre.getId();
            if (this.id == "") {
                fenetre.addErreur("L'adresse ne peut pas être vide.");
                return;
            }

            this.adresse = fenetre.getAdresse();
            if (this.adresse == "") {
                fenetre.addErreur("L'adresse ne peut pas être vide.");
                return;
            }

            this.complementAdresse = fenetre.getComplementAdresse();

            this.codePostal = String.valueOf(fenetre.getCodePostal());
            if (this.codePostal.length() != 5) {
                fenetre.addErreur("Le code postal doit comporter 5 caractères.");
                return;
            }

            this.ville = fenetre.getVille();
            if (this.ville == "") {
                fenetre.addErreur("La ville ne peut pas être vide.");
                return;
            }

            this.type = TypeBien.getTypeBien(fenetre.getTypeBien());

            switch (this.type) {
                case GARAGE:
                case LOGEMENT: {
                    this.surface = fenetre.getSurface();
                    if (this.surface <= 0) {
                        fenetre.addErreur("La surface doit être supérieure à 0.");
                        return;
                    }

                    this.Npieces = fenetre.getNPieces();
                    if (this.Npieces <= 0) {
                        fenetre.addErreur("Le nombre de pièces doit être supérieur à 0.");
                        return;
                    }

                    this.NFiscal = String.valueOf(fenetre.getNFiscal());
                    if (this.NFiscal.length() != 12) {
                        fenetre.addErreur("Le numéro fiscal doit comporter 12 caractères.");
                        return;
                    }

                    BienLocatif bien = new BienLocatif(
                            this.id, this.type,
                            this.adresse, this.complementAdresse,
                            this.codePostal, this.ville,
                            this.NFiscal, this.surface, this.Npieces);
                    this.bienDAO.create(bien, proprio);
                    this.proprio.addBien(bien);
                }
                    break;
                case BATIMENT: {
                    BienImmobilier bien = new BienImmobilier(
                            this.id, this.type,
                            this.adresse, this.complementAdresse,
                            this.codePostal, this.ville);
                    this.bienDAO.create(bien, proprio);
                    this.proprio.addBien(bien);
                }
                    break;
            }
            // Ajouter ce bien à la liste des biens du propriétaire

            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(this.fenetre, "Bien ajouté avec succès!");

            // Fermer la fenêtre après ajout
            FEN_Biens nouvelleFenetre = new FEN_Biens(this.proprio);
            nouvelleFenetre.setVisible(true);
            this.fenetre.dispose();
        }
    }

}
