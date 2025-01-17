package model;

import java.time.LocalDate;

public class FactureTravaux {
  private String id;
  private String description;
  private double montantDevis;
  private double montantFacture;
  private String entreprise;
  private LocalDate date;
  private BienImmobilier bien;

  public FactureTravaux(String id, String description, double montantDevis, double montantFacture, String entreprise,
      LocalDate date, BienImmobilier bien) {
    this.id = id;
    this.description = description;
    this.montantDevis = montantDevis;
    this.montantFacture = montantFacture;
    this.entreprise = entreprise;
    this.date = date;
    this.bien = bien;
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

  public LocalDate getDate() {
    return this.date;
  }
}
