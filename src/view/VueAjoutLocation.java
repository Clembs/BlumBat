package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControleurAjoutLocation;
import model.BienLocatif;
import model.Locataire;
import model.Proprietaire;

public class VueAjoutLocation extends JFrame {
  private static final long serialVersionUID = 1L;
  private JSpinner loyerSpinner;
  private JTextField dateEntreeField;
  private JTextField dateSortieField;
  private List<Locataire> locataires;
  private JTable tableLocatairesSelectionnes;
  private JList<String> erreursList;
  private DefaultListModel<String> erreursListModel;

  public VueAjoutLocation(VueBiens fenBiens, BienLocatif bien, Proprietaire proprietaire) {
    ControleurAjoutLocation controleur = new ControleurAjoutLocation(this, bien, proprietaire, fenBiens);

    setBounds(100, 100, 600, 500);
    setTitle("Louer");
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

    loyerSpinner = new JSpinner();
    loyerSpinner.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
    loyerSpinner.setFont(new Font("SansSerif", Font.PLAIN, 14));
    loyerSpinner.setBackground(new Color(60, 60, 75));
    loyerSpinner.setForeground(Color.WHITE);
    loyerSpinner.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100)));
    panelCenter.add(loyerSpinner);

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

    DefaultTableModel locatairesModel = new DefaultTableModel(new Object[] { "ID", "Nom", "Prénom" }, 0);
    tableLocatairesSelectionnes = new JTable(locatairesModel);
    tableLocatairesSelectionnes.setFont(new Font("SansSerif", Font.PLAIN, 14));
    tableLocatairesSelectionnes.setBackground(new Color(60, 60, 75));
    tableLocatairesSelectionnes.setForeground(Color.WHITE);

    JScrollPane scrollPane = new JScrollPane(tableLocatairesSelectionnes);
    scrollPane.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100)));
    panelCenter.add(scrollPane);

    JPanel panelFooter = new JPanel();
    panelFooter.setBackground(new Color(45, 45, 60));
    panelFooter.setLayout(new BorderLayout(0, 0));

    erreursListModel = new DefaultListModel<String>();
    erreursList = new JList<>(erreursListModel);
    erreursList.setEnabled(false);
    erreursList.setFont(new Font("Rockwell", Font.PLAIN, 14));
    erreursList.setForeground(Color.RED);
    erreursList.setBorder(BorderFactory.createLineBorder(Color.RED));
    erreursList.setModel(erreursListModel);
    panelFooter.add(erreursList, BorderLayout.CENTER);

    JPanel panelBoutons = new JPanel();
    panelBoutons.setBackground(new Color(45, 45, 60));
    panelFooter.add(panelBoutons, BorderLayout.SOUTH);

    JButton btnLouer = new JButton("Louer");
    btnLouer.setForeground(Color.WHITE);
    btnLouer.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnLouer.setFocusPainted(false);
    btnLouer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    btnLouer.setBackground(new Color(0, 170, 85));
    panelBoutons.add(btnLouer);
    btnLouer.addActionListener(controleur);

    contentPane.add(panelFooter, BorderLayout.SOUTH);
  }

  public List<Locataire> getLocataires() {
    return this.locataires;
  }

  public void setLocataires(List<Locataire> locataires) {
    this.locataires = locataires;

    // Vider la table
    DefaultTableModel model = (DefaultTableModel) tableLocatairesSelectionnes.getModel();
    model.setRowCount(0);

    // Pour chaque locataire, ajouter une ligne dans la table
    for (Locataire locataire : locataires) {
      model.addRow(new Object[] { locataire.getId(), locataire.getNom(), locataire.getPrenom() });
    }

    // Rafraîchir la table
    tableLocatairesSelectionnes.revalidate();
    tableLocatairesSelectionnes.repaint();
  }

  public double getLoyer() {
    return (double) this.loyerSpinner.getValue();
  }

  public String getDateEntree() {
    return this.dateEntreeField.getText().trim();
  }

  public String getDateSortie() {
    return this.dateSortieField.getText().trim();
  }

  public void addErreur(String erreur) {
    erreursListModel.addElement(erreur);
    erreursList.setModel(erreursListModel);
  }

  public void clearErreurs() {
    if (erreursListModel != null) {
      erreursListModel = new DefaultListModel<String>();
    }
    erreursListModel.clear();
    erreursList.setModel(erreursListModel);
  }

  public boolean hasErreurs() {
    return erreursListModel.size() > 0;
  }
}
