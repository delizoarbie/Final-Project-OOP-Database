-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: vanrental
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `Booking_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BookDstart` date NOT NULL,
  `BookDend` date NOT NULL,
  `BookInsurance` varchar(45) NOT NULL,
  `BookNumPass` int(11) NOT NULL,
  `Transaction_No` int(11) DEFAULT NULL,
  `Van_Code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Booking_ID`),
  KEY `Transaction_No_idx` (`Transaction_No`)
) ENGINE=InnoDB AUTO_INCREMENT=12346 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (11,'2019-10-25','2019-10-23','Collision Damage Waiver (CDW)',1,NULL,'1234'),(111,'2019-10-03','2019-10-18','Collision Damage Waiver (CDW)',1,NULL,NULL),(112,'2019-10-25','2019-10-23','Collision Damage Waiver (CDW)',1,NULL,'1234'),(1111,'2019-10-10','2019-10-24','Collision Damage Waiver (CDW)',1,NULL,'1234'),(1123,'2019-10-18','2019-10-12','Collision Damage Waiver (CDW)',1,NULL,'1238'),(11112,'2019-10-11','2019-10-18','Collision Damage Waiver (CDW)',1,NULL,'1234'),(12344,'2019-10-17','2019-10-24','Collision Damage Waiver (CDW)',1,NULL,'1234'),(12345,'2019-10-03','2019-10-11','Supplemental Liabillity Protection (SLP)',5,NULL,NULL);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `Customer_ID` int(11) NOT NULL,
  `Customer_FName` varchar(45) DEFAULT NULL,
  `Customer_MName` varchar(45) DEFAULT NULL,
  `Customer_LName` varchar(45) DEFAULT NULL,
  `Customer_Address` varchar(45) DEFAULT NULL,
  `Customer_Email` varchar(45) DEFAULT NULL,
  `Customer_PhoneNumber` int(11) DEFAULT NULL,
  `Customer_Birthdate` date DEFAULT NULL,
  `Customer_Gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Customer_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'liza','tq','paano',NULL,NULL,NULL,NULL,NULL),(11,'Maria','lisaus','Paanoc','cdo','lijsj',897868,'2019-10-24','Male'),(111,'fLORENCE','tq','rEYES',NULL,NULL,NULL,NULL,NULL),(999,'liza','orongan','paano','Address','Email',9797,'2019-10-16','Male'),(1111,'liza','to','paano','cdo','liza',98764,'2019-10-09','Male'),(1123,'Maria','lisaus','Paanoc','cdo','lijsj',897868,'2019-10-14','Male'),(9991,'liza','orongan','paano','cdo','liza',9797,'2019-10-16','Male'),(11112,'FLORENCE','to','REYES','cdo','liza',98764,'2019-10-09','Male'),(12334,'liza','mug','uyjjjjj',NULL,NULL,NULL,NULL,NULL),(12344,'liza','orongan','paano','Address','Email',987653,'2019-10-16','Male'),(1231111,'liza','mug','uyjjjjj',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `Driver_ID` int(11) NOT NULL,
  `DriverFname` varchar(45) DEFAULT NULL,
  `DriverMname` varchar(45) DEFAULT NULL,
  `DriverLname` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `DrivePhoneNum` int(11) DEFAULT NULL,
  `DriverEmail` varchar(45) DEFAULT NULL,
  `Driver_Bdate` date DEFAULT NULL,
  `Insurance` varchar(45) DEFAULT NULL,
  `Guardian_name` varchar(45) DEFAULT NULL,
  `Guardian_Pnum` int(11) DEFAULT NULL,
  `driver_gender` varchar(45) DEFAULT NULL,
  `Van_Code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Driver_ID`),
  KEY `Van_Code_idx` (`Van_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (123,'Johann','Palma','Domasig','cdo',9878975,'johannCutie@gmail.com','1998-12-26','Progressive','Arbie Delizo',9878975,'Male','123'),(1234,'Niel','Osman','Abad','cdo',98789443,'nielAbad@gmail.com','1999-11-03','Allstate','Arbie Delizo',9887898,'Male','123456'),(12345,'Mariz','Soberano','Bernardo','cdo',98789443,'nielAbad@gmail.com','2019-10-10','Allstate','Ann Luyao',9887898,'Female','123456'),(123456,'Mariz','Soberano','Bernardo','cdo',98789443,'nielAbad@gmail.com','2019-10-23','Allstate','Ann Luyao',9887898,'Female','123456');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `Employee_ID` int(11) NOT NULL,
  `Employee_FName` varchar(45) DEFAULT NULL,
  `Employee_MName` varchar(45) DEFAULT NULL,
  `Employee_LName` varchar(45) DEFAULT NULL,
  `Employee_Gender` varchar(45) DEFAULT NULL,
  `Employee_Position` varchar(45) DEFAULT NULL,
  `Employee_Birthdate` date DEFAULT NULL,
  `Employee_PhoneNum` int(11) DEFAULT NULL,
  `Employee_Email` varchar(45) DEFAULT NULL,
  `Employee_Password` varchar(45) DEFAULT NULL,
  `Employee_Address` varchar(45) DEFAULT NULL,
  `Employee_Username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Employee_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (123,'Maria Liza','Orongan','Paano','Female','idk','2019-10-21',96786931,'liza@gmail.com','12345','SDS','Liza'),(1111111,'liza','wla','Paano','Female','idk','2019-10-08',8978,'lkjdusd','123','cdo','liza'),(1211123,'Arbie','IDk','Gabales','Female','ifdk','2019-10-22',98763567,'marizdjhs@jdgkdhfs','gabales','cdo','mariz'),(1234578,'Arbie','IDk','Gabales','Female','ifdk','2019-10-08',98763567,'marizdjhs@jdgkdhfs','gabales','cdo','mariz'),(12345678,'Mariz','IDk','Gabales','Female','ifdk','2019-10-08',98763567,'marizdjhs@jdgkdhfs','gabales','cdo','mariz'),(1234578900,'Arbie','IDk','Gabales','Female','ifdk','2019-10-08',98763567,'marizdjhs@jdgkdhfs','gabales','cdo','mariz');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance`
