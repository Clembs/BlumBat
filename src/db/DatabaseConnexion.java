package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnexion {
  private String url;
  private String utilisateur;
  private String mdp;
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
      Dotenv dotenv = Dotenv.load();

      this.url = dotenv.get("DATABASE_URL");
      this.utilisateur = dotenv.get("DATABASE_USER");
      this.mdp = dotenv.get("DATABASE_PASSWORD");

      this.connection = DriverManager.getConnection(this.url, this.utilisateur, this.mdp);
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
