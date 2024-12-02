package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.Controleur_Ajout_Biens;
import model.BienImmobilier;
import model.Proprietaire;

public class FEN_Ajout_Biens extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField villeField;
    private JTextField adresseField;
    private JTextField codePostalField;
    private JTextField surfaceField;
    private JTextField NFiscalField;
    private JTextArea descriptionArea;
    private JTextField NPiecesField;
    private JTextField IdField;
    private JComboBox<String> cmbType;

    public FEN_Ajout_Biens(Proprietaire P) {
        this.setTitle("Ajout d'un Bien Immobilier");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 600, 500);

        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.contentPane.setBackground(new Color(240, 240, 250));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(new BorderLayout(20, 20));

        JLabel lblTitle = new JLabel("Ajout d'un Bien Immobilier");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("rockewell", Font.BOLD, 28));
        lblTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(150, 150, 200)));
        this.contentPane.add(lblTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(9, 2, 10, 10));
        centerPanel.setBackground(new Color(240, 240, 250));
        this.contentPane.add(centerPanel, BorderLayout.CENTER);

        JLabel lblId = new JLabel("Id:");
        lblId.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setForeground(new Color(80, 80, 100));
        centerPanel.add(lblId);

        IdField = new JTextField();
        IdField.setFont(new Font("rockewell", Font.PLAIN, 14));
        IdField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(IdField);

        JLabel lblType = new JLabel("Type de Bien:");
        lblType.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblType.setHorizontalAlignment(SwingConstants.CENTER);
        lblType.setForeground(new Color(80, 80, 100));
        centerPanel.add(lblType);

        cmbType = new JComboBox<>(new String[] { "BATIMENT", "LOGEMENT", "GARAGE" });
        cmbType.setFont(new Font("rockewell", Font.PLAIN, 14));
        cmbType.setBorder(new LineBorder(new Color(150, 150, 150)));
        centerPanel.add(cmbType);

        JLabel lblAddress = new JLabel("Adresse:");
        lblAddress.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddress.setForeground(new Color(80, 80, 100));
        centerPanel.add(lblAddress);

        adresseField = new JTextField();
        adresseField.setFont(new Font("rockewell", Font.PLAIN, 14));
        adresseField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(adresseField);

        JLabel lblVille = new JLabel("Ville :");
        lblVille.setHorizontalAlignment(SwingConstants.CENTER);
        lblVille.setForeground(new Color(80, 80, 100));
        lblVille.setFont(new Font("Rockwell", Font.BOLD, 14));
        centerPanel.add(lblVille);

        villeField = new JTextField();
        villeField.setFont(new Font("rockewell", Font.PLAIN, 14));
        villeField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(villeField);

        JLabel lblDpartement = new JLabel("Code Postal :");
        lblDpartement.setHorizontalAlignment(SwingConstants.CENTER);
        lblDpartement.setForeground(new Color(80, 80, 100));
        lblDpartement.setFont(new Font("Rockwell", Font.BOLD, 14));
        centerPanel.add(lblDpartement);

        codePostalField = new JTextField();
        codePostalField.setFont(new Font("rockewell", Font.PLAIN, 14));
        codePostalField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(codePostalField);

        JLabel lblSurface = new JLabel("Surface (m²):");
        lblSurface.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblSurface.setHorizontalAlignment(SwingConstants.CENTER);
        lblSurface.setForeground(new Color(80, 80, 100));
        centerPanel.add(lblSurface);

        surfaceField = new JTextField();
        surfaceField.setFont(new Font("rockewell", Font.PLAIN, 14));
        surfaceField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(surfaceField);

        JLabel NFiscal = new JLabel("Numero Fiscal:");
        NFiscal.setFont(new Font("Rockwell", Font.BOLD, 14));
        NFiscal.setHorizontalAlignment(SwingConstants.CENTER);
        NFiscal.setForeground(new Color(80, 80, 100));
        centerPanel.add(NFiscal);

        NFiscalField = new JTextField();
        NFiscalField.setFont(new Font("rockewell", Font.PLAIN, 14));
        NFiscalField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(NFiscalField);

        JLabel NPieces = new JLabel("Numero de Pieces:");
        NPieces.setFont(new Font("Rockwell", Font.BOLD, 14));
        NPieces.setHorizontalAlignment(SwingConstants.CENTER);
        NPieces.setForeground(new Color(80, 80, 100));
        centerPanel.add(NPieces);

        NPiecesField = new JTextField();
        NPiecesField.setFont(new Font("rockewell", Font.PLAIN, 14));
        NPiecesField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        centerPanel.add(NPiecesField);

        JLabel lblcomplementAdresse = new JLabel("Complement Adresse:");
        lblcomplementAdresse.setFont(new Font("Rockwell", Font.BOLD, 14));
        lblcomplementAdresse.setHorizontalAlignment(SwingConstants.CENTER);
        lblcomplementAdresse.setForeground(new Color(80, 80, 100));
        centerPanel.add(lblcomplementAdresse);

        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setFont(new Font("rockewell", Font.PLAIN, 14));
        descriptionArea.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        centerPanel.add(scrollPane);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(220, 220, 240));
        this.contentPane.add(bottomPanel, BorderLayout.SOUTH);

        JButton btnSave = new JButton("Ajouter");
        btnSave.setBackground(new Color(100, 200, 100));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("rockewell", Font.BOLD, 14));
        bottomPanel.add(btnSave);

        JButton btnCancel = new JButton("Annuler");
        btnCancel.setBackground(new Color(200, 100, 100));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("rockewell", Font.BOLD, 14));
        btnCancel.addActionListener(e -> this.dispose());
        bottomPanel.add(btnCancel);

        Controleur_Ajout_Biens controleur = new Controleur_Ajout_Biens(P, this);

        btnSave.addActionListener(controleur);
    }

    // Getters pour chaque champ
    public String getAdresse() {
        return adresseField.getText();
    }

    public String getVille() {
        return villeField.getText();
    }

    public String getCodePostal() {
        return codePostalField.getText();
    }

    public String getSurface() {
        return surfaceField.getText();
    }

    public String getNFiscal() {
        return NFiscalField.getText();
    }

    public String getComplementAdresse() {
        return descriptionArea.getText();
    }

    public String getTypeBien() {
        return (String) cmbType.getSelectedItem();
    }

    public String getNPieces() {
        return NPiecesField.getText();
    }

    public String getId() {
        return IdField.getText();
    }

    public static void main(String[] args) {
        Proprietaire P = new Proprietaire("1", "VOISIN", "Clément", new LinkedList<BienImmobilier>());
        SwingUtilities.invokeLater(() -> {
            try {
                FEN_Ajout_Biens frame = new FEN_Ajout_Biens(P);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