--

DROP TABLE IF EXISTS `maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance` (
  `Garage_Code` int(11) NOT NULL,
  `Main_Type` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Main_Date` date DEFAULT NULL,
  `Cost` int(11) DEFAULT NULL,
  `vanCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Garage_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance`
--

LOCK TABLES `maintenance` WRITE;
/*!40000 ALTER TABLE `maintenance` DISABLE KEYS */;
INSERT INTO `maintenance` VALUES (3,'Aircon','cdo','2019-10-24',111,'1234');
/*!40000 ALTER TABLE `maintenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `Payment_No` int(11) NOT NULL,
  `Payment_Type` varchar(45) DEFAULT NULL,
  `Payment_Date` date DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `Category` varchar(45) DEFAULT NULL,
  `Employee_ID` int(11) DEFAULT NULL,
  `Transaction_No` int(11) DEFAULT NULL,
  PRIMARY KEY (`Payment_No`),
  KEY `Transaction_No_idx` (`Transaction_No`),
  KEY `Employee_ID_idx` (`Employee_ID`),
  CONSTRAINT `Employee_ID1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`),
  CONSTRAINT `Transaction_No` FOREIGN KEY (`Transaction_No`) REFERENCES `transaction` (`Transaction_No`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (2,'Check','2019-10-08',123,'Repair',123,NULL),(21,'Cash','2019-10-08',123,'Reservation',123,NULL);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair`
--

DROP TABLE IF EXISTS `repair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair` (
  `Repair_Code` int(11) NOT NULL,
  `Repair_Type` varchar(45) DEFAULT NULL,
  `Rep_Date` date DEFAULT NULL,
  `Rep_Cost` double DEFAULT NULL,
  `Van_Code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Repair_Code`),
  KEY `Van_Code_idx` (`Van_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair`
--

LOCK TABLES `repair` WRITE;
/*!40000 ALTER TABLE `repair` DISABLE KEYS */;
INSERT INTO `repair` VALUES (123,'Air Conditioning Service','2019-10-24',2000,'1234'),(1234,'Clutches','2019-10-24',2000,'1237'),(12345,'Air Conditioning Service','2019-10-22',2000,'1234');
/*!40000 ALTER TABLE `repair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `Reservation_ID` int(11) NOT NULL,
  `ResDate` date DEFAULT NULL,
  `Van_Code` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Reservation_ID`),
  KEY `Van_Code_idx` (`Van_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'2019-10-11','1234',NULL),(111,'2019-10-11','1234',NULL),(12311,'2019-10-28','1235',NULL),(12334,'2019-10-15','1235',NULL),(1231111,'2019-10-28','1235',NULL);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `Transaction_No` int(11) NOT NULL,
  `Transaction_Type` varchar(45) DEFAULT NULL,
  `Transaction_Date` date DEFAULT NULL,
  `Employee_ID` int(11) DEFAULT NULL,
  `Customer_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Transaction_No`),
  KEY `Customer_ID_idx` (`Customer_ID`),
  KEY `Employee_ID_idx` (`Employee_ID`),
  CONSTRAINT `Customer_ID` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`Customer_ID`),
  CONSTRAINT `Employee_ID` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `van`
--

DROP TABLE IF EXISTS `van`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `van` (
  `Van_Code` int(11) NOT NULL,
  `Van_Type` varchar(45) DEFAULT NULL,
  `Regis_No` varchar(45) DEFAULT NULL,
  `Van_Insurance` varchar(45) DEFAULT NULL,
  `Van_Model` varchar(45) DEFAULT NULL,
  `Garage_Code` int(11) DEFAULT NULL,
  `Repair_Code` int(11) DEFAULT NULL,
  PRIMARY KEY (`Van_Code`),
  KEY `Garage_Code_idx` (`Garage_Code`),
  KEY `Repair_Code_idx` (`Repair_Code`),
  CONSTRAINT `Garage_Code` FOREIGN KEY (`Garage_Code`) REFERENCES `maintenance` (`Garage_Code`),
  CONSTRAINT `Repair_Code` FOREIGN KEY (`Repair_Code`) REFERENCES `repair` (`Repair_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `van`
--

LOCK TABLES `van` WRITE;
/*!40000 ALTER TABLE `van` DISABLE KEYS */;
/*!40000 ALTER TABLE `van` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-14 11:59:39
