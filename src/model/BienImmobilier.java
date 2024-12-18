package model;

public class BienImmobilier {
  private String id;
  private TypeBien typeBien;
  private String adresse;
  private String complementAdresse;
  private String codePostal;
  private String ville;

  public BienImmobilier(
      String id, TypeBien typeBien,
      String adresse, String complementAdresse,
      String codePostal, String ville) {
    this.id = id;
    this.typeBien = typeBien;
    this.adresse = adresse;
    this.complementAdresse = complementAdresse;
    this.codePostal = codePostal;
    this.ville = ville;
  }

  public String getId() {
    return this.id;
  }

  public TypeBien getTypeBien() {
    return this.typeBien;
  }

  public String getAdresse() {
    return this.adresse;
  }

  public String getComplementAdresse() {
    return this.complementAdresse;
  }

  public String getCodePostal() {
    return this.codePostal;
  }

  public String getVille() {
    return this.ville;
  }

  @Override
  public String toString() {
    return this.typeBien + " - " + this.adresse + ", " + this.codePostal + " " + this.ville;
  }
}