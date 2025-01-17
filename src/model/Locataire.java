package model;

import java.util.LinkedList;
import java.util.List;

public class Locataire {
  private String id;
  private String nom;
  private String prenom;
  private String email;
  private String telephone;
  private List<Location> locations;

  public Locataire(String id, String nom, String prenom, String email, String telephone) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.telephone = telephone;
    this.locations = new LinkedList<>();
  }

  public String getId() {
    return this.id;
  }

  public String getNom() {
    return this.nom;
  }

  public String getPrenom() {
    return this.prenom;
  }

  public String getNomComplet() {
    return this.prenom + " " + this.nom;
  }

  public String getEmail() {
    return this.email;
  }

  public String getTelephone() {
    return this.telephone;
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
