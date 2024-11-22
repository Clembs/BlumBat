package model;

public enum TypeBien {
  BATIMENT("BATIMENT"), LOGEMENT("LOGEMENT"), GARAGE("GARAGE");

  private String typeBienString;

  private TypeBien(String typeBienString) {
    this.typeBienString = typeBienString;
  }

  public String toString() {
    return this.typeBienString;
  }

  public static TypeBien getTypeBien(String typeBienString) {
    for (TypeBien t : TypeBien.values()) {
      if (t.toString().equals(typeBienString)) {
        return t;
      }
    }

    return null;
  }
}
