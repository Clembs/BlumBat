import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String url = "jdbc:mysql://mysql-projetr301.alwaysdata.net/projetr301_gestion_immobilier";
    private static final String utilisateur = "384907";
    private static final String mdp = "rootiutinfo";
    private static DatabaseConnection dbConnection; 

    public static synchronized DatabaseConnection getDbConnection() {
        if (dbConnection == null) {
            dbConnection = new DatabaseConnection();
        }
        return dbConnection;
    }

    public  DatabaseConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, utilisateur, mdp);
            System.out.println("Connexion réussie !");
            return conn;
        } catch (SQLException e) {
          
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            return null;
        }
    }

    
}
