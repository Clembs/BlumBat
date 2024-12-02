package model;

public class BienLogement extends BienImmobilier {
  private String numeroFiscal;
  private float surface;
  private int nombrePieces;

  public BienLogement(
      String id, String adresse,
      String complementAdresse, int codePostal,
      String ville, String numeroFiscal,
      float surface, int nombrePieces) {
    super(id, TypeBien.LOGEMENT, adresse, complementAdresse, codePostal, ville);
    this.numeroFiscal = numeroFiscal;
    this.surface = surface;
    this.nombrePieces = nombrePieces;
  }

  public String getNumeroFiscal() {
    return this.numeroFiscal;
  }

  public float getSurface() {
    return this.surface;
  }

  public int getNombrePieces() {
    return this.nombrePieces;
  }
}
