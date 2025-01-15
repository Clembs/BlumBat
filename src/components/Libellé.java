package components;

import javax.swing.*;
import java.awt.*;

public class Libellé extends JLabel {
  public Libellé(String text) {
    this(text, TypeLibellé.PARAGRAPHE, true);
  }

  public Libellé(String text, TypeLibellé type) {
    this(text, type, true);
  }

  public Libellé(String text, TypeLibellé type, boolean privé) {
    super(text);
    this.setFont(type.getFont());
    this.setForeground(type.getColor());
  }

  public enum TypeLibellé {
    /*
     * EN_TETE: Pour les gros titres (équivalent à h1)
     * TITRE: Titres lambdas (équivalent à h2/h3)
     * SOUS_TITRE: Texte sous les titres
     * PARAGRAPHE: Texte standard
     * CLEF: Utilisé dans le cas de combos clefs/valeurs (gras). Valeurs à utiliser
     * en PARAGRAPHE.
     * DANGER: Utilisé pour une erreur, un avertissement ou de confirmation (coloré
     * en rouge)
     * SUCCES: Utilisé pour un message de validation (coloré en vert)
     * SOUS_TEXTE: Texte sous les paragraphes, ou pour un texte d'importance mineure
     */

    EN_TETE, TITRE, SOUS_TITRE, PARAGRAPHE, CLEF, DANGER, SUCCES, SOUS_TEXTE;

    public Font getFont() {
      switch (this) {
        case EN_TETE:
          return Layout.POLICE_EXTRA_LARGE;
        case TITRE:
          return Layout.POLICE_LARGE;
        case SOUS_TITRE:
          return Layout.POLICE_MEDIUM;
        case CLEF:
          return Layout.POLICE_REGULAR_BOLD;
        case PARAGRAPHE:
        case DANGER:
        case SUCCES:
        default:
          return Layout.POLICE_REGULAR;
        case SOUS_TEXTE:
          return Layout.POLICE_SMALL;
      }
    }

    public Color getColor() {
      switch (this) {
        case EN_TETE:
        case TITRE:
          return Layout.COULEUR_EN_TETE;
        case SOUS_TEXTE:
        case SOUS_TITRE:
          return Layout.COULEUR_SOUS_TEXTE;
        case PARAGRAPHE:
        case CLEF:
        default:
          return Layout.COULEUR_PARAGRAPHE;
        case DANGER:
          return Layout.COULEUR_DANGER;
        case SUCCES:
          return Layout.COULEUR_SUCCES;
      }
    }
  }
}
