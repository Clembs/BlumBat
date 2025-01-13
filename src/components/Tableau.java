package components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Tableau extends JTable {

    public Tableau(Object[][] data, Object[] columnNames) {
        super(new DefaultTableModel(data, columnNames));
        StyleTableau();
    }

    private void StyleTableau() {
        setBackground(Layout.COULEUR_FOND);
        setGridColor(Layout.COULEUR_SECONDAIRE);
        setSelectionBackground(Layout.COULEUR_PRIMAIRE);
        setSelectionForeground(Layout.COULEUR_FOND);
        setFont(Layout.POLICE_PARAGRAPHES);

        DefaultTableCellRenderer cellulecentrer = new DefaultTableCellRenderer();
        cellulecentrer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(cellulecentrer);
        }
        getTableHeader().setFont(Layout.POLICE_ERREUR_SUCCES);
        getTableHeader().setForeground(Layout.COULEUR_FOND);
        getTableHeader().setBackground(Layout.COULEUR_INFO);
        setRowHeight(30);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
}