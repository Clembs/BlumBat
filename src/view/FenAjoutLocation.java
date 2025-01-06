package view;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.ControleurAjoutLocation;
import model.BienImmobilier;
import model.Locataire;
import model.Proprietaire;

public class FenAjoutLocation extends JFrame {
  private static final long serialVersionUID = 1L;
  private JTextField loyerField;
  private JTextField dateEntreeField;
  private JTextField dateSortieField;
  private List<Locataire> locataires;

  public FenAjoutLocation(FenBiens fenetre, BienImmobilier bien, Proprietaire proprietaire) {
    ControleurAjoutLocation controleur = new ControleurAjoutLocation(this, bien, proprietaire);

    setBounds(100, 100, 600, 500);
    setTitle("Louer un bien");
    setResizable(true);

    JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
    contentPane.setLayout(new BorderLayout(15, 15));
    contentPane.setBackground(new Color(45, 45, 60));
    setContentPane(contentPane);

    JLabel lblTitre = new JLabel("Louer");
    lblTitre.setFont(new Font("SansSerif", Font.BOLD, 26));
    lblTitre.setForeground(Color.WHITE);
    lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    contentPane.add(lblTitre, BorderLayout.NORTH);

    JPanel panelCenter = new JPanel(new GridLayout(5, 2, 10, 10));
    panelCenter.setBackground(new Color(50, 50, 70));
    panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    contentPane.add(panelCenter, BorderLayout.CENTER);

    JLabel lblLoyer = new JLabel("Loyer (€) :");
    lblLoyer.setFont(new Font("SansSerif", Font.PLAIN, 14));
    lblLoyer.setForeground(Color.WHITE);
    panelCenter.add(lblLoyer);

    loyerField = new JTextField();
    loyerField.setFont(new Font("SansSerif", Font.PLAIN, 14));
    loyerField.setBackground(new Color(60, 60, 75));
    loyerField.setForeground(Color.WHITE);
    loyerField.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100)));
    panelCenter.add(loyerField);

    JLabel lblDateEntree = new JLabel("Date d'entrée (dd/MM/yyyy) :");
    lblDateEntree.setFont(new Font("SansSerif", Font.PLAIN, 14));
    lblDateEntree.setForeground(Color.WHITE);
    panelCenter.add(lblDateEntree);

    dateEntreeField = new JTextField();
    dateEntreeField.setFont(new Font("SansSerif", Font.PLAIN, 14));
    dateEntreeField.setBackground(new Color(60, 60, 75));
    dateEntreeField.setForeground(Color.WHITE);
    dateEntreeField.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100)));
    panelCenter.add(dateEntreeField);

    JLabel lblDateSortie = new JLabel("Date de sortie (dd/MM/yyyy) (facultatif) :");
    lblDateSortie.setFont(new Font("SansSerif", Font.PLAIN, 14));
    lblDateSortie.setForeground(Color.WHITE);
    panelCenter.add(lblDateSortie);

    dateSortieField = new JTextField();
    dateSortieField.setFont(new Font("SansSerif", Font.PLAIN, 14));
    dateSortieField.setBackground(new Color(60, 60, 75));
    dateSortieField.setForeground(Color.WHITE);
    dateSortieField.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100)));
    panelCenter.add(dateSortieField);

    JLabel lblLocataires = new JLabel("Locataires :");
    lblLocataires.setFont(new Font("SansSerif", Font.PLAIN, 14));
    lblLocataires.setForeground(Color.WHITE);
    panelCenter.add(lblLocataires);

    JButton btnSelectionner = new JButton("Sélectionner");
    btnSelectionner.setForeground(Color.WHITE);
    btnSelectionner.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnSelectionner.setFocusPainted(false);
    btnSelectionner.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    btnSelectionner.setBackground(new Color(0, 170, 85));
    panelCenter.add(btnSelectionner);
    btnSelectionner.addActionListener(controleur);

    JPanel panelFooter = new JPanel();
    panelFooter.setBackground(new Color(45, 45, 60));
    contentPane.add(panelFooter, BorderLayout.SOUTH);
    panelFooter.setLayout(new BorderLayout(0, 0));

    JPanel panelBtn = new JPanel();
    panelBtn.setBackground(new Color(45, 45, 60));
    panelFooter.add(panelBtn, BorderLayout.EAST);

    JButton btnLouer = new JButton("Louer");
    btnLouer.setForeground(Color.WHITE);
    btnLouer.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnLouer.setFocusPainted(false);
    btnLouer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    btnLouer.setBackground(new Color(0, 170, 85));
    panelBtn.add(btnLouer);
    btnLouer.addActionListener(controleur);
  }

  public List<Locataire> getLocataires() {
    return this.locataires;
  }

  public void setLocataires(List<Locataire> locataires) {
    this.locataires = locataires;
  }

  public double getLoyer() {
    try {
      return Double.parseDouble(loyerField.getText().trim());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Le loyer doit être un nombre valide.", e);
    }
  }

  public LocalDate getDateEntree() throws java.text.ParseException {
    return parseDate(dateEntreeField.getText().trim(), "Date d'entrée invalide. Utilisez le format dd/MM/yyyy.");
  }

  public LocalDate getDateSortie() throws java.text.ParseException {
    return parseDate(dateSortieField.getText().trim(), "Date de sortie invalide. Utilisez le format dd/MM/yyyy.");
  }

  private LocalDate parseDate(String dateStr, String errorMessage) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    try {
      return LocalDate.parse(dateStr, formatter);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException(errorMessage, e);
    }
  }

}
