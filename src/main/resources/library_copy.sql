-- MySQL dump 10.13  Distrib 5.6.26, for Win32 (x86)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `aliases`
--

DROP TABLE IF EXISTS `aliases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aliases` (
  `idAliases` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) NOT NULL,
  PRIMARY KEY (`idAliases`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aliases`
--

LOCK TABLES `aliases` WRITE;
/*!40000 ALTER TABLE `aliases` DISABLE KEYS */;
INSERT INTO `aliases` VALUES (1,'Maryla','Szymiczkowa'),(2,'Richard','Bachman');
/*!40000 ALTER TABLE `aliases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `idAuthors` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  PRIMARY KEY (`idAuthors`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Jacek','Dehnel'),(2,'Karl Ove','Knausgaard'),(3,'Stephen','King'),(4,'Anna','Pamuła'),(5,'Monika','Sznajderman'),(6,'Carolina','De Robertis'),(7,'Edward','Rutherfurd');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorsaliases`
--

DROP TABLE IF EXISTS `authorsaliases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorsaliases` (
  `Authors_idAuthors` int(11) NOT NULL,
  `Aliases_idAliases` int(11) NOT NULL,
  PRIMARY KEY (`Authors_idAuthors`,`Aliases_idAliases`),
  KEY `fk_Authors_has_Aliases_Aliases1_idx` (`Aliases_idAliases`),
  KEY `fk_Authors_has_Aliases_Authors_idx` (`Authors_idAuthors`),
  CONSTRAINT `fk_Authors_has_Aliases_Aliases1` FOREIGN KEY (`Aliases_idAliases`) REFERENCES `aliases` (`idAliases`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Authors_has_Aliases_Authors` FOREIGN KEY (`Authors_idAuthors`) REFERENCES `authors` (`idAuthors`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorsaliases`
--

LOCK TABLES `authorsaliases` WRITE;
/*!40000 ALTER TABLE `authorsaliases` DISABLE KEYS */;
INSERT INTO `authorsaliases` VALUES (1,1),(3,2);
/*!40000 ALTER TABLE `authorsaliases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `idBooks` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) NOT NULL,
  `ISBN` varchar(45) NOT NULL,
  `PublishingHouse` varchar(45) NOT NULL,
  `PublicationYear` year(4) NOT NULL,
  `Categories_idCategories` int(11) NOT NULL,
  PRIMARY KEY (`idBooks`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`),
  KEY `fk_Books_Categories1_idx` (`Categories_idCategories`),
  CONSTRAINT `fk_Books_Categories1` FOREIGN KEY (`Categories_idCategories`) REFERENCES `categories` (`idCategories`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Rozdarta zasłona','97883240-3950-0','Znak',2016,1),(2,'Tajemnica domu Helclów','9788308-06224-1','Znak',2015,1),(3,'Lato','97883280-1487-9','Wydawnictwo literackie',2015,6),(4,'To','9788308-06344-6','Albatros',2015,1),(5,'Zielona mila','9788308-06367-5','Albatros',2014,1),(6,'Patrioci','97883-62304-196','Prószyński i S-ka',2011,1),(7,'Polacos. Chajka płynie do Kostaryki','9788365586-97-1','Czarne',2017,2),(8,'Anglia Tudorów','9788380321588','Czarne',2017,2),(9,'Bogowie tanga','9788380313507','Albatros',2017,6),(10,'Niewidoczna góra','9788380971523','Albatros',2011,6),(11,'Londyn','978838075-252-8','Czarna owca',2016,6),(12,'Paryż','97883-7536-1179','Czarna owca',2015,6),(13,'Nowy Jork','97883-7536-2923','Czarna owca',2015,6);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booksauthors`
--

DROP TABLE IF EXISTS `booksauthors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booksauthors` (
  `Books_idBooks` int(11) NOT NULL,
  `Authors_idAuthors` int(11) NOT NULL,
  PRIMARY KEY (`Books_idBooks`,`Authors_idAuthors`),
  KEY `fk_Books_has_Authors_Authors1_idx` (`Authors_idAuthors`),
  KEY `fk_Books_has_Authors_Books1_idx` (`Books_idBooks`),
  CONSTRAINT `fk_Books_has_Authors_Authors1` FOREIGN KEY (`Authors_idAuthors`) REFERENCES `authors` (`idAuthors`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Books_has_Authors_Books1` FOREIGN KEY (`Books_idBooks`) REFERENCES `books` (`idBooks`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksauthors`
--

LOCK TABLES `booksauthors` WRITE;
/*!40000 ALTER TABLE `booksauthors` DISABLE KEYS */;
INSERT INTO `booksauthors` VALUES (1,1),(2,1),(3,2),(4,3),(5,3),(6,3),(7,4),(8,5),(9,6),(10,6),(11,7),(12,7),(13,7);
/*!40000 ALTER TABLE `booksauthors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `idCategories` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategories`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Kryminał'),(2,'Reportaż'),(3,'Biografia'),(4,'Fantastyka'),(5,'Literatura popularnonaukowa'),(6,'Literatura piękna obca'),(7,'Literatura piękna polska'),(8,'Literatura dziecięca');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loans` (
  `idLoans` int(11) NOT NULL AUTO_INCREMENT,
  `DateFrom` date NOT NULL,
  `DateTo` date NOT NULL,
  `returnDate` date DEFAULT NULL,
  `Readers_idReaders` int(11) NOT NULL,
  `Books_idBooks` int(11) NOT NULL,
  PRIMARY KEY (`idLoans`),
  KEY `fk_Loans_Readers1_idx` (`Readers_idReaders`),
  KEY `fk_Loans_Books1_idx` (`Books_idBooks`),
  CONSTRAINT `fk_Loans_Books1` FOREIGN KEY (`Books_idBooks`) REFERENCES `books` (`idBooks`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Loans_Readers1` FOREIGN KEY (`Readers_idReaders`) REFERENCES `readers` (`idReaders`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (1,'2018-03-12','2018-06-01',NULL,4,2),(2,'2018-02-02','2018-03-02',NULL,4,3),(3,'2018-04-02','2018-04-20',NULL,4,13),(4,'2018-04-12','2018-08-13',NULL,1,7),(5,'2018-05-10','2018-05-21',NULL,1,9),(6,'2018-04-17','2018-05-01',NULL,1,10),(7,'2018-04-17','2018-05-01',NULL,4,8),(8,'2018-04-17','2018-05-01',NULL,1,4),(9,'2018-04-17','2018-04-30',NULL,3,12),(11,'2018-04-17','2018-04-30',NULL,3,6),(12,'2018-02-14','2018-08-13',NULL,2,5),(13,'2018-02-12','2018-03-10','2018-02-20',4,8),(14,'2018-03-01','2018-03-10','2018-03-20',4,10),(15,'2018-03-21','2018-04-10','2018-04-02',2,2);
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readers`
--

DROP TABLE IF EXISTS `readers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readers` (
  `idReaders` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Login` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Street` varchar(45) NOT NULL,
  `Postcode` varchar(6) NOT NULL,
  `City` varchar(45) NOT NULL,
  `Country` varchar(45) NOT NULL,
  `Mail` varchar(45) DEFAULT NULL,
  `Telephone` varchar(45) DEFAULT NULL,
  `Role` varchar(15) NOT NULL,
  `Payment` decimal(5,0) NOT NULL,
  PRIMARY KEY (`idReaders`),
  UNIQUE KEY `Login_UNIQUE` (`Login`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readers`
--

LOCK TABLES `readers` WRITE;
/*!40000 ALTER TABLE `readers` DISABLE KEYS */;
INSERT INTO `readers` VALUES (1,'Pawe?','Skoczylas','Pawelu','stoMil','Milionowa 12 m 8','23-400','Warszawa','Polska','pSkoczylas@wp.pl','+48794312900','USER',41),(2,'Aleksandra','Fokus','Alaq','true44','Kolejowa 22','60-432','Gda?sk','Polska','aleksandraP@gmail.com','+48766514332','USER',32),(3,'Katarzyna','Ziomek','Katarzyna33','Domnadwisla','Ogarna 43 m 6','60-453','Pozna?','Polska','katarzynaZ@gmail.com','+48790312248','USER',15),(4,'Patryk','Leski','PatrykW','zadomowienie','Galicyjska 44','34-900','Gda?sk','Polska',NULL,NULL,'USER',6),(5,'Ewa','Kot','Rachel','naoko','Sobiesława 3','50-413','Wrocław','Polska',NULL,NULL,'ADMIN',0),(6,'Izabela','Węglowska','PawieOko','dorm','Skalista 33','50-713','Wrocław','Polska',NULL,NULL,'READER',0);
/*!40000 ALTER TABLE `readers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations` (
  `idReservations` int(11) NOT NULL AUTO_INCREMENT,
  `DateFrom` date NOT NULL,
  `DateTo` date NOT NULL,
  `cancelDate` date DEFAULT NULL,
  `Readers_idReaders` int(11) NOT NULL,
  `Books_idBooks` int(11) NOT NULL,
  PRIMARY KEY (`idReservations`),
  KEY `fk_Reservations_Readers1_idx` (`Readers_idReaders`),
  KEY `fk_Reservations_Books1_idx` (`Books_idBooks`),
  CONSTRAINT `fk_Reservations_Books1` FOREIGN KEY (`Books_idBooks`) REFERENCES `books` (`idBooks`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservations_Readers1` FOREIGN KEY (`Readers_idReaders`) REFERENCES `readers` (`idReaders`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (4,'2018-04-08','2018-04-25',NULL,2,9),(5,'2018-04-08','2018-04-20',NULL,2,8),(6,'2018-05-08','2018-05-20',NULL,3,7),(7,'2018-04-18','2018-04-30',NULL,4,1),(8,'2018-04-08','2018-04-20',NULL,4,12),(9,'2018-04-18','2018-05-01',NULL,3,4),(10,'2018-05-01','2018-06-01',NULL,4,5),(11,'2018-02-15','2018-02-20','2018-05-03',1,1),(13,'2018-07-13','2018-07-20',NULL,2,2);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-17 22:16:38
