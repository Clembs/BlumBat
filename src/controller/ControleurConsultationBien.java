package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.BienDAO;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;
import view.FenBiens;
import view.PanelConsultationBien;

public class ControleurConsultationBien implements ActionListener {
  private FenBiens fenetre;
  private PanelConsultationBien panel;
  private BienImmobilier bien;
  private Proprietaire proprietaire;
  private BienDAO bienDAO;

  public ControleurConsultationBien(FenBiens fenetre, PanelConsultationBien panel, Proprietaire proprietaire,
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

    switch (boutonTexte) {
      case "Modifier le bien": {
        // TODO: aller to PanelModificationBien lorsqu'il sera implémenté
      }
      case "Supprimer le bien": {
        int entrée = JOptionPane.showConfirmDialog(boutonClique,
            "Voulez-vous vraiment supprimer ce bien ?"
                + (this.bien instanceof BienLocatif && ((BienLocatif) bien).estLoué()
                    ? " Cela entraînera la suppression de toutes les locations du bien."
                    : ""),
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (entrée == JOptionPane.YES_OPTION) {
          this.bienDAO.delete(this.bien, this.proprietaire);

          JOptionPane.showMessageDialog(boutonClique, "Le bien a été supprimé avec succès.", "Bien supprimé",
              JOptionPane.INFORMATION_MESSAGE);

          List<BienImmobilier> listeBiensFiltree = this.fenetre.getBiens().stream()
              .filter(b -> b.getId() != this.bien.getId())
              .collect(Collectors.toList());

          this.fenetre.setBiens(listeBiensFiltree);
          this.fenetre.resetPanelCentral();
        }

        break;
      }
      case "Louer": {
        // TODO: louer un bien
      }
    }
  }

  // TODO: double-clic sur un locataire pour afficher ses informations avec
  // MouseListener
}
