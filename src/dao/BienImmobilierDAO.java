package dao;

import java.sql.*;

import db.DatabaseConnexion;
import model.BienImmobilier;

public class BienImmobilierDAO {
  private final Connection connection;

  public BienImmobilierDAO() {
    this.connection = DatabaseConnexion.getConnexion();
  }

  public void create(BienImmobilier bienImmobilier) {
      try {
          String query = "INSERT INTO bien_immobilier (Id, type_bien, adresse, complement_adresse, code_postal, ville) VALUES (?, ?, ?, ?, ?)";
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          preparedStatement.setString(1, bienImmobilier.getId().toString());
          preparedStatement.setString(2, bienImmobilier.getTypeBien().toString());
          preparedStatement.setString(3, bienImmobilier.getAdresse());
          preparedStatement.setString(4, bienImmobilier.getComplementAdresse());
          preparedStatement.setInt(5, bienImmobilier.getCodePostal());
          preparedStatement.setString(6, bienImmobilier.getVille());
          preparedStatement.executeUpdate();
      } catch (SQLException e) {
          throw new RuntimeException("Erreur lors de la création du bien immobilier", e);
      }
  }

}
