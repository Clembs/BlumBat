package view;

import javax.swing.*;
// import javax.swing.border.*;

// import controller.ControleurConsultationBien;
import model.BienImmobilier;

public class PanelConsultationBien extends JPanel {
  private static final long serialVersionUID = 1L;
  // private ControleurConsultationBien controleur;
  // private FenBiens fenBiens;
  // private BienImmobilier bien;

  public PanelConsultationBien(FenBiens fenetre, BienImmobilier bien) {
    // this.fenBiens = fenetre;
    // this.bien = bien;

    fenetre.setTitle("Consultation d'un bien - " + bien.getId());
    fenetre.add(this);

    JLabel lblTitle = new JLabel("Consultation du bien - " + bien.getId());
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setBounds(10, 11, 414, 14);
    add(lblTitle);
  }
}
