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
          String query = "INSERT INTO bien_logement (id_bien, type_bien, adresse, complement_adresse, code_postal, ville, numero_fiscal, surface, nombre_pieces) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          preparedStatement.setString(1, bienLogement.getId());
          preparedStatement.setString(2, bienLogement.getTypeBien().toString());
          preparedStatement.setString(3, bienLogement.getAdresse());
          preparedStatement.setString(4, bienLogement.getComplementAdresse());
          preparedStatement.setInt(5, bienLogement.getCodePostal());
          preparedStatement.setString(6, bienLogement.getVille());
          preparedStatement.setInt(7, bienLogement.getNumeroFiscal());
          preparedStatement.setFloat(8, bienLogement.getSurface());
          preparedStatement.setInt(9, bienLogement.getNombrePieces());
          preparedStatement.executeUpdate();
      } catch (SQLException e) {
          throw new RuntimeException("Erreur lors de la création du bien logement", e);
      }
  }

}