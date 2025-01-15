package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControleurAjoutTravaux;
import controller.ControleurTravaux;
import model.BienImmobilier;

public class VueAjoutTravaux extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textID;
    private JTextField textDescription;
    private JTextField textEntreprise;
    private JTextField textMontantDevise;
    private JTextField textMontantFacture;
    private BienImmobilier bien;

    public VueAjoutTravaux(BienImmobilier bien, ControleurTravaux control) {
        this.bien = bien;

        setTitle("Ajouter Travaux");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 250));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.setBackground(new Color(245, 245, 250));

        textID = new JTextField();
        addField(formPanel, "ID :", textID);

        textDescription = new JTextField();
        addField(formPanel, "Description :", textDescription);

        textEntreprise = new JTextField();
        addField(formPanel, "Entreprise :", textEntreprise);

        textMontantDevise = new JTextField();
        addField(formPanel, "Montant Devise :", textMontantDevise);

        textMontantFacture = new JTextField();
        addField(formPanel, "Montant Facture :", textMontantFacture);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 250));

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

    public String getID() {
        return textID.getText();
    }

    public String getDescription() {
        return textDescription.getText();
    }

    public String getEntreprise() {
        return textEntreprise.getText();
    }

    public String getMontantDevise() {
        return textMontantDevise.getText();
    }

    public String getMontantFacture() {
        return textMontantFacture.getText();
    }
}