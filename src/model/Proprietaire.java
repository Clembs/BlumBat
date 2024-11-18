package model;

import java.util.List;

public class Proprietaire {
  private String id;
  private String nom;
  private String prenom;
  private List<BienImmobilier> biens;

  public Proprietaire(String id, String nom, String prenom, List<BienImmobilier> biens) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.biens = biens;
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

  public List<BienImmobilier> getBiens() {
    return this.biens;
  }

  public void addBien(BienImmobilier bien) {
    this.biens.add(bien);
  }

  public void removeBien(BienImmobilier bien) {
    this.biens.remove(bien);
  }
}
