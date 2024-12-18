package model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BienLocatif extends BienImmobilier {
  private String numeroFiscal;
  private float surface;
  private int nombrePieces;
  private List<Location> locations;

  public BienLocatif(
      String id,
      TypeBien type,
      String adresse,
      String complementAdresse, String codePostal,
      String ville, String numeroFiscal,
      float surface, int nombrePieces) {
    super(id, type, adresse, complementAdresse, codePostal, ville);
    this.numeroFiscal = numeroFiscal;
    this.surface = surface;
    this.nombrePieces = nombrePieces;
    this.locations = new LinkedList<>();
  }

  public String getNumeroFiscal() {
    return this.numeroFiscal;
  }

  public float getSurface() {
    return this.surface;
  }

  public int getNombrePieces() {
    return this.nombrePieces;
  }

  public List<Location> getLocations() {
    return this.locations;
  }

  public void setLocations(List<Location> locations) {
    this.locations = locations;
  }

  public void addLocation(Location location) {
    this.locations.add(location);
  }

  public void removeLocation(Location location) {
    this.locations.remove(location);
  }

  public boolean estLoué() {
    return this.getLocationsCourantes().size() > 0;
  }

  // récupère les locations en cours (plusieurs dans le cas d'une colocation)
  public List<Location> getLocationsCourantes() {
    return this.locations.stream()
        .filter(location -> location.getDateSortie() == null)
        .collect(Collectors.toList());
  }

  // récupère toutes les locations passées
  public List<Location> getLocationsPassees() {
    return this.locations.stream()
        .filter(location -> location.getDateSortie() != null)
        .collect(Collectors.toList());
  }

}
