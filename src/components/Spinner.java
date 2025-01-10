package components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Spinner extends JSpinner {
    public Spinner(int initialValue, int minValue, int maxValue) {
        super(new SpinnerNumberModel(initialValue, minValue, maxValue, 1));

        setFont(new Font("Arial", Font.PLAIN, 14));
        setForeground(Color.DARK_GRAY);
        setOpaque(false);

        // Personnalisation des bordures et de la taille du JSpinner
        setPreferredSize(new Dimension(150, 40));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Personnaliser l'apparence des boutons + et -
        JComponent editor = getEditor();
        editor.setBorder(BorderFactory.createEmptyBorder());
        editor.setBackground(new Color(0xF0F0F0));
        editor.setOpaque(false);

        // Personnaliser les boutons de valeur
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JComponent) {
                JComponent button = (JComponent) component;
                button.setOpaque(false);
                button.setMaximumSize(new Dimension(40, 40));
                button.setBackground(new Color(0xD1D1D1)); // Couleur de fond des boutons
                button.setBorder(BorderFactory.createLineBorder(new Color(0xB0B0B0), 1));
            }
        }
        // Gérer le focus
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(new Color(0xD3D3D3)); // Change la couleur du fond au focus
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(Color.WHITE); // Restaure la couleur du fond
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 20, 20);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.setColor(hasFocus() ? new Color(0x4682b4) : new Color(0x828282));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        super.paintComponent(g);
    }
}
