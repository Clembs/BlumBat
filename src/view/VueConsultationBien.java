package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import components.Libellé;
import components.Libellé.TypeLibellé;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;

public class VueConsultationBien extends JPanel {
  private static final long serialVersionUID = 1L;

  public VueConsultationBien(VueBiens fenetre, Proprietaire proprietaire, BienImmobilier bien,
      Onglets ongletSélectionné) {
    fenetre.setTitle("Gestion des biens - " + bien.getId());
    this.setLayout(new BorderLayout(0, 16));
    this.setBorder(new EmptyBorder(16, 16, 16, 16));

    Libellé titleLabel = new Libellé(bien.getId(), TypeLibellé.EN_TETE);
    this.add(titleLabel, BorderLayout.NORTH);

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
