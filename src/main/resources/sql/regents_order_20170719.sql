CREATE DATABASE  IF NOT EXISTS `regentsorder` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `regentsorder`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: regentsorder
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `district_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l2aubhk7g9o13bi6i4gyhxdbc` (`USERNAME`),
  KEY `FK_kb1p3vso8hwc2keufinclog2q` (`district_id`),
  CONSTRAINT `FK_kb1p3vso8hwc2keufinclog2q` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'wh1tehouse@password.com','Dan','Whitehouse','$2a$10$AUHlIZIoGNOkzYdRKlTS.O2.7OnRUG0oQ/uCaZxytJlkjnWuapnGi','dan',1);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user_user_profile`
--

DROP TABLE IF EXISTS `app_user_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user_user_profile` (
  `USER_ID` int(11) NOT NULL,
  `USER_PROFILE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`USER_PROFILE_ID`),
  KEY `FK_gs2lq4vmukguubh36utd4r2cl` (`USER_PROFILE_ID`),
  CONSTRAINT `FK_brmce0t584euix4wb4rursf1q` FOREIGN KEY (`USER_ID`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK_gs2lq4vmukguubh36utd4r2cl` FOREIGN KEY (`USER_PROFILE_ID`) REFERENCES `user_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user_user_profile`
--

LOCK TABLES `app_user_user_profile` WRITE;
/*!40000 ALTER TABLE `app_user_user_profile` DISABLE KEYS */;
INSERT INTO `app_user_user_profile` VALUES (1,1);
/*!40000 ALTER TABLE `app_user_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `district` (
  `district_id` int(11) NOT NULL AUTO_INCREMENT,
  `bedsCode` varchar(255) DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`district_id`),
  UNIQUE KEY `UK_kvkg405mr04cjytv1tuyh7ryr` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,'999999','\0','Admin District','\0');
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `locked` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`document_id`),
  UNIQUE KEY `UK_36vs45u76s1n950kwxfa5lyhc` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (1,'\0','Document 1',NULL),(2,NULL,'Document 2',NULL);
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `exam_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `locked` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`exam_id`),
  UNIQUE KEY `UK_2p6pdai45f01gc5v9dsrxnb77` (`code`),
  UNIQUE KEY `UK_qkjn1bn0arx376uotsywsy9bg` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (1,'E1',NULL,'Exam 1',NULL),(2,'E2',NULL,'Exam 2',NULL);
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `option_print`
--

DROP TABLE IF EXISTS `option_print`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `option_print` (
  `option_print_id` int(11) NOT NULL AUTO_INCREMENT,
  `locked` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`option_print_id`),
  UNIQUE KEY `UK_p4p4exffkcuobyqa000uw8fa2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option_print`
--

LOCK TABLES `option_print` WRITE;
/*!40000 ALTER TABLE `option_print` DISABLE KEYS */;
INSERT INTO `option_print` VALUES (1,NULL,'PO1',NULL),(2,NULL,'PO2',NULL);
/*!40000 ALTER TABLE `option_print` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `option_scan`
--

DROP TABLE IF EXISTS `option_scan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `option_scan` (
  `option_scan_id` int(11) NOT NULL AUTO_INCREMENT,
  `locked` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`option_scan_id`),
  UNIQUE KEY `UK_ltbgotxo843bkiijq17r14dep` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option_scan`
--

LOCK TABLES `option_scan` WRITE;
/*!40000 ALTER TABLE `option_scan` DISABLE KEYS */;
INSERT INTO `option_scan` VALUES (1,NULL,'SO1',NULL),(2,NULL,'SO2',NULL);
/*!40000 ALTER TABLE `option_scan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` date NOT NULL,
  `order_status` varchar(255) NOT NULL,
  `report_to_level_one` bit(1) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `option_print_id` int(11) NOT NULL,
  `option_scan_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `UK_ly2iwdbdejldpfws4g3aixpt3` (`uuid`),
  KEY `FK_e2uxdj3x9oqk5135hcst0rw03` (`option_print_id`),
  KEY `FK_nt4qvo407qirnypq0bc2pifkq` (`option_scan_id`),
  KEY `FK_mh40cn97o5svvy5c32ws9tnvp` (`user_id`),
  CONSTRAINT `FK_e2uxdj3x9oqk5135hcst0rw03` FOREIGN KEY (`option_print_id`) REFERENCES `option_print` (`option_print_id`),
  CONSTRAINT `FK_mh40cn97o5svvy5c32ws9tnvp` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK_nt4qvo407qirnypq0bc2pifkq` FOREIGN KEY (`option_scan_id`) REFERENCES `option_scan` (`option_scan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'2017-06-21','SOMETHING','','255d09fd-1d86-492f-8a4e-1661678759ce',2,2,1),(2,'2017-07-12','SOMETHING','','b846b7b6-6b57-4b97-9d6a-78d32e9031b4',1,1,1),(3,'2017-07-12','SOMETHING','','517a2cf6-4eb9-4ca6-910e-5283f7c37125',1,1,1),(4,'2017-07-12','SOMETHING','','5ed62c1b-95f8-4933-8d66-2a955ee0217b',1,1,1),(5,'2017-07-12','SOMETHING','','76c001fe-2a75-45e1-aed2-729374d786e7',1,1,1),(6,'2017-07-12','SOMETHING','\0','bc4c3b46-1cb1-413d-931e-3fae98997a3b',1,1,1),(7,'2017-07-12','SOMETHING','','1655f054-845e-409f-92a5-8efc32f08576',1,1,1),(8,'2017-07-12','SOMETHING','','c87a45fe-26de-4cc3-a9eb-e1787d500321',1,1,1),(9,'2017-07-12','SOMETHING','','d13e401a-4db3-4bdc-b764-7bd6b2bff419',1,1,1),(10,'2017-07-12','SOMETHING','','45f928d3-dc3f-48e8-8d1e-704b71882497',1,1,1),(11,'2017-07-12','SOMETHING','','3fdcc3e0-540e-45e7-9f19-9c532beb3edc',1,1,1),(12,'2017-07-12','SOMETHING','','bf278c2f-2e65-402e-9cc5-b8c83d7293bc',1,1,1),(13,'2017-07-12','SOMETHING','\0','320312a2-08a8-499f-b838-11b31ebb3eb1',1,1,1),(14,'2017-07-12','SOMETHING','\0','276695ab-488d-4e31-82d6-2be572f2a5c8',1,1,1),(15,'2017-07-12','SOMETHING','\0','12f7f60a-61db-4cf9-9810-62ebb803a7d2',1,1,1),(16,'2017-07-12','SOMETHING','','6a16bdc9-f85d-4daa-8f19-e3fc19b809fc',1,1,1),(17,'2017-07-12','SOMETHING','\0','6a93bc65-2a8f-4b50-8066-2b5ed189c423',1,1,1),(18,'2017-07-12','SOMETHING','\0','281e5d2b-40f7-4439-8b7e-042084427c86',1,1,1),(19,'2017-07-12','SOMETHING','\0','7c05463b-8dfe-45f4-a77b-8a695285e1ad',2,2,1),(20,'2017-07-12','SOMETHING','','1d5043b8-3bb4-430a-9893-f7eff20870a5',1,1,1),(21,'2017-07-12','SOMETHING','','72914286-a9a4-469d-b957-bd55ba2c6533',1,1,1),(22,'2017-07-12','SOMETHING','\0','a98f42d2-d53f-4381-93e3-334d9eeae155',1,1,1),(23,'2017-07-19','SOMETHING','','e200e45f-2b4b-4e48-8e02-af373e0aed31',1,1,1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_contact`
--

DROP TABLE IF EXISTS `order_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_contact` (
  `order_contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `alt_city` varchar(255) DEFAULT NULL,
  `alt_line1` varchar(255) DEFAULT NULL,
  `alt_line2` varchar(255) DEFAULT NULL,
  `alt_contact_name` varchar(255) DEFAULT NULL,
  `alt_state` varchar(255) DEFAULT NULL,
  `alt_zip_code` varchar(255) DEFAULT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `contact_phone` varchar(255) DEFAULT NULL,
  `contact_title` varchar(255) DEFAULT NULL,
  `order_id` int(11) NOT NULL,
  `school_id` int(11) NOT NULL,
  PRIMARY KEY (`order_contact_id`),
  KEY `FK_7gbi8xexm0w43u8fann8gblao` (`order_id`),
  KEY `FK_12ui3s9y0a3mgb2k7w21mu4sv` (`school_id`),
  CONSTRAINT `FK_12ui3s9y0a3mgb2k7w21mu4sv` FOREIGN KEY (`school_id`) REFERENCES `school` (`school_id`),
  CONSTRAINT `FK_7gbi8xexm0w43u8fann8gblao` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_contact`
--

LOCK TABLES `order_contact` WRITE;
/*!40000 ALTER TABLE `order_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_document`
--

DROP TABLE IF EXISTS `order_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_document` (
  `order_document_id` int(11) NOT NULL AUTO_INCREMENT,
  `document_amount` int(11) DEFAULT NULL,
  `document_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`order_document_id`),
  KEY `FK_966st4vfkptxukkv7xgq7t8gy` (`document_id`),
  KEY `FK_bt4hhjflli7bfhfa4pp2waarp` (`order_id`),
  CONSTRAINT `FK_966st4vfkptxukkv7xgq7t8gy` FOREIGN KEY (`document_id`) REFERENCES `document` (`document_id`),
  CONSTRAINT `FK_bt4hhjflli7bfhfa4pp2waarp` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_document`
--

LOCK TABLES `order_document` WRITE;
/*!40000 ALTER TABLE `order_document` DISABLE KEYS */;
INSERT INTO `order_document` VALUES (1,1,1,1),(2,2,2,1),(3,15,1,23);
/*!40000 ALTER TABLE `order_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_exam`
--

DROP TABLE IF EXISTS `order_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_exam` (
  `order_exam_id` int(11) NOT NULL AUTO_INCREMENT,
  `answer_sheet_amount` varchar(255) DEFAULT NULL,
  `exam_amount` int(11) DEFAULT NULL,
  `pas` bit(1) DEFAULT NULL,
  `students_per_csv` varchar(255) DEFAULT NULL,
  `exam_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`order_exam_id`),
  KEY `FK_mfkks8r3uocv3364yc88ucwwg` (`exam_id`),
  KEY `FK_b4jsx722x7ol6psk1g5ajuur3` (`order_id`),
  CONSTRAINT `FK_b4jsx722x7ol6psk1g5ajuur3` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `FK_mfkks8r3uocv3364yc88ucwwg` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_exam`
--

LOCK TABLES `order_exam` WRITE;
/*!40000 ALTER TABLE `order_exam` DISABLE KEYS */;
INSERT INTO `order_exam` VALUES (1,'2',2,NULL,'2',2,1),(2,'1',1,NULL,'1',1,1),(3,'1',1,NULL,'1',1,23);
/*!40000 ALTER TABLE `order_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderform`
--

DROP TABLE IF EXISTS `orderform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderform` (
  `orderForm_id` int(11) NOT NULL AUTO_INCREMENT,
  `endDate` date NOT NULL,
  `locked` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `startDate` date NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`orderForm_id`),
  UNIQUE KEY `UK_p9uljyuy9gthuw3f3r7pnv3ac` (`name`),
  UNIQUE KEY `UK_jobq4tq3wlm9c2uuaabsm334r` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderform`
