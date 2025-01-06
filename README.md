# BlumBat

Application de gestion immobilière pour les propriétaires. Projet étudiant de l'IUT Paul Sabatier (SAÉ S3.A.01).

[Miroir GitHub](https://github.com/Clembs/BlumBat) | [Localized English README](README-en.md)

## Prérequis

- [Java Development Kit (JDK) 8](https://adoptium.net/temurin/releases/?version=8)
- [Apache Maven](https://maven.apache.org/download.cgi) ([Tutoriel pour Windows (en anglais)](https://phoenixnap.com/kb/install-maven-windows))
- Une base de données MySQL

## Installation et exécution

### Configuration de la base de données

1. Créez une base de données MySQL
2. Exécutez le script `sql/create_tables.sql` pour créer les tables nécessaires
3. Créez un fichier `.env` à la racine du projet et ajoutez les informations de connexion à la base de données :
<!-- TODO: ajouter un fichier SQL pour seed la base de données -->

```properties
DATABASE_URL = "jdbc:mysql://[hôte]:[port]/[nom de la base de données]"
DATABASE_USER = "[nom d'utilisateur]"
DATABASE_PASSWORD = "[mot de passe]"
```

### Compilation et exécution

```bash
$ mvn clean compile # Pour compiler le .jar
$ mvn exec:java # Pour exécuter l'application

$ mvn clean compile exec:java # Pour faire les deux en une seule commande
```

### Lancer les tests unitaires

```bash
$ mvn clean test
```

## Structure du projet

- `sql` : Migrations et scripts SQL
- `src/controller` : Contrôleurs de l'application
- `src/dao` : Data Access Objects pour les classes du modèle
- `src/db` : Connexion à la base de données
- `src/model` : Abstractions de la base de données en classes Java
- `src/view` : Fenêtres, panels et pop-ups de l'application
