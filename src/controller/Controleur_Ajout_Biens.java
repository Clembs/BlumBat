package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.BienImmobilier;
import model.BienLogement;
import model.Proprietaire;
import model.TypeBien;
import view.FEN_Ajout_Biens;
import view.FEN_Biens;

public class Controleur_Ajout_Biens implements ActionListener {
    private FEN_Ajout_Biens fenetre;
    private Proprietaire P;
    private String adresse, ville, complementAdresse, id;
    private Float surface;
    private int codePostal, NFiscal, Npieces;
    private TypeBien type;
    private BienImmobilier bien;

    public Controleur_Ajout_Biens(Proprietaire P, FEN_Ajout_Biens fenetre) {
        this.P = P;
        this.fenetre = fenetre;
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
            this.surface = Float.parseFloat(fenetre.getSurface());
            this.NFiscal = Integer.parseInt(fenetre.getNFiscal());
            this.complementAdresse = "Bonjour"; // Assuming complementAdresse is description field
            this.type = TypeBien.getTypeBien(fenetre.gettType()); // Add getter for 'type' field if necessary
            this.Npieces = Integer.parseInt(fenetre.getNPieces());

            switch (this.type) {
            case LOGEMENT:
            	bien = new BienLogement(this.id, this.adresse, this.complementAdresse, this.codePostal, this.ville, this.NFiscal, this.surface, this.Npieces);
            	break;
			case BATIMENT:
				bien = new BienImmobilier(this.id, this.type, this.adresse, this.complementAdresse,
                        this.codePostal, this.ville);
				break;
			case GARAGE:
				break;
			default:
				 bien = new BienImmobilier(this.id, this.type, this.adresse, this.complementAdresse,
                        this.codePostal, this.ville);
				break;
            }
            // Ajouter ce bien à la liste des biens du propriétaire
            this.P.addBien(bien);

            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(this.fenetre, "Bien ajouté avec succès!");

            // Fermer la fenêtre après ajout
            FEN_Biens nouvelleFenetre = new FEN_Biens(this.P);
			nouvelleFenetre.setVisible(true);
            this.fenetre.dispose();
        }
    }

}
