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
  private List<Locataire> locataires;
  private JList<String> listeLocataires;
  private JPanel panelCentralCourant;

  // la fenêtre de consultation & sélection des locataires
  // on peut optionnellement passer une fenêtre d'ajout de location pour
  // sélectionner un ou plusieurs locataires pour une location
  public FenLocataires(Proprietaire proprietaire, FenAjoutLocation fenAjoutLocation) {
    boolean modeSelection = fenAjoutLocation != null;

    setTitle("Gestion des locataires");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    // Panel latéral, contenant la liste des locataires
    JPanel sidePanel = new JPanel(new BorderLayout(5, 5));
    sidePanel.setBorder(
        new TitledBorder(new EtchedBorder(), "Vos locataires", TitledBorder.CENTER, TitledBorder.TOP));

    DefaultListModel<String> model = new DefaultListModel<String>();
    this.listeLocataires = new JList<String>(model);
    // Autoriser la sélection multiple si on est en mode sélection
    if (modeSelection) {
      this.listeLocataires.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    this.listeLocataires.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
    this.listeLocataires.setBackground(Color.LIGHT_GRAY);

    // Initialisation du controleur
    ControleurLocataires controleur = new ControleurLocataires(proprietaire, this, fenAjoutLocation);

    // Lorsqu'on clique sur un locataire
    this.listeLocataires.addListSelectionListener(controleur);

    JScrollPane scrollPane = new JScrollPane(listeLocataires);
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

    if (modeSelection) {
      JButton btnSelect = new JButton("Sélectionner");
      btnSelect.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
      btnSelect.setBackground(new Color(46, 139, 87));
      btnSelect.setForeground(Color.WHITE);
      buttonPanel.add(btnSelect);

      // Lorsqu'on clique sur le bouton "Sélectionner"
      btnSelect.addActionListener(controleur);
    }

    mainPanel.add(sidePanel, BorderLayout.WEST);

    this.resetPanelCentral(modeSelection);
  }

  public List<Locataire> getLocataires() {
    return this.locataires;
  }

  // Changer le panel central
  public void setPanelCentral(JPanel panel) {
    if (this.panelCentralCourant != null) {
      this.panelCentralCourant.setVisible(false);
      this.remove(this.panelCentralCourant);
    }

    this.panelCentralCourant = panel;
    this.add(panelCentralCourant, BorderLayout.CENTER);
    this.panelCentralCourant.setVisible(true);
  }

  // Mettre à jour la liste des locataires
  public void setLocataires(List<Locataire> locataires) {
    this.locataires = locataires;
    DefaultListModel<String> model = new DefaultListModel<String>();

    for (Locataire locataire : locataires) {
      model.addElement(locataire.getNom() + " " + locataire.getPrenom());
    }

    this.listeLocataires.setModel(model);
  }

  public void resetPanelCentral(boolean modeSelection) {
    // Panel central par défaut, affiché lorsqu'aucun locataire n'est sélectionné
    // incite à sélectionner un locataire en mode sélection

    JPanel panel = new JPanel(new BorderLayout(5, 5));

    JTextArea lblChoix = new JTextArea(modeSelection
        ? "Sélectionnez un ou plusieurs locataires pour la location.\nRestez appuyé sur Ctrl pour en sélectionner plusieurs."
        : "Choisissez un locataire pour afficher ses détails");
    lblChoix.setEditable(false);
    lblChoix.setOpaque(false);
    lblChoix.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));

    setPanelCentral(panel);
  }
}
