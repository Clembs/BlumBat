package db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnexion {
    private static final String url = "jdbc:mysql://mysql-projetr301.alwaysdata.net/projetr301_gestion_immobilier";
    private static final String utilisateur = "384907";
    private static final String mdp = "rootiutinfo";
    private Connection connection;
    private static DatabaseConnexion instance;

    public static synchronized Connection getConnexion() {
        if (instance == null) {
            instance = new DatabaseConnexion();
        }
        return instance.connection;
    }

    public DatabaseConnexion() {
        try {
            this.connection = DriverManager.getConnection(url, utilisateur, mdp);
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }
    public static void executerRequete(String requete) {
        try (Statement stmt = getConnexion().createStatement()) {
            stmt.executeUpdate(requete);
            System.out.println("Requête exécutée avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
    }
}


