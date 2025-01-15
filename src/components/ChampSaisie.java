package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import components.Libellé.TypeLibellé;

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
    this.setLayout(new BorderLayout(0, 8));
    this.setOpaque(false); // Permet de personnaliser le fond

    label = new Libellé(libellé, TypeLibellé.CLEF);
    this.add(label, BorderLayout.NORTH);
  }

  private void addChampSaisie(JComponent composant) {
    this.champSaisie = composant;
    composant.setBorder(BorderFactory.createCompoundBorder(
        new LineBorder(Layout.COULEUR_PRIMAIRE_SOMBRE),
        new EmptyBorder(8, 12, 8, 12)));
    composant.setPreferredSize(new Dimension(180, 40));
    composant.setFont(Layout.POLICE_SMALL);
    composant.setBackground(Color.WHITE);

    composant.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        composant.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Layout.COULEUR_PRIMAIRE),
            new EmptyBorder(8, 12, 8, 12)));
      }

      @Override
      public void focusLost(FocusEvent e) {
        composant.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Layout.COULEUR_PRIMAIRE_SOMBRE),
            new EmptyBorder(8, 12, 8, 12)));
      }
    });

    this.add(composant, BorderLayout.CENTER);
  }

  public JComponent getChampSaisie() {
    return this.champSaisie;
  }

  public JLabel getLabel() {
    return this.label;
  }

  // récupère la valeur du champ (un String pour JTextField/JPasswordField, un
  // int/float/double/peut importe pour JSpinner)
  @SuppressWarnings("unchecked")
  public <T> T getValue() {
    if (this.champSaisie instanceof JTextField) {
      return (T) ((JTextField) this.champSaisie).getText();
    } else if (this.champSaisie instanceof JPasswordField) {
      return (T) new String(((JPasswordField) this.champSaisie).getPassword());
    } else if (this.champSaisie instanceof JSpinner) {
      return (T) ((JSpinner) this.champSaisie).getValue();
    }
    return null;
  }
}
