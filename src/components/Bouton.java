package components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Bouton extends JButton {
  private VarianteButton variante;

  public Bouton(String text) {
    this(text, VarianteButton.PRIMAIRE, false);
  }

  public Bouton(String text, VarianteButton variante) {
    this(text, variante, false);
  }

  // constructeur privé appelé par les deux constructeurs publiques
  // (pour définir une variante par défaut et éviter de répéter le code)
  private Bouton(String text, VarianteButton variante, boolean privé) {
    super(text);
    this.variante = variante;

    // Paramètres de style du bouton
    this.setFont(Layout.POLICE_REGULAR);
    this.setFocusPainted(false);
    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    this.setOpaque(false);
    this.setContentAreaFilled(false);
    this.setBorderPainted(false);
    this.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
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
    g2.setColor(Layout.COULEUR_OMBRES);
    g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 20, 20);
    // Fond du bouton
    g2.setColor(getBackground());
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
    super.paintComponent(g);
  }

  public enum VarianteButton {
    PRIMAIRE, SECONDAIRE, SUCCES, DANGER;

    public Color getBackgroundColor() {
      switch (this) {
        case PRIMAIRE:
          return Layout.COULEUR_PRIMAIRE;
        case SECONDAIRE:
          return Layout.COULEUR_SECONDAIRE;
        case SUCCES:
          return Layout.COULEUR_SUCCES;
        case DANGER:
          return Layout.COULEUR_DANGER;
        default:
          return Layout.COULEUR_PRIMAIRE;
      }
    }

    public Color getBackgroundHoverColor() {
      switch (this) {
        case PRIMAIRE:
          return Layout.COULEUR_PRIMAIRE_VARIANTE;
        case SECONDAIRE:
          return Layout.COULEUR_SECONDAIRE_VARIANTE;
        case SUCCES:
          return Layout.COULEUR_SUCCES_VARIANTE;
        case DANGER:
          return Layout.COULEUR_DANGER_VARIANTE;
        default:
          return Layout.COULEUR_PRIMAIRE_VARIANTE;
      }
    }

    public Color getBackgroundPressedColor() {
      switch (this) {
        case PRIMAIRE:
          return Layout.COULEUR_PRIMAIRE_SOMBRE;
        case SECONDAIRE:
          return Layout.COULEUR_SECONDAIRE_SOMBRE;
        case SUCCES:
          return Layout.COULEUR_SUCCES_SOMBRE;
        case DANGER:
          return Layout.COULEUR_DANGER_SOMBRE;
        default:
          return Layout.COULEUR_PRIMAIRE_SOMBRE;
      }
    }
  }
}
