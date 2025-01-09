package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Bouton extends JButton {
  private VarianteButton variante;

  public Bouton(String text) {
    this(text, VarianteButton.PRIMARY, false);
  }

  public Bouton(String text, VarianteButton variante) {
    this(text, variante, false);
  }

  // constructeur privé appelé par les deux constructeurs publiques
  // (pour définir une variante par défaut et éviter de répéter le code)
  private Bouton(String text, VarianteButton variante, boolean privé) {
    super(text);
    this.variante = variante;

    this.setFont(new Font("Arial", Font.BOLD, 14));
    this.setFocusPainted(false);
    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    this.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

    this.setBackground(this.variante.getBackgroundColor());
    this.setForeground(Color.WHITE);

    this.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent evt) {
        Bouton button = (Bouton) evt.getSource();
        button.setBackground(button.variante.getBackgroundHoverColor());
      }

      public void mouseExited(MouseEvent evt) {
        Bouton button = (Bouton) evt.getSource();
        button.setBackground(button.variante.getBackgroundColor());
      }
    });

  }

  public enum VarianteButton {
    PRIMARY, SECONDARY, SUCCESS, DANGER;

    public Color getBackgroundColor() {
      switch (this) {
        case PRIMARY:
          return new Color(0x4682b4);
        case SECONDARY:
          return new Color(0x828282);
        case SUCCESS:
          return new Color(0x00aa55);
        case DANGER:
          return new Color(0xc83232);
        default:
          return new Color(0x4682b4);
      }
    }

    public Color getBackgroundHoverColor() {
      switch (this) {
        case PRIMARY:
          return new Color(0x38678F);
        case SECONDARY:
          return new Color(0x696969);
        case SUCCESS:
          return new Color(0x00753B);
        case DANGER:
          return new Color(0x9F2828);
        default:
          return new Color(0x6496dc);
      }
    }
  }
}
