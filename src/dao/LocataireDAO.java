package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnexion;
import model.Locataire;

public class LocataireDAO {
    private final Connection connection;

    public LocataireDAO() {
        this.connection = DatabaseConnexion.getConnexion();
    }

    public void create(Locataire locataire) {
        try {
            String query = "INSERT INTO locataires (id_locataire, nom, prenom, email, telephone) VALUES (?, ?, ?, ?, ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, locataire.getId());
            preparedStatement.setString(2, locataire.getNom());
            preparedStatement.setString(3, locataire.getPrenom());
            preparedStatement.setString(4, locataire.getEmail());
            preparedStatement.setString(5, locataire.getTelephone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du propriétaire", e);
        }
    }

    // récupération d'un propriétaire à partir de son identifiant
    public Locataire read(String id) {
        Locataire locataire = null;

        try {
            String query = "SELECT * FROM Locataires WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                locataire = new Locataire(
                        resultSet.getString("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la lecture du locataire", e);
        }
        return locataire;
    }

    public List<Locataire> getAll() {
        List<Locataire> locataires = new ArrayList<Locataire>();

        try {
            String query = "SELECT * FROM locataires";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Locataire locataire = new Locataire(
                        resultSet.getString("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone"));
                locataires.add(locataire);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des locataires", e);
        }

        return locataires;
    }

    public void modifierLocataire(Locataire locataire) {
        try {
            String query = "UPDATE locataires SET nom = ?, prenom = ?, email = ?, telephone = ? WHERE id_locataire = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, locataire.getNom());
            preparedStatement.setString(2, locataire.getPrenom());
            preparedStatement.setString(3, locataire.getEmail());
            preparedStatement.setString(4, locataire.getTelephone());
            preparedStatement.setString(5, locataire.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification du locataire", e);
        }
    }
}
