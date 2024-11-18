package model;

public class BienLogement extends BienImmobilier {
  private int numeroFiscal;
  private float surface;
  private int nombrePieces;

  public BienLogement(String id, String adresse, String complementAdresse, int numeroAppt,
      String nomResidence, int codePostal, String ville, int numeroFiscal, float surface, int nombrePieces) {
    super(id, TypeBien.LOGEMENT, adresse, complementAdresse, numeroAppt, nomResidence, codePostal, ville);
    this.numeroFiscal = numeroFiscal;
    this.surface = surface;
    this.nombrePieces = nombrePieces;
  }

  public int getNumeroFiscal() {
    return this.numeroFiscal;
  }

  public float getSurface() {
    return this.surface;
  }

  public float getNombrePieces() {
    return this.nombrePieces;
  }
}
