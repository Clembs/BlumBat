package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.BienDAO;
import model.BienImmobilier;
import model.BienLogement;
import model.Proprietaire;
import model.TypeBien;
import view.FEN_Ajout_Biens;
import view.FEN_Biens;

public class Controleur_Ajout_Biens implements ActionListener {
    private FEN_Ajout_Biens fenetre;
    private Proprietaire proprio;
    private String adresse, ville, complementAdresse, id;
    private Float surface;
    private int codePostal, Npieces;
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

        // Vérifier si le bouton "Ajouter" est cliqué
        if (boutonClique.getText().equals("Ajouter")) {
            // Récupérer les données depuis les champs de texte via les getters
            this.adresse = fenetre.getAdresse();
            this.ville = fenetre.getVille();
            this.codePostal = Integer.parseInt(fenetre.getCodePostal());
            this.id = fenetre.getId();
            this.complementAdresse = fenetre.getComplementAdresse(); // Assuming complementAdresse is description field
            this.type = TypeBien.getTypeBien(fenetre.getTypeBien()); // Add getter for 'type' field if necessary

            switch (this.type) {
                case GARAGE:
                case LOGEMENT: {
                    this.surface = Float.parseFloat(fenetre.getSurface());
                    this.NFiscal = fenetre.getNFiscal();
                    this.Npieces = Integer.parseInt(fenetre.getNPieces());
                    BienLogement bien = new BienLogement(this.id, this.adresse, this.complementAdresse, this.codePostal,
                            this.ville,
                            this.NFiscal, this.surface, this.Npieces);
                    this.bienDAO.create(bien, proprio);
                    this.proprio.addBien(bien);
                }
                    break;
                case BATIMENT: {
                    BienImmobilier bien = new BienImmobilier(this.id, this.type, this.adresse, this.complementAdresse,
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
