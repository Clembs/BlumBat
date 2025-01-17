CREATE TABLE IF NOT EXISTS proprietaires (
	id_proprietaire    int                                    NOT NULL AUTO_INCREMENT,
  nom                varchar(100)                           NOT NULL,
  prenom             varchar(100)                           NOT NULL,
  email              varchar(100)                           NOT NULL,
  mot_de_passe       varchar(100)                           NOT NULL,
  PRIMARY KEY(id_proprietaire)
);

CREATE TABLE IF NOT EXISTS biens (
	id_bien            varchar(50)                            NOT NULL,
  id_proprietaire    int                                    NOT NULL,
  type_bien          enum('BATIMENT', 'LOGEMENT', 'GARAGE') NOT NULL,
  adresse            text                                   NOT NULL,
  complement_adresse text,
  code_postal        varchar(5)                             NOT NULL,
  ville              varchar(100)                           NOT NULL,
  numero_fiscal      varchar(12),
  surface            float,
  nombre_pieces      int,
  PRIMARY KEY(id_bien),
  FOREIGN KEY(id_proprietaire) REFERENCES proprietaires(id_proprietaire)
);

CREATE TABLE IF NOT EXISTS charges (
	id_charge          int                                    NOT NULL AUTO_INCREMENT,
  id_bien            varchar(50)                            NOT NULL,
  montant_total      double                                 NOT NULL,
  taxe_ordures       double                                 NOT NULL,
  PRIMARY KEY(id_charge),
  FOREIGN KEY(id_bien) REFERENCES biens(id_bien)
);

CREATE TABLE IF NOT EXISTS assurances (
	id_assurance       int                                    NOT NULL AUTO_INCREMENT,
  id_bien            varchar(50)                            NOT NULL,
  montant            double                                 NOT NULL,
  PRIMARY KEY(id_assurance),
  FOREIGN KEY(id_bien) REFERENCES biens(id_bien)
);

CREATE TABLE IF NOT EXISTS compteurs (
  id_compteur        int                                    NOT NULL,
  id_bien            varchar(50)                            NOT NULL,
  index_precedent    double,
  index_courant      double                                 NOT NULL,
  date_releve        date                                   NOT NULL,
  PRIMARY KEY(id_compteur),
  FOREIGN KEY(id_bien) REFERENCES biens(id_bien)
);

CREATE TABLE IF NOT EXISTS factures_travaux (
	id_facture           varchar(50)                            NOT NULL,
  id_bien              varchar(50)                            NOT NULL,
  description_travail  varchar(100)                           NOT NULL,
  montant_devis        double                                 NOT NULL, 
  montant_facture      double                                 NOT NULL,
  entreprise           varchar(100)                           NOT NULL,
  date_facture         date                                   NOT NULL,
  PRIMARY KEY(id_facture),
  FOREIGN KEY(id_bien) REFERENCES biens(id_bien)
);

CREATE TABLE IF NOT EXISTS locataires (
	id_locataire       varchar(50)                            NOT NULL,
  id_proprietaire    int                                    NOT NULL,
  nom                varchar(100)                           NOT NULL,
  prenom             varchar(100)                           NOT NULL,
  email              varchar(100)                           NOT NULL,
  telephone          varchar(10)                            NOT NULL,
  PRIMARY KEY(id_locataire),
  FOREIGN KEY(id_proprietaire) REFERENCES proprietaires(id_proprietaire)
);

CREATE TABLE IF NOT EXISTS locations (
  id_location        int                                    NOT NULL AUTO_INCREMENT,
	id_locataire       varchar(50)                            NOT NULL,
  id_bien            varchar(50)                            NOT NULL,
  loyer              double                                 NOT NULL,
  date_entree        date                                   NOT NULL,
  date_sortie        date,
  PRIMARY KEY(id_location),
  FOREIGN KEY(id_bien) REFERENCES biens(id_bien),
  FOREIGN KEY(id_locataire) REFERENCES locataires(id_locataire)
);