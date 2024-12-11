package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnexion;
import model.Location;

public class LocationDAO {
	private final Connection connection;

	public LocationDAO() {
		this.connection = DatabaseConnexion.getConnexion();
	}

	public void create(Location location) {
		try {
			String query = "INSERT INTO location (loyer, dateEntree, dateSortie, bien, locataires) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, location.getLoyer());
			preparedStatement.setDate(2, location.getDateEntree());
			preparedStatement.setDate(3, location.getDateSortie());
			preparedStatement.setString(4, location.getBien());
			preparedStatement.setString(5, location.getLocataires());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la création de la location", e);
		}
	}

}