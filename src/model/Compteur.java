package model;

import java.util.Date;

public class Compteur {
  private String id;
  private BienImmobilier bien;
  private double indexPrecedent;
  private double indexCourant;
  private Date dateReleve;

  public Compteur(String id, BienImmobilier bien, Date dateReleve, double indexPrecedent, double indexCourant) {
    this.id = id;
    this.bien = bien;
    this.indexPrecedent = indexPrecedent;
    this.indexCourant = indexCourant;
    this.dateReleve = dateReleve;
  }

  public String getId() {
    return this.id;
  }

  public BienImmobilier getBien() {
    return this.bien;
  }

  public double getIndexPrecedent() {
    return this.indexPrecedent;
  }

  public double getIndexCourant() {
    return this.indexCourant;
  }

  public Date getDateReleve() {
    return this.dateReleve;
  }

  public void setIndex(double index) {
    this.indexPrecedent = this.indexCourant;
    this.indexCourant = index;
  }
}
