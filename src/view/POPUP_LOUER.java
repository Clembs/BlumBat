package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.thoughtworks.qdox.parser.ParseException;

import controleur.Controleur_Ajout_Location;
import model.BienImmobilier;
import model.Locataire;
import model.TypeBien;

public class POPUP_LOUER extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtLoyer;
    private JTextField txtDateEntree;
    private JTextField txtDateSortie;
    private BienImmobilier bien;
    private List<Locataire> locataires;

    public POPUP_LOUER(BienImmobilier b) {
        locataires = new ArrayList<>();
        Controleur_Ajout_Location controleur = new Controleur_Ajout_Location(this, b);

        setBounds(100, 100, 600, 500);
        setClosable(true);
        setResizable(true);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BorderLayout(15, 15));
        contentPane.setBackground(new Color(45, 45, 60));
        setContentPane(contentPane);

        JLabel lblTitre = new JLabel("Louer");
        lblTitre.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitre.setForeground(Color.WHITE);
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(lblTitre, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel(new GridLayout(5, 2, 10, 10));
        panelCenter.setBackground(new Color(50, 50, 70));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(panelCenter, BorderLayout.CENTER);

        JLabel lblLoyer = new JLabel("Loyer :");
        lblLoyer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblLoyer.setForeground(Color.WHITE);
        panelCenter.add(lblLoyer);

        txtLoyer = new JTextField();
        txtLoyer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtLoyer.setBackground(new Color(60, 60, 75));
        txtLoyer.setForeground(Color.WHITE);
        txtLoyer.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100)));
        panelCenter.add(txtLoyer);

        JLabel lblDateEntree = new JLabel("Date d'Entrée (dd/MM/yyyy) :");
        lblDateEntree.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDateEntree.setForeground(Color.WHITE);
        panelCenter.add(lblDateEntree);

        txtDateEntree = new JTextField();
        txtDateEntree.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDateEntree.setBackground(new Color(60, 60, 75));
        txtDateEntree.setForeground(Color.WHITE);
        txtDateEntree.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100)));
        panelCenter.add(txtDateEntree);

        JLabel lblDateSortie = new JLabel("Date de Sortie (dd/MM/yyyy) :");
        lblDateSortie.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDateSortie.setForeground(Color.WHITE);
        panelCenter.add(lblDateSortie);

        txtDateSortie = new JTextField();
        txtDateSortie.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDateSortie.setBackground(new Color(60, 60, 75));
        txtDateSortie.setForeground(Color.WHITE);
        txtDateSortie.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 100)));
        panelCenter.add(txtDateSortie);

        JLabel lblLocataires = new JLabel("Locataires :");
        lblLocataires.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblLocataires.setForeground(Color.WHITE);
        panelCenter.add(lblLocataires);

        JButton btnSlectionner = new JButton("Sélectionner");
        btnSlectionner.setForeground(Color.WHITE);
        btnSlectionner.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSlectionner.setFocusPainted(false);
        btnSlectionner.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnSlectionner.setBackground(new Color(0, 170, 85));
        panelCenter.add(btnSlectionner);

        JPanel panelFooter = new JPanel();
        panelFooter.setBackground(new Color(45, 45, 60));
        contentPane.add(panelFooter, BorderLayout.SOUTH);
        panelFooter.setLayout(new BorderLayout(0, 0));

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(new Color(45, 45, 60));
        panelFooter.add(panelBtn, BorderLayout.EAST);

        JButton btnLouer = new JButton("Louer");
        btnLouer.setForeground(Color.WHITE);
        btnLouer.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLouer.setFocusPainted(false);
        btnLouer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLouer.setBackground(new Color(0, 170, 85));
        panelBtn.add(btnLouer);
        btnLouer.addActionListener(controleur);
        btnSlectionner.addActionListener(controleur);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Test POPUP_LOUER");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.getContentPane().setLayout(new BorderLayout());

                JDesktopPane desktopPane = new JDesktopPane();
                frame.getContentPane().add(desktopPane, BorderLayout.CENTER);

                POPUP_LOUER popup = new POPUP_LOUER(
                        new BienImmobilier("3344", TypeBien.BATIMENT, "Rue ta mere", "ap4", 81000, "Toulouse"));
                desktopPane.add(popup);
                popup.setVisible(true);

                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public double getLoyer() {
        try {
            return Double.parseDouble(txtLoyer.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Le loyer doit être un nombre valide.", e);
        }
    }

    public Date getDateEntree() throws java.text.ParseException {
        return parseDate(txtDateEntree.getText().trim(), "Date d'entrée invalide. Utilisez le format dd/MM/yyyy.");
    }

    public Date getDateSortie() throws java.text.ParseException {
        return parseDate(txtDateSortie.getText().trim(), "Date de sortie invalide. Utilisez le format dd/MM/yyyy.");
    }

    private Date parseDate(String dateStr, String errorMessage) throws java.text.ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException(errorMessage, e);
        }
    }

    public List<Locataire> getLocataires() {
        if (locataires.isEmpty()) {
            throw new IllegalStateException("Aucun locataire n'a été sélectionné.");
        }
        return new ArrayList<>(locataires);
    }
}
