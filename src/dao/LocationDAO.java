package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DatabaseConnexion;
import model.Location;

public class LocationDAO {
	private final Connection connection;

	public LocationDAO() {
		this.connection = DatabaseConnexion.getConnexion();
	}

	public void create(Location location) {
		try {
			String query = "INSERT INTO locations (loyer, dateEntree, dateSortie, bien, locataires) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, location.getLoyer());
			preparedStatement.setDate(2, (Date) location.getDateEntree());
			preparedStatement.setDate(3, (Date) location.getDateSortie());
			preparedStatement.setString(4, location.getBien().toString());
			preparedStatement.setString(5, location.getLocataires().toString());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la création de la location", e);
		}
	}

}