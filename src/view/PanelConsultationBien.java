package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import controller.ControleurConsultationBien;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;

public class PanelConsultationBien extends JPanel {
  private ControleurConsultationBien controleur;
  private FenBiens fenetre;
  private BienImmobilier bien;

  public PanelConsultationBien(FenBiens fenetre, Proprietaire proprietaire, BienImmobilier bien) {
    this.fenetre = fenetre;
    this.bien = bien;

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
    centerPanel.setLayout(new BorderLayout(10, 10));
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
            bien.getLocationCourante().getDateEntree()
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

    JScrollPane scrollPane = new JScrollPane(champsPanel);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    centerPanel.add(scrollPane, BorderLayout.CENTER);
    add(centerPanel, BorderLayout.CENTER);

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
