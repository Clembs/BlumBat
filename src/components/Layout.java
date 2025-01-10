package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class Layout {

    //Polices
    public final static Font POLICE_TITRE = new Font("Segoe UI", Font.BOLD, 28);
    public final static Font POLICE_EN_TETE = new Font("Segoe UI", Font.BOLD, 20);
    public final static Font POLICE_SOUSTITRE = new Font("Arial", Font.ITALIC, 18);
    public final static Font POLICE_PARAGRAPHES = new Font("Arial", Font.PLAIN, 14);
    public final static Font POLICE_ERREUR_SUCCES = new Font("Arial", Font.PLAIN, 16);

    //Couleurs
    public final static Color COULEUR_PRIMAIRE = new Color(0x4682b4);
    public final static Color COULEUR_SECONDAIRE = new Color(0x6C757D);
    public final static Color COULEUR_ERREUR = new Color(0xD32F2F);
    public final static Color COULEUR_SUCCES = new Color(0x388E3C);
    public final static Color COULEUR_INFO = new Color(0x0288D1);
    public final static Color COULEUR_FOND = new Color(0xF4F4F4);
    public final static Color COULEUR_TEXTE = new Color(0x333333);

    //Dimensions
    public final static Dimension DIMENSION_BOUTON = new Dimension(120, 40);
    public final static Dimension DIMENSION_CHAMP_SAISIE = new Dimension(200, 30);
    public final static Dimension DIMENSION_FENETRE = new Dimension(800, 600);

    //Espacement
    public final static int ESPACEMENT_DEFAUT = 10;
    public final static int ESPACEMENT_BORDS = 20;

    //Police Par Défaut
    public final static String POLICE_PAR_DEFAUT = "Arial";

}

