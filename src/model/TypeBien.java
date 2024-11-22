package model;

public enum TypeBien {
  BATIMENT("BATIMENT"), LOGEMENT("LOGEMENT"), GARAGE("GARAGE");

  private String typeBienString;

  private TypeBien(String typeBienString) {
    this.typeBienString = typeBienString;
  }

  @Override
  public String toString() {
    return this.typeBienString;
  }
}
