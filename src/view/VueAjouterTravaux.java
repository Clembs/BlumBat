package view;

import javax.swing.*;
import java.awt.*;

import model.BienImmobilier;
import model.TypeBien;

public class VueAjouterTravaux extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textTravaux;
    private JTextField textDescription;
    private JTextField textEntreprise;
    private JTextField textMontantDevise;
    private JTextField textMontantFacture;
    private BienImmobilier bien;
    private PanelTravaux panelTravaux;

    public VueAjouterTravaux(BienImmobilier bien, PanelTravaux panelTravaux) {
        this.bien = bien;
        this.panelTravaux = panelTravaux;

        setTitle("Ajouter Travaux");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 250));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.setBackground(new Color(245, 245, 250));

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

        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setFont(new Font("Arial", Font.BOLD, 14));
        btnEnregistrer.setBackground(new Color(39, 174, 96));
        btnEnregistrer.setForeground(Color.WHITE);
        btnEnregistrer.setFocusPainted(false);
        btnEnregistrer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(btnEnregistrer);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Arial", Font.BOLD, 14));
        btnAnnuler.setBackground(new Color(200, 50, 50));
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setFocusPainted(false);
        btnAnnuler.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnAnnuler.addActionListener(e -> dispose());
        buttonPanel.add(btnAnnuler);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, String label, JTextField textField) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lbl);

        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(textField);
    }

    public String getTravaux() {
        return textTravaux.getText();
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
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BienImmobilier bien = new BienImmobilier("123456", TypeBien.BATIMENT, "rue compte", "ap4", "36000", "Toulouse");
                PanelTravaux panelTravaux = new PanelTravaux(bien);
                VueAjouterTravaux frame = new VueAjouterTravaux(bien, panelTravaux);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}