package db;


public class Create_Table {
    public static void main(String[] args) {
    	String createTableProprietaire = 
                "CREATE TABLE IF NOT EXISTS proprietaire (" +
                    "id_proprietaire INT AUTO_INCREMENT PRIMARY KEY," +
                    "nom VARCHAR(100) NOT NULL," +
                    "prenom VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) NOT NULL," +
                    "mot_de_passe VARCHAR(100) NOT NULL" +
                ");";

    	String createTableBienImmobilier = 
                "CREATE TABLE IF NOT EXISTS bien_immobilier (" +
                    "id_bien INT PRIMARY KEY," +
                    "type_bien VARCHAR(50) NOT NULL," +
                    "adresse TEXT NOT NULL," +
                    "complement_adresse TEXT," +
                    "code_postal INT NOT NULL," +
                    "ville VARCHAR(50) NOT NULL" +
                ");";

    	String createTableFactureTravaux = 
                "CREATE TABLE IF NOT EXISTS Facture_Travaux (" +
                    "id_facture INT PRIMARY KEY," +
                    "montant_facture DOUBLE NOT NULL," +
                    "montant_devis DOUBLE NOT NULL," +
                    "entreprise VARCHAR(50) NOT NULL," +
                    "id_bien INT NOT NULL," +
                    "FOREIGN KEY (id_bien) REFERENCES bien_immobilier(id_bien)" +
                ");";

    	String createTableLocataire = 
                "CREATE TABLE IF NOT EXISTS Locataire (" +
                    "id_locataire INT PRIMARY KEY," +
                    "nom_locataire VARCHAR(100) NOT NULL," +
                    "prenom_locataire VARCHAR(100) NOT NULL," +
                    "email_locataire VARCHAR(100) NOT NULL," +
                    "telephone_locataire VARCHAR(50) NOT NULL" +
                ");";

    	String createTableCompteur = 
                "CREATE TABLE IF NOT EXISTS Compteur (" +
                    "id_compteur INT PRIMARY KEY," +
                    "id_bien INT NOT NULL," +
                    "indexPrecedent DOUBLE NOT NULL," +
                    "indexCourant DOUBLE NOT NULL," +
                    "date_releve DATE NOT NULL," +
                    "FOREIGN KEY (id_bien) REFERENCES bien_immobilier(id_bien)" +
                ");";

    	String createTableCharges = 
                "CREATE TABLE IF NOT EXISTS Charges (" +
                    "id_charge INT PRIMARY KEY," +
                    "montantTotal DOUBLE NOT NULL," +
                    "taxeOrdures DOUBLE NOT NULL," +
                    "id_bien INT NOT NULL," +
                    "FOREIGN KEY (id_bien) REFERENCES bien_immobilier(id_bien)" +
                ");";

    	String createTableBienLogement = 
                "CREATE TABLE IF NOT EXISTS BienLogement (" +
                    "id_bienlogement INT PRIMARY KEY," +
                    "id_bien INT NOT NULL," +
                    "numeroFiscal INT NOT NULL," +
                    "surface FLOAT NOT NULL," +
                    "nombrePieces INT NOT NULL," +
                    "FOREIGN KEY (id_bien) REFERENCES bien_immobilier(id_bien)" +
                ");";

    	String createTableAssurance = 
                "CREATE TABLE IF NOT EXISTS Assurance (" +
                    "id_Assurance INT PRIMARY KEY," +
                    "montant DOUBLE NOT NULL," +
                    "id_bien INT NOT NULL," +
                    "FOREIGN KEY (id_bien) REFERENCES bien_immobilier(id_bien)" +
                ");";

   
       
        DatabaseConnexion.executerRequete(createTableProprietaire);
        DatabaseConnexion.executerRequete(createTableBienImmobilier);
        DatabaseConnexion.executerRequete(createTableFactureTravaux);
        DatabaseConnexion.executerRequete(createTableLocataire);
        DatabaseConnexion.executerRequete(createTableCompteur);
        DatabaseConnexion.executerRequete(createTableCharges);
        DatabaseConnexion.executerRequete(createTableBienLogement);
        DatabaseConnexion.executerRequete(createTableAssurance);


    }
}
