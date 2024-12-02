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
          String query = "INSERT INTO BienLogement (type_bien, adresse, complement_adresse, code_postal, ville, numeroFiscal, surface, nombrePieces) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          preparedStatement.setString(1, bienLogement.getTypeBien().toString());
          preparedStatement.setString(2, bienLogement.getAdresse());
          preparedStatement.setString(3, bienLogement.getComplementAdresse());
          preparedStatement.setInt(4, bienLogement.getCodePostal());
          preparedStatement.setString(5, bienLogement.getVille());
          preparedStatement.setInt(6, bienLogement.getNumeroFiscal());
          preparedStatement.setFloat(7, bienLogement.getSurface());
          preparedStatement.setInt(8, bienLogement.getNombrePieces());
          preparedStatement.executeUpdate();
      } catch (SQLException e) {
          throw new RuntimeException("Erreur lors de la création du bien logement", e);
      }
  }

}