package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelLocatairesPasses extends JPanel {

    public PanelLocatairesPasses() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Locataires Passés", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Nom", "Date d'entrée", "Date de sortie", "Dernier loyer payé"};
        Object[][] data = {
                {"Mouloud taxi", "01/01/2020", "31/12/2021", "500 €"},
                {"Michel Barnier", "01/03/2019", "30/06/2021", "450 €"}
        };

        // Permet de rendre les cellules non éditables puisque par défaut elles le sont
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pannel des Locataires Passés");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new PanelLocatairesPasses());
        frame.setVisible(true);
    }
}
