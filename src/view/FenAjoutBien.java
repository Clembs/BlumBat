package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import controller.ControleurAjoutBiens;
import model.BienImmobilier;
import model.Proprietaire;
import model.TypeBien;

public class FenAjoutBien extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField villeField;
    private JTextField adresseField;
    private JSpinner codePostalField;
    private JSpinner surfaceField;
    private JSpinner NFiscalField;
    private JTextField complementAdresse;
    private JSpinner NPiecesField;
    private JTextField IdField;
    private JComboBox<String> cmbType;
    private JList<String> erreursList;
    private DefaultListModel<String> erreursListModel;

    public FenAjoutBien(Proprietaire P) {
        this.setTitle("Ajout d'un bien immobilier");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 800, 500);

        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.contentPane.setBackground(new Color(240, 240, 250));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(new BorderLayout(20, 20));

        JLabel lblTitle = new JLabel("Ajout d'un bien immobilier");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Rockwell", Font.BOLD, 28));
        lblTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(150, 150, 200)));
        this.contentPane.add(lblTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(9, 2, 10, 10));
        centerPanel.setBackground(new Color(240, 240, 250));
        this.contentPane.add(centerPanel, BorderLayout.CENTER);

        JLabel lblType = new JLabel("Type de bien :");
        lblType.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblType.setForeground(new Color(80, 80, 100));
        centerPanel.add(lblType);

        cmbType = new JComboBox<>(new String[] { "BATIMENT", "LOGEMENT", "GARAGE" });
        cmbType.setFont(new Font("Rockwell", Font.PLAIN, 14));
        cmbType.setBorder(new LineBorder(new Color(150, 150, 150)));
        centerPanel.add(cmbType);

        JLabel lblId = new JLabel("Identifiant (unique) :");
        lblId.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblId.setForeground(new Color(80, 80, 100));
        centerPanel.add(lblId);

        IdField = new JTextField();
        IdField.setFont(new Font("Rockwell", Font.PLAIN, 14));
        IdField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(IdField);

        JLabel lblAddress = new JLabel("Adresse :");
        lblAddress.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblAddress.setForeground(new Color(80, 80, 100));
        centerPanel.add(lblAddress);

        adresseField = new JTextField();
        adresseField.setFont(new Font("Rockwell", Font.PLAIN, 14));
        adresseField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(adresseField);

        JLabel lblcomplementAdresse = new JLabel("Complément d'adresse (facultatif) :");
        lblcomplementAdresse.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblcomplementAdresse.setForeground(new Color(80, 80, 100));
        centerPanel.add(lblcomplementAdresse);

        complementAdresse = new JTextField();
        complementAdresse.setFont(new Font("Rockwell", Font.PLAIN, 14));
        complementAdresse.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(complementAdresse);

        JLabel lblVille = new JLabel("Ville :");
        lblVille.setForeground(new Color(80, 80, 100));
        lblVille.setFont(new Font("Rockwell", Font.BOLD, 14));
        centerPanel.add(lblVille);

        villeField = new JTextField();
        villeField.setFont(new Font("Rockwell", Font.PLAIN, 14));
        villeField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(villeField);

        JLabel lblDpartement = new JLabel("Code postal :");
        lblDpartement.setForeground(new Color(80, 80, 100));
        lblDpartement.setFont(new Font("Rockwell", Font.BOLD, 14));
        centerPanel.add(lblDpartement);

        codePostalField = new JSpinner();
        codePostalField.setModel(new SpinnerNumberModel(0, null, 999990, 1));
        codePostalField.setFont(new Font("Rockwell", Font.PLAIN, 14));
        codePostalField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(codePostalField);

        JLabel lblSurface = new JLabel("Surface (en m²) :");
        lblSurface.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblSurface.setForeground(new Color(80, 80, 100));
        // centerPanel.add(lblSurface);

        surfaceField = new JSpinner();
        surfaceField.setModel(new SpinnerNumberModel(Float.valueOf(0), null, null, Float.valueOf(1)));
        surfaceField.setFont(new Font("Rockwell", Font.PLAIN, 14));
        surfaceField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));

        JLabel lblNFiscal = new JLabel("Numéro fiscal :");
        lblNFiscal.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblNFiscal.setForeground(new Color(80, 80, 100));

        NFiscalField = new JSpinner();
        NFiscalField.setModel(new SpinnerNumberModel(Long.valueOf(0), null,
                Long.valueOf(2147483647), // 999999999999, voire 12 chiffres
                Long.valueOf(1)));
        NFiscalField.setFont(new Font("Rockwell", Font.PLAIN, 14));
        NFiscalField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));

        JLabel lblNPieces = new JLabel("Nombre de pièces :");
        lblNPieces.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblNPieces.setForeground(new Color(80, 80, 100));

        NPiecesField = new JSpinner();
        NPiecesField.setModel(new SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
        NPiecesField.setFont(new Font("Rockwell", Font.PLAIN, 14));
        NPiecesField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));

        // affichage conditionnel des champs liés aux locations selon le type
        // sélectionné
        cmbType.addActionListener((ActionEvent e) -> {
            TypeBien selectedType = TypeBien.getTypeBien((String) cmbType.getSelectedItem());
            System.out.println(selectedType);

            switch (selectedType) {
                case BATIMENT: {
                    // suppression des champs & leurs libellés
                    centerPanel.remove(lblSurface);
                    centerPanel.remove(surfaceField);
                    centerPanel.remove(lblNFiscal);
                    centerPanel.remove(NFiscalField);
                    centerPanel.remove(lblNPieces);
                    centerPanel.remove(NPiecesField);
                    // obligatoire pour rafraichir l'interface
                    centerPanel.revalidate();
                    centerPanel.repaint();
                }
                    break;
                case LOGEMENT:
                case GARAGE: {
                    // pareil mais on ajoute les champs
                    centerPanel.add(lblSurface);
                    centerPanel.add(surfaceField);
                    centerPanel.add(lblNFiscal);
                    centerPanel.add(NFiscalField);
                    centerPanel.add(lblNPieces);
                    centerPanel.add(NPiecesField);
                    centerPanel.revalidate();
                    centerPanel.repaint();
                }
                    break;
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(220, 220, 240));
        this.contentPane.add(bottomPanel, BorderLayout.SOUTH);

        erreursListModel = new DefaultListModel<String>();
        erreursList = new JList<>(erreursListModel);
        erreursList.setEnabled(false);
        erreursList.setFont(new Font("Rockwell", Font.PLAIN, 14));
        erreursList.setForeground(Color.RED);
        erreursList.setBorder(BorderFactory.createLineBorder(Color.RED));
        erreursList.setModel(erreursListModel);
        bottomPanel.add(erreursList);

        JButton btnSave = new JButton("Ajouter");
        btnSave.setBackground(new Color(100, 200, 100));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Rockwell", Font.BOLD, 14));
        bottomPanel.add(btnSave);

        JButton btnCancel = new JButton("Annuler");
        btnCancel.setBackground(new Color(200, 100, 100));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Rockwell", Font.BOLD, 14));
        btnCancel.addActionListener(e -> this.dispose());
        bottomPanel.add(btnCancel);

        ControleurAjoutBiens controleur = new ControleurAjoutBiens(P, this);

        btnSave.addActionListener(controleur);
    }

    public String getId() {
        return IdField.getText();
    }

    // Getters pour chaque champ
    public String getAdresse() {
        return adresseField.getText();
    }

    public String getComplementAdresse() {
        return complementAdresse.getText();
    }

    public int getCodePostal() {
        return (int) codePostalField.getValue();
    }

    public String getVille() {
        return villeField.getText();
    }

    public float getSurface() {
        return (float) surfaceField.getValue();
    }

    public long getNFiscal() {
        return (long) NFiscalField.getValue();
    }

    public String getTypeBien() {
        return (String) cmbType.getSelectedItem();
    }

    public int getNPieces() {
        return (int) NPiecesField.getValue();
    }

    public void addErreur(String erreur) {
        erreursListModel.addElement(erreur);
        erreursList.setModel(erreursListModel);
    }

    public void clearErreurs() {
        if (erreursListModel != null) {
            erreursListModel = new DefaultListModel<String>();
        }
        erreursListModel.clear();
        erreursList.setModel(erreursListModel);
    }

    public boolean hasErreurs() {
        return erreursListModel.size() > 0;
    }

    public static void main(String[] args) {
        Proprietaire P = new Proprietaire("1", "VOISIN", "Clément", new LinkedList<BienImmobilier>());
        SwingUtilities.invokeLater(() -> {
            try {
                FenAjoutBien frame = new FenAjoutBien(P);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
