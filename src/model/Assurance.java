package model;

public class Assurance {
  private String id;
  private double montant;
  private TypeContratAssurance typeContrat;
  private BienImmobilier bien;

  public Assurance(String id, double montant, TypeContratAssurance typeContrat, BienImmobilier bien) {
    this.id = id;
    this.montant = montant;
    this.typeContrat = typeContrat;
    this.bien = bien;
  }

  public String getId() {
    return this.id;
  }

  public double getMontant() {
    return this.montant;
  }

  public TypeContratAssurance getTypeContrat() {
    return this.typeContrat;
  }

  public BienImmobilier getBien() {
    return this.bien;
  }
}
