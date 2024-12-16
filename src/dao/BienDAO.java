package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnexion;
import model.BienImmobilier;
import model.BienLocatif;
import model.Proprietaire;
import model.TypeBien;

public class BienDAO {
	private final Connection connection;

	public BienDAO() {
		this.connection = DatabaseConnexion.getConnexion();
	}

	public void create(BienImmobilier bien, Proprietaire proprietaire) {
		try {
			String query = "INSERT INTO biens (id_bien, id_proprietaire, type_bien, adresse, complement_adresse, code_postal, ville) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bien.getId().toString());
			preparedStatement.setInt(2, Integer.parseInt(proprietaire.getId()));
			preparedStatement.setString(3, bien.getTypeBien().toString());
			preparedStatement.setString(4, bien.getAdresse());
			preparedStatement.setString(5, bien.getComplementAdresse());
			preparedStatement.setString(6, bien.getCodePostal());
			preparedStatement.setString(7, bien.getVille());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la création du bien immobilier", e);
		}
	}

	public void create(BienLocatif bien, Proprietaire proprietaire) {
		try {
			String query = "INSERT INTO biens (id_bien, id_proprietaire, type_bien, adresse, complement_adresse, code_postal, ville, numero_fiscal, surface, nombre_pieces) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bien.getId().toString());
			preparedStatement.setInt(2, Integer.parseInt(proprietaire.getId()));
			preparedStatement.setString(3, bien.getTypeBien().toString());
			preparedStatement.setString(4, bien.getAdresse());
			preparedStatement.setString(5, bien.getComplementAdresse());
			preparedStatement.setString(6, bien.getCodePostal());
			preparedStatement.setString(7, bien.getVille());
			preparedStatement.setString(8, bien.getNumeroFiscal());
			preparedStatement.setFloat(9, bien.getSurface());
			preparedStatement.setInt(10, bien.getNombrePieces());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la création du logement", e);
		}
	}

	public List<BienImmobilier> getAllBiens(Proprietaire proprietaire) {
		List<BienImmobilier> biens = new ArrayList<>();

		try {
			String query = "SELECT * FROM biens WHERE id_proprietaire = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(proprietaire.getId()));
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String typeBienStr = resultSet.getString("type_bien");
				TypeBien typeBien = TypeBien.getTypeBien(typeBienStr);

				if (typeBien != TypeBien.BATIMENT) {
					BienLocatif bien = new BienLocatif(
							resultSet.getString("id_bien"),
							typeBien,
							resultSet.getString("adresse"),
							resultSet.getString("complement_adresse"),
							resultSet.getString("code_postal"),
							resultSet.getString("ville"),
							resultSet.getString("numero_fiscal"),
							resultSet.getFloat("surface"),
							resultSet.getInt("nombre_pieces"));

					LocationDAO locationDAO = new LocationDAO();
					bien.setLocations(locationDAO.getAllLocations(bien));

					biens.add(bien);
				} else {
					BienImmobilier bien = new BienImmobilier(
							resultSet.getString("id_bien"),
							typeBien,
							resultSet.getString("adresse"),
							resultSet.getString("complement_adresse"),
							resultSet.getString("code_postal"),
							resultSet.getString("ville"));

					biens.add(bien);
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la récupération des biens", e);
		}

		return biens;
	}

	public List<BienLocatif> getAllLogements(Proprietaire proprietaire) {
		List<BienLocatif> logements = new ArrayList<>();

		try {
			String query = "SELECT * FROM biens WHERE type_bien != 'BATIMENT' AND id_proprietaire = " + proprietaire.getId();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String typeBienStr = resultSet.getString("type_bien");
				TypeBien typeBien = TypeBien.getTypeBien(typeBienStr);

				BienLocatif bien = new BienLocatif(
						resultSet.getString("id_bien"),
						typeBien,
						resultSet.getString("adresse"),
						resultSet.getString("complement_adresse"),
						resultSet.getString("code_postal"),
						resultSet.getString("ville"),
						resultSet.getString("numero_fiscal"),
						resultSet.getFloat("surface"),
						resultSet.getInt("nombre_pieces"));

				LocationDAO locationDAO = new LocationDAO();
				bien.setLocations(locationDAO.getAllLocations(bien));

				logements.add(bien);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la récupération des logements", e);
		}

		return logements;
	}
}
