import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static  String url = "jdbc:mysql://localhost:3306/gestion_bien_immobilier"; 
    private static String utilisateur = "root";
    private static String mdp = ""; 

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(url, utilisateur,mdp );
            System.out.println("Connexion réussie !");
            return conn;
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        connect();
    }
}
