package dao;

import java.sql.*;
import java.util.List;

import db.DatabaseConnexion;
import model.Locataire;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import model.BienImmobilier;
import model.Location;
import model.Proprietaire;
import model.TypeBien;

public class LocataireDAO {
  private final Connection connection;

  public LocataireDAO() {
    this.connection = DatabaseConnexion.getConnexion();
  }



  public void create(Locataire locataire, Proprietaire proprietaire) {
    try {
      String query = "INSERT INTO locataires (id_locataire, id_proprietaire, nom, prenom, email, telephone) VALUES (?, ?, ?, ?, ? ,?)";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, locataire.getId());
      preparedStatement.setInt(2, proprietaire.getId());
      preparedStatement.setString(3, locataire.getNom());
      preparedStatement.setString(4, locataire.getPrenom());
      preparedStatement.setString(5, locataire.getEmail());
      preparedStatement.setString(6, locataire.getTelephone());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la création du locataire", e);
    }
  }

  // récupération d'un locataire à partir de son identifiant,
  // de ses locations et des biens associés
  public Locataire read(String id) {
    Locataire locataire = null;

    try {
      // comme un locataire peut avoir plusieurs locations,
      // on risque d'avoir des doublons, on utilise donc un LEFT JOIN
      String query = "SELECT *\n" +
          "FROM locataires AS lt\n" +
          "LEFT JOIN locations AS lo \n" +
          "ON lt.id_locataire = lo.id_locataire\n" +
          "LEFT JOIN biens AS b\n" +
          "ON b.id_bien = lo.id_bien\n" +
          "WHERE lt.id_locataire = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        // on crée le locataire s'il n'existe pas
        if (locataire == null) {
          locataire = new Locataire(
              resultSet.getString("id_locataire"),
              resultSet.getString("nom"),
              resultSet.getString("prenom"),
              resultSet.getString("email"),
              resultSet.getString("telephone"));
        }

        String idBien = resultSet.getString("id_bien");

        // s'il y a un bien, on le récupère et sa location
        if (idBien != null) {
          String typeBienStr = resultSet.getString("type_bien");
          TypeBien typeBien = TypeBien.getTypeBien(typeBienStr);

          // Fetch the Bien and Location data
          BienImmobilier bien = new BienImmobilier(
              resultSet.getString("id_bien"),
              typeBien,
              resultSet.getString("adresse"),
              resultSet.getString("complement_adresse"),
              resultSet.getString("code_postal"),
              resultSet.getString("ville"));

          Date dateSortie = resultSet.getDate("date_sortie");

          Location location = new Location(
              resultSet.getDouble("loyer"),
              resultSet.getDate("date_entree").toLocalDate(),
              dateSortie == null ? null : dateSortie.toLocalDate(),
              bien,
              locataire);

          locataire.addLocation(location);
        }

      }

    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la lecture du locataire", e);
    }

    return locataire;
  }

  public boolean UpdateLocataire(Locataire locataire) {
    try {
      String query = "UPDATE locataires SET nom = ?, prenom = ?, email = ?, telephone = ? WHERE id_locataire = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, locataire.getNom());
      preparedStatement.setString(2, locataire.getPrenom());
      preparedStatement.setString(3, locataire.getEmail());
      preparedStatement.setString(4, locataire.getTelephone());
      preparedStatement.setString(5, locataire.getId());

      int rowsAffected = preparedStatement.executeUpdate();
      return rowsAffected > 0; // Retourne true si au moins une ligne a été mise à jour
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la modification du locataire", e);
    }
  }


  // retourne une liste de locataires avec leurs locations et les biens associés
  public List<Locataire> getAllLocataires(Proprietaire proprietaire) {
    Map<String, Locataire> locataires = new HashMap<>();

    try {
      String query = "SELECT *\n" +
          "FROM locataires AS lt\n" +
          "LEFT JOIN locations AS lo \n" +
          "ON lt.id_locataire = lo.id_locataire\n" +
          "LEFT JOIN biens AS b\n" +
          "ON b.id_bien = lo.id_bien\n" +
          "WHERE lt.id_proprietaire = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, proprietaire.getId());
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        String idLocataire = resultSet.getString("id_locataire");

        // on crée le locataire s'il n'existe pas
        if (!locataires.containsKey(idLocataire)) {
          Locataire locataire = new Locataire(
              idLocataire,
              resultSet.getString("nom"),
              resultSet.getString("prenom"),
              resultSet.getString("email"),
              resultSet.getString("telephone"));
          locataires.put(idLocataire, locataire);
        }

        String idBien = resultSet.getString("id_bien");

        // s'il y a un bien, on le récupère et sa location
        if (idBien != null) {
          Locataire locataire = locataires.get(idLocataire);

          BienImmobilier bien = new BienImmobilier(
              idBien,
                  TypeBien.getTypeBien(resultSet.getString("type_bien")),
              resultSet.getString("adresse"),
              resultSet.getString("complement_adresse"),
              resultSet.getString("code_postal"),
              resultSet.getString("ville"));

          Date dateSortie = resultSet.getDate("date_sortie");

          Location location = new Location(
              resultSet.getDouble("loyer"),
              resultSet.getDate("date_entree").toLocalDate(),
              dateSortie == null ? null : dateSortie.toLocalDate(),
              bien,
              locataire);

          locataire.addLocation(location);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la récupération des locataires", e);
    }

    return locataires.values().stream().collect(Collectors.toList());
  }
  public  void delete(Locataire locataire , Proprietaire proprietaire) {
    try {
      String query = "DELETE FROM locataires WHERE id_locataire = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, locataire.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la suppression du locataire", e);
    }
  }
}