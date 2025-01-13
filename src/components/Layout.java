package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class Layout {

  // Polices
  public final static Font POLICE_TITRE = new Font("Segoe UI", Font.BOLD, 28);
  public final static Font POLICE_EN_TETE = new Font("Segoe UI", Font.BOLD, 20);
  public final static Font POLICE_SOUSTITRE = new Font("Arial", Font.ITALIC, 18);
  public final static Font POLICE_PARAGRAPHES = new Font("Arial", Font.PLAIN, 14);
  public final static Font POLICE_ERREUR_SUCCES = new Font("Arial", Font.PLAIN, 16);
  public final static Font POLICE_SOUSTITRE_GRAS = new Font("Arial", Font.BOLD, 14);

  // Couleurs
  public final static Color COULEUR_PRIMAIRE = new Color(0x4682b4);
  public final static Color COULEUR_PRIMAIRE_VARIANTE = new Color(0x38678F);
  public final static Color COULEUR_PRIMAIRE_SOMBRE = new Color(0x2C4D6E);
  public final static Color COULEUR_SECONDAIRE = new Color(0x6C757D);
  public final static Color COULEUR_SECONDAIRE_VARIANTE = new Color(0x696969);
  public final static Color COULEUR_SECONDAIRE_SOMBRE = new Color(0x4F4F4F);
  public final static Color COULEUR_SUCCES = new Color(0x388E3C);
  public final static Color COULEUR_SUCCES_VARIANTE = new Color(0x00753B);
  public final static Color COULEUR_SUCCES_SOMBRE = new Color(0x00522A);
  public final static Color COULEUR_DANGER = new Color(0xD32F2F);
  public final static Color COULEUR_DANGER_VARIANTE = new Color(0x9F2828);
  public final static Color COULEUR_DANGER_SOMBRE = new Color(0x6E1E1E);
  public final static Color COULEUR_INFO = new Color(0x0288D1);
  public final static Color COULEUR_FOND = new Color(0xF4F4F4);
  public final static Color COULEUR_TEXTE = new Color(0x333333);

  public final static Color COULEUR_OMBRES = new Color(0, 0, 0, 50);

  // Dimensions
  public final static Dimension DIMENSION_FENETRE = new Dimension(800, 600);

  // Espacement
  public final static int ESPACEMENT_DEFAUT = 10;
  public final static int ESPACEMENT_BORDS = 20;
}
