package view;

import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner.DateEditor;

import components.Bouton;
import components.ChampSaisie;
import components.Libellé;
import components.Liste;
import components.Tableau;
import components.Bouton.VarianteButton;
import components.ChampSaisie.TypeChamp;
import components.Libellé.TypeLibellé;

public class VueComponents extends JFrame {
  public VueComponents() {
    this.setTitle("Composants");
    this.setLayout(new FlowLayout(0, 10, 10));
    this.setBounds(0, 0, 1000, 1000);
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

    // Liste
    Liste<String> liste = new Liste<>();
    liste.addElement("Elément 1");
    liste.addElement("Elément 2");
    liste.addElement("Elément 3");

    JScrollPane listeScrollPane = new JScrollPane(liste);

    // Ajout de la liste
    this.add(listeScrollPane);

    // Tableau
    Tableau tableau = new Tableau("Colonne 1", "Colonne 2", "Colonne 3");

    tableau.addRow("Élément 1", "Élément 2", "Élément 3");
    tableau.addRow("Élément 1", "Élément 2", "Élément 3");
    tableau.addRow("Élément 1", "Élément 2", "Élément 3");

    JScrollPane tableauScrollPane = new JScrollPane(tableau);

    // Ajout du tableau
    this.add(tableauScrollPane);

    // Libellés
    Libellé libelléEnTête = new Libellé("En tête", TypeLibellé.EN_TETE);
    Libellé libelléTitre = new Libellé("Titre", TypeLibellé.TITRE);
    Libellé libelléSousTitre = new Libellé("Sous-titre", TypeLibellé.SOUS_TITRE);
    Libellé libelléParagraphe = new Libellé("Paragraphe", TypeLibellé.PARAGRAPHE);
    Libellé libelléParagrapheDéfaut = new Libellé("Paragraphe (constructeur par défaut)");
    Libellé libelléDanger = new Libellé("Danger", TypeLibellé.DANGER);
    Libellé libelléSuccès = new Libellé("Succès", TypeLibellé.SUCCES);
    Libellé libelléSousTexte = new Libellé("Sous-texte", TypeLibellé.SOUS_TEXTE);

    JPanel panelLibellés = new JPanel();
    panelLibellés.setLayout(new BoxLayout(panelLibellés, BoxLayout.Y_AXIS));

    // Ajout des libellés
    panelLibellés.add(libelléEnTête);
    panelLibellés.add(libelléTitre);
    panelLibellés.add(libelléSousTitre);
    panelLibellés.add(libelléParagraphe);
    panelLibellés.add(libelléParagrapheDéfaut);
    panelLibellés.add(libelléDanger);
    panelLibellés.add(libelléSuccès);
    panelLibellés.add(libelléSousTexte);
    this.add(panelLibellés);

  }

  public static void main(String[] args) {
    new VueComponents().setVisible(true);
  }
}
