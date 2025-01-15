package view;

import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner.DateEditor;

import components.Bouton;
import components.ChampSaisie;
import components.Bouton.VarianteButton;
import components.ChampSaisie.TypeChamp;

public class VueComponents extends JFrame {
  public VueComponents() {
    this.setTitle("Composants");
    this.setLayout(new FlowLayout(0, 10, 10));
    this.setBounds(0, 0, 1000, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Boutons
    Bouton boutonPrimary = new Bouton("Primary");
    Bouton boutonSecondary = new Bouton("Secondary", VarianteButton.SECONDAIRE);
    Bouton boutonSuccess = new Bouton("Success", VarianteButton.SUCCES);
    Bouton boutonDanger = new Bouton("Danger", VarianteButton.DANGER);
    Bouton boutonTexteLong = new Bouton("Bouton avec beaucoup de texte bonjour aaaaaaaaaaaaaaaaaaaaaaaa");

    // Ajout des boutons
    this.add(boutonPrimary);
    this.add(boutonSecondary);
    this.add(boutonSuccess);
    this.add(boutonDanger);
    this.add(boutonTexteLong);

    // Champs de saisie
    ChampSaisie champSaisieSansType = new ChampSaisie("Champ de saisie sans type");
    ChampSaisie champSaisieSansTypeValeur = new ChampSaisie("Champ de saisie sans type avec valeur", "truc");

    ChampSaisie champSaisieTexte = new ChampSaisie("Champ de saisie texte", TypeChamp.TEXTE);
    ChampSaisie champSaisieTexteValeur = new ChampSaisie("Champ de saisie texte", TypeChamp.TEXTE, "truc");

    ChampSaisie champSaisieNombre = new ChampSaisie("Champ de saisie nombre", TypeChamp.NOMBRE);
    ChampSaisie champSaisieNombreValeur = new ChampSaisie("Champ de saisie nombre avec valeur (spinner)",
        new SpinnerNumberModel(10, 0, Integer.MAX_VALUE, 1));
    ChampSaisie champSaisieNombreFlottant = new ChampSaisie("Champ de saisie nombre avec valeur flottante (spinner)",
        new SpinnerNumberModel(10f, 0, Float.MAX_VALUE, 1));
    ChampSaisie champSaisieNombreDate = new ChampSaisie("Champ de saisie nombre avec valeur date (spinner)",
        new SpinnerDateModel());
    JSpinner spinner = (JSpinner) champSaisieNombreDate.getChampSaisie();
    DateEditor dateEditor = new DateEditor(spinner, "dd/MM/yyyy");
    spinner.setEditor(dateEditor);
    spinner.setValue(new Date());

    ChampSaisie champSaisieMotDePasse = new ChampSaisie("Champ de saisie mot de passe", TypeChamp.MOT_DE_PASSE);
    ChampSaisie champSaisieMotDePasseValeur = new ChampSaisie("Champ de saisie mot de passe avec valeur",
        TypeChamp.MOT_DE_PASSE, "truc");

    // Ajout des champs de saisie
    this.add(champSaisieSansType);
    this.add(champSaisieSansTypeValeur);
    this.add(champSaisieTexte);
    this.add(champSaisieTexteValeur);
    this.add(champSaisieNombre);
    this.add(champSaisieNombreValeur);
    this.add(champSaisieNombreFlottant);
    this.add(champSaisieNombreDate);
    this.add(champSaisieMotDePasse);
    this.add(champSaisieMotDePasseValeur);
  }

  public static void main(String[] args) {
    new VueComponents().setVisible(true);
  }
}
