package view;

import controller.ControleurLocataires;
import model.Locataire;
import model.Proprietaire;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FenLocataires extends JFrame {
  private static final long serialVersionUID = 1L;
  private ControleurLocataires controleur;
  private List<Locataire> locataires;
  private JList<String> locatairesList;
  private JPanel panelCentralCourant;
  private DefaultListModel<String> model;

  public FenLocataires(Proprietaire proprietaire) {
    setTitle("Gestion des locataires");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 720, 500);

    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    setContentPane(mainPanel);

    JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    titlePanel.setBackground(Color.DARK_GRAY);
    JLabel lblTitle = new JLabel("Gestion des locataires");
    lblTitle.setFont(new Font("Rockwell", Font.BOLD, 24));
    lblTitle.setForeground(Color.WHITE);
    titlePanel.add(lblTitle);
    mainPanel.add(titlePanel, BorderLayout.NORTH);

    // Initialisation du controleur
    controleur = new ControleurLocataires(proprietaire, this);

    // Panel latéral, contenant la liste des locataires
    JPanel sidePanel = new JPanel(new BorderLayout(5, 5));
    sidePanel.setBorder(
        new TitledBorder(new EtchedBorder(), "Liste des locataires", TitledBorder.CENTER, TitledBorder.TOP));

    locatairesList = new JList<>(model);
    locatairesList.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
    locatairesList.setBackground(Color.LIGHT_GRAY);

    // Lorsqu'on clique sur un locataire
    locatairesList.addListSelectionListener(controleur);

    JScrollPane scrollPane = new JScrollPane(locatairesList);
    sidePanel.add(scrollPane, BorderLayout.CENTER);

    // Panel (Bouttons)
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
    JButton btnAdd = new JButton("Ajouter");
    btnAdd.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
    btnAdd.setBackground(new Color(46, 139, 87));
    btnAdd.setForeground(Color.WHITE);
    buttonPanel.add(btnAdd);
    sidePanel.add(buttonPanel, BorderLayout.SOUTH);

    // Lorsqu'on clique sur le bouton "Ajouter"
    btnAdd.addActionListener(controleur);

    mainPanel.add(sidePanel, BorderLayout.WEST);

    // Panel central, initialisé avec un panel qui incite à sélectionner un
    // locataire
    JPanel centralPanel = new JPanel(new BorderLayout(5, 5));

    JLabel lblChoix = new JLabel("Choisissez un locataire pour afficher ses détails");
    lblChoix.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
    lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
    centralPanel.add(lblChoix);

    setPanelCentral(centralPanel);
  }

  // Changer le panel central
  public void setPanelCentral(JPanel panel) {
    if (panelCentralCourant != null) {
      panelCentralCourant.setVisible(false);
      remove(panelCentralCourant);
    }

    panelCentralCourant = panel;
    add(panelCentralCourant, BorderLayout.CENTER);
    panelCentralCourant.setVisible(true);
  }

  // Mettre à jour la liste des locataires
  public void setLocataires(List<Locataire> locataires) {
    this.locataires = locataires;
    model = new DefaultListModel<String>();

    for (Locataire locataire : locataires) {
      System.out.println(locataire.getNom() + " " + locataire.getPrenom());

      model.addElement(locataire.getNom() + " " + locataire.getPrenom());
    }

    // locatairesList.setModel(model);
    // locatairesList.setSelectedIndex(0);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        Proprietaire proprietaire = new Proprietaire(1, "VOISIN", "Clément", "clembs@clembs.com", "1234567890");

        FenLocataires frame = new FenLocataires(proprietaire);
        frame.setVisible(true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

}
