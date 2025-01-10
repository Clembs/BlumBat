package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Password extends JPasswordField {
    private String placeholder;
    private boolean showingPlaceholder;

    public Password(String placeholder) {
        super();
        this.placeholder = placeholder;
        this.showingPlaceholder = true;

        //  du mot de passe
        setFont(new Font("Arial", Font.PLAIN, 14));
        setForeground(Color.LIGHT_GRAY);
        setCaretColor(Color.DARK_GRAY);
        setOpaque(false);
        setMaximumSize(new Dimension(200, 40));

        //EchoChar pour masquer les caractères avec des *
        setEchoChar((char) 0);

        // Ajouter un padding/margin via une bordure (sans arrondi ici)
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        // Gestion du focus et du placeholder
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText(""); // Supprimer le placeholder
                    setForeground(Color.DARK_GRAY);
                    setEchoChar('*'); // Réactiver le masquage des caractères
                    showingPlaceholder = false;
                }
                repaint(); // Rafraîchir l'affichage
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getPassword().length == 0) {
                    setText(placeholder); // Réafficher le placeholder
                    setForeground(Color.LIGHT_GRAY);
                    setEchoChar((char) 0); // Ne pas masquer les caractères pour le placeholder
                    showingPlaceholder = true;
                }
                repaint();
            }
        });

        // ajouter le texte du placeholder
        setText(placeholder);
    }


    //Pour avoir des bord arrondie
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessiner une ombre légère
        g2.setColor(new Color(0, 0, 0, 30));
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 20, 20); // Bordure arrondie de l'ombre

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Fond arrondi

        // Dessiner la bordure arrondie avec changement de couleur selon le focus
        g2.setColor(hasFocus() ? new Color(0x4682b4) : new Color(0x828282)); // Bordure bleue si focus, grise sinon
        g2.setStroke(new BasicStroke(2)); // Définir la largeur de la bordure
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20); // Bordure arrondie

        super.paintComponent(g);
    }
}
