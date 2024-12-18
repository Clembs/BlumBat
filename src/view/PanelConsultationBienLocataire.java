package view;

import controller.ControleurConsultationLocataires;
import model.Locataire;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


//Panel pour lister les biens de chaque locataire à faire !
public class PanelConsultationBienLocataire extends JFrame{
    private static final long serialVersionUID = 1L;
    private JPanel MainPane;
    private JTable tableBiens;
    private JTextField txtNom, txtPrenom, txtAdresse, txtTelephone;
    private JList<String> locatairesList;
    private ControleurConsultationLocataires controleur;

    public PanelConsultationBienLocataire(Locataire locataire) {
        // Panel  (Informations & Table)
        JPanel RightPanel = new JPanel(new BorderLayout(5, 5));
        // InfoPanel
        JPanel InfoPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        InfoPanel.setBorder(new TitledBorder(new EtchedBorder(), "Informations Personnelles", TitledBorder.CENTER, TitledBorder.TOP));
        InfoPanel.setBackground(Color.LIGHT_GRAY);

        JLabel lblNom = new JLabel("Nom:", SwingConstants.CENTER);
        txtNom = new JTextField();
        txtNom.setFont(new Font("Rockwell", Font.PLAIN, 12));
        txtNom.setEditable(false);

        JLabel lblPrenom = new JLabel("Prénom:", SwingConstants.CENTER);
        txtPrenom = new JTextField();
        txtPrenom.setFont(new Font("Rockwell", Font.PLAIN, 12));
        txtPrenom.setEditable(false);

        JLabel lblAdresse = new JLabel("Adresse:", SwingConstants.CENTER);
        txtAdresse = new JTextField();
        txtAdresse.setFont(new Font("Rockwell", Font.PLAIN, 12));
        txtAdresse.setEditable(false);

        JLabel lblTelephone = new JLabel("Téléphone:", SwingConstants.CENTER);
        txtTelephone = new JTextField();
        txtTelephone.setFont(new Font("Rockwell", Font.PLAIN, 12));
        txtTelephone.setEditable(false);

        InfoPanel.add(lblNom);
        InfoPanel.add(txtNom);
        InfoPanel.add(lblPrenom);
        InfoPanel.add(txtPrenom);
        InfoPanel.add(lblAdresse);
        InfoPanel.add(txtAdresse);
        InfoPanel.add(lblTelephone);
        InfoPanel.add(txtTelephone);

        RightPanel.add(InfoPanel, BorderLayout.NORTH);

        // Panel (Liste des biens)
        JPanel TablePanel = new JPanel(new BorderLayout());
        TablePanel.setBackground(Color.LIGHT_GRAY);
        TablePanel.setBorder(new TitledBorder(new EtchedBorder(), "Liste des biens", TitledBorder.CENTER, TitledBorder.TOP));

        tableBiens = new JTable(new DefaultTableModel(
                new Object[][] {

                },
                new String[] {"Numéro Fiscale", "Adresse", "Type"}
        ));
        tableBiens.setFont(new Font("Rockwell", Font.PLAIN, 12));
        JScrollPane tableScrollPane = new JScrollPane(tableBiens);
        TablePanel.add(tableScrollPane, BorderLayout.CENTER);
        RightPanel.add(TablePanel, BorderLayout.CENTER);

        MainPane.add(RightPanel, BorderLayout.CENTER);


    }

}

