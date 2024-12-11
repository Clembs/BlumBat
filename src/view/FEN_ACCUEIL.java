package view;

import javax.swing.*;

import java.awt.*;

import model.Proprietaire;

public class FEN_ACCUEIL extends JFrame {
    private JLabel labelLoyersImpayes;
    private JLabel labelCharges;
    private JLabel labelLocatairesDepart;

    public FEN_ACCUEIL( Proprietaire p) {
        setTitle("Tableau de bord");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel labelTitre = new JLabel("Tableau de bord");
        labelTitre.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitre.setForeground(new Color(60, 60, 60));
        labelTitre.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitre.setBounds(50, 30, 500, 40);
        getContentPane().add(labelTitre);

        JPanel panelLoyers = new JPanel();
        panelLoyers.setBackground(new Color(230, 230, 250));
        panelLoyers.setBounds(50, 100, 500, 60);
        panelLoyers.setLayout(new BorderLayout());
        JLabel labelLoyersTitre = new JLabel("Loyers impayés:");
        labelLoyersTitre.setFont(new Font("Arial", Font.BOLD, 16));
        labelLoyersTitre.setForeground(new Color(75, 75, 75));
        panelLoyers.add(labelLoyersTitre, BorderLayout.WEST);
        labelLoyersImpayes = new JLabel("0 loyers impayés");
        labelLoyersImpayes.setFont(new Font("Arial", Font.PLAIN, 16));
        panelLoyers.add(labelLoyersImpayes, BorderLayout.EAST);
        getContentPane().add(panelLoyers);

        JPanel panelCharges = new JPanel();
        panelCharges.setBackground(new Color(230, 230, 250));
        panelCharges.setBounds(50, 180, 500, 60);
        panelCharges.setLayout(new BorderLayout());
        JLabel labelChargesTitre = new JLabel("Charges:");
        labelChargesTitre.setFont(new Font("Arial", Font.BOLD, 16));
        labelChargesTitre.setForeground(new Color(75, 75, 75));
        panelCharges.add(labelChargesTitre, BorderLayout.WEST);
        labelCharges = new JLabel("0 charges en attente");
        labelCharges.setFont(new Font("Arial", Font.PLAIN, 16));
        panelCharges.add(labelCharges, BorderLayout.EAST);
        getContentPane().add(panelCharges);

        JPanel panelLocataires = new JPanel();
        panelLocataires.setBackground(new Color(230, 230, 250));
        panelLocataires.setBounds(50, 260, 500, 60);
        panelLocataires.setLayout(new BorderLayout());
        JLabel labelLocatairesTitre = new JLabel("Locataires en départ:");
        labelLocatairesTitre.setFont(new Font("Arial", Font.BOLD, 16));
        labelLocatairesTitre.setForeground(new Color(75, 75, 75));
        panelLocataires.add(labelLocatairesTitre, BorderLayout.WEST);
        labelLocatairesDepart = new JLabel("0 locataires en départ");
        labelLocatairesDepart.setFont(new Font("Arial", Font.PLAIN, 16));
        panelLocataires.add(labelLocatairesDepart, BorderLayout.EAST);
        getContentPane().add(panelLocataires);

        JButton boutonRafraichir = new JButton("Rafraîchir");
        boutonRafraichir.setBounds(150, 350, 130, 35);
        boutonRafraichir.setBackground(new Color(100, 149, 237));
        boutonRafraichir.setForeground(Color.WHITE);
        boutonRafraichir.setFont(new Font("Arial", Font.BOLD, 14));
        boutonRafraichir.setFocusPainted(false);

        getContentPane().add(boutonRafraichir);

        JButton boutonDeconnexion = new JButton("Déconnexion");
        boutonDeconnexion.setBounds(300, 350, 130, 35);
        boutonDeconnexion.setBackground(new Color(255, 69, 0));
        boutonDeconnexion.setForeground(Color.WHITE);
        boutonDeconnexion.setFont(new Font("Arial", Font.BOLD, 14));
        boutonDeconnexion.setFocusPainted(false);

        getContentPane().add(boutonDeconnexion);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FEN_ACCUEIL frame = new FEN_ACCUEIL();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
