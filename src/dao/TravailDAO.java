package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.DatabaseConnexion;
import model.BienImmobilier;
import model.BienLocatif;
import model.FactureTravaux;
import model.TypeBien;

public class TravailDAO {
  private final Connection connection;

  public TravailDAO() {
    this.connection = DatabaseConnexion.getConnexion();
  }

  public void create(FactureTravaux factureTravaux, BienImmobilier bien) {
    try {
      String query = "INSERT INTO factures_travaux(id_facture, id_bien, description_travail, montant_devis, montant_facture, entreprise, date_facture) VALUES (?, ?, ?, ?, ?, ?, ?)";

      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, factureTravaux.getId());
      preparedStatement.setString(2, factureTravaux.getBien().getId());
      preparedStatement.setString(3, factureTravaux.getDescription());
      preparedStatement.setDouble(4, factureTravaux.getMontantDevis());
      preparedStatement.setDouble(5, factureTravaux.getMontantFacture());
      preparedStatement.setString(6, factureTravaux.getEntreprise());
      preparedStatement.setDate(7, Date.valueOf(factureTravaux.getDate()));
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erreur lors de la creation d'une facture de travaux", e);
    }
  }

  // recuperation d'un travail associé a un bien a partir de l'id du bien et de
  // sont description
  public FactureTravaux read(String id) {
    FactureTravaux factureTravaux = null;

    try {
      String query = "SELECT * FROM factures_travaux WHERE id_facture = ? LEFT JOIN biens ON factures_travaux.id_bien = biens.id_bien";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        BienImmobilier bien;
        TypeBien typeBien = TypeBien.valueOf(resultSet.getString("type_bien"));

        if (typeBien == TypeBien.BATIMENT) {
          bien = new BienImmobilier(
              resultSet.getString("id_bien"),
              typeBien,
              resultSet.getString("adresse"),
              resultSet.getString("complement_adresse"),
              resultSet.getString("code_postal"),
              resultSet.getString("ville"));
        } else {
          bien = new BienLocatif(
              resultSet.getString("id_bien"), typeBien, resultSet.getString("adresse"),
              resultSet.getString("complement_adresse"), resultSet.getString("code_postal"),
              resultSet.getString("ville"), resultSet.getString("numero_fiscal"), resultSet.getFloat("surface"),
              resultSet.getInt("nombre_pieces"));
        }

        factureTravaux = new FactureTravaux(
            resultSet.getString("id_facture"),
            resultSet.getString("description_travail"),
            resultSet.getDouble("montant_devis"),
            resultSet.getDouble("montant_facture"),
            resultSet.getString("entreprise"),
            resultSet.getDate("date_facture").toLocalDate(),
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
            resultSet.getString("description_travail"),
            resultSet.getDouble("montant_devis"),
            resultSet.getDouble("montant_facture"),
            resultSet.getString("entreprise"),
            resultSet.getDate("date_facture").toLocalDate(),
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
