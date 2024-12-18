package dao;

import java.sql.*;

import db.DatabaseConnexion;
import model.Proprietaire;

public class ProprietaireDAO {
  private final Connection connection;

  public ProprietaireDAO() {
    this.connection = DatabaseConnexion.getConnexion();
  }

  // récupération d'un propriétaire à partir de son identifiant
  public Proprietaire read(String id) {
    Proprietaire proprietaire = null;

    try {
      String query = "SELECT * FROM proprietaires WHERE id_proprietaire = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        proprietaire = new Proprietaire(
            resultSet.getString("id_proprietaire"),
            resultSet.getString("nom"),
            resultSet.getString("prenom"),
            resultSet.getString("email"),
            resultSet.getString("mot_de_passe"));
      }

    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la lecture du propriétaire", e);
    }

    return proprietaire;
  }

  // récupération d'un propriétaire à partir de son email et de son mot de passe
  // (connexion)
  public Proprietaire read(String email, String motDePasse) throws RuntimeException {
    Proprietaire proprietaire = null;

    try {
      String query = "SELECT * FROM proprietaires WHERE email = ? AND mot_de_passe = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, motDePasse);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        proprietaire = new Proprietaire(
            resultSet.getString("id_proprietaire"),
            resultSet.getString("nom"),
            resultSet.getString("prenom"),
            resultSet.getString("email"),
            resultSet.getString("mot_de_passe"));
      }

    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la lecture du propriétaire", e);
    }

    return proprietaire;
  }
}
