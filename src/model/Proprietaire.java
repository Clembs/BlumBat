package model;

public class Proprietaire {
  private int id;
  private String nom;
  private String prenom;
  private String email;
  private String motDePasse;

  public Proprietaire(int id, String nom, String prenom, String email, String motDePasse) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.motDePasse = motDePasse;
  }

  public int getId() {
    return this.id;
  }

  public String getNom() {
    return this.nom;
  }

  public String getPrenom() {
    return this.prenom;
  }

  public String getEmail() {
    return this.email;
  }

  public String getMotDePasse() {
    return this.motDePasse;
  }
}
