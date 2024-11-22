package model;

public class Locataire {
  private String id;
  private String nom;
  private String prenom;
  private String email;
  private String telephone;

  public Locataire(String id, String nom, String prenom, String email, String telephone) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.telephone = telephone;
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

}
