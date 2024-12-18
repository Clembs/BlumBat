package view;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import controller.ControleurConsultationBien;
import model.BienImmobilier;
import model.BienLocatif;
import model.Locataire;
import model.Location;
import model.Proprietaire;

public class PanelConsultationBien extends JPanel {
  private static final long serialVersionUID = 1L;
  private ControleurConsultationBien controleur;

  public PanelConsultationBien(FenBiens fenetre, Proprietaire proprietaire, BienImmobilier bien) {
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

    JLabel lblIdKey = new JLabel("Identifiant :");
    lblIdKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblIdKey.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblIdKey);

    JLabel lblIdValue = new JLabel(bien.getId());
    lblIdValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblIdValue.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblIdValue);

    JLabel lblTypeKey = new JLabel("Type :");
    lblTypeKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblTypeKey.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblTypeKey);

    JLabel lblTypeValue = new JLabel(bien.getTypeBien().toString());
    lblTypeValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblTypeValue.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblTypeValue);

    JLabel lblAdresseKey = new JLabel("Adresse :");
    lblAdresseKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblAdresseKey.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblAdresseKey);

    JLabel lblAdresseValue = new JLabel(bien.getAdresse());
    lblAdresseValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblAdresseValue.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblAdresseValue);

    JLabel lblComplementAdresseKey = new JLabel("Complément d'adresse :");
    lblComplementAdresseKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblComplementAdresseKey.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblComplementAdresseKey);

    JLabel lblComplementAdresseValue = new JLabel(bien.getComplementAdresse());
    lblComplementAdresseValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblComplementAdresseValue.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblComplementAdresseValue);

    JLabel lblVilleKey = new JLabel("Ville :");
    lblVilleKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblVilleKey.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblVilleKey);

    JLabel lblVilleValue = new JLabel(bien.getVille());
    lblVilleValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblVilleValue.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblVilleValue);

    JLabel lblCodePostalKey = new JLabel("Code postal :");
    lblCodePostalKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblCodePostalKey.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblCodePostalKey);

    JLabel lblCodePostalValue = new JLabel(bien.getCodePostal());
    lblCodePostalValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblCodePostalValue.setHorizontalAlignment(SwingConstants.LEFT);
    champsPanel.add(lblCodePostalValue);

    if (bien instanceof BienLocatif) {
      BienLocatif bienL = (BienLocatif) bien;

      JLabel lblDisponibiliteKey = new JLabel("Disponibilité :");
      lblDisponibiliteKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
      lblDisponibiliteKey.setHorizontalAlignment(SwingConstants.LEFT);
      champsPanel.add(lblDisponibiliteKey);

      String dDisponibiliteValue;
      if (bienL.estLoué()) {
        dDisponibiliteValue = "En location depuis le " +
            bienL.getLocationsCourantes().get(0).getDateEntree()
                .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH));
      } else {
        dDisponibiliteValue = "Disponible";
      }
      JLabel lblDisponibiliteValue = new JLabel(dDisponibiliteValue);
      lblDisponibiliteValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
      lblDisponibiliteValue.setHorizontalAlignment(SwingConstants.LEFT);
      champsPanel.add(lblDisponibiliteValue);

      JLabel lblSurfaceKey = new JLabel("Surface :");
      lblSurfaceKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
      lblSurfaceKey.setHorizontalAlignment(SwingConstants.LEFT);
      champsPanel.add(lblSurfaceKey);

      JLabel lblSurfaceValue = new JLabel(bienL.getSurface() + " m²");
      lblSurfaceValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
      lblSurfaceValue.setHorizontalAlignment(SwingConstants.LEFT);
      champsPanel.add(lblSurfaceValue);

      JLabel lblNombrePiecesKey = new JLabel("Nombre de pièces :");
      lblNombrePiecesKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
      lblNombrePiecesKey.setHorizontalAlignment(SwingConstants.LEFT);
      champsPanel.add(lblNombrePiecesKey);

      JLabel lblNombrePiecesValue = new JLabel(String.valueOf(bienL.getNombrePieces()));
      lblNombrePiecesValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
      lblNombrePiecesValue.setHorizontalAlignment(SwingConstants.LEFT);
      champsPanel.add(lblNombrePiecesValue);

      JLabel lblNumeroFiscalKey = new JLabel("Numéro fiscal :");
      lblNumeroFiscalKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
      lblNumeroFiscalKey.setHorizontalAlignment(SwingConstants.LEFT);
      champsPanel.add(lblNumeroFiscalKey);

      JLabel lblNumeroFiscalValue = new JLabel(bienL.getNumeroFiscal());
      lblNumeroFiscalValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
      lblNumeroFiscalValue.setHorizontalAlignment(SwingConstants.LEFT);
      champsPanel.add(lblNumeroFiscalValue);
    }

    centerPanel.add(champsPanel);

    if (bien instanceof BienLocatif) {
      BienLocatif bienL = (BienLocatif) bien;

      JLabel lblLocataires = new JLabel("Locataires actuels :");
      lblLocataires.setFont(new Font("Rockwell", Font.BOLD, 16));
      lblLocataires.setHorizontalAlignment(SwingConstants.LEFT);
      centerPanel.add(lblLocataires);

      // Liste des locataires actuels
      if (bienL.estLoué()) {
        List<Location> locatairesCourants = bienL.getLocationsCourantes();

        DefaultTableModel tableLocationsModel = new DefaultTableModel(
            new Object[] { "ID", "Nom", "Téléphone", "Mail", "Date d'entrée", "Loyer" }, 0);

        JTable tableLocations = new JTable(tableLocationsModel);
        tableLocations.setRowSelectionAllowed(true);
        tableLocations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // On rend la table non-éditable
        tableLocations.setDefaultEditor(Object.class, null);
        // On définit la largeur des colonnes
        tableLocations.getColumnModel().getColumn(0).setPreferredWidth(20); // ID
        tableLocations.getColumnModel().getColumn(1).setPreferredWidth(80); // Nom
        tableLocations.getColumnModel().getColumn(2).setPreferredWidth(10); // Téléphone
        tableLocations.getColumnModel().getColumn(3).setPreferredWidth(20); // Mail
        tableLocations.getColumnModel().getColumn(4).setPreferredWidth(20); // Date d'entrée
        tableLocations.getColumnModel().getColumn(5).setPreferredWidth(5); // Loyer

        for (Location location : locatairesCourants) {
          Locataire locataire = location.getLocataire();
          tableLocationsModel.addRow(new Object[] {
              locataire.getId(),
              locataire.getNom() + " " + locataire.getPrenom(),
              locataire.getEmail(),
              locataire.getTelephone(),
              location.getDateEntree()
                  .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
              location.getLoyer() + " €"
          });
        }

        JScrollPane locationsScrollPane = new JScrollPane(tableLocations);
        locationsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        locationsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        // petit fix : on propage l'événement de la molette de la souris au parent
        // sinon le scroll du centerPanel est hijacké par le scrollPane de la table
        // et on perd en UX
        locationsScrollPane.addMouseWheelListener(new MouseWheelListener() {
          @Override
          public void mouseWheelMoved(MouseWheelEvent e) {
            Component parent = locationsScrollPane.getParent();
            parent.dispatchEvent(SwingUtilities.convertMouseEvent(locationsScrollPane, e, parent));
          }
        });

        centerPanel.add(locationsScrollPane);
      } else {
        JLabel lblAucunLocataire = new JLabel("Aucun locataire actuel.");
        lblAucunLocataire.setFont(new Font("Rockwell", Font.PLAIN, 14));
        lblAucunLocataire.setHorizontalAlignment(SwingConstants.LEFT);
        centerPanel.add(lblAucunLocataire);
      }

      // Liste des locataires passés
      JLabel lblHistorique = new JLabel("Historique des locataires :");
      lblHistorique.setFont(new Font("Rockwell", Font.BOLD, 16));
      lblHistorique.setHorizontalAlignment(SwingConstants.LEFT);
      centerPanel.add(lblHistorique);

      List<Location> locationsPassees = bienL.getLocationsPassees();

      DefaultTableModel tableLocationsPasseesModel = new DefaultTableModel(
          new Object[] { "ID", "Nom", "Téléphone", "Mail", "Date d'entrée", "Date de sortie", "Loyer" }, 0);

      JTable tableLocationsPassees = new JTable(tableLocationsPasseesModel);
      tableLocationsPassees.setRowSelectionAllowed(true);
      tableLocationsPassees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      // On rend la table non-éditable
      tableLocationsPassees.setDefaultEditor(Object.class, null);
      // On définit la largeur des colonnes
      tableLocationsPassees.getColumnModel().getColumn(0).setPreferredWidth(20); // ID
      tableLocationsPassees.getColumnModel().getColumn(1).setPreferredWidth(80); // Nom
      tableLocationsPassees.getColumnModel().getColumn(2).setPreferredWidth(10); // Téléphone
      tableLocationsPassees.getColumnModel().getColumn(3).setPreferredWidth(20); // Mail
      tableLocationsPassees.getColumnModel().getColumn(4).setPreferredWidth(20); // Date d'entrée
      tableLocationsPassees.getColumnModel().getColumn(5).setPreferredWidth(20); // Date de sortie
      tableLocationsPassees.getColumnModel().getColumn(6).setPreferredWidth(5); // Loyer

      for (Location location : locationsPassees) {
        Locataire locataire = location.getLocataire();
        tableLocationsPasseesModel.addRow(new Object[] {
            locataire.getId(),
            locataire.getNom() + " " + locataire.getPrenom(),
            locataire.getEmail(),
            locataire.getTelephone(),
            location.getDateEntree()
                .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
            location.getDateSortie()
                .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH)),
            location.getLoyer() + " €"
        });
      }

      JScrollPane locationsPasseesScrollPane = new JScrollPane(tableLocationsPassees);
      locationsPasseesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      locationsPasseesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

      locationsPasseesScrollPane.addMouseWheelListener(new MouseWheelListener() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
          Component parent = locationsPasseesScrollPane.getParent();
          parent.dispatchEvent(SwingUtilities.convertMouseEvent(locationsPasseesScrollPane, e, parent));
        }
      });

      centerPanel.add(locationsPasseesScrollPane);
    }

    JScrollPane scrollPane = new JScrollPane(centerPanel);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    add(scrollPane, BorderLayout.CENTER);

    // Panel du bas contenant les boutons de modification et de suppression
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));

    controleur = new ControleurConsultationBien(fenetre, this, proprietaire, bien);

    JButton btnLouer = new JButton("Louer");
    btnLouer.setFont(new Font("Rockwell", Font.BOLD, 14));
    btnLouer.setBackground(Color.GRAY);
    btnLouer.setForeground(Color.WHITE);
    btnLouer.addActionListener(controleur);
    buttonsPanel.add(btnLouer);

    JButton btnSupprimer = new JButton("Supprimer le bien");
    btnSupprimer.setFont(new Font("Rockwell", Font.BOLD, 14));
    btnSupprimer.setBackground(Color.RED);
    btnSupprimer.setForeground(Color.WHITE);
    btnSupprimer.addActionListener(controleur);
    buttonsPanel.add(btnSupprimer);

    JButton btnModifier = new JButton("Modifier le bien");
    btnModifier.setFont(new Font("Rockwell", Font.BOLD, 14));
    btnModifier.setBackground(Color.BLUE);
    btnModifier.setForeground(Color.WHITE);
    btnModifier.addActionListener(controleur);
    buttonsPanel.add(btnModifier);

    add(buttonsPanel, BorderLayout.SOUTH);
  }
}
