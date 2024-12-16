package model;

public class BienLocatif extends BienImmobilier {
  private String numeroFiscal;
  private float surface;
  private int nombrePieces;

  public BienLocatif(
      String id,
      TypeBien type,
      String adresse,
      String complementAdresse, String codePostal,
      String ville, String numeroFiscal,
      float surface, int nombrePieces) {
    super(id, type, adresse, complementAdresse, codePostal, ville);
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
