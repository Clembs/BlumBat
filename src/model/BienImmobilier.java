package model;

import java.util.List;

public class BienImmobilier {
  private String id;
  private TypeBien typeBien;
  private String adresse;
  private String complementAdresse;
  private int codePostal;
  private String ville;
  private List<Location> locations;

  public BienImmobilier(
      String id, TypeBien typeBien,
      String adresse, String complementAdresse,
      int codePostal, String ville) {
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
  
  @Override
  public String toString() {
      return this.typeBien + " - " + this.adresse + ", " + this.codePostal + " " + this.ville;
  }
}