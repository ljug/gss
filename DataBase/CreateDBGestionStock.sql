


CREATE DATABASE `GestionStock` DEFAULT CHARACTER SET utf8  ;



CREATE TABLE `Articles` (
  `idArticle` int(11) NOT NULL AUTO_INCREMENT,
  `referenceArticle` varchar(45) NOT NULL COMMENT 'Tout identifiant sur l''objet lui même. Serial number ou autre. sinon un code interne',
  `nomArticle` varchar(45) DEFAULT NULL,
  `descriptionArtile` text,
  PRIMARY KEY (`idArticle`),
  UNIQUE KEY `referenceArticle_UNIQUE` (`referenceArticle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Destination` (
  `idDestination` int(11) NOT NULL COMMENT 'Une destination est le lieu de stockage de l''article OU la personne ou entité qui à consomé (utilisé) l''article',
  `referenceDestination` varchar(45) NOT NULL,
  `description` text,
  PRIMARY KEY (`idDestination`),
  UNIQUE KEY `referenceDestination_UNIQUE` (`referenceDestination`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/**
 * Mouvement de(From) D1 vers(To) D2 article a uantite qt 
*  alors qt(D1) -= qt et qt(D2) += qt
*/

CREATE TABLE `Mouvement` (
  `idMouvement` int(11) NOT NULL COMMENT 'Un movement est un deplacement d''articles entres destinations',
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




