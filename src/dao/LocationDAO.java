package dao;

import java.sql.Connection;
import java.util.List;
import java.util.LinkedList;
import java.sql.*;

import db.DatabaseConnexion;
import model.BienLocatif;
import model.Locataire;
import model.Location;

public class LocationDAO {
  private final Connection connection;

  public LocationDAO() {
    this.connection = DatabaseConnexion.getConnexion();
  }

  public void create(Location location) {
    try {
      String query = "INSERT INTO locations (id_locataire, id_bien, loyer, date_entree, date_sortie) VALUES (?, ?, ?, ?, ?)";

      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, location.getLocataire().getId());
      preparedStatement.setString(2, location.getBien().getId());
      preparedStatement.setDouble(3, location.getLoyer());
      preparedStatement.setDate(4, Date.valueOf(location.getDateEntree()));
      if (location.getDateSortie() == null) {
        preparedStatement.setNull(5, Types.DATE);
      } else {
        preparedStatement.setDate(5, Date.valueOf(location.getDateSortie()));
      }
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la création du logement", e);
    }
  }

  public List<Location> getAllLocations(BienLocatif bien) {
    // On crée un set pour éviter les doublons
    List<Location> locations = new LinkedList<>();

    try {
      String query = "SELECT * FROM locations AS lo, locataires AS lt WHERE id_bien = ? AND lo.id_locataire = lt.id_locataire";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, bien.getId());
      ResultSet resultSet = preparedStatement.executeQuery();

      // comme chaque résultat est un locataire avec une location (il y aura plus de
      // locataires que de locations), on groupe les locataires pour éviter les
      // doublons
      while (resultSet.next()) {
        Locataire locataire = new Locataire(
            resultSet.getString("id_locataire"),
            resultSet.getString("nom"),
            resultSet.getString("prenom"),
            resultSet.getString("telephone"),
            resultSet.getString("email"));

        Date dateSortie = resultSet.getDate("date_sortie");

        Location location = new Location(
            resultSet.getDouble("loyer"),
            resultSet.getDate("date_entree").toLocalDate(),
            dateSortie == null ? null : dateSortie.toLocalDate(),
            bien,
            locataire);

        // si la location n'est pas déjà dans la liste, on l'ajoute
        if (!locations.contains(location)) {
          locations.add(location);
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la récupération des locations", e);
    }

    return locations;
  }
}
