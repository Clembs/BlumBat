package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// import controller.ControleurConsultationBien;
import model.BienImmobilier;

public class PanelConsultationBien extends JPanel {
  // private ControleurConsultationBien controleur;
  // private FenBiens fenBiens;
  // private BienImmobilier bien;

  public PanelConsultationBien(FenBiens fenetre, BienImmobilier bien) {
    // this.fenBiens = fenetre;
    // this.bien = bien;
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
    centerPanel.setLayout(new GridLayout(6, 2, 10, 10));
    // centerPanel.setBorder(new EmptyBorder(10, 5, 10, 5));

    JLabel lblIdKey = new JLabel("Identifiant :");
    lblIdKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblIdKey.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblIdKey);

    JLabel lblIdValue = new JLabel(bien.getId());
    lblIdValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblIdValue.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblIdValue);

    JLabel lblTypeKey = new JLabel("Type :");
    lblTypeKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblTypeKey.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblTypeKey);

    JLabel lblTypeValue = new JLabel(bien.getTypeBien().toString());
    lblTypeValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblTypeValue.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblTypeValue);

    JLabel lblAdresseKey = new JLabel("Adresse :");
    lblAdresseKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblAdresseKey.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblAdresseKey);

    JLabel lblAdresseValue = new JLabel(bien.getAdresse());
    lblAdresseValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblAdresseValue.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblAdresseValue);

    JLabel lblComplementAdresseKey = new JLabel("Complément d'adresse :");
    lblComplementAdresseKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblComplementAdresseKey.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblComplementAdresseKey);

    JLabel lblComplementAdresseValue = new JLabel(bien.getComplementAdresse());
    lblComplementAdresseValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblComplementAdresseValue.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblComplementAdresseValue);

    JLabel lblVilleKey = new JLabel("Ville :");
    lblVilleKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblVilleKey.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblVilleKey);

    JLabel lblVilleValue = new JLabel(bien.getVille());
    lblVilleValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblVilleValue.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblVilleValue);

    JLabel lblCodePostalKey = new JLabel("Code postal :");
    lblCodePostalKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblCodePostalKey.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblCodePostalKey);

    JLabel lblCodePostalValue = new JLabel(bien.getCodePostal());
    lblCodePostalValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblCodePostalValue.setHorizontalAlignment(SwingConstants.LEFT);
    centerPanel.add(lblCodePostalValue);

    JScrollPane scrollPane = new JScrollPane(centerPanel);
    centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    add(scrollPane, BorderLayout.CENTER);

    // Panel du bas contenant les boutons de modification et de suppression
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));

    JButton btnSupprimer = new JButton("Supprimer le bien");
    btnSupprimer.setFont(new Font("Rockwell", Font.BOLD, 14));
    btnSupprimer.setBackground(Color.RED);
    btnSupprimer.setForeground(Color.WHITE);
    buttonsPanel.add(btnSupprimer);

    JButton btnModifier = new JButton("Modifier le bien");
    btnModifier.setFont(new Font("Rockwell", Font.BOLD, 14));
    btnModifier.setBackground(Color.BLUE);
    btnModifier.setForeground(Color.WHITE);
    buttonsPanel.add(btnModifier);

    add(buttonsPanel, BorderLayout.SOUTH);
  }
}
