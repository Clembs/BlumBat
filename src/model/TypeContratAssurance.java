package model;

public enum TypeContratAssurance {
  PROPRIETAIRE("PROPRIETAIRE"), LOCATAIRE("LOCATAIRE");

  private String typeContratString;

  private TypeContratAssurance(String typeContratString) {
    this.typeContratString = typeContratString;
  }

  public String toString() {
    return this.typeContratString;
  }
}
