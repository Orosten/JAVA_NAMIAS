DROP DATABASE IF EXISTS easytrain;

CREATE DATABASE easytrain;

CREATE TABLE Utilisateur (
     id INT(3) NOT NULL AUTO_INCREMENT,
     login VARCHAR(20) NOT NULL UNIQUE,
     mdr VARCHAR(256) NOT NULL,
     nom VARCHAR(30) NOT NULL,
     prenom VARCHAR(30) NOT NULL,
     date_embauche DATETIME NOT NULL,
     role ENUM('ADMIN', 'EMPLOYE') NOT NULL,
     PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE Arret (
   id INT(3) NOT NULL AUTO_INCREMENT,
   nom VARCHAR(30) NOT NULL,
   PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE Trajet (
    code VARCHAR(30) NOT NULL,
    temps_depart DATETIME NOT NULL,
    temps_arrivee DATETIME NOT NULL,
    arret_depart_id INT(3) NOT NULL,
    arret_arrivee_id INT(3) NOT NULL,
    PRIMARY KEY (code),
    FOREIGN KEY (arret_depart_id) REFERENCES Arret(id),
    FOREIGN KEY (arret_arrivee_id) REFERENCES Arret(id)
) ENGINE=InnoDB;