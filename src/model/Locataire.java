package model;

import java.util.List;

public class Locataire {
  private String id;
  private String nom;
  private String prenom;
  private String email;
  private String telephone;
  private List<BienImmobilier> biens;

  public Locataire(String id, String nom, String prenom, String email, String telephone, List<BienImmobilier> biens) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.telephone = telephone;
    this.biens = biens;
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

  public List<BienImmobilier> getBiens() {
    return biens;
  }

  public void addBien(BienImmobilier bien) {
    biens.add(bien);
  }

  public void removeBien(BienImmobilier bien) {
    biens.remove(bien);
  }
}
