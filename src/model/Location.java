package model;

import java.time.LocalDate;

public class Location {
  private double loyer;
  private LocalDate dateEntree;
  private LocalDate dateSortie;
  private BienImmobilier bien;
  private Locataire locataire;
  // private double quotiteLoyer;

  public Location(double loyer, LocalDate dateEntree, LocalDate dateSortie, BienImmobilier bien,
      Locataire locataire) {
    this.loyer = loyer;
    this.dateEntree = dateEntree;
    this.dateSortie = dateSortie;
    this.bien = bien;
    this.locataire = locataire;
  }

  public double getLoyer() {
    return this.loyer;
  }

  public LocalDate getDateEntree() {
    return this.dateEntree;
  }

  public LocalDate getDateSortie() {
    return this.dateSortie;
  }

  public BienImmobilier getBien() {
    return this.bien;
  }

  public Locataire getLocataire() {
    return this.locataire;
  }
}
