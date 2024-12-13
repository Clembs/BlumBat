package model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BienImmobilier {
  private String id;
  private TypeBien typeBien;
  private String adresse;
  private String complementAdresse;
  private String codePostal;
  private String ville;
  private List<Location> locations;

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
    this.locations = new LinkedList<>();
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

  public List<Location> getLocations() {
    return this.locations;
  }

  public void addLocation(Location location) {
    this.locations.add(location);
  }

  public void removeLocation(Location location) {
    this.locations.remove(location);
  }

  public boolean estLoué() {
    // Retourne vrai s'il n'y a aucune location sans date de sortie
    return !this.locations.stream()
        .allMatch(location -> location.getDateSortie() == null);
  }

  // récupère la location courante
  public Location getLocationCourante() {
    return this.locations.stream()
        .filter(location -> location.getDateSortie() == null)
        .findFirst()
        .orElse(null);
  }

  // récupère toutes les locations passées
  public List<Location> getLocationsPassees() {
    return this.locations.stream()
        .filter(location -> location.getDateSortie() != null)
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return this.typeBien + " - " + this.adresse + ", " + this.codePostal + " " + this.ville;
  }
}