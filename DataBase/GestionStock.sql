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
-- Temporary table structure for view `ArticleFrom`
--

DROP TABLE IF EXISTS `ArticleFrom`;
/*!50001 DROP VIEW IF EXISTS `ArticleFrom`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `ArticleFrom` (
  `id` tinyint NOT NULL,
  `fr` tinyint NOT NULL,
  `moinsqt` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `ArticleTo`
--

DROP TABLE IF EXISTS `ArticleTo`;
/*!50001 DROP VIEW IF EXISTS `ArticleTo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `ArticleTo` (
  `id` tinyint NOT NULL,
  `mto` tinyint NOT NULL,
  `plusqt` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Articles`
--

LOCK TABLES `Articles` WRITE;
/*!40000 ALTER TABLE `Articles` DISABLE KEYS */;
INSERT INTO `Articles` VALUES (1,'PAS001','HP1300','Imprimante hp'),(2,'HPportable','Portable','Les portable HP'),(3,'papier80g','Papier','');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Destination`
--

LOCK TABLES `Destination` WRITE;
/*!40000 ALTER TABLE `Destination` DISABLE KEYS */;
INSERT INTO `Destination` VALUES (1,'dépôt principal','Stock global de l\'ISSAE avant dispatching مخزن المركزي'),(2,'Fournisseur','Fournisseur quelconque / أي مورد'),(3,'Laboratoire informatique','Les laboratoires informatique équipe Khaled Sawaya'),(4,'Exterieur','Provenances quelconque / أي مصادر'),(5,'BureauSI','Bureau Système information');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mouvement`
--

LOCK TABLES `Mouvement` WRITE;
/*!40000 ALTER TABLE `Mouvement` DISABLE KEYS */;
INSERT INTO `Mouvement` VALUES (1,2,1,1,10,'2014-03-05 18:41:18'),(2,1,3,1,5,'2014-03-05 18:41:56'),(3,4,1,2,5,'2014-03-05 19:10:15'),(4,1,5,2,1,'2014-03-05 19:10:36'),(5,2,1,3,100,'2014-03-05 19:11:00');
/*!40000 ALTER TABLE `Mouvement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `PlusMoins`
--

DROP TABLE IF EXISTS `PlusMoins`;
/*!50001 DROP VIEW IF EXISTS `PlusMoins`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `PlusMoins` (
  `id` tinyint NOT NULL,
  `mto` tinyint NOT NULL,
  `fr` tinyint NOT NULL,
  `plusqt` tinyint NOT NULL,
  `moinsqt` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `GestionStock`
--

USE `GestionStock`;

--
-- Final view structure for view `ArticleFrom`
--

/*!50001 DROP TABLE IF EXISTS `ArticleFrom`*/;
/*!50001 DROP VIEW IF EXISTS `ArticleFrom`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ArticleFrom` AS select `MF`.`idArticle` AS `id`,`MF`.`MouvementFrom` AS `fr`,sum(`MF`.`qt`) AS `moinsqt` from `Mouvement` `MF` group by `MF`.`MouvementFrom`,`MF`.`idArticle` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ArticleTo`
--

/*!50001 DROP TABLE IF EXISTS `ArticleTo`*/;
/*!50001 DROP VIEW IF EXISTS `ArticleTo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ArticleTo` AS select `MT`.`idArticle` AS `id`,`MT`.`MouvementTo` AS `mto`,sum(`MT`.`qt`) AS `plusqt` from `Mouvement` `MT` group by `MT`.`MouvementTo`,`MT`.`idArticle` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `PlusMoins`
--

/*!50001 DROP TABLE IF EXISTS `PlusMoins`*/;
/*!50001 DROP VIEW IF EXISTS `PlusMoins`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `PlusMoins` AS select `t`.`id` AS `id`,`t`.`mto` AS `mto`,`f`.`fr` AS `fr`,`t`.`plusqt` AS `plusqt`,`f`.`moinsqt` AS `moinsqt` from (`ArticleTo` `t` left join `ArticleFrom` `f` on(((`t`.`id` = `f`.`id`) and (`t`.`mto` = `f`.`fr`)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-05 20:00:58
