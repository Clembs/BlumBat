package controller;

import java.awt.event.*;

import javax.swing.*;

import dao.ProprietaireDAO;
import model.Proprietaire;
import view.FenBiens;
import view.FenConnexionClient;

public class ControleurConnexionClient implements ActionListener {
  private FenConnexionClient fenetre;
  private ProprietaireDAO proprietaireDAO;

  public ControleurConnexionClient(FenConnexionClient fenetre) {
    this.fenetre = fenetre;
    this.proprietaireDAO = new ProprietaireDAO();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String email = fenetre.getEmail();
    String motDePasse = fenetre.getMotDePasse();

    try {
      Proprietaire proprietaire = proprietaireDAO.read(email, motDePasse);

      JOptionPane.showMessageDialog(
          this.fenetre,
          String.format("Heureux de vous retrouver, %s %s", proprietaire.getPrenom(), proprietaire.getNom()),
          "Connection établie",
          JOptionPane.INFORMATION_MESSAGE);

      // TODO: Montrer le tableau de bord au lieu des biens lorsque ce premier sera
      // implémenté
      FenBiens fenetreBiens = new FenBiens(proprietaire);
      fenetreBiens.setVisible(true);
      this.fenetre.dispose();

    } catch (RuntimeException exception) {
      JOptionPane.showMessageDialog(
          this.fenetre,
          "Aucun compte trouvé avec ces identifiants. Veuillez réessayer.",
          "Identifiants incorrects",
          JOptionPane.ERROR_MESSAGE);
    }

  }
}