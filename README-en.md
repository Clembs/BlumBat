# BlumBat

Real estate application for landlords. Student projet for IUT Paul Sabatier (SAÉ S3.A.01).

[GitHub mirror](https://github.com/Clembs/BlumBat) | [README original en Français](README.md)

## Prerequisites

- [Java Development Kit (JDK) 8](https://adoptium.net/temurin/releases/?version=8)
- [Apache Maven](https://maven.apache.org/download.cgi) ([Windows how-to](https://phoenixnap.com/kb/install-maven-windows))
- A MySQL database

## Installation and exécution

### Configuring the database

1. Create a MySQL database
2. Execute the script in `sql/create_tables.sql` to create the required tables
3. Created a `.env` file at the project root, and add the following database credentials:
<!-- TODO: ajouter un fichier SQL pour seed la base de données -->

```properties
DATABASE_URL = "jdbc:mysql://[host]:[port]/[database name]"
DATABASE_USER = "[username]"
DATABASE_PASSWORD = "[password]"
```

### Compiling and executing

```bash
$ mvn clean compile # To compile a .jar
$ mvn exec:java # To run the application

$ mvn clean compile exec:java # To do both in one command
```

### Run unit tests

```bash
$ mvn clean test
```

## Project structure

- `sql`: Migrations and other SQL scripts
- `src/controller`: Application controllers
- `src/dao`: Data Access Objects for model classes
- `src/db`: Database connection
- `src/model`: Abstract Java classes for database tables
- `src/view` : Application windows, panels and pop-ups
