package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnexion;
import model.BienImmobilier;
import model.TypeBien;

public class BienImmobilierDAO {
  private final Connection connection;

  public BienImmobilierDAO() {
    this.connection = DatabaseConnexion.getConnexion();
  }

  public void create(BienImmobilier bienImmobilier) {
      try {
          String query = "INSERT INTO bien_immobilier (id_bien, type_bien, adresse, complement_adresse, code_postal, ville) VALUES (?, ?, ?, ?, ?, ?)";
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
  
  public List<BienImmobilier> getAllBiens() {
	    List<BienImmobilier> biens = new ArrayList<>();

	    try {
	        String query = "SELECT * FROM bien_immobilier";
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(query);

	        while (resultSet.next()) {
	        	
	        	String typeBienStr = resultSet.getString("type_bien");
	            TypeBien typeBien = TypeBien.valueOf(typeBienStr.toUpperCase());

	        	
	            BienImmobilier bien = new BienImmobilier(
	            	resultSet.getString("id_bien"),
	            	typeBien,
	                resultSet.getString("adresse"),
	                resultSet.getString("complement_adresse"),
	                resultSet.getInt("code_postal"),
	                resultSet.getString("ville")
	            );
	            biens.add(bien);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Erreur lors de la récupération des biens immobiliers", e);
	    }

	    return biens;
	}

}
