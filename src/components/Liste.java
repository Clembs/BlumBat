package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Liste<T> extends JList<T> {

  private DefaultListModel<T> model;

  public Liste() {
    super();
    model = new DefaultListModel<>();
    this.setModel(model);

    this.setFont(Layout.POLICE_PARAGRAPHES);
    this.setBackground(Layout.COULEUR_FOND);
    this.setForeground(Layout.COULEUR_TEXTE);
    this.setSelectionBackground(Layout.COULEUR_PRIMAIRE);
    this.setFixedCellHeight(50);
    this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    this.setCellRenderer(new CustomCellRenderer<>());

    // Event pour définir les curseurs !
    this.addMouseListener(new MouseAdapter() {
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

  public DefaultListModel<T> getModel() {
    return this.model;
  }

  public void setModel(DefaultListModel<T> model) {
    this.model = model;
    this.setModel(model);
  }

  public void addElement(T item) {
    this.model.addElement(item);
  }

  public void clear() {
    this.model.clear();
  }

  public void removeElement(T item) {
    this.model.removeElement(item);
  }

  public int getElementsSize() {
    return this.model.getSize();
  }

  public T getElementAt(int index) {
    return this.model.getElementAt(index);
  }

  // Méthode pour personnaliser les Cellules
  private static class CustomCellRenderer<T> extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object valeurListe, int n, boolean selection,
        boolean focus) {
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
          BorderFactory.createEmptyBorder(5, 5, 5, 5)));
      label.setOpaque(true);

      return label;
    }
  }
}