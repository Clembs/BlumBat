package model;

public class FactureTravaux {
  private String id;
  private BienImmobilier bien;
  private double montantFacture;
  private double montantDevis;
  private String entreprise;
  private String description;

  public FactureTravaux(String id, BienImmobilier bien, double montantFacture, String description, double montantDevis,
      String entreprise) {
    this.id = id;
    this.bien = bien;
    this.montantFacture = montantFacture;
    this.description = description;
    this.montantDevis = montantDevis;
    this.entreprise = entreprise;
  }

  public String getId() {
    return id;
  }

  public BienImmobilier getBien() {
    return bien;
  }

  public double getMontantFacture() {
    return montantFacture;
  }

  public String getDescription() {
    return description;
  }

  public double getMontantDevis() {
    return montantDevis;
  }

  public String getEntreprise() {
    return entreprise;
  }
}
