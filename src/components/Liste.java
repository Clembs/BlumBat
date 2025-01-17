package components;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;

public class Liste<T> extends JList<T> {

  private DefaultListModel<T> model;

  public Liste() {
    super();
    model = new DefaultListModel<>();
    this.setModel(model);

    this.setFont(Layout.POLICE_SMALL);
    this.setBackground(Layout.COULEUR_FOND);
    this.setForeground(Layout.COULEUR_PARAGRAPHE);

    this.setSelectionBackground(Layout.COULEUR_PRIMAIRE_VARIANTE);
    this.setSelectionForeground(Layout.COULEUR_FOND);

    DefaultListCellRenderer customCellRenderer = new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(
          JList<?> list, Object value, int index, boolean isSelected,
          boolean isFocused) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value,
            index, isSelected, isFocused);

        boolean estPremierÉl = index == 0;
        boolean estDernierÉl = index == list.getModel().getSize() - 1;

        label.setBorder(
            new CompoundBorder(
                new MatteBorder(estPremierÉl ? 0 : 1, 0, estDernierÉl ? 1 : 0, 0, Layout.COULEUR_SECONDAIRE),
                new EmptyBorder(12, 16, 12, 16)));
        return label;
      }
    };

    this.setCellRenderer(customCellRenderer);
  }

  public DefaultListModel<T> getModel() {
    return this.model;
  }

  public void setModel(DefaultListModel<T> model) {
    this.model = model;
    super.setModel(model);
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
}