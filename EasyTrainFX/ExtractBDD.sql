-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 07 mars 2025 à 13:14
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `easytrain`
--

-- --------------------------------------------------------

--
-- Structure de la table `arret`
--

DROP TABLE IF EXISTS `arret`;
CREATE TABLE IF NOT EXISTS `arret` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `trajet`
--

DROP TABLE IF EXISTS `trajet`;
CREATE TABLE IF NOT EXISTS `trajet` (
  `code` varchar(30) NOT NULL,
  `temps_depart` datetime NOT NULL,
  `temps_arrivee` datetime NOT NULL,
  `arret_depart_id` int NOT NULL,
  `arret_arrivee_id` int NOT NULL,
  PRIMARY KEY (`code`),
  KEY `arret_depart_id` (`arret_depart_id`),
  KEY `arret_arrivee_id` (`arret_arrivee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `mdp` varchar(256) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `date_embauche` datetime NOT NULL,
  `role` enum('ADMIN','EMPLOYE') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déclencheurs `utilisateur`
--
DROP TRIGGER IF EXISTS `bloquer_test`; -- Supprime le trigger s'il existe déjà
DELIMITER $$ -- Change le délimiteur pour le bloc de code

CREATE TRIGGER `bloquer_test` BEFORE INSERT ON `utilisateur` FOR EACH ROW BEGIN
  IF NEW.nom LIKE '%test%' THEN -- Vérifie si le nom contient "test"
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nom invalide: ne peut pas contenir "test"'; -- Affiche une erreur
  END IF;
END
$$

DELIMITER ; -- Remet le délimiteur normal

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `trajet`
--
ALTER TABLE `trajet`
  ADD CONSTRAINT `trajet_ibfk_1` FOREIGN KEY (`arret_depart_id`) REFERENCES `arret` (`id`),
  ADD CONSTRAINT `trajet_ibfk_2` FOREIGN KEY (`arret_arrivee_id`) REFERENCES `arret` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
