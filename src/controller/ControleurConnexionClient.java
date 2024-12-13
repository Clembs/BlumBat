package controller;

import java.awt.event.*;

import javax.swing.*;

import dao.ProprietaireDAO;
import model.Proprietaire;
import view.FenConnexionClient;

public class ControleurConnexionClient implements ActionListener {
  private FenConnexionClient fenetre;

  public ControleurConnexionClient(FenConnexionClient fenetre) {
    this.fenetre = fenetre;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton bouton = (JButton) e.getSource();

    String email = fenetre.getEmail();
    String motDePasse = fenetre.getMotDePasse();

    ProprietaireDAO daoProprietaire = new ProprietaireDAO();

    System.out.println(email + " " + motDePasse);

    try {
      Proprietaire proprietaire = daoProprietaire.read(email, motDePasse);

      // TODO: afficher le tableau de bord
      JOptionPane.showMessageDialog(this.fenetre,
          String.format("Vous êtes bien connecté %s %s", proprietaire.getPrenom(), proprietaire.getNom()));
      // this.fenetre.dispose();
    } catch (RuntimeException exception) {
      JOptionPane.showMessageDialog(this.fenetre, "Aucun compte trouvé avec ces identifiants");
    }

  }
}
