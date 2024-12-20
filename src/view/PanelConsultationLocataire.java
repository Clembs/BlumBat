package view;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControleurConsultationLocataire;
import model.BienImmobilier;
import model.Locataire;
import model.Location;
import model.Proprietaire;

public class PanelConsultationLocataire extends JPanel {
  private static final long serialVersionUID = 1L;
  private ControleurConsultationLocataire controleur;

  public PanelConsultationLocataire(FenLocataires fenetre, Proprietaire proprietaire, Locataire locataire) {
    setLayout(new BorderLayout(10, 10));
    setBorder(new TitledBorder(new EtchedBorder(), "Informations Personnelles", TitledBorder.CENTER,
        TitledBorder.TOP));
    setBackground(Color.LIGHT_GRAY);
    fenetre.setTitle("Consultation d'un locataire - " + locataire.getId());

    // Panel central contenant les informations du locataire
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

    // Panel contenant les champs
    JPanel champsPanel = new JPanel();
    champsPanel.setLayout(new GridLayout(0, 2, 5, 5));
    champsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    champsPanel.setAutoscrolls(true);

    JLabel lblPrenomKey = new JLabel("Prénom :", SwingConstants.LEFT);
    lblPrenomKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    champsPanel.add(lblPrenomKey);

    JLabel lblPrenomValue = new JLabel(locataire.getPrenom(), SwingConstants.LEFT);
    lblPrenomValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    champsPanel.add(lblPrenomValue);

    JLabel lblNomKey = new JLabel("Nom :", SwingConstants.LEFT);
    lblNomKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    champsPanel.add(lblNomKey);

    JLabel lblNomValue = new JLabel(locataire.getNom(), SwingConstants.LEFT);
    lblNomValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    champsPanel.add(lblNomValue);

    JLabel lblTelephoneKey = new JLabel("Téléphone :", SwingConstants.LEFT);
    lblTelephoneKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    champsPanel.add(lblTelephoneKey);

    JLabel lblTelephoneValue = new JLabel(locataire.getTelephone(), SwingConstants.LEFT);
    lblTelephoneValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    champsPanel.add(lblTelephoneValue);

    JLabel lblEmailKey = new JLabel("Adresse email :", SwingConstants.LEFT);
    lblEmailKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    champsPanel.add(lblEmailKey);

    JLabel lblEmailValue = new JLabel(locataire.getEmail(), SwingConstants.LEFT);
    lblEmailValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    champsPanel.add(lblEmailValue);

    centerPanel.add(champsPanel);

    // Panel (Locations)
    JPanel tablePanel = new JPanel(new BorderLayout());
    // tablePanel.setBackground(Color.LIGHT_GRAY);
    tablePanel.setBorder(
        new TitledBorder(new EtchedBorder(), "Locations", TitledBorder.CENTER, TitledBorder.TOP));

    DefaultTableModel model = new DefaultTableModel(new Object[] {
        "ID", "Type", "Adresse", "Ville", "Loyer", "Date d'entrée", "Date de sortie"
    }, 0);
    JTable tableLocataires = new JTable(model);

    for (Location location : locataire.getLocations()) {
      BienImmobilier bien = location.getBien();

      model.addRow(new Object[] {
          bien.getId(),
          bien.getTypeBien(),
          bien.getAdresse(),
          bien.getCodePostal() + " " + bien.getVille(),
          location.getLoyer(),
          location.getDateEntree(),
          location.getDateSortie() == null ? "En cours" : location.getDateSortie()
      });
    }

    tableLocataires.setFont(new Font("Rockwell", Font.PLAIN, 12));

    JScrollPane tableScrollPane = new JScrollPane(tableLocataires);
    tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

    // cf. PanelConsultationBien.java:212
    tableScrollPane.addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        Component parent = tableScrollPane.getParent();
        parent.dispatchEvent(SwingUtilities.convertMouseEvent(tableScrollPane, e, parent));
      }
    });

    tablePanel.add(tableScrollPane, BorderLayout.CENTER);
    centerPanel.add(tablePanel);

    JScrollPane scrollPane = new JScrollPane(centerPanel);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    add(scrollPane, BorderLayout.CENTER);

    // Panel du bas contenant les boutons de modification et de suppression
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));

    controleur = new ControleurConsultationLocataire(fenetre, this, proprietaire, locataire);

    JButton btnSupprimer = new JButton("Supprimer le locataire");
    btnSupprimer.setFont(new Font("Rockwell", Font.BOLD, 14));
    btnSupprimer.setBackground(Color.RED);
    btnSupprimer.setForeground(Color.WHITE);
    btnSupprimer.addActionListener(controleur);
    buttonsPanel.add(btnSupprimer);

    JButton btnModifier = new JButton("Modifier le locataire");
    btnModifier.setFont(new Font("Rockwell", Font.BOLD, 14));
    btnModifier.setBackground(Color.BLUE);
    btnModifier.setForeground(Color.WHITE);
    btnModifier.addActionListener(controleur);
    buttonsPanel.add(btnModifier);

    add(buttonsPanel, BorderLayout.SOUTH);
  }
}
