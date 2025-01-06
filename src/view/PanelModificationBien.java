package view;

import controller.ControleurModificationBien;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;
import model.TypeBien;

import javax.swing.*;
import java.awt.*;

public class PanelModificationBien extends JPanel {
    private JComboBox<TypeBien> comboTypeBien;
    private JTextField textIdBien;
    private JTextField textAdresse;
    private JTextField textComplementAdresse;
    private JTextField textVille;
    private JTextField textCP;
    private JTextField textSurface;
    private JTextField textNbFiscal;
    private JTextField textNbPiece;
    private DefaultListModel<String> erreursListModel;
    private JList<String> erreursList;

    public PanelModificationBien(FenBiens fenetre, Proprietaire proprietaire, BienImmobilier bien) {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(40, 40, 40));

        erreursListModel = new DefaultListModel<>();
        erreursList = new JList<>(erreursListModel);

        JLabel lblTitle = new JLabel("Modifier un bien", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(240, 240, 240));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(60, 60, 60));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBackground(new Color(50, 50, 50));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        comboTypeBien = new JComboBox<>(TypeBien.values());
        addField(formPanel, "Type:*", comboTypeBien);

        textIdBien = new JTextField(bien.getId());
        addField(formPanel, "Identifiant:*", textIdBien);

        textAdresse = new JTextField(bien.getAdresse());
        addField(formPanel, "Adresse:*", textAdresse);

        textComplementAdresse = new JTextField(bien.getComplementAdresse());
        addField(formPanel, "Complément Adresse:*", textComplementAdresse);

        textVille = new JTextField(bien.getVille());
        addField(formPanel, "Ville:*", textVille);

        textCP = new JTextField(bien.getCodePostal());
        addField(formPanel, "Code Postal:*", textCP);

        if (bien instanceof BienLocatif) {
            BienLocatif bienL = (BienLocatif) bien;

            textSurface = new JTextField(String.valueOf(bienL.getSurface()));
            addField(formPanel, "Surface:*", textSurface);

            textNbFiscal = new JTextField(String.valueOf(bienL.getNombrePieces()));
            addField(formPanel, "Numéro Fiscal:*", textNbFiscal);

            textNbPiece = new JTextField(String.valueOf(bienL.getNombrePieces()));
            addField(formPanel, "Nombre de Pièces:*", textNbPiece);
        }

        add(formPanel, BorderLayout.CENTER);

        JPanel erreurPanel = new JPanel(new BorderLayout());
        erreurPanel.setBackground(new Color(40, 40, 40));

        erreursList.setFont(new Font("SansSerif", Font.PLAIN, 12));
        erreursList.setBackground(new Color(60, 60, 60));
        erreursList.setForeground(Color.RED);

        JScrollPane erreurScrollPane = new JScrollPane(erreursList);
        erreurScrollPane.setPreferredSize(new Dimension(200, 100));

        erreurPanel.add(erreurScrollPane, BorderLayout.CENTER);
        add(erreurPanel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(40, 40, 40));

        JButton btnModifier = new JButton("Enregistrer");
        btnModifier.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnModifier.setBackground(new Color(0, 170, 85));
        btnModifier.setForeground(Color.WHITE);
        btnModifier.setFocusPainted(false);
        btnModifier.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(btnModifier);

        ControleurModificationBien controleur = new ControleurModificationBien(fenetre, this, proprietaire, bien);
        btnModifier.addActionListener(controleur);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAnnuler.setBackground(new Color(200, 50, 50));
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setFocusPainted(false);
        btnAnnuler.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnAnnuler.addActionListener(e -> closePanel());
        buttonPanel.add(btnAnnuler);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, String label, JComponent field) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl.setForeground(new Color(230, 230, 230));
        panel.add(lbl);

        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setBackground(new Color(60, 60, 60));
        field.setForeground(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        panel.add(field);
    }

    public String getIdBien() {
        return textIdBien.getText();
    }

    public String getAdresse() {
        return textAdresse.getText();
    }

    public String getComplementAdresse() {
        return textComplementAdresse.getText();
    }

    public String getVille() {
        return textVille.getText();
    }

    public String getCodePostal() {
        return textCP.getText();
    }

    public TypeBien getTypeBien() {
        return (TypeBien) comboTypeBien.getSelectedItem();
    }

    public float getSurface() {
        return Float.parseFloat(textSurface.getText());
    }

    public String getNbFiscal() {
        return textNbFiscal.getText();
    }

    public int getNbPiece() {
        return Integer.parseInt(textNbPiece.getText());
    }

    public void addErreur(String erreur) {
        erreursListModel.addElement(erreur);
    }

    public void clearErreurs() {
        erreursListModel.clear();
    }

    public void closePanel() {
        Container parent = getParent();
        if (parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }
    }
}