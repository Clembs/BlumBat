package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyLoyerDialog extends JDialog {
    private JTextField txtMontantLoyer;
    private JButton btnConfirmer;
    private JButton btnAnnuler;
    private boolean confirmed = false;

    public ModifyLoyerDialog(Frame parent, String locationId, float montantLoyer) {
        super(parent, "Modifier le montant du loyer", true);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Modifier le loyer de: " + locationId);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Montant total du loyer :"), gbc);

        gbc.gridx = 1;
        txtMontantLoyer = new JTextField(String.valueOf(montantLoyer), 10);
        panel.add(txtMontantLoyer, gbc);

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        btnConfirmer = new JButton("Confirmer");
        btnAnnuler = new JButton("Annuler");

        buttonPanel.add(btnConfirmer);
        buttonPanel.add(btnAnnuler);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 150);
        setLocationRelativeTo(parent);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModifyLoyerDialog dialog = new ModifyLoyerDialog(null, "LOC123", 800.0f);
            dialog.setVisible(true);
        });
    }
}