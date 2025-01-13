package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;

public class VuelConsultationBien extends JPanel {
  private static final long serialVersionUID = 1L;

  public VuelConsultationBien(VueBiens fenetre, Proprietaire proprietaire, BienImmobilier bien,
      Onglets ongletSélectionné) {
    setLayout(new BorderLayout(10, 10));
    fenetre.setTitle("Consultation d'un bien - " + bien.getId());

    // Panel du haut contenant le titre
    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

    JLabel titleLabel = new JLabel(bien.getId());
    titleLabel.setFont(new Font("Rockwell", Font.BOLD, 23));
    titleLabel.setHorizontalAlignment(SwingConstants.LEFT);

    titlePanel.add(titleLabel);
    add(titlePanel, BorderLayout.NORTH);

    // Panel central contenant les informations du bien
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));

    // Panel contenant les champs
    JPanel champsPanel = new JPanel();
    champsPanel.setLayout(new GridLayout(0, 2, 10, 10));
    champsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    champsPanel.setAutoscrolls(true);

    centerPanel.add(champsPanel);

    JTabbedPane tabs = new JTabbedPane();

    VueDétailsBien panelBienDetails = new VueDétailsBien(fenetre, proprietaire, bien);
    tabs.addTab("Détails", null, panelBienDetails);

    if (bien instanceof BienLocatif) {
      BienLocatif bienLocatif = (BienLocatif) bien;

      VueLocationsPassésBien panelLocationsPassées = new VueLocationsPassésBien(fenetre, proprietaire, bienLocatif);
      tabs.addTab("Locations passées", null, panelLocationsPassées);
    }

    tabs.setSelectedIndex(ongletSélectionné.ordinal());

    add(tabs);
  }

  public static enum Onglets {
    DÉTAILS, LOCATIONS_EN_COURS, LOCATIONS_PASSÉES, FACTURES
  }
}
