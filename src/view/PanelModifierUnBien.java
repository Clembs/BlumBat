package view;

import controller.ControleurModifierBien;
import model.TypeBien;

import javax.swing.*;
import java.awt.*;

public class PanelModifierUnBien extends JPanel {

    private JComboBox<TypeBien> comboTypeBien;
    private JTextField textIdBien;
    private JTextField textAdresse;
    private JTextField textComplementAdresse;
    private JTextField textVille;
    private JTextField textDepartement;
    private JTextField textCP;
    private JTextField textSurface;
    private JTextField textNbFiscal;
    private JTextField textNbPiece;
    private DefaultListModel<String> erreursListModel;
    private JList<String> erreursList;

    public PanelModifierUnBien(String typeBien, String idBien, String adresse, String cadresse, String ville, String departement,
                               String CP, String surface, String nbFiscal, String nbPiece) {

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(40, 40, 40));

        // Initialisation du modèle de liste des erreurs
        erreursListModel = new DefaultListModel<>();
        erreursList = new JList<>(erreursListModel);

        // Titre
        JLabel lblTitle = new JLabel("Modifier un bien", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(240, 240, 240));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(60, 60, 60));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitle, BorderLayout.NORTH);

        // Panneau de formulaire avec deux colonnes
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBackground(new Color(50, 50, 50));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Champs de formulaire du bien
        // Initialisation du JComboBox avec les valeurs de l'énumération
        comboTypeBien = new JComboBox<>(TypeBien.values());


// Création et ajout du JLabel pour "Type:"
        JLabel lblTypeBien = new JLabel("Type:*");
        lblTypeBien.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblTypeBien.setForeground(new Color(230, 230, 230));  // Couleur de texte claire pour l'étiquette
        formPanel.add(lblTypeBien);

// Configuration du JComboBox
        comboTypeBien.setFont(new Font("SansSerif", Font.PLAIN, 14));
        comboTypeBien.setBackground(new Color(60, 60, 60));  // Fond sombre pour le combo
        comboTypeBien.setForeground(Color.WHITE);  // Texte en blanc pour le combo
        comboTypeBien.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));  // Bordure claire pour le combo
        formPanel.add(comboTypeBien);  // Ajouter le JComboBox au formulaire

        textIdBien = new JTextField(idBien);
        addField(formPanel, "Identifiant:*", textIdBien);

        textAdresse = new JTextField(adresse);
        addField(formPanel, "Adresse:*", textAdresse);

        textComplementAdresse = new JTextField(cadresse);
        addField(formPanel, "Complément Adresse:*", textComplementAdresse);

        textVille = new JTextField(ville);
        addField(formPanel, "Ville:*", textVille);

        textDepartement = new JTextField(departement);
        addField(formPanel, "Département:*", textDepartement);

        textCP = new JTextField(String.valueOf(CP));
        addField(formPanel, "Code Postal:*", textCP);

        textSurface = new JTextField(String.valueOf(surface));
        addField(formPanel, "Surface:*", textSurface);

        textNbFiscal = new JTextField(String.valueOf(nbFiscal));
        addField(formPanel, "Numéro Fiscal:*", textNbFiscal);

        textNbPiece = new JTextField(String.valueOf(nbPiece));
        addField(formPanel, "Nombre de Pièces:*", textNbPiece);

        add(formPanel, BorderLayout.CENTER);

        // Panneau des erreurs
        JPanel erreurPanel = new JPanel(new BorderLayout());
        erreurPanel.setBackground(new Color(40, 40, 40));

        erreursList.setFont(new Font("SansSerif", Font.PLAIN, 12));
        erreursList.setBackground(new Color(60, 60, 60));
        erreursList.setForeground(Color.RED);

        JScrollPane erreurScrollPane = new JScrollPane(erreursList);
        erreurScrollPane.setPreferredSize(new Dimension(200, 100));

        erreurPanel.add(erreurScrollPane, BorderLayout.CENTER);
        add(erreurPanel, BorderLayout.WEST);

        // Panneau des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(40, 40, 40));

        JButton btnModifier = new JButton("Modifier");
        btnModifier.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnModifier.setBackground(new Color(0, 170, 85));
        btnModifier.setForeground(Color.WHITE);
        btnModifier.setFocusPainted(false);
        btnModifier.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(btnModifier);

        ControleurModifierBien controleur = new ControleurModifierBien(this);
        btnModifier.addActionListener(controleur);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAnnuler.setBackground(new Color(200, 50, 50));
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setFocusPainted(false);
        btnAnnuler.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(btnAnnuler);

        add(buttonPanel, BorderLayout.SOUTH);
    }




    // Méthodes pour ajouter un champ de saisie
    private void addField(JPanel panel, String label, JTextField textField) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl.setForeground(new Color(230, 230, 230));
        panel.add(lbl);

        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.setBackground(new Color(60, 60, 60));
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        panel.add(textField);
    }

    // Méthodes pour récupérer les valeurs
    public String getIdBien() {
        return textIdBien.getText();
    }

    public String getAdresse() {
        return textAdresse.getText();
    }

    public String getComplementAdresse() {
        return textComplementAdresse.getText();
    }
    public String getDepartement() {
        return textDepartement.getText();
    }

    public String getVille() {
        return textVille.getText();
    }

    public String getCodePostal() {
        return textCP.getText();
    }

    public TypeBien getTypeBien() {
        return (TypeBien) comboTypeBien.getSelectedItem();  // Récupérer l'objet sélectionné dans le combo
    }

    public String getSurface() {
        return textSurface.getText();
    }

    public String getNbFiscal() {
        return textNbFiscal.getText();
    }

    public String getNbPiece() {
        return textNbPiece.getText();
    }

    // Méthodes pour gérer les erreurs
    public void addErreur(String erreur) {
        erreursListModel.addElement(erreur);
    }

    public void clearErreurs() {
        erreursListModel.clear();
    }





}
