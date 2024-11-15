import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Définir les constantes pour la connexion
    private static final String url = "jdbc:mysql://mysql-projetr301.alwaysdata.net/projetr301_gestion_immobilier";
    private static final String utilisateur = "384907";
    private static final String mdp = "rootiutinfo";

    public static Connection connect() {
        try {
            // Etablir une connexion à la base de données
            Connection conn = DriverManager.getConnection(url, utilisateur, mdp);
            System.out.println("Connexion réussie !");
            return conn;
        } catch (SQLException e) {
            // Gérer les exceptions et afficher un message d'erreur
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // Tester la connexion
        connect();
    }
}
