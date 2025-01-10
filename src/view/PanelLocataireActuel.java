package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelLocataireActuel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Panel Locataires Actuels");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

            JLabel totalLoyerLabel = new JLabel("Loyer total : 1305€", JLabel.CENTER);
            totalLoyerLabel.setFont(new Font("Arial", Font.BOLD, 16));
            mainPanel.add(totalLoyerLabel, BorderLayout.NORTH);

            String[] columnNames = {"Nom", "Date entrée", "Part du loyer"};
            Object[][] data = {
                    {"Jean Michel", "20 avril 2025", "1000€"},
                    {"Jean Eude", "12 mars 2025", "300€"},
                    {"Jean Marie", "25 novembre 2024", "5€"}
            };

            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(tableModel);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane tableScrollPane = new JScrollPane(table);
            mainPanel.add(tableScrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

            JButton detailsButton = new JButton("Détails du locataire");
            JButton suppButton = new JButton("Retirer de la colocation");
            suppButton.setBackground(Color.PINK);
            JButton modifButton = new JButton("Modifier part du loyer");

            buttonPanel.add(detailsButton);
            buttonPanel.add(suppButton);
            buttonPanel.add(modifButton);

            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
