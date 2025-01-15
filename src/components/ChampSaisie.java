package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ChampSaisie extends JPanel {
  // Enumération pour définir le type de champ
  public enum TypeChamp {
    TEXTE, NOMBRE, MOT_DE_PASSE;
  }

  private JLabel label;
  private JComponent champSaisie;

  // Champ sans type explicite sans valeur
  public ChampSaisie(String libellé) {
    this(libellé, TypeChamp.TEXTE, false);

    addChampSaisie(new JTextField());
  }

  // Champ sans type explicite avec valeur
  public ChampSaisie(String libellé, String valeur) {
    this(libellé, TypeChamp.TEXTE, false);

    addChampSaisie(new JTextField(valeur));
  }

  // Champ avec un type explicite sans valeur
  public ChampSaisie(String libellé, TypeChamp typeChamp) {
    this(libellé, typeChamp, false);

    // Choix du type du champ de saisie
    switch (typeChamp) {
      case TEXTE: {
        addChampSaisie(new JTextField());
        break;
      }
      case MOT_DE_PASSE: {
        addChampSaisie(new JPasswordField());
        break;
      }
      case NOMBRE: {
        addChampSaisie(new JSpinner());
        break;
      }
    }
  }

  // Champ avec un type explicite avec valeur
  public ChampSaisie(String libellé, TypeChamp typeChamp, String valeur) {
    this(libellé, typeChamp, false);

    // Choix du type du champ de saisie
    switch (typeChamp) {
      case TEXTE: {
        addChampSaisie(new JTextField(valeur));
        break;
      }
      case MOT_DE_PASSE: {
        addChampSaisie(new JPasswordField(valeur));
        break;
      }
      case NOMBRE: {
        addChampSaisie(new JSpinner());
        break;
      }
    }
  }

  // Champ sans type explicite avec un modèle de Spinner
  public ChampSaisie(String libellé, SpinnerModel model) {
    this(libellé, TypeChamp.NOMBRE, false);

    addChampSaisie(new JSpinner(model));
  }

  // Constructeur privé qui initie le libellé
  private ChampSaisie(String libellé, TypeChamp typeChamp, boolean privé) {
    this.setLayout(new BorderLayout(0, 5));
    this.setOpaque(false); // Permet de personnaliser le fond

    // Configuration du label
    label = new JLabel(libellé);
    label.setFont(Layout.POLICE_REGULAR);
    label.setForeground(Layout.COULEUR_SECONDAIRE);

    this.add(label, BorderLayout.NORTH);
  }

  private void addChampSaisie(JComponent composant) {
    this.champSaisie = composant;
    composant.setBorder(BorderFactory.createCompoundBorder(
        new LineBorder(Layout.COULEUR_PRIMAIRE),
        new EmptyBorder(10, 10, 10, 10)));
    composant.setPreferredSize(new Dimension(180, 40));
    composant.setFont(Layout.POLICE_SMALL);
    composant.setBackground(Color.WHITE);

    composant.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        composant.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Layout.COULEUR_PRIMAIRE),
            new EmptyBorder(10, 10, 10, 10)));
      }

      @Override
      public void focusLost(FocusEvent e) {
        composant.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Layout.COULEUR_PRIMAIRE_SOMBRE),
            new EmptyBorder(10, 10, 10, 10)));
      }
    });

    this.add(composant, BorderLayout.SOUTH);
  }

  public JComponent getChampSaisie() {
    return this.champSaisie;
  }

  public JLabel getLabel() {
    return this.label;
  }
}
