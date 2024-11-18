package model;

import java.util.List;

public class BienImmobilier {
  private String id;
  private TypeBien typeBien;
  private String adresse;
  private String complementAdresse;
  private int numeroAppt;
  private String nomResidence;
  private int codePostal;
  private String ville;
  private List<Location> locations;

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

  public int getNumeroAppt() {
    return this.numeroAppt;
  }

  public String getNomResidence() {
    return this.nomResidence;
  }

  public int getCodePostal() {
    return this.codePostal;
  }

  public String getVille() {
    return this.ville;
  }

  public List<Location> getLocations() {
    return this.locations;
  }

  public void addLocation(Location location) {
    this.locations.add(location);
  }

  public void removeLocation(Location location) {
    this.locations.remove(location);
  }
}