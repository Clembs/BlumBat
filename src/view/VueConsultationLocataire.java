package view;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import components.Bouton;
import components.Libellé;
import components.Tableau;
import components.Bouton.VarianteButton;
import components.Libellé.TypeLibellé;
import controller.ControleurConsultationLocataire;
import model.BienImmobilier;
import model.Locataire;
import model.Location;
import model.Proprietaire;

public class VueConsultationLocataire extends JPanel {
  private static final long serialVersionUID = 1L;
  private ControleurConsultationLocataire controleur;

  public VueConsultationLocataire(VueLocataires fenetre, Proprietaire proprietaire, Locataire locataire) {
    this.setLayout(new BorderLayout(0, 8));
    this.setBorder(new EmptyBorder(0, 16, 16, 16));
    fenetre.setTitle("Gestion des locataires - " + locataire.getId());

    // Panel du haut contenant le titre
    Libellé titleLabel = new Libellé(locataire.getPrenom() + " " + locataire.getNom(), TypeLibellé.EN_TETE);
    this.add(titleLabel, BorderLayout.NORTH);

    // panel central contenant les informations du locataire
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

    // Panel contenant les champs
    JPanel champsPanel = new JPanel();
    champsPanel.setLayout(new GridLayout(0, 2, 12, 12));
    champsPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
    // champsPanel.setAutoscrolls(true);
    centerPanel.add(champsPanel);

    JPanel panelIdentifiant = new JPanel(new GridLayout(2, 0, 0, 0));
    Libellé lblIdentifiantKey = new Libellé("Identifiant", TypeLibellé.CLEF);
    Libellé lblIdentifiantValue = new Libellé(locataire.getId());
    panelIdentifiant.add(lblIdentifiantKey);
    panelIdentifiant.add(lblIdentifiantValue);
    champsPanel.add(panelIdentifiant);

    JPanel panelPrenom = new JPanel(new GridLayout(2, 0, 0, 0));
    Libellé lblPrenomKey = new Libellé("Prénom", TypeLibellé.CLEF);
    Libellé lblPrenomValue = new Libellé(locataire.getPrenom());
    panelPrenom.add(lblPrenomKey);
    panelPrenom.add(lblPrenomValue);
    champsPanel.add(panelPrenom);

    JPanel panelNom = new JPanel(new GridLayout(2, 0, 0, 0));
    Libellé lblNomKey = new Libellé("Nom", TypeLibellé.CLEF);
    Libellé lblNomValue = new Libellé(locataire.getNom());
    panelNom.add(lblNomKey);
    panelNom.add(lblNomValue);
    champsPanel.add(panelNom);

    JPanel panelTelephone = new JPanel(new GridLayout(2, 0, 0, 0));
    Libellé lblTelephoneKey = new Libellé("Téléphone", TypeLibellé.CLEF);
    Libellé lblTelephoneValue = new Libellé(locataire.getTelephone());
    panelTelephone.add(lblTelephoneKey);
    panelTelephone.add(lblTelephoneValue);
    champsPanel.add(panelTelephone);

    JPanel panelEmail = new JPanel(new GridLayout(2, 0, 0, 0));
    Libellé lblEmailKey = new Libellé("Adresse email", TypeLibellé.CLEF);
    Libellé lblEmailValue = new Libellé(locataire.getEmail());
    panelEmail.add(lblEmailKey);
    panelEmail.add(lblEmailValue);
    champsPanel.add(panelEmail);

    // Panel (Locations)
    // JPanel tablePanel = new JPanel(new BorderLayout());
    // tablePanel.setBorder();

    Tableau tableLocataires = new Tableau(
        "ID", "Ville", "Loyer", "Date d'entrée", "Date de sortie");

    for (Location location : locataire.getLocations()) {
      BienImmobilier bien = location.getBien();

      tableLocataires.addRow(
          bien.getId(),
          bien.getVille(),
          String.format("%.2f €", location.getLoyer()),
          location.getDateEntree()
              .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
          location.getDateSortie() == null ? "En cours"
              : location.getDateSortie()
                  .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)));
    }

    JScrollPane tableScrollPane = new JScrollPane(tableLocataires);
    tableScrollPane.setBorder(
        new TitledBorder(new EtchedBorder(), "Locations", TitledBorder.CENTER, TitledBorder.TOP));
    // tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

    // cf. PanelConsultationBien.java:212
    tableScrollPane.addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        Component parent = tableScrollPane.getParent();
        parent.dispatchEvent(SwingUtilities.convertMouseEvent(tableScrollPane, e,
            parent));
      }
    });

    // tablePanel.add(tableScrollPane, BorderLayout.CENTER);
    centerPanel.add(tableScrollPane);

    JScrollPane scrollPane = new JScrollPane(centerPanel);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    this.add(scrollPane, BorderLayout.CENTER);

    // Panel du bas contenant les boutons de modification et de suppression
    JPanel panelBoutons = new JPanel();
    panelBoutons.setLayout(new FlowLayout(FlowLayout.TRAILING, 8, 8));

    controleur = new ControleurConsultationLocataire(fenetre, this, proprietaire, locataire);

    Bouton btnSupprimer = new Bouton("Supprimer", VarianteButton.DANGER);
    btnSupprimer.addActionListener(controleur);
    panelBoutons.add(btnSupprimer);

    Bouton btnModifier = new Bouton("Modifier");
    btnModifier.addActionListener(controleur);
    panelBoutons.add(btnModifier);

    this.add(panelBoutons, BorderLayout.SOUTH);
  }
}
