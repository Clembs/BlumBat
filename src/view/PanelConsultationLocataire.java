package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

// import controller.ControleurConsultationLocataire;
import model.Locataire;
import model.Proprietaire;

public class PanelConsultationLocataire extends JPanel {
  private static final long serialVersionUID = 1L;
  // private ControleurConsultationLocataaire controleur;

  public PanelConsultationLocataire(FenLocataires fenetre, Proprietaire proprietaire, Locataire locataire) {
    setLayout(new GridLayout(4, 2, 5, 5));
    setBorder(new TitledBorder(new EtchedBorder(), "Informations Personnelles", TitledBorder.CENTER,
        TitledBorder.TOP));
    setBackground(Color.LIGHT_GRAY);

    JLabel lblPrenomKey = new JLabel("Prénom :", SwingConstants.LEFT);
    lblPrenomKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    add(lblPrenomKey);

    JLabel lblPrenomValue = new JLabel(locataire.getPrenom(), SwingConstants.LEFT);
    lblPrenomValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    add(lblPrenomValue);

    JLabel lblNomKey = new JLabel("Nom :", SwingConstants.LEFT);
    lblNomKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    add(lblNomKey);

    JLabel lblNomValue = new JLabel(locataire.getNom(), SwingConstants.LEFT);
    lblNomValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    add(lblNomValue);

    JLabel lblTelephoneKey = new JLabel("Téléphone :", SwingConstants.LEFT);
    lblTelephoneKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    add(lblTelephoneKey);

    JLabel lblTelephoneValue = new JLabel(locataire.getTelephone(), SwingConstants.LEFT);
    lblTelephoneValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    add(lblTelephoneValue);

    JLabel lblEmailKey = new JLabel("Adresse email :", SwingConstants.LEFT);
    lblEmailKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    add(lblEmailKey);

    JLabel lblEmailValue = new JLabel(locataire.getEmail(), SwingConstants.LEFT);
    lblEmailValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    add(lblEmailValue);

    // Panel (Liste des biens)
    JPanel TablePanel = new JPanel(new BorderLayout());
    TablePanel.setBackground(Color.LIGHT_GRAY);
    TablePanel.setBorder(
        new TitledBorder(new EtchedBorder(), "Liste des biens", TitledBorder.CENTER, TitledBorder.TOP));

    JTable tableBiens = new JTable(new DefaultTableModel(
        new Object[][] {
            { "Bien 1", "Adresse 1", "Type 1" },
            { "Bien 2", "Adresse 2", "Type 2" }
        },
        new String[] { "Nom du Bien", "Adresse", "Type" }));
    tableBiens.setFont(new Font("Rockwell", Font.PLAIN, 12));

    JScrollPane tableScrollPane = new JScrollPane(tableBiens);
    TablePanel.add(tableScrollPane, BorderLayout.CENTER);
  }
}
