-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `idAddress` int(11) NOT NULL AUTO_INCREMENT,
  `PrimaryAddress` varchar(50) NOT NULL,
  `SecondaryAddress` varchar(50) DEFAULT NULL,
  `idCustomer` int(11) NOT NULL,
  PRIMARY KEY (`idAddress`),
  UNIQUE KEY `idAddress_UNIQUE` (`idAddress`),
  KEY `idCustomerID_idx` (`idCustomer`),
  CONSTRAINT `idCustomerID` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (33,'Av. Jaques Dolores 1024','na',32),(36,'Ruas','avenidas',35),(37,'Avenida das cenas n345','NA',36);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `idCustomer` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `ContactDetails` varchar(30) NOT NULL,
  `Contact` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`idCustomer`),
  UNIQUE KEY `idCustomer_UNIQUE` (`idCustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (32,'PT MEO','joao.manuel@hotmail.com','916630586'),(35,'NOS','cenas@hotmail.com','213434445'),(36,'Accenture','geral@accenture.pt','212303609');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `idEquipment` int(11) NOT NULL AUTO_INCREMENT,
  `SerialNumber` varchar(45) NOT NULL,
  `Vendor` varchar(45) NOT NULL,
  `Model` varchar(45) NOT NULL,
  `InstallationAddress` varchar(250) NOT NULL,
  `SoftwareVersion` varchar(15) DEFAULT NULL,
  `SoftwareLastVersion` varchar(15) DEFAULT NULL,
  `SoftwareUpdate` tinyint(1) DEFAULT NULL,
  `UpdateDate` date DEFAULT NULL,
  `idCustomer` int(11) DEFAULT NULL,
  `idSLA` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEquipment`),
  UNIQUE KEY `idEquipment_UNIQUE` (`idEquipment`),
  KEY `idCustomer_idx` (`idCustomer`),
  KEY `idSLA_idx` (`idSLA`),
  CONSTRAINT `idCustomer` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idCustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idSLA` FOREIGN KEY (`idSLA`) REFERENCES `sla` (`idSLA`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (46,'Teste567','Teste567','Teste567','Teste567','v.1.3','Teste562',0,'2017-08-10',32,42),(59,'cenascoiso','Motorola','cenascoiso','Ruas mario miguel de sousa rama','cenas 13432423','cenas',0,'2017-07-27',32,55),(60,'coisas1','coisas1','coisas1','coisas1','coisas1','coisas1',0,'2017-07-27',35,56),(63,'1234','Cisco','1234','rua qualquer 123','v.1.3','v.1.2',1,'2017-08-17',35,59),(64,'cenas','Siemens','cenas','Avenida da Liberdade','v.1.3','v1.3',0,'2017-08-17',32,60);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla`
--

DROP TABLE IF EXISTS `sla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla` (
  `idSLA` int(11) NOT NULL AUTO_INCREMENT,
  `SLA` varchar(15) DEFAULT NULL,
  `ContractStart` date NOT NULL,
  `ContractEnd` date NOT NULL,
  `InstallationDate` date NOT NULL,
  PRIMARY KEY (`idSLA`),
  UNIQUE KEY `idSLA_UNIQUE` (`idSLA`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla`
--

LOCK TABLES `sla` WRITE;
/*!40000 ALTER TABLE `sla` DISABLE KEYS */;
INSERT INTO `sla` VALUES (3,NULL,'2014-01-07','2017-06-30','2015-01-07'),(42,'Teste565','2017-07-19','2017-07-19','2017-07-19'),(55,'24x7','2017-07-20','2017-08-31','2017-07-20'),(56,'coisas1','2017-07-20','2017-07-20','2017-07-20'),(59,'24x7','2017-07-26','2017-07-26','2017-07-28'),(60,'24x7','2017-08-17','2017-08-30','2017-08-18');
/*!40000 ALTER TABLE `sla` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-22 11:13:05
