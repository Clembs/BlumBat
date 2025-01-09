package model;

public enum TypeBien {
  // Cet enum a deux attributs, screamingString et niceString.
  // Sur la base de données, le type de bien est enregistré en
  // SCREAMING_SNAKE_CASE (BATIMENT, LOGEMENT, GARAGE)
  // Mais on veut pouvoir afficher un nom plus lisible à l'utilisateur (avec des
  // minuscules et des accents) (Bâtiment, Logement, Garage)
  // D'où l'utilisation de ces deux attributs.
  BATIMENT("BATIMENT", "Bâtiment"), LOGEMENT("LOGEMENT", "Logement"), GARAGE("GARAGE", "Garage");

  private String screamingString;
  private String niceString;

  private TypeBien(String screamingString, String niceString) {
    this.screamingString = screamingString;
    this.niceString = niceString;
  }

  // La méthode toString() retourne le nom lisible par l'utilisateur
  @Override
  public String toString() {
    return this.niceString;
  }

  public String toScreamingString() {
    return this.screamingString;
  }

  public static TypeBien getTypeBien(String typeBienString) {
    for (TypeBien t : TypeBien.values()) {
      // Check si le paramètre en entrée est égal à l'un des deux attributs de notre
      // enum
      if (t.screamingString.toString().equals(typeBienString) || t.niceString.toString().equals(typeBienString)) {
        return t;
      }
    }

    return null;
  }
}
