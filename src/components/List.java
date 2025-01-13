package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class List<T> extends JList<T> {

    private DefaultListModel<T> liste;

    public List() {
        liste = new DefaultListModel<>();
        setModel(liste);

        setFont(Layout.POLICE_PARAGRAPHES);
        setBackground(Layout.COULEUR_FOND);
        setForeground(Layout.COULEUR_TEXTE);
        setSelectionBackground(Layout.COULEUR_PRIMAIRE);
        setFixedCellHeight(50);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setCellRenderer(new Apparence<>());

        // Event pour définir les curseurs !
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    public void addItem(T item) {
        liste.addElement(item);
    }

    public DefaultListModel<T> getListModel() {
        return liste;
    }

    // Méthode pour personnaliser les Cellules
    private static class Apparence<T> extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object valeurListe, int n, boolean selection, boolean focus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, valeurListe, n, selection, focus);

            label.setFont(Layout.POLICE_PARAGRAPHES);
            label.setBorder(new EmptyBorder(10, 15, 10, 15)); // Padding intérieur

            if (selection) {
                label.setBackground(Layout.COULEUR_INFO);
                label.setForeground(Layout.COULEUR_FOND);
            } else {
                label.setBackground(Layout.COULEUR_FOND);
                label.setForeground(Layout.COULEUR_TEXTE);
            }

            label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Layout.COULEUR_SECONDAIRE, 1),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            label.setOpaque(true);

            return label;
        }
    }
}