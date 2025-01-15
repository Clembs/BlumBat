package components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Tableau extends JTable {
  private DefaultTableModel model;

  public Tableau(String... columnNames) {
    super();
    model = new DefaultTableModel(columnNames, 0);
    this.setModel(model);

    this.setFont(Layout.POLICE_PARAGRAPHES);
    this.setBackground(Layout.COULEUR_FOND);

    this.setGridColor(Layout.COULEUR_SECONDAIRE);
    this.setSelectionBackground(Layout.COULEUR_PRIMAIRE_VARIANTE);
    this.setSelectionForeground(Layout.COULEUR_FOND);
    this.setRowHeight(40);

    // Non-éditable
    this.setDefaultEditor(Object.class, null);
    // Largeur auto des colonnes
    this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

    // Alignement centré des cellules
    DefaultTableCellRenderer customCellRenderer = new DefaultTableCellRenderer();
    customCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

    for (int i = 0; i < getColumnCount(); i++) {
      this.getColumnModel().getColumn(i).setCellRenderer(customCellRenderer);
    }

    this.getTableHeader().setFont(Layout.POLICE_PETIT_TITRE);
    this.getTableHeader().setForeground(Layout.COULEUR_FOND);
    this.getTableHeader().setBackground(Layout.COULEUR_PRIMAIRE);
    this.getTableHeader().setBorder(new LineBorder(Layout.COULEUR_PRIMAIRE_VARIANTE));
  }

  public DefaultTableModel getModel() {
    return this.model;
  }

  public void setModel(DefaultTableModel model) {
    this.model = model;
    super.setModel(model);
  }

  public void addRow(String... columns) {
    this.model.addRow(columns);
  }

  public void removeRow(int rowIndex) {
    this.model.removeRow(rowIndex);
  }

  public void clear() {
    this.model.setRowCount(0);
  }
}