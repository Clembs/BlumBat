



-- Table des propriétaire
CREATE TABLE proprietaire (
        id_propriétaire INT AUTO_INCREMENT PRIMARY KEY,
        nom VARCHAR(100) NOT NULL,
        prenom VARCHAR(100) NOT NULL, 
        email VARCHAR(100)NOT null , 
        mdp VARCHAR(100) NOT null ,
);

-- Table des bien_immobilier
CREATE TABLE bien_immobilier (
        id_bien INT AUTO_INCREMENT PRIMARY KEY,
        type_bien VARCHAR(50) NOT NULL,
        adresse TEXT NOT NULL,
        complément_adresse TEXT NOT NULL,
        code_postal INT NOT NULL,
        ville VARCHAR(50) NOT NULL,

);

--Table Facture Travaux

CREATE TABLE Facture_Travaux(
        id_facture INT AUTO_INCREMENT PRIMARY KEY,
        montant_facture DOUBLE NOT NULL,
        montant_devis DOUBLE NOT NULL,
        entreprise VARCHAR(50) NOT NULL,
        FOREIGN KEY(id_bien) REFERENCES bien_immobilier(id_bien)

)

--Table Locataire 

CREATE TABLE Locataire(
        id_locataire INT AUTO_INCREMENT PRIMARY KEY,
        nom_locataire VARCHAR(100) NOT NULL , 
        prenom_locataire VARCHAR(100) NOT NULL , 
        email_locataire VARCHAR(100) NOT NULL , 
        telephone_locataire VARCHAR(50) NOT NULL
)

--Table Compteur

CREATE TABLE Compteur(
        id_compteur INT AUTO_INCREMENT PRIMARY KEY , 
        FOREIGN KEY(id_bien) REFERENCES bien_immobilier(id_bien)
        indexPrecedent DOUBLE NOT NULL,
        indexCourant DOUBLE NOT NULL,
        date_releve DATE NOT NULL,

)

--Table  Charges 
CREATE TABLE Charges(
        id_charge INT AUTO_INCREMENT PRIMARY KEY , 
        montantTotal DOUBLE NOT NULL , 
        taxeOrdures DOUBLE NOT NULL , 
        FOREIGN KEY(id_bien) REFERENCES bien_immobilier(id_bien)
)


--Table BienLogement

CREATE TABLE BienLogement(
        id_bienlogement INT AUTO_INCREMENT PRIMARY KEY , 
        FOREIGN KEY(id_bien) REFERENCES bien_immobilier(id_bien)
        numeroFiscal INT NOT NULL , 
        surface float NOT NULL , 
        nombrePieces INT NOT NULL , 

)

--Table Assurance 
CREATE TABLE Assurance(
        id_Assurance INT AUTO_INCREMENT PRIMARY KEY , 
        montant DOUBLE NOT NULL , 
        FOREIGN KEY(id_bien) REFERENCES bien_immobilier(id_bien)

)

