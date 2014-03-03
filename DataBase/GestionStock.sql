-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: GestionStock
-- ------------------------------------------------------
-- Server version	5.5.35-0ubuntu0.13.10.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `GestionStock`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `GestionStock` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `GestionStock`;

--
-- Table structure for table `Articles`
--

DROP TABLE IF EXISTS `Articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Articles` (
  `idArticle` int(11) NOT NULL AUTO_INCREMENT,
  `referenceArticle` varchar(45) NOT NULL COMMENT 'Tout identifiant sur l''objet lui même. Serial number ou autre. sinon un code interne',
  `nomArticle` varchar(45) DEFAULT NULL,
  `descriptionArtile` text,
  PRIMARY KEY (`idArticle`),
  UNIQUE KEY `referenceArticle_UNIQUE` (`referenceArticle`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Articles`
--

LOCK TABLES `Articles` WRITE;
/*!40000 ALTER TABLE `Articles` DISABLE KEYS */;
INSERT INTO `Articles` VALUES (1,'PAS001','HP1300','Imprimante hp');
/*!40000 ALTER TABLE `Articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Destination`
--

DROP TABLE IF EXISTS `Destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Destination` (
  `idDestination` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Une destination est le lieu de stockage de l''article OU la personne ou entité qui à consomé (utilisé) l''article',
  `referenceDestination` varchar(45) NOT NULL,
  `description` text,
  PRIMARY KEY (`idDestination`),
  UNIQUE KEY `referenceDestination_UNIQUE` (`referenceDestination`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Destination`
--

LOCK TABLES `Destination` WRITE;
/*!40000 ALTER TABLE `Destination` DISABLE KEYS */;
INSERT INTO `Destination` VALUES (1,'dépôt principal','Stock global de l\'ISSAE avant dispatching\r\n???????? ???????'),(2,'Fournisseur','Fournisseur quelconque / ?? ????'),(3,'Laboratoire informatique','Les laboratoires informatique équipe Khaled Sawaya'),(4,'Exterieur','Provenances quelconque ?? ?????');
/*!40000 ALTER TABLE `Destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Mouvement`
--

DROP TABLE IF EXISTS `Mouvement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Mouvement` (
  `idMouvement` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Un movement est un deplacement d''articles entres destinations',
  `MouvementFrom` int(11) NOT NULL,
  `MouvementTo` int(11) NOT NULL,
  `idArticle` int(11) NOT NULL,
  `qt` int(11) NOT NULL COMMENT 'Quantité',
  `Mouvementcol` datetime NOT NULL,
  PRIMARY KEY (`idMouvement`),
  KEY `fk_Mouvement_1_idx` (`MouvementFrom`),
  KEY `fk_Mouvement_2_idx` (`MouvementTo`),
  KEY `fk_Mouvement_3_idx` (`idArticle`),
  CONSTRAINT `fk_Mouvement_1` FOREIGN KEY (`MouvementFrom`) REFERENCES `Destination` (`idDestination`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Mouvement_2` FOREIGN KEY (`MouvementTo`) REFERENCES `Destination` (`idDestination`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Mouvement_3` FOREIGN KEY (`idArticle`) REFERENCES `Articles` (`idArticle`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mouvement`
--

LOCK TABLES `Mouvement` WRITE;
/*!40000 ALTER TABLE `Mouvement` DISABLE KEYS */;
/*!40000 ALTER TABLE `Mouvement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-03 11:02:29
