package components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Bouton extends JButton {
  private VarianteButton variante;

  public Bouton(String text) {
    this(text, VarianteButton.PRIMARY);
  }

  public Bouton(String text, VarianteButton variante) {
    super(text);
    this.variante = variante;


    // Paramètres de style du bouton
    this.setFont(new Font("Arial", Font.BOLD, 14));
    this.setFocusPainted(false);
    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    this.setOpaque(false);
    this.setContentAreaFilled(false);
    this.setBorderPainted(false);
    this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    this.setForeground(Color.WHITE);

    // Couleurs par défaut
    this.setBackground(this.variante.getBackgroundColor());

    // Ajout des effets au survol
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent evt) {
        Bouton button = (Bouton) evt.getSource();
        button.setBackground(button.variante.getBackgroundHoverColor());
      }

      @Override
      public void mouseExited(MouseEvent evt) {
        Bouton button = (Bouton) evt.getSource();
        button.setBackground(button.variante.getBackgroundColor());
      }

      @Override
      public void mousePressed(MouseEvent evt) {
        Bouton button = (Bouton) evt.getSource();
        button.setBackground(button.variante.getBackgroundPressedColor());
      }

      @Override
      public void mouseReleased(MouseEvent evt) {
        Bouton button = (Bouton) evt.getSource();
        button.setBackground(button.variante.getBackgroundHoverColor());
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    // Dessin avec des bordures arrondies et ombres
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    // Ombre légère
    g2.setColor(new Color(0, 0, 0, 50));
    g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 20, 20);
    // Fond du bouton
    g2.setColor(getBackground());
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
    super.paintComponent(g);
  }

  public enum VarianteButton {
    PRIMARY, SECONDARY, SUCCESS, DANGER;

    public Color getBackgroundColor() {
      switch (this) {
        case PRIMARY:
          return new Color(0x4682b4); // Bleu
        case SECONDARY:
          return new Color(0x828282); // Gris
        case SUCCESS:
          return new Color(0x00aa55); // Vert
        case DANGER:
          return new Color(0xc83232); // Rouge
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

    public Color getBackgroundPressedColor() {
      switch (this) {
        case PRIMARY:
          return new Color(0x2C4D6E);
        case SECONDARY:
          return new Color(0x4F4F4F);
        case SUCCESS:
          return new Color(0x00522A);
        case DANGER:
          return new Color(0x6E1E1E);
        default:
          return new Color(0x2C4D6E);
      }
    }
  }
}
