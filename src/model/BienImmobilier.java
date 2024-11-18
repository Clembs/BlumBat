package model;

public class BienImmobilier {
  private String id;
  private TypeBien typeBien;
  private String adresse;
  private String complementAdresse;
  private int numeroAppt;
  private String nomResidence;
  private int codePostal;
  private String ville;

  public BienImmobilier(String id, TypeBien typeBien, String adresse, String complementAdresse, int numeroAppt,
      String nomResidence, int codePostal, String ville) {
    this.id = id;
    this.typeBien = typeBien;
    this.adresse = adresse;
    this.complementAdresse = complementAdresse;
    this.numeroAppt = numeroAppt;
    this.nomResidence = nomResidence;
    this.codePostal = codePostal;
    this.ville = ville;
  }
}