package components;

import javax.swing.*;
import java.awt.*;

public class ChampSaisie extends JPanel {

    // Enumération pour définir le type de champ
    public enum TypeChamp {
        TEXTE, NOMBRE, MOT_DE_PASSE
    }

    private JLabel label;
    private JComponent champSaisie;

    public ChampSaisie(String texte, TypeChamp typeChamp) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // BoxLayout
        setOpaque(false); // Permet de personnaliser le fond

        // Configuration du label
        label = new JLabel(texte);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.DARK_GRAY);
        label.setAlignmentX(CENTER_ALIGNMENT); // Centrer le label

        // Choix du type du champ de saisie
        switch (typeChamp) {
            case TEXTE -> {
                champSaisie = new TextField("Entrez du texte...");
                champSaisie.setPreferredSize(new Dimension(180, 35));
            }
            case NOMBRE -> {
                champSaisie = new Spinner(0, 0, 10);
                champSaisie.setPreferredSize(new Dimension(180, 35));
            }
            case MOT_DE_PASSE -> {
                champSaisie = new Password("Mot de passe");
                champSaisie.setPreferredSize(new Dimension(180, 35));
            }
        }

        champSaisie.setAlignmentX(CENTER_ALIGNMENT); // Centrer le champ de saisie
        add(label);
        add(Box.createVerticalStrut(5)); // Espacement entre le label et le champ
        add(champSaisie);
    }
}