--

LOCK TABLES `orderform` WRITE;
/*!40000 ALTER TABLE `orderform` DISABLE KEYS */;
INSERT INTO `orderform` VALUES (1,'2017-07-20',NULL,'OF1','2017-07-19','6ce053c6-c401-40e2-bd73-1c8752fe471d',NULL),(2,'2017-08-03',NULL,'OF2','2017-07-27','7de80812-be35-4e68-9803-a2e875154776',NULL),(3,'2017-06-27',NULL,'OF3','2017-07-05','6cc81962-04c5-413c-8d93-0ea55ca13620',NULL);
/*!40000 ALTER TABLE `orderform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderform_document`
--

DROP TABLE IF EXISTS `orderform_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderform_document` (
  `orderForm_document_id` int(11) NOT NULL AUTO_INCREMENT,
  `document_id` int(11) NOT NULL,
  `orderForm_id` int(11) NOT NULL,
  PRIMARY KEY (`orderForm_document_id`),
  KEY `FK_o0oln2ypnrc16wp2d8vohdyis` (`document_id`),
  KEY `FK_qyfq755k26v1ke4j00e0tss6c` (`orderForm_id`),
  CONSTRAINT `FK_o0oln2ypnrc16wp2d8vohdyis` FOREIGN KEY (`document_id`) REFERENCES `document` (`document_id`),
  CONSTRAINT `FK_qyfq755k26v1ke4j00e0tss6c` FOREIGN KEY (`orderForm_id`) REFERENCES `orderform` (`orderForm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderform_document`
--

LOCK TABLES `orderform_document` WRITE;
/*!40000 ALTER TABLE `orderform_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderform_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderform_exam`
--

DROP TABLE IF EXISTS `orderform_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderform_exam` (
  `orderForm_exam_id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` int(11) NOT NULL,
  `orderForm_id` int(11) NOT NULL,
  PRIMARY KEY (`orderForm_exam_id`),
  KEY `FK_sinb7vqh5dkh2q5sldqp2cnyb` (`exam_id`),
  KEY `FK_eowrf03np2s8wno16pdv5ia7n` (`orderForm_id`),
  CONSTRAINT `FK_eowrf03np2s8wno16pdv5ia7n` FOREIGN KEY (`orderForm_id`) REFERENCES `orderform` (`orderForm_id`),
  CONSTRAINT `FK_sinb7vqh5dkh2q5sldqp2cnyb` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderform_exam`
--

LOCK TABLES `orderform_exam` WRITE;
/*!40000 ALTER TABLE `orderform_exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderform_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `series` varchar(255) NOT NULL,
  `last_used` datetime DEFAULT NULL,
  `TOKEN` varchar(255) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  PRIMARY KEY (`series`),
  UNIQUE KEY `UK_3gq9wkitbp2ave684iu50mhn7` (`TOKEN`),
  UNIQUE KEY `UK_a6c251uovnx2cp2c3vv2dentk` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `district_id` int(11) NOT NULL,
  PRIMARY KEY (`school_id`),
  UNIQUE KEY `UK_251hwtk4rvkoblr76wknh8v41` (`name`),
  KEY `FK_7a0s73dc8qn5ipd54t791knq9` (`district_id`),
  CONSTRAINT `FK_7a0s73dc8qn5ipd54t791knq9` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cva7m2blp7ekclxworqxau1l7` (`TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (1,'ADMIN'),(2,'DBA'),(3,'USER');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'regentsorder'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-19 15:47:43
