package dao;

import java.sql.*;

import db.DatabaseConnexion;
import model.BienLogement;

public class BienLogementDAO {
  private final Connection connection;

  public BienLogementDAO() {
    this.connection = DatabaseConnexion.getConnexion();
  }

  public void create(BienLogement bienLogement) {
      try {
          String query = "INSERT INTO BienLogement (id_bienlogement, id_bien, numeroFiscal, surface, nombrePieces) VALUES (?, ?, ?, ?, ?)";
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          preparedStatement.setString(1, bienLogement.getId());
          preparedStatement.setString(2, bienLogement.getId());
          preparedStatement.setInt(3, bienLogement.getNumeroFiscal());
          preparedStatement.setFloat(4, bienLogement.getSurface());
          preparedStatement.setInt(5, bienLogement.getNombrePieces());
          preparedStatement.executeUpdate();
      } catch (SQLException e) {
          throw new RuntimeException("Erreur lors de la création du bien logement", e);
      }
  }

}