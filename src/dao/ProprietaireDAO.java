package dao;

import java.sql.*;

import db.DatabaseConnexion;
import model.Proprietaire;

public class ProprietaireDAO {
  private final Connection connection;

  public ProprietaireDAO() {
    this.connection = DatabaseConnexion.getConnexion();
  }

  public void create(Proprietaire proprietaire) {
    try {
      String query = "INSERT INTO proprietaire (nom, email, email, mot_de_passe) VALUES (?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, proprietaire.getNom());
      preparedStatement.setString(2, proprietaire.getPrenom());
      preparedStatement.setString(3, proprietaire.getEmail());
      preparedStatement.setString(4, proprietaire.getMotDePasse());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la création du propriétaire", e);
    }
  }

  // récupération d'un propriétaire à partir de son identifiant
  public Proprietaire read(String id) {
    Proprietaire proprietaire = null;

    try {
      String query = "SELECT * FROM proprietaire WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        proprietaire = new Proprietaire(
            resultSet.getString("id"),
            resultSet.getString("nom"),
            resultSet.getString("prenom"),
            resultSet.getString("email"),
            resultSet.getString("motDePasse"));
      }

    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la lecture du propriétaire", e);
    }

    return proprietaire;
  }

  // récupération d'un propriétaire à partir de son email et de son mot de passe
  // (connexion)
  public Proprietaire read(String email, String motDePasse) {
    Proprietaire proprietaire = null;

    try {
      String query = "SELECT * FROM proprietaire WHERE email = ? AND mot_de_passe = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, motDePasse);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        proprietaire = new Proprietaire(
            resultSet.getString("id"),
            resultSet.getString("nom"),
            resultSet.getString("prenom"),
            resultSet.getString("email"),
            resultSet.getString("motDePasse"));
      }

    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la lecture du propriétaire", e);
    }

    return proprietaire;
  }
}
