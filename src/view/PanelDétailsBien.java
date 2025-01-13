package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import controller.ControleurDétailsBien;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;

public class PanelDétailsBien extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelDétailsBien(FenBiens fenetre, Proprietaire proprietaire, BienImmobilier bien) {

		this.setLayout(new BorderLayout(0, 0));

		// panel du haut contenant les informations
		JPanel panelInfos = new JPanel();
		panelInfos.setLayout(new BoxLayout(panelInfos, BoxLayout.Y_AXIS));

		this.add(panelInfos, BorderLayout.CENTER);

		// première ligne : identifiant et type
		JPanel premièreLigne = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 5));

		panelInfos.add(premièreLigne);

		JPanel panelIdentifiant = new JPanel(new GridLayout(2, 0, 0, 0));
		premièreLigne.add(panelIdentifiant);

		JLabel lblIdentifiantKey = new JLabel("Identifiant");
		panelIdentifiant.add(lblIdentifiantKey);

		JLabel lblIdentifiantValue = new JLabel(bien.getId());
		lblIdentifiantValue.setForeground(Color.GRAY);
		panelIdentifiant.add(lblIdentifiantValue);

		JPanel panelType = new JPanel(new GridLayout(2, 0, 0, 0));
		premièreLigne.add(panelType);

		JLabel lblTypeKey = new JLabel("Type de bien");
		panelType.add(lblTypeKey);

		JLabel lblTypeValue = new JLabel(bien.getTypeBien().toString());
		panelType.add(lblTypeValue);
		lblTypeValue.setForeground(Color.GRAY);

		// deuxième ligne : adresse
		JPanel deuxièmeLigne = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 5));
		panelInfos.add(deuxièmeLigne);

		JPanel panelAdresse = new JPanel();
		panelAdresse.setLayout(new BoxLayout(panelAdresse, BoxLayout.Y_AXIS));
		deuxièmeLigne.add(panelAdresse);

		JLabel lblAdresseKey = new JLabel("Adresse");
		panelAdresse.add(lblAdresseKey);

		JLabel lblAdresseValue = new JLabel(bien.getAdresse());
		lblAdresseValue.setForeground(Color.GRAY);
		panelAdresse.add(lblAdresseValue);

		if (bien.getComplementAdresse() != null) {
			JLabel lblComplémentAdresse = new JLabel(bien.getComplementAdresse());
			lblComplémentAdresse.setForeground(Color.GRAY);
			panelAdresse.add(lblComplémentAdresse);
		}

		JLabel lblVille = new JLabel(bien.getCodePostal() + " " + bien.getVille());
		panelAdresse.add(lblVille);
		lblVille.setForeground(Color.GRAY);

		if (bien instanceof BienLocatif) {
			BienLocatif bienL = (BienLocatif) bien;

			// troisième ligne : surface, numéro fiscal, nombre de pièces
			JPanel troisièmeLigne = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 5));
			panelInfos.add(troisièmeLigne);

			JPanel panelSurface = new JPanel(new GridLayout(2, 0, 0, 0));
			troisièmeLigne.add(panelSurface);

			JLabel lblSurfaceKey = new JLabel("Surface");
			panelSurface.add(lblSurfaceKey);

			JLabel lblSurfaceValue = new JLabel(bienL.getSurface() + "m²");
			lblSurfaceValue.setForeground(Color.GRAY);
			panelSurface.add(lblSurfaceValue);

			JPanel panelNumeroFiscal = new JPanel(new GridLayout(2, 0, 0, 0));
			troisièmeLigne.add(panelNumeroFiscal);

			JLabel lblFiscalKey = new JLabel("Numéro fiscal");
			panelNumeroFiscal.add(lblFiscalKey);

			JLabel lblFiscalValue = new JLabel(bienL.getNumeroFiscal());
			panelNumeroFiscal.add(lblFiscalValue);
			lblFiscalValue.setForeground(Color.GRAY);

			JPanel panelPieces = new JPanel(new GridLayout(2, 0, 0, 0));
			troisièmeLigne.add(panelPieces);

			JLabel lblPiecesKey = new JLabel("Nombre de pièces");
			panelPieces.add(lblPiecesKey);

			JLabel lblPiecesValue = new JLabel(String.valueOf(bienL.getNombrePieces()));
			panelPieces.add(lblPiecesValue);
			lblPiecesValue.setForeground(Color.GRAY);

			// quatrième ligne : disponibilité
			JPanel quatrièmeLigne = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 5));
			panelInfos.add(quatrièmeLigne);

			JPanel panelDisponibilité = new JPanel(new GridLayout(2, 0, 0, 0));
			quatrièmeLigne.add(panelDisponibilité);

			JLabel lblDisponibilitéKey = new JLabel("Disponibilité");
			panelDisponibilité.add(lblDisponibilitéKey);

			String disponibilité;
			if (bienL.estLoué()) {
				disponibilité = "En location depuis le " +
						bienL.getLocationsCourantes().get(0).getDateEntree()
								.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH));
			} else {
				disponibilité = "Disponible";
			}
			JLabel lblDisponibilitéValue = new JLabel(disponibilité);
			lblDisponibilitéValue.setForeground(Color.GRAY);
			panelDisponibilité.add(lblDisponibilitéValue);

			JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 5));
			panelInfos.add(panel_2);
		}

		// Panel du bas contenant les boutons
		JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.TRAILING, 10, 10));
		panelBoutons.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));

		ControleurDétailsBien controleur = new ControleurDétailsBien(fenetre, this, proprietaire, bien);

		if (bien instanceof BienLocatif) {
			JButton btnLouer = new JButton("Louer");
			btnLouer.setBackground(new Color(192, 192, 192));
			btnLouer.addActionListener(controleur);
			panelBoutons.add(btnLouer);
		}

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(new Color(255, 128, 128));
		btnSupprimer.addActionListener(controleur);
		panelBoutons.add(btnSupprimer);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBackground(new Color(128, 255, 255));
		btnModifier.addActionListener(controleur);
		panelBoutons.add(btnModifier);

		add(panelBoutons, BorderLayout.SOUTH);
	}
}
