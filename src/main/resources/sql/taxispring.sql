-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: taxispring
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `car_category`
--

DROP TABLE IF EXISTS `car_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_category` (
  `id` int NOT NULL,
  `translation_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa6xjcwb033nb7ob6s3q0vfn3j` (`translation_id`),
  CONSTRAINT `FKa6xjcwb033nb7ob6s3q0vfn3j` FOREIGN KEY (`translation_id`) REFERENCES `translations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_category`
--

LOCK TABLES `car_category` WRITE;
/*!40000 ALTER TABLE `car_category` DISABLE KEYS */;
INSERT INTO `car_category` VALUES (1,6),(2,7),(3,8);
/*!40000 ALTER TABLE `car_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_status`
--

DROP TABLE IF EXISTS `car_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_status` (
  `id` int NOT NULL,
  `translation_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6yj7617j475gdo9scgfxaua73` (`translation_id`),
  CONSTRAINT `FK6yj7617j475gdo9scgfxaua73` FOREIGN KEY (`translation_id`) REFERENCES `translations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_status`
--

LOCK TABLES `car_status` WRITE;
/*!40000 ALTER TABLE `car_status` DISABLE KEYS */;
INSERT INTO `car_status` VALUES (1,3),(2,4),(3,5);
/*!40000 ALTER TABLE `car_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `id` int NOT NULL,
  `passenger_count` int DEFAULT NULL,
  `plate` varchar(255) DEFAULT NULL,
  `price_multiplier` float DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `coordinates_id` int NOT NULL,
  `manufacturer_id` int DEFAULT NULL,
  `model_id` int DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKppithwu9i8a8keb8ao07lc4mm` (`category_id`),
  KEY `FK7ubjc9enkpjutw2715noffhk4` (`coordinates_id`),
  KEY `FK6hctqxdwhcl223hfxh4swhdxm` (`manufacturer_id`),
  KEY `FKrwe6b2vd08hb4gnst223xsna4` (`model_id`),
  KEY `FK1nq5bx54xyo41stcm2h5ds8dh` (`status_id`),
  CONSTRAINT `FK1nq5bx54xyo41stcm2h5ds8dh` FOREIGN KEY (`status_id`) REFERENCES `car_status` (`id`),
  CONSTRAINT `FK6hctqxdwhcl223hfxh4swhdxm` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers` (`id`),
  CONSTRAINT `FK7ubjc9enkpjutw2715noffhk4` FOREIGN KEY (`coordinates_id`) REFERENCES `coordinates` (`id`),
  CONSTRAINT `FKppithwu9i8a8keb8ao07lc4mm` FOREIGN KEY (`category_id`) REFERENCES `car_category` (`id`),
  CONSTRAINT `FKrwe6b2vd08hb4gnst223xsna4` FOREIGN KEY (`model_id`) REFERENCES `models` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,4,'AA0000AA',1.6,2,1,1,1,1),(2,4,'AA0001AA',1.9,2,2,2,2,1),(3,4,'AA0002AA',1,3,1,3,3,1);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordinates`
--

DROP TABLE IF EXISTS `coordinates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coordinates` (
  `id` int NOT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordinates`
--

LOCK TABLES `coordinates` WRITE;
/*!40000 ALTER TABLE `coordinates` DISABLE KEYS */;
INSERT INTO `coordinates` VALUES (1,'50.515337','30.418900'),(2,'50.495412','30.563643'),(16,'50.48793996556941','30.478083614491652'),(17,'50.4402287265342','30.561873706331085'),(20,'50.487211090141614','30.477967969563906'),(21,'50.46820305341561','30.52345823445671'),(24,'50.45058945726569','30.52588316149331'),(25,'50.45829099449301','30.38550378808577'),(28,'50.468368539753726','30.523475785803612'),(29,'50.4940933078438','30.475091058462'),(32,'50.455823929445955','30.517854560791534'),(33,'50.45462170042873','30.45760141748076'),(36,'50.48777678604296','30.473491060562537'),(37,'50.38416462001783','30.445143421287867'),(40,'50.46904643219432','30.522317756592315'),(41,'50.4718871486279','30.453138221680092'),(44,'50.43636592492646','30.545148719727877'),(45,'50.4802990384195','30.460519660889076'),(48,'50.46434794968815','30.505666603028317'),(49,'50.485651279838294','30.463609565674005'),(52,'50.487260802387624','30.4622490805624'),(53,'50.45513935485738','30.50825432958561'),(56,'50.468390858009684','30.51253305810701'),(57,'50.481063681447694','30.456743110596904'),(60,'50.48881864739684','30.477857459961683'),(61,'50.46096038199698','30.525922645509127'),(64,'50.47931590777657','30.515966285645504'),(65,'50.471450126439436','30.458116401612642'),(68,'50.51561407704065','30.36230908511243'),(69,'50.442223359724295','30.524406861524994'),(72,'50.50438717360527','30.43861221687814'),(73,'50.49532698751818','30.576787112416696'),(76,'50.47691261344875','30.500173438965362'),(77,'50.45691683840383','30.528669227540604'),(80,'50.46740747969059','30.50995813745226'),(81,'50.48772647566952','30.456571449220178'),(84,'50.475055438684024','30.51630960839907'),(85,'50.46992051696742','30.451764930664353'),(88,'50.46970199728912','30.526952613769822'),(89,'50.487180380339225','30.479574073730987'),(92,'50.46697041609525','30.52506433862368'),(93,'50.487944912034806','30.479402412354148'),(96,'50.45494958410006','30.53467737573385'),(97,'50.48783569397861','30.479574073730987'),(100,'50.477349585142605','30.49450861352568'),(101,'50.42608712113537','30.461721290527976'),(104,'50.44992178474348','30.5901240004888'),(105,'50.4393179347654','30.52506433862368'),(108,'50.50112241736781','30.428827280807127'),(109,'50.50141475302783','30.497057173244457'),(112,'50.49579187863924','30.426808054557682'),(113,'50.46697653442874','30.524540660461753'),(116,'50.48154824826142','30.45700255341876'),(117,'50.48613579961534','30.529443654493207'),(120,'50.46843850471544','30.525152120070288'),(121,'50.44111486129151','30.559484395460345');
/*!40000 ALTER TABLE `coordinates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drivers`
--

DROP TABLE IF EXISTS `drivers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drivers` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drivers`
--

LOCK TABLES `drivers` WRITE;
/*!40000 ALTER TABLE `drivers` DISABLE KEYS */;
INSERT INTO `drivers` VALUES (1,'Vasia',NULL,'Pupkin'),(2,'Ivan',NULL,'Ivanov'),(3,'Petr',NULL,'Petrov');
/*!40000 ALTER TABLE `drivers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driving`
--

DROP TABLE IF EXISTS `driving`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driving` (
  `id` int NOT NULL,
  `day_of_driving` date DEFAULT NULL,
  `car_id` int NOT NULL,
  `driver_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9089ctjr1ftd8efdjd8kvta91` (`car_id`),
  KEY `FK7s3g5it1fk5dctv9ni4tw7lc2` (`driver_id`),
  CONSTRAINT `FK7s3g5it1fk5dctv9ni4tw7lc2` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`),
  CONSTRAINT `FK9089ctjr1ftd8efdjd8kvta91` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driving`
--

LOCK TABLES `driving` WRITE;
/*!40000 ALTER TABLE `driving` DISABLE KEYS */;
INSERT INTO `driving` VALUES (1,'2022-02-02',1,1),(2,'2022-02-02',2,2),(3,'2022-02-02',3,3);
/*!40000 ALTER TABLE `driving` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (122),(122),(122),(122),(122),(122),(122),(122),(122),(122),(122),(122);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturers`
--

DROP TABLE IF EXISTS `manufacturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturers` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturers`
--

LOCK TABLES `manufacturers` WRITE;
/*!40000 ALTER TABLE `manufacturers` DISABLE KEYS */;
INSERT INTO `manufacturers` VALUES (1,'BMW'),(2,'Mercedes'),(3,'Skoda');
/*!40000 ALTER TABLE `manufacturers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `models`
--

DROP TABLE IF EXISTS `models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `models` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `models`
--

LOCK TABLES `models` WRITE;
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` VALUES (1,'310i'),(2,'E63'),(3,'Rapid');
/*!40000 ALTER TABLE `models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `id` int NOT NULL,
  `translation_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlknj9t1lsrtxo93drwdu6ee8m` (`translation_id`),
  CONSTRAINT `FKlknj9t1lsrtxo93drwdu6ee8m` FOREIGN KEY (`translation_id`) REFERENCES `translations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL,
  `date_of_order` datetime(6) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `time_to_arrival` int DEFAULT NULL,
  `driving_id` int NOT NULL,
  `route_id` int NOT NULL,
  `status_id` int NOT NULL DEFAULT '1',
  `user_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjkh50il7x2adcs9w6obqhpsfo` (`driving_id`),
  KEY `FK6wuyx1giqf3cs3yu9cc3oaiun` (`route_id`),
  KEY `FK1abokg3ghque9pf2ujbxakng` (`status_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK1abokg3ghque9pf2ujbxakng` FOREIGN KEY (`status_id`) REFERENCES `order_status` (`id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK6wuyx1giqf3cs3yu9cc3oaiun` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`),
  CONSTRAINT `FKjkh50il7x2adcs9w6obqhpsfo` FOREIGN KEY (`driving_id`) REFERENCES `driving` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (14,'2020-10-05 15:12:29.257364',103,18,1,15,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(18,'2020-10-05 16:02:14.954446',68,11,2,19,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(22,'2020-10-05 16:02:23.253041',79,33,3,23,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(26,'2020-10-05 18:16:46.056188',81,23,2,27,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(30,'2020-10-05 18:16:52.928961',82,24,2,31,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(34,'2020-10-05 18:22:25.867481',155,18,1,35,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(38,'2020-10-05 18:22:29.549315',85,23,2,39,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(42,'2020-10-05 18:22:34.201344',74,40,3,43,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(46,'2020-10-05 18:23:13.062924',86,24,2,47,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(50,'2020-10-05 18:23:41.408823',78,18,1,51,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(54,'2020-10-05 18:24:29.746211',80,19,2,55,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(58,'2020-10-05 18:31:25.089942',65,16,1,59,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(62,'2020-10-05 18:32:13.592169',99,13,2,63,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(66,'2020-10-05 19:11:45.479600',165,8,1,67,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(70,'2020-10-05 19:26:13.282363',102,5,1,71,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(74,'2020-10-05 20:44:30.427899',48,15,2,75,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(78,'2020-10-05 20:44:55.461481',66,19,2,79,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(82,'2020-10-05 20:46:00.661843',76,25,1,83,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(86,'2020-10-05 20:47:49.661068',39,27,3,87,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(90,'2020-10-05 20:48:35.600287',83,9,2,91,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(94,'2020-10-05 20:48:56.035900',78,12,2,95,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(98,'2020-10-05 20:49:04.274129',93,19,2,99,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(102,'2020-10-05 20:49:11.724746',91,21,2,103,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(106,'2022-02-02 19:17:02.583383',50,8,1,107,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(110,'2022-02-02 19:17:19.651385',76,9,1,111,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(114,'2022-02-02 19:17:25.689385',87,16,2,115,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S'),(118,'2022-02-02 19:17:31.528385',51,22,3,119,2,_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES (1,'2020-10-05 15:05:46.613124','79df3981-9583-413e-8a49-01e8ca23482d'),(2,'2020-10-05 19:11:36.488025','26c7926d-18a3-425f-aacf-6a19387f4add'),(3,'2020-10-05 19:25:28.402502','606b03bf-47a4-4c7e-b4b2-8dab8510405e'),(4,'2020-10-05 19:42:33.633118','1dbc5e33-8eff-4653-bb4b-006c4aa8fe13'),(5,'2020-10-05 19:42:40.920694','b273c0cd-b065-489d-b383-07e3a8446dbc'),(6,'2020-10-05 19:43:34.544293','84c3f947-2a2e-472b-a010-2d43b3a45368'),(7,'2020-10-05 19:43:41.930448','28416fba-5cf4-4121-a9dc-2c96309f5e6a'),(8,'2020-10-05 19:47:38.305267','942fa1d3-8289-465d-8575-693a7ed1d080'),(9,'2020-10-05 19:47:52.474673','8219441c-de8b-4c50-b3f5-f43e39a4f5fb'),(10,'2020-10-05 19:50:36.006297','7781946e-eb60-42b9-9906-c11d0002d791'),(11,'2020-10-05 19:50:41.043633','d5447262-c2a6-4bb8-997d-a9a2e17c4a85'),(12,'2020-10-05 20:44:25.773678','a8b9d296-21d6-4307-b50e-821c132e5392'),(13,'2020-10-08 20:04:50.123363','f0cfb64c-ad69-4dc4-bcfd-572a1787d825'),(14,'2020-10-08 20:04:56.536378','4c2308a7-9b6d-4ee5-bdf6-d42cc3237b02'),(15,'2020-10-08 20:07:58.028607','4ebcdb6e-738d-4d5a-8fc4-c4736f00276a'),(16,'2020-10-08 20:08:04.420471','922312a2-31fb-421d-b900-fefc0e9c68eb'),(17,'2020-10-08 20:08:59.596586','b70849e3-020b-426c-8d47-04366fd26a2c'),(18,'2020-10-08 20:09:05.382222','540ad842-941a-4eba-8690-fa2e355416c1'),(19,'2020-10-08 20:11:02.717172','2ae2dbec-7e33-4f13-8d1a-b591f998455c'),(20,'2020-10-08 20:11:10.217211','cc5986e5-5b49-4d55-920f-80e2e4e4e235'),(21,'2020-10-08 20:14:05.301362','cbe04d4e-b9e4-458c-bbbc-eb4bd0c07a11'),(22,'2020-10-08 20:14:12.385440','e01e6ea7-9a91-46c3-88ea-6080f05afe1a'),(23,'2020-10-08 20:16:38.711511','de7b5e4e-60cf-4eed-81c0-878ed81c9f3a'),(24,'2020-10-08 20:16:44.644360','abff81e5-4987-4faf-8ae5-d3fa61b13aa3'),(25,'2020-10-08 20:26:03.939263','b0961759-245e-4a38-8ca7-e185a47be3d0'),(26,'2020-10-08 20:26:08.628632','3a272726-5479-4c76-a35a-8d513ba4f6f7'),(27,'2020-10-08 20:31:13.608997','52708a6a-8842-4242-b5b3-4294f580f688'),(28,'2020-10-08 20:31:19.406591','73986cc4-7148-4487-9765-072dab767dfc'),(29,'2020-10-08 20:40:40.014914','489afa4d-e81a-4f9e-bcab-ce7aa443d47c'),(30,'2020-10-08 20:41:51.372361','0c25f471-a199-4992-9c82-f23399d92916'),(31,'2020-10-08 20:43:48.160579','958a09b2-cd73-4416-9a59-b7fe9560c1a1'),(32,'2020-10-08 20:44:37.622640','bc0c80bb-2d09-404e-9cbb-b469304fc0ab'),(33,'2020-10-08 20:45:04.700049','ad7e07e5-af6d-40b9-a05f-0d3ddb1d81f4'),(34,'2020-10-08 20:45:23.605700','0d17b828-4714-4eb2-a2b2-086d2a27c60f'),(35,'2020-10-08 20:46:02.322605','949ad29a-1d6c-44d8-9be1-8feb9f5b3749'),(36,'2020-10-08 20:47:04.477552','6d326896-921c-41d5-85fa-30b508bbdf1b'),(37,'2020-10-08 20:47:46.664564','5174340b-a739-459e-b942-d991217df795'),(38,'2020-10-08 20:52:19.732242','ee26bb8f-164a-41a3-92e7-d14d35353ce5'),(39,'2020-10-08 20:53:25.087251','1185c617-e97e-44cd-b1c5-be4634d6db31'),(40,'2020-10-08 20:54:13.862766','3db1180b-19be-47d4-9004-4dddf7cf981b'),(41,'2020-10-08 20:54:46.939035','7f00320c-c01c-4c57-91a6-387475bc0e20'),(42,'2020-10-08 20:55:15.854670','2943feed-9a70-457a-be03-0f05c2b90b9c'),(43,'2020-10-08 20:56:24.190579','25df4437-a7a4-4cd1-996d-1d56f6b86c63'),(44,'2020-10-08 20:57:04.217739','d8c4b7b3-7e0e-47d4-a463-a584ca20c5f5'),(45,'2020-10-08 20:57:50.850626','ce063743-fda0-4e94-9ab4-f078b02c30f0'),(46,'2020-10-08 20:58:15.856712','db72daec-0e38-4f83-8858-605957970227'),(47,'2020-10-08 20:59:23.467740','503acb9d-3bf6-4b44-afc5-a368d943a593'),(48,'2020-10-08 21:00:05.870119','8b459014-a902-4a0b-87d1-74578281b44b'),(49,'2020-10-08 21:00:46.985566','08f6940c-69b7-42be-b483-2f21f3a96929'),(50,'2020-10-08 21:01:44.466175','e58cf04e-2328-4492-85fb-4afe6796b6d1'),(51,'2020-10-08 21:02:29.058342','d4b40c79-38f6-46b3-9bae-2e4787fe4c41'),(52,'2020-10-08 21:03:53.214635','35530019-a32c-4690-8103-818142f31327'),(53,'2020-10-08 21:04:24.980811','13463dd8-afb6-49f6-8b27-99983bed54b8'),(54,'2020-10-08 21:05:02.329024','4932acea-cf72-451a-bbff-5f6c0741c6ac'),(55,'2020-10-08 21:05:32.055332','60745a41-79db-4bca-895f-064f021954e2'),(56,'2020-10-08 21:06:06.561656','9beadf39-7f49-45b8-bf80-3ca06cc18c78'),(57,'2020-10-08 21:06:36.893676','1e680986-e6e9-454d-bb5d-36923449e52c'),(59,'2020-10-08 21:14:52.632291','ce211751-6200-4c75-9f19-1b7b2e54a548'),(60,'2020-10-08 21:15:39.772573','8352504a-a7d5-4318-b68e-498b09e96c59'),(61,'2020-10-08 21:19:22.247537','fba37f45-4115-4e68-ac89-7e5ecb736780'),(62,'2020-10-08 21:19:46.304506','5976389a-cf09-4083-89c7-6e637863de22'),(63,'2020-10-08 21:21:01.562638','02deed87-66d2-403c-b832-ad1c667fc55a'),(64,'2020-10-08 21:22:11.102560','0ab3efba-9b69-466a-9342-c32a9d443926'),(65,'2020-10-08 21:23:39.765561','a1a8d82d-2ae2-4540-929c-5f96aa550d5d'),(81,'2020-10-08 21:30:41.633106','9197230d-56e6-4eac-80ee-55ce72c13a04'),(82,'2020-10-09 16:05:41.445760','ef440693-89ca-4d26-8389-fc609a3150b6'),(85,'2022-02-02 19:14:54.105386','6ea79b96-01ed-40cd-add2-299b37115561'),(86,'2022-02-02 23:28:01.190061','fc984d53-985e-4be2-b7c7-0f23838edc73'),(87,'2022-02-02 23:28:38.133061','e8b1b4e6-57ea-4abb-a762-279bca48d654'),(88,'2022-02-02 23:30:33.766059','f25737c8-3c49-4952-b686-419ede0b26c2'),(89,'2022-02-02 23:33:57.285060','d836d472-591c-49a9-b5f5-15162fb05248'),(90,'2022-02-02 23:38:06.930972','07523a0a-22c0-4287-b425-787a03dbeb22'),(91,'2022-02-02 23:40:06.298702','58afdd77-7a73-46c2-804f-0846a0417c21'),(92,'2022-02-02 23:44:38.839617','29d63a37-4b3d-466a-88fd-4878d0a4fff9'),(93,'2022-02-02 23:47:12.545837','2c72991f-5b50-4f3e-a108-430f340199a8'),(94,'2022-02-02 23:52:08.534701','a42e3d82-03b9-4019-8600-0aad32dea931'),(95,'2022-02-02 23:56:16.902638','b85fa009-4617-4568-aa35-ef311d2cb471'),(96,'2022-02-02 23:57:21.062550','4cc36f33-5910-40ad-99a0-5e36a81d3fa1'),(97,'2022-02-03 00:01:32.333438','be96dd91-8e20-44d7-926b-391ec00bab5f'),(98,'2022-02-03 00:06:04.287229','694b85b3-265d-4657-8ab5-8494720b0681'),(99,'2022-02-03 00:07:09.766581','9efeb233-1318-4deb-83cf-7e632b28850f'),(100,'2022-02-03 00:08:54.016337','30cac9dc-e34e-43c1-a7cd-4fd93168e6e4'),(102,'2022-02-03 00:09:28.541159','88e946a2-5979-46a1-9836-0d99906444fa'),(103,'2022-02-03 00:11:46.462181','d1e97864-8546-41a1-a17c-d24fd759ebd3');
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routes` (
  `id` int NOT NULL,
  `distance` float DEFAULT NULL,
  `time` int DEFAULT NULL,
  `departure_coordinate_id` int NOT NULL,
  `destination_coordinate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt0t7a6dep5322nkyq3d41srld` (`departure_coordinate_id`),
  KEY `FKlawu4prr7yv4r48xgou9bfu6q` (`destination_coordinate_id`),
  CONSTRAINT `FKlawu4prr7yv4r48xgou9bfu6q` FOREIGN KEY (`destination_coordinate_id`) REFERENCES `coordinates` (`id`),
  CONSTRAINT `FKt0t7a6dep5322nkyq3d41srld` FOREIGN KEY (`departure_coordinate_id`) REFERENCES `coordinates` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (15,9.72532,17,16,17),(19,4.6363,12,20,21),(23,11.2677,23,24,25),(27,6.23603,18,28,29),(31,6.31288,22,32,33),(35,16.7461,42,36,37),(39,6.96758,27,40,41),(43,11.1672,30,44,45),(47,7.19346,21,48,49),(51,7.64424,24,52,53),(55,6.77829,20,56,57),(59,6.20442,13,60,61),(63,8.80042,23,64,65),(67,18.892,40,68,69),(71,11.2079,21,72,73),(75,3.9384,10,76,77),(79,5.91733,18,80,81),(83,8.24937,20,84,85),(87,5.96115,13,88,89),(91,7.74431,16,92,93),(95,7.32435,16,96,97),(99,9.07168,29,100,101),(103,8.9059,17,104,105),(107,5.64752,13,108,109),(111,8.90847,20,112,113),(115,8.75482,16,116,117),(119,9.576,14,120,121);
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
  `id` binary(16) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES (_binary 'BcœbI_¹\Ç1[­…°À','ac04b801-9d7c-4dfd-830f-a15a00908422'),(_binary 'Ø¥n{@šDƒ\è\ì©\ÍÞ­t','dca61aab-f9ce-4e03-a041-06160863ea69');
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `translations`
--

DROP TABLE IF EXISTS `translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `translations` (
  `id` int NOT NULL,
  `text_en` varchar(255) DEFAULT NULL,
  `text_ru` varchar(255) DEFAULT NULL,
  `text_ua` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `translations`
--

LOCK TABLES `translations` WRITE;
/*!40000 ALTER TABLE `translations` DISABLE KEYS */;
INSERT INTO `translations` VALUES (1,'Active','ÐÐºÑ‚Ð¸Ð²Ð½Ñ‹Ð¹','ÐÐºÑ‚Ð¸Ð²Ð½Ð¸Ð¹'),(2,'Finished','Ð—Ð°Ð²ÐµÑ€ÑˆÐµÐ½','Ð—Ð°Ð²ÐµÑ€ÑˆÐµÐ½Ð¾'),(3,'Free','Ð¡Ð²Ð¾Ð±Ð¾Ð´Ð½Ð°','Ð’?Ð»ÑŒÐ½Ð°'),(4,'Busy','Ð—Ð°Ð½ÑÑ‚Ð°','Ð—Ð°Ð¹Ð½ÑÑ‚Ð°'),(5,'Inactive','ÐÐµÐ°ÐºÑ‚Ð¸Ð²Ð½Ð°Ñ','ÐÐµÐ°ÐºÑ‚Ð¸Ð²Ð½Ð°'),(6,'Isolated','Ð˜Ð·Ð¾Ð»Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð½Ð°Ñ','?Ð·Ð¾Ð»ÑŒÐ¾Ð²Ð°Ð½Ð°'),(7,'Regular','ÐžÐ±Ñ‹Ñ‡Ð½Ð°Ñ','Ð—Ð²Ð¸Ñ‡Ð°Ð¹Ð½Ð°'),(8,'Lux','Ð›ÑŽÐºÑ','Ð›ÑŽÐºÑ');
/*!40000 ALTER TABLE `translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary '\ÈrXiBR¶|.nX\Â',NULL,'$2a$10$uLJr/G0sxYwbpsRt1dQbweA04rNhiG6n5sUSYo7vbOQHidnWILya6',5,'ADMIN',NULL,'aaa'),(_binary '\ÍÞ•\ã3ŽB¸G\Ð>N[~S',NULL,'$2a$10$7exj1aFf4CO/L3WIqts1a.HumGL615Qh7VUFJDuqyJa.7kf7wJ7cq',5,'USER',NULL,'alexzhyshko');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-12 23:01:24
