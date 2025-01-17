package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import components.Bouton;
import components.Layout;
import components.Libellé;
import components.Bouton.VarianteButton;
import components.Libellé.TypeLibellé;
import controller.ControleurDétailsBien;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;

public class VueDétailsBien extends JPanel {
	private static final long serialVersionUID = 1L;

	public VueDétailsBien(VueBiens fenetre, Proprietaire proprietaire, BienImmobilier bien) {
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

		Libellé lblIdentifiantKey = new Libellé("Identifiant", TypeLibellé.CLEF);
		panelIdentifiant.add(lblIdentifiantKey);

		Libellé lblIdentifiantValue = new Libellé(bien.getId());
		panelIdentifiant.add(lblIdentifiantValue);

		JPanel panelType = new JPanel(new GridLayout(2, 0, 0, 0));
		premièreLigne.add(panelType);

		Libellé lblTypeKey = new Libellé("Type de bien", TypeLibellé.CLEF);
		panelType.add(lblTypeKey);

		Libellé lblTypeValue = new Libellé(bien.getTypeBien().toString());
		panelType.add(lblTypeValue);

		// deuxième ligne : adresse
		JPanel deuxièmeLigne = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 5));
		panelInfos.add(deuxièmeLigne);

		JPanel panelAdresse = new JPanel();
		panelAdresse.setLayout(new BoxLayout(panelAdresse, BoxLayout.Y_AXIS));
		deuxièmeLigne.add(panelAdresse);

		Libellé lblAdresseKey = new Libellé("Adresse", TypeLibellé.CLEF);
		panelAdresse.add(lblAdresseKey);

		Libellé lblAdresseValue = new Libellé(bien.getAdresse());
		panelAdresse.add(lblAdresseValue);

		if (bien.getComplementAdresse() != null) {
			Libellé lblComplémentAdresse = new Libellé(bien.getComplementAdresse());
			panelAdresse.add(lblComplémentAdresse);
		}

		Libellé lblVille = new Libellé(bien.getCodePostal() + " " + bien.getVille());
		panelAdresse.add(lblVille);

		if (bien instanceof BienLocatif) {
			BienLocatif bienL = (BienLocatif) bien;

			// troisième ligne : surface, numéro fiscal, nombre de pièces
			JPanel troisièmeLigne = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 5));
			panelInfos.add(troisièmeLigne);

			JPanel panelSurface = new JPanel(new GridLayout(2, 0, 0, 0));
			troisièmeLigne.add(panelSurface);

			Libellé lblSurfaceKey = new Libellé("Surface", TypeLibellé.CLEF);
			panelSurface.add(lblSurfaceKey);

			Libellé lblSurfaceValue = new Libellé(bienL.getSurface() + "m²");
			panelSurface.add(lblSurfaceValue);

			JPanel panelNumeroFiscal = new JPanel(new GridLayout(2, 0, 0, 0));
			troisièmeLigne.add(panelNumeroFiscal);

			Libellé lblFiscalKey = new Libellé("Numéro fiscal", TypeLibellé.CLEF);
			panelNumeroFiscal.add(lblFiscalKey);

			Libellé lblFiscalValue = new Libellé(bienL.getNumeroFiscal());
			panelNumeroFiscal.add(lblFiscalValue);

			JPanel panelPieces = new JPanel(new GridLayout(2, 0, 0, 0));
			troisièmeLigne.add(panelPieces);

			Libellé lblPiecesKey = new Libellé("Nombre de pièces", TypeLibellé.CLEF);
			panelPieces.add(lblPiecesKey);

			Libellé lblPiecesValue = new Libellé(String.valueOf(bienL.getNombrePieces()));
			panelPieces.add(lblPiecesValue);

			// quatrième ligne : disponibilité
			JPanel quatrièmeLigne = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 5));
			panelInfos.add(quatrièmeLigne);

			JPanel panelDisponibilité = new JPanel(new GridLayout(2, 0, 0, 0));
			quatrièmeLigne.add(panelDisponibilité);

			Libellé lblDisponibilitéKey = new Libellé("Disponibilité", TypeLibellé.CLEF);
			panelDisponibilité.add(lblDisponibilitéKey);

			String disponibilité;
			if (bienL.estLoué()) {
				disponibilité = "En location depuis le " +
						bienL.getLocationsCourantes().get(0).getDateEntree()
								.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRENCH));
			} else {
				disponibilité = "Disponible";
			}
			Libellé lblDisponibilitéValue = new Libellé(disponibilité);
			panelDisponibilité.add(lblDisponibilitéValue);

			JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 5));
			panelInfos.add(panel_2);
		}

		// Panel du bas contenant les boutons
		JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.TRAILING, 10, 10));
		panelBoutons.setBorder(new MatteBorder(1, 0, 0, 0, Layout.COULEUR_SOUS_TEXTE));

		ControleurDétailsBien controleur = new ControleurDétailsBien(fenetre, this, proprietaire, bien);

		if (bien instanceof BienLocatif) {
			Bouton btnLouer = new Bouton("Louer", VarianteButton.SECONDAIRE);
			btnLouer.addActionListener(controleur);
			panelBoutons.add(btnLouer);
		}

		Bouton btnSupprimer = new Bouton("Supprimer", VarianteButton.DANGER);
		btnSupprimer.addActionListener(controleur);
		panelBoutons.add(btnSupprimer);

		Bouton btnModifier = new Bouton("Modifier");
		btnModifier.addActionListener(controleur);
		panelBoutons.add(btnModifier);

		add(panelBoutons, BorderLayout.SOUTH);
	}
}
