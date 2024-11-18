package model;

import java.util.List;

public class Locataire {
  private String id;
  private String nom;
  private String prenom;
  private String email;
  private String telephone;
  private List<Location> locations;

  public Locataire(String id, String nom, String prenom, String email, String telephone, List<Location> locations) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.telephone = telephone;
    this.locations = locations;
  }

  public String getId() {
    return id;
  }

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getEmail() {
    return email;
  }

  public String getTelephone() {
    return telephone;
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
