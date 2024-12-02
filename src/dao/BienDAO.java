package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnexion;
import model.BienImmobilier;
import model.BienLogement;
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
			preparedStatement.setInt(6, bien.getCodePostal());
			preparedStatement.setString(7, bien.getVille());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la création du bien immobilier", e);
		}
	}

	public void create(BienLogement bien, Proprietaire proprietaire) {
		try {
			String query = "INSERT INTO biens (id_bien, id_proprietaire, type_bien, adresse, complement_adresse, code_postal, ville, numero_fiscal, surface, nombre_pieces) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bien.getId().toString());
			preparedStatement.setInt(2, Integer.parseInt(proprietaire.getId()));
			preparedStatement.setString(3, bien.getTypeBien().toString());
			preparedStatement.setString(4, bien.getAdresse());
			preparedStatement.setString(5, bien.getComplementAdresse());
			preparedStatement.setInt(6, bien.getCodePostal());
			preparedStatement.setString(7, bien.getVille());
			preparedStatement.setString(8, bien.getNumeroFiscal());
			preparedStatement.setFloat(9, bien.getSurface());
			preparedStatement.setInt(10, bien.getNombrePieces());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la création du logement", e);
		}
	}

	public List<BienImmobilier> getAllBiens() {
		List<BienImmobilier> biens = new ArrayList<>();

		try {
			String query = "SELECT * FROM biens";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String typeBienStr = resultSet.getString("type_bien");
				TypeBien typeBien = TypeBien.getTypeBien(typeBienStr);

				if (typeBien != TypeBien.BATIMENT) {
					BienLogement bien = new BienLogement(
							resultSet.getString("id_bien"),
							resultSet.getString("adresse"),
							resultSet.getString("complement_adresse"),
							resultSet.getInt("code_postal"),
							resultSet.getString("ville"),
							resultSet.getString("numero_fiscal"),
							resultSet.getFloat("surface"),
							resultSet.getInt("nombre_pieces"));

					biens.add(bien);
				} else {
					BienImmobilier bien = new BienImmobilier(
							resultSet.getString("id_bien"),
							typeBien,
							resultSet.getString("adresse"),
							resultSet.getString("complement_adresse"),
							resultSet.getInt("code_postal"),
							resultSet.getString("ville"));

					biens.add(bien);
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la récupération des biens", e);
		}

		return biens;
	}

	public List<BienLogement> getAllLogements() {
		List<BienLogement> logements = new ArrayList<>();

		try {
			String query = "SELECT * FROM biens WHERE type_bien != 'BATIMENT'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				BienLogement bien = new BienLogement(
						resultSet.getString("id_bien"),
						resultSet.getString("adresse"),
						resultSet.getString("complement_adresse"),
						resultSet.getInt("code_postal"),
						resultSet.getString("ville"),
						resultSet.getString("numero_fiscal"),
						resultSet.getFloat("surface"),
						resultSet.getInt("nombre_pieces"));

				logements.add(bien);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la récupération des logements", e);
		}

		return logements;
	}
}
