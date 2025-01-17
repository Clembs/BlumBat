package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControleurModifierPartBien;
import model.BienLocatif;
import model.Location;

public class VueModifierPartBiens extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable otherTenantsTable;
    private JSpinner spinner;
    private JLabel lblTotalAmount;
    private JCheckBox chckbxModifyOthers;

    public VueModifierPartBiens(BienLocatif bien) {
        setTitle("Modifier la répartition des loyers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 600);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 249, 250));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(20, 20));
        setContentPane(contentPane);


        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(41, 128, 185));
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(titlePanel, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("Modifier la répartition des loyers");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);


        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // Formulaire
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        centerPanel.add(formPanel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblLoyer = new JLabel("Loyer du locataire sélectionné :");
        lblLoyer.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblLoyer, gbc);

        spinner = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
        spinner.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        spinner.setPreferredSize(new Dimension(140, 35));
        gbc.gridx = 1;
        formPanel.add(spinner, gbc);

        chckbxModifyOthers = new JCheckBox("Modifier la répartition pour les autres locataires");
        chckbxModifyOthers.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        chckbxModifyOthers.setBackground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(chckbxModifyOthers, gbc);

        // Tableau des locataires
        JLabel lblOtherTenants = new JLabel("Autres Locataires");
        lblOtherTenants.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblOtherTenants.setBorder(new EmptyBorder(10, 0, 10, 0));
        centerPanel.add(lblOtherTenants, BorderLayout.CENTER);

        otherTenantsTable = new JTable(new DefaultTableModel(
                new Object[]{"Nom", "Loyer", "Date d'entrée", "Date de sortie"}, 0));
        otherTenantsTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        otherTenantsTable.setRowHeight(30);
        otherTenantsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        otherTenantsTable.getTableHeader().setBackground(new Color(230, 230, 230));
        otherTenantsTable.setSelectionBackground(new Color(41, 128, 185));
        otherTenantsTable.setSelectionForeground(Color.WHITE);

        JScrollPane otherTenantsScrollPane = new JScrollPane(otherTenantsTable);
        otherTenantsScrollPane.setPreferredSize(new Dimension(600, 250));
        otherTenantsScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(otherTenantsScrollPane, BorderLayout.SOUTH);

        // Total du loyer
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(new Color(236, 240, 241)); // Gris clair
        totalPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(totalPanel, BorderLayout.SOUTH);

        JLabel lblTotalLoyer = new JLabel("Loyer Total :");
        lblTotalLoyer.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalPanel.add(lblTotalLoyer);

        lblTotalAmount = new JLabel("0 €");
        lblTotalAmount.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotalAmount.setForeground(new Color(39, 174, 96)); // Vert
        totalPanel.add(lblTotalAmount);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnEnregistrer.setBackground(new Color(46, 204, 113)); // Vert clair
        btnEnregistrer.setForeground(Color.WHITE);
        btnEnregistrer.setFocusPainted(false);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnAnnuler.setBackground(new Color(231, 76, 60)); // Rouge clair
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setFocusPainted(false);

        buttonPanel.add(btnEnregistrer);
        buttonPanel.add(btnAnnuler);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Contrôleur
        ControleurModifierPartBien controleur = new ControleurModifierPartBien(this, bien);
        btnEnregistrer.addActionListener(controleur);
        btnAnnuler.addActionListener(e -> dispose());

        // Charger les données initiales
        rafraichirTable(bien);
    }

    public JSpinner getSpinner() {
        return spinner;
    }

    public JTable getOtherTenantsTable() {
        return otherTenantsTable;
    }

    public JCheckBox getChckbxModifyOthers() {
        return chckbxModifyOthers;
    }

    public void rafraichirTable(BienLocatif bien) {
        DefaultTableModel model = (DefaultTableModel) otherTenantsTable.getModel();
        model.setRowCount(0);
        double totalLoyer = 0;
        for (Location location : bien.getLocationsCourantes()) {
            model.addRow(new Object[]{
                    location.getLocataire().getNom(),
                    location.getLoyer() + " €",
                    location.getDateEntree(),
                    location.getDateSortie()
            });
            totalLoyer += location.getLoyer();
        }
        lblTotalAmount.setText(totalLoyer + " €");
    }
}
