package model;

import java.util.Date;
import java.util.List;

public class Location {
  private double loyer;
  private Date dateEntree;
  private Date dateSortie;
  private BienImmobilier bien;
  private List<Locataire> locataires;
  // private double quotiteLoyer;

  public Location(double loyer, Date dateEntree, Date dateSortie, BienImmobilier bien, List<Locataire> locataires) {
    this.loyer = loyer;
    this.dateEntree = dateEntree;
    this.dateSortie = dateSortie;
    this.bien = bien;
    this.locataires = locataires;
  }

  public double getLoyer() {
    return this.loyer;
  }

  public Date getDateEntree() {
    return this.dateEntree;
  }

  public Date getDateSortie() {
    return this.dateSortie;
  }

  public BienImmobilier getBien() {
    return this.bien;
  }

  public List<Locataire> getLocataires() {
    return this.locataires;
  }
}
