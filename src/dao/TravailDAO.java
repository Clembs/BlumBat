package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.DatabaseConnexion;
import model.BienImmobilier;
import model.FactureTravaux;

public class TravailDAO {
  private final Connection connection;

  public TravailDAO() {
    this.connection = DatabaseConnexion.getConnexion();
  }

  public void create(FactureTravaux factureTravaux, BienImmobilier bien) {
    try {
      String query = "INSERT INTO factures_travaux(id_facture, id_bien, montant_facture, description_travaux, montant_devis, entreprise) VALUES(?, ?, ?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, factureTravaux.getId());
      preparedStatement.setString(2, factureTravaux.getBien().getId());
      preparedStatement.setDouble(3, factureTravaux.getMontantFacture());
      preparedStatement.setString(4, factureTravaux.getDescription());
      preparedStatement.setDouble(5, factureTravaux.getMontantDevis());
      preparedStatement.setString(6, factureTravaux.getEntreprise());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la creation d'une facture de travaux", e);
    }
  }

  // recuperation d'un travail associé a un bien a partir de l'id du bien et de
  // sont description
  public FactureTravaux getTravailByDescription(String description, BienImmobilier bien) {
    FactureTravaux factureTravaux = null;

    try {
      String query = "SELECT * FROM factures_travaux WHERE description_travaux = ? AND id_bien = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, description);
      preparedStatement.setString(2, bien.getId());
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        factureTravaux = new FactureTravaux(
            resultSet.getString("id_facture"),
            resultSet.getDouble("montant_facture"),
            resultSet.getString("description_travaux"),
            resultSet.getDouble("montant_devis"),
            resultSet.getString("entreprise"),
            bien);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la récupération du travail par description", e);
    }
    return factureTravaux;
  }

  // recuperation de tout les travaux associé a un bien a partir de l'id du bien
  public List<FactureTravaux> getAllTravaux(BienImmobilier bien) {
    List<FactureTravaux> factures = new LinkedList<>();

    try {
      String query = "SELECT * FROM factures_travaux where id_bien =? ";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, bien.getId());
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        FactureTravaux factureTravaux = new FactureTravaux(
            resultSet.getString("id_facture"),
            resultSet.getDouble("montant_facture"),
            resultSet.getString("description_travaux"),
            resultSet.getDouble("montant_devis"),
            resultSet.getString("entreprise"),
            bien);

        factures.add(factureTravaux);
        // on crée la facture si elle n'existe pas

      }
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la récupération des factures de travaux", e);
    }
    return factures;
  }

  public void delete(FactureTravaux factureTravaux) {
    try {
      String query = "DELETE FROM factures_travaux WHERE id_facture = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, factureTravaux.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la suppression d'une facture de travaux", e);
    }
  }
}
