package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import components.Layout;
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

    JTabbedPane tabs = new JTabbedPane();
    tabs.setFont(Layout.POLICE_REGULAR);

    VueDétailsBien vueDétails = new VueDétailsBien(fenetre, proprietaire, bien);
    tabs.addTab("Détails", null, vueDétails);

    VueTravaux vueTravaux = new VueTravaux(bien);
    tabs.addTab("Travaux", null, vueTravaux);

    if (bien instanceof BienLocatif) {
      BienLocatif bienLocatif = (BienLocatif) bien;

      VueLocationsPassésBien vueLocatairesPassés = new VueLocationsPassésBien(fenetre, proprietaire, bienLocatif);
      tabs.addTab("Locations passées", null, vueLocatairesPassés);
    }

    tabs.setSelectedIndex(ongletSélectionné.ordinal());

    add(tabs, BorderLayout.CENTER);
  }

  public static enum Onglets {
    DÉTAILS, TRAVAUX, LOCATIONS_EN_COURS, LOCATIONS_PASSÉES
  }
}
