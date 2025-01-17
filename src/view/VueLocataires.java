package view;

import controller.ControleurLocataires;
import model.Locataire;
import model.Proprietaire;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import components.Bouton;
import components.Layout;
import components.Libellé;
import components.Liste;
import components.Bouton.VarianteButton;
import components.Libellé.TypeLibellé;

public class VueLocataires extends JFrame {
  private static final long serialVersionUID = 1L;
  private List<Locataire> locataires;
  private Liste<String> listeLocataires;
  private JPanel panelCentralCourant;
  private Proprietaire proprietaire;

  // la fenêtre de consultation & sélection des locataires
  // on peut optionnellement passer une fenêtre d'ajout de location pour
  // sélectionner un ou plusieurs locataires pour une location
  public VueLocataires(Proprietaire proprietaire, VueAjoutLocation fenAjoutLocation) {
    this.proprietaire = proprietaire;
    boolean estSélection = fenAjoutLocation != null;

    this.setTitle(estSélection ? "Sélection de locataires" : "Gestion des locataires");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setBounds(100, 100, 1000, 500);
    this.setLayout(new BorderLayout(0, 0));

    Libellé lblTitle = new Libellé(estSélection ? "Sélectionnez un ou plusieurs locataires" : "Gestion des locataires",
        TypeLibellé.EN_TETE);
    lblTitle.setBorder(new EmptyBorder(16, 16, 16, 16));
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(lblTitle, BorderLayout.NORTH);

    // Panel latéral, contenant la liste des locataires
    JPanel sidePanel = new JPanel(new BorderLayout(0, 8));
    sidePanel.setBorder(
        new TitledBorder(new EtchedBorder(), "Vos locataires", TitledBorder.CENTER, TitledBorder.TOP));

    // Création de la liste des locataires
    this.listeLocataires = new Liste<>();
    // Autoriser la sélection multiple si on est en mode sélection
    if (estSélection) {
      this.listeLocataires.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    // Initialisation du controleur
    ControleurLocataires controleur = new ControleurLocataires(proprietaire, this, fenAjoutLocation);

    // Lorsqu'on clique sur un locataire
    this.listeLocataires.addListSelectionListener(controleur);

    JScrollPane scrollPane = new JScrollPane(listeLocataires);
    scrollPane.setPreferredSize(new Dimension(250, 0));
    sidePanel.add(scrollPane, BorderLayout.CENTER);

    JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));

    Bouton btnAdd = new Bouton("Ajouter", estSélection ? VarianteButton.SECONDAIRE : VarianteButton.PRIMAIRE);
    // Lorsqu'on clique sur le bouton "Ajouter"
    btnAdd.addActionListener(controleur);

    panelBoutons.add(btnAdd);

    if (estSélection) {
      Bouton btnSelect = new Bouton("Sélectionner");
      panelBoutons.add(btnSelect);

      // Lorsqu'on clique sur le bouton "Sélectionner"
      btnSelect.addActionListener(controleur);
    }

    sidePanel.add(panelBoutons, BorderLayout.SOUTH);
    this.add(sidePanel, BorderLayout.WEST);

    this.resetPanelCentral(estSélection);
  }

  public List<Locataire> getLocataires() {
    return this.locataires;
  }

  // mise à jour d'un locataire
  public void updateLocataire(Locataire nouveauLocataire) {
    this.locataires = this.locataires.stream()
        .map(locataireCourant -> locataireCourant.getId().equals(nouveauLocataire.getId()) ? nouveauLocataire
            : locataireCourant)
        .collect(Collectors.toList());

    // rafraîchir la liste des biens
    this.setLocataires(this.locataires);

    // rafraîchir le panel courant (s'il est en consultation) en le remplaçant
    if (this.panelCentralCourant instanceof VueConsultationLocataire) {
      VueConsultationLocataire panel = new VueConsultationLocataire(this, this.proprietaire, nouveauLocataire);

      this.setPanelCentral(panel);
    }
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

    for (Locataire locataire : locataires) {
      this.listeLocataires.addElement(locataire.getPrenom() + " " + locataire.getNom());
    }
  }

  public void resetPanelCentral(boolean modeSelection) {
    // Panel central par défaut, affiché lorsqu'aucun locataire n'est sélectionné
    // incite à sélectionner un locataire en mode sélection

    JPanel panel = new JPanel(new GridLayout());
    JTextArea lblChoix = new JTextArea(modeSelection
        ? "Sélectionnez un ou plusieurs locataires pour la location.\nRestez appuyé sur Ctrl pour en sélectionner plusieurs."
        : "Choisissez un locataire pour afficher ses détails");
    lblChoix.setEditable(false);
    lblChoix.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblChoix.setAlignmentY(Component.CENTER_ALIGNMENT);
    lblChoix.setFont(Layout.POLICE_REGULAR);
    lblChoix.setForeground(Layout.COULEUR_PARAGRAPHE);
    panel.add(lblChoix);

    this.setPanelCentral(panel);
  }

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        Proprietaire proprietaire = new Proprietaire(1, "Voisin", "Clément", "clembs@clembs.com", "truc");
        VueLocataires frame = new VueLocataires(proprietaire, null);
        frame.setVisible(true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}
