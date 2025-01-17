package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.ControleurAjoutTravaux;
import controller.ControleurTravaux;
import model.BienImmobilier;

public class VueAjoutTravail extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textID;
    private JTextField textDescription;
    private JTextField textEntreprise;
    private JSpinner spinnerMontantDevise;
    private JSpinner spinnerMontantFacture;
    private BienImmobilier bien;
    private JList<String> erreursList;
    private DefaultListModel<String> erreursListModel;

    public VueAjoutTravail(BienImmobilier bien, ControleurTravaux control) {
        this.bien = bien;

        setTitle("Ajouter Travaux");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 250));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.setBackground(new Color(245, 245, 250));

        textID = new JTextField();
        addField(formPanel, "ID :", textID);

        textDescription = new JTextField();
        addField(formPanel, "Description :", textDescription);

        textEntreprise = new JTextField();
        addField(formPanel, "Entreprise :", textEntreprise);

        spinnerMontantDevise = new JSpinner(new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.1));
        addField(formPanel, "Montant Devise :", spinnerMontantDevise);

        spinnerMontantFacture = new JSpinner(new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.1));
        addField(formPanel, "Montant Facture :", spinnerMontantFacture);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 250));

        erreursListModel = new DefaultListModel<>();
        erreursList = new JList<>(erreursListModel);
        erreursList.setEnabled(false);
        erreursList.setFont(new Font("Rockwell", Font.PLAIN, 14));
        erreursList.setForeground(Color.RED);
        erreursList.setBorder(BorderFactory.createLineBorder(Color.RED));

        JScrollPane scrollPane = new JScrollPane(erreursList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Erreurs"));
        scrollPane.setBackground(new Color(245, 245, 250));
        scrollPane.setPreferredSize(new java.awt.Dimension(350, 100));

        formPanel.add(scrollPane, BorderLayout.CENTER);

        ControleurAjoutTravaux controleur = new ControleurAjoutTravaux(this.bien, this, control);

        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setFont(new Font("Arial", Font.BOLD, 14));
        btnEnregistrer.setBackground(new Color(39, 174, 96));
        btnEnregistrer.setForeground(Color.WHITE);
        btnEnregistrer.setFocusPainted(false);
        btnEnregistrer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(btnEnregistrer);
        btnEnregistrer.addActionListener(controleur);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Arial", Font.BOLD, 14));
        btnAnnuler.setBackground(new Color(200, 50, 50));
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setFocusPainted(false);
        btnAnnuler.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnAnnuler.addActionListener(e -> dispose());
        buttonPanel.add(btnAnnuler);
        btnAnnuler.addActionListener(controleur);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, String label, JTextField textField) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lbl);

        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(textField);
    }

    private void addField(JPanel panel, String label, JSpinner spinner) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lbl);

        spinner.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(spinner);
    }

    public String getID() {
        return textID.getText();
    }

    public String getDescription() {
        return textDescription.getText();
    }

    public String getEntreprise() {
        return textEntreprise.getText();
    }

    public double getMontantDevis() {
        return (double) spinnerMontantDevise.getValue();
    }

    public double getMontantFacture() {
        return (double) spinnerMontantFacture.getValue();
    }

    public void addErreur(String erreur) {
        erreursListModel.addElement(erreur);
        erreursList.setModel(erreursListModel);
    }

    public void clearErreurs() {
        if (erreursListModel != null) {
            erreursListModel = new DefaultListModel<>();
        }
        erreursListModel.clear();
        erreursList.setModel(erreursListModel);
    }

    public boolean hasErreurs() {
        return erreursListModel.size() > 0;
    }
}
