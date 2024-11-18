package model;

public class Charges {
  private String id;
  private double montantTotal;
  private double taxeOrdures;
  private BienImmobilier bien;

  public Charges(String id, double montantTotal, double taxeOrdures, BienImmobilier bien) {
    this.id = id;
    this.montantTotal = montantTotal;
    this.taxeOrdures = taxeOrdures;
    this.bien = bien;
  }

  public String getId() {
    return this.id;
  }

  public double getMontantTotal() {
    return this.montantTotal;
  }

  public double getTaxeOrdures() {
    return this.taxeOrdures;
  }

  public BienImmobilier getBien() {
    return this.bien;
  }
}
