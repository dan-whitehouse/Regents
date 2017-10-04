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
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` (`id`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `USERNAME`, `locked`, `visible`, `uuid`) VALUES (1,'Andrew','Pieniezny','$2a$10$ny3S4PqK/SRndFMhrh6En.kgTMF406ZoR7uGtGaMcr0Y4zWGTAdoC','andrew','\0','\0','ee337526-7ec1-11e7-846f-00ffaf6e1663'),(2,'user','test','$2a$10$qyZx2/T3hwmimiG7yu8nmOZDlz9mI8Oe/aunRFtPJYVib7ToK9z5W','user','\0','','ee33870d-7ec1-11e7-846f-00ffaf6e1663'),(3,'TestFN','TestLN','$2a$10$yZ7DO/RosEd7wT0WBbx6TeS.SwY7fbYes87C/YTOu92VwOqR37YR2','test','\0','','e9f3cc7d-0367-4c31-97a7-cde915ee8ae7'),(4,'TestFN2','TestLN2','$2a$10$4.6gToISJyXfOZ11Jq5afOlSzWctR2MgaYkXmotzuPwhhlpvtuNA6','test2','\0','','e4f50ddd-e643-47cb-a3f8-9deb570a9e55'),(5,'TestFN3','TestLN3','$2a$10$lqZ0NP66X4GPxuU/4zX3k.MKpZMDULsZ06yJVBKE50GmXdEP17qyu','test3','\0','','006e86f5-c7dc-44bf-9a5a-aa2f93f8822f'),(8,'TestFN4','TestLN4','$2a$10$EG6KlPaY9LQMldV7gHDbju0JDcpuhNhd5xdRMzbVk6v3olm.hDQqi','test4','\0','','96e76482-470b-4bf3-a538-876021d4e922'),(9,'ffdfd','dfdfdff','$2a$10$kWkCWKY.17oSlhj4LLWlxORC1hNXEAWmg4F8uv1rEn2wLkk0pLNqa','test5@test.com','\0','','df70a523-bf23-4abd-932c-530818acff79'),(10,'TestFN6','TestLN6','$2a$10$y5lsZidd4t9SC0VbBXPcV.sqcbAl60N3FHyVLmQ5UQUx9pvVD7.fy','test6@test.com','\0','','cd84931f-ea26-418c-b4ed-6fbb54659a69');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `app_user_user_profile`
--

LOCK TABLES `app_user_user_profile` WRITE;
/*!40000 ALTER TABLE `app_user_user_profile` DISABLE KEYS */;
INSERT INTO `app_user_user_profile` (`USER_ID`, `USER_PROFILE_ID`) VALUES (1,1),(10,1),(2,3),(3,3),(4,3),(5,3),(8,3),(9,3);
/*!40000 ALTER TABLE `app_user_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` (`district_id`, `bedsCode`, `locked`, `name`, `visible`, `uuid`) VALUES (1,'999999','','Admin District','\0','c4fe8935-8804-11e7-9627-00ffaf6e1663'),(2,'12345','\0','District 2','','c4fe99ed-8804-11e7-9627-00ffaf6e1663');
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` (`document_id`, `locked`, `name`, `visible`, `uuid`) VALUES (1,'\0','Comprehensive English','','93a6eeec-87fc-11e7-9627-00ffaf6e1663'),(2,'\0','Global History & Geography','','93a70977-87fc-11e7-9627-00ffaf6e1663'),(3,'\0','Chemistry','','93a71bee-87fc-11e7-9627-00ffaf6e1663'),(4,'\0','Physical Settings/Earth Science','','93a7aa85-87fc-11e7-9627-00ffaf6e1663'),(5,'\0','US History & Government','','93a7b6f0-87fc-11e7-9627-00ffaf6e1663'),(6,'\0','Physics','','8f77cfe2-5f26-4bb7-ac61-ad9d5c82bd94'),(7,'\0','ELA Common Core','','c6c6d72c-1cb8-4ee0-b946-6f328b86b34d');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` (`exam_id`, `code`, `locked`, `name`, `visible`, `uuid`) VALUES (1,'02052CC','\0','Algebra 1','','20a0dc43-8800-11e7-9627-00ffaf6e1663'),(2,'01003','\0','Comprehensive English','','20a0e7c8-8800-11e7-9627-00ffaf6e1663'),(3,'02106','\0','Algebra 2/Trigonometry','','20a0f4db-8800-11e7-9627-00ffaf6e1663'),(4,'01003CC','\0','ELA (Common Core)','','20a1b431-8800-11e7-9627-00ffaf6e1663'),(5,'02072CC','\0','Geometry (Common Core)','','20a1c011-8800-11e7-9627-00ffaf6e1663'),(6,'02052','\0','Integrated Algebra','','20a1cf26-8800-11e7-9627-00ffaf6e1663'),(7,'04052','\0','Global History & Geography','','ecbcfed8-bf78-4ce1-924a-224482026851'),(8,'03051','\0','Living Environment','','23ddba00-11e5-4486-ad6c-8acede7d2761'),(9,'03101','\0','Physical Settings/Chemistry','','e95c104b-ec42-44f8-8fe4-d2e33417738a'),(10,'03001','\0','Physical Settings/Earth Science','','d3247b35-82dd-4e5c-ab10-b9d68d5c8738'),(11,'03151','\0','Physical Settings/Physics','','9608f0d0-d35a-4847-a2fd-90f4b824606f'),(12,'04101','\0','US History & Government','','ac71ebba-a442-410c-aa8b-139b5ec63918'),(13,'20106CC','\0','Algebra 2 (Common Core)','','051ef534-7e2e-4c9f-a8f1-4e9aa0e542fb');
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `option_print`
--

LOCK TABLES `option_print` WRITE;
/*!40000 ALTER TABLE `option_print` DISABLE KEYS */;
INSERT INTO `option_print` (`option_print_id`, `locked`, `name`, `visible`, `uuid`) VALUES (1,'\0','Alpha (Building/Student)','','90a76814-8802-11e7-9627-00ffaf6e1663'),(2,'','Teacher (Building/Teacher/Student)','','90a776bc-8802-11e7-9627-00ffaf6e1663'),(3,'\0','Course Section (Building/Course Section/Student)','','90a78659-8802-11e7-9627-00ffaf6e1663');
/*!40000 ALTER TABLE `option_print` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `option_scan`
--

LOCK TABLES `option_scan` WRITE;
/*!40000 ALTER TABLE `option_scan` DISABLE KEYS */;
INSERT INTO `option_scan` (`option_scan_id`, `locked`, `name`, `visible`, `uuid`) VALUES (1,'\0','Scan @ Regional BOCES Scan Site','','8500383c-8802-11e7-9627-00ffaf6e1663'),(2,'\0','Manually Score then mail or deliver sheets to NERIC for scanning','','8500448e-8802-11e7-9627-00ffaf6e1663'),(3,'\0','Scan In-District','','7739bc29-9ae8-455c-bbc0-6ab193da9a46');
/*!40000 ALTER TABLE `option_scan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `optout`
--

LOCK TABLES `optout` WRITE;
/*!40000 ALTER TABLE `optout` DISABLE KEYS */;
/*!40000 ALTER TABLE `optout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`order_id`, `order_date`, `order_status`, `report_to_level_one`, `uuid`, `option_print_id`, `option_scan_id`, `user_id`, `orderForm_id`, `district_id`, `school_id`, `special_requests`) VALUES (47,'2017-09-06','Processing','','5767d270-8f68-4ba2-bdd1-f6027107817f',3,2,1,12,2,3,NULL),(48,'2017-09-06','Processing','','58fedea8-7381-434b-8f28-6b33a101b9ad',1,1,3,12,2,3,NULL),(49,'2017-09-13','Processing','\0','376460b9-928f-4ee0-b88f-f4fc1a5a04b3',1,3,1,1,2,3,NULL),(50,'2017-09-13','Processing','\0','9bfe017b-8e6e-4448-a79d-3cf5bbcae942',1,3,1,1,2,3,NULL),(51,'2017-09-13','Processing','\0','e49ec431-a668-4c53-8990-979ca6de7d22',1,3,1,12,2,3,NULL),(52,'2017-09-13','Processing','\0','a20cfc50-949a-40fd-9759-7a1399f88eb7',2,2,1,12,2,3,NULL),(53,'2017-09-13','Processing','\0','af289240-7fe8-45cc-bd7b-78e3d1c366c8',1,3,1,12,2,3,NULL),(54,'2017-09-13','Processing','\0','89cb23a7-26a4-4bab-968d-bde471f997e1',1,2,1,12,2,3,NULL),(55,'2017-09-13','Processing','\0','99d4b2ac-01f8-48a8-8d1f-f91d4d84c0c8',1,2,1,12,2,3,NULL),(56,'2017-09-13','Processing','\0','8f7393fb-8c03-4d62-bb45-ec9c793a229f',1,2,1,12,2,3,NULL),(57,'2017-09-13','Processing','\0','0540bb40-f9d7-4f73-819d-5b4e45a7f434',1,2,1,12,2,3,NULL),(58,'2017-09-13','Processing','\0','d58b5030-8dab-4626-a1b4-8a40e4a3f092',1,2,1,12,2,3,NULL),(59,'2017-09-13','Processing','\0','eb2de0e5-4dca-44c1-8697-d3033457b36e',1,2,1,12,2,3,NULL),(60,'2017-09-13','Processing','\0','17dfdae3-eed3-4a28-b190-7809787d57b1',2,2,1,12,2,3,NULL),(61,'2017-09-13','Processing','\0','41327ea2-b24a-45d8-ad7d-83a217496750',1,3,1,12,2,3,NULL),(62,'2017-09-13','Processing','\0','0c43c3f0-b9f1-487f-b85c-58e615a49f53',1,3,1,12,2,3,NULL),(63,'2017-09-13','Processing','\0','eba36365-824b-4273-aacd-cf5c4c2ade08',1,3,1,12,2,3,NULL),(64,'2017-09-13','Processing','\0','89e11b04-2592-48e5-8a79-c52e74e5fbf5',1,3,1,12,2,3,NULL),(65,'2017-09-13','Processing','\0','6f7d3659-fd24-4585-ab0b-781b4851524c',1,2,1,12,2,3,NULL),(67,'2017-09-13','Processing','\0','f1b64d43-5b33-4511-ab95-364565082374',1,2,1,12,2,3,NULL),(68,'2017-09-13','Processing','','c58d7d89-1a90-4bb1-a59e-0e5682bbc546',1,2,1,12,2,3,NULL),(69,'2017-09-13','Processing','','11f2d7cb-489a-4708-8b90-e077f9315018',1,2,1,12,2,3,NULL),(70,'2017-09-13','Processing','','d94a55c3-f09e-46f2-b7ad-0936cd0ca387',1,3,1,12,2,3,NULL),(71,'2017-09-13','Processing','','36a2ebe2-57e8-4f6e-8ae7-292f3760eb14',1,3,1,12,2,3,NULL),(72,'2017-09-13','Processing','','13f869f9-faa4-445c-9ad6-193fdc18099b',1,3,1,12,2,3,NULL),(75,'2017-09-13','Processing','\0','255681a7-9896-4368-9fd0-3542918164bb',1,2,1,12,2,3,NULL),(76,'2017-09-13','Processing','','8d8b9434-0fdd-4c8c-aaad-2fbc56ed796f',1,1,1,12,2,3,NULL),(77,'2017-09-13','Processing','\0','e24a6593-ab51-4a3d-8d12-6d84b48bb426',1,3,1,12,2,3,NULL),(78,'2017-09-13','Processing','\0','beefb9fa-d7e2-44df-b721-8e3457ac043c',1,3,1,12,2,3,NULL),(79,'2017-09-20','Processing','\0','250fad7f-611b-44ff-95bd-715ea9fcc865',1,1,1,12,2,3,NULL),(80,'2017-09-20','Processing','\0','b24ab59e-9404-4006-97ce-16a3fa779517',1,2,1,12,2,3,NULL),(81,'2017-09-20','Processing','\0','f297750b-0544-45b2-97f3-0f83c04633df',1,3,1,12,2,3,NULL),(82,'2017-09-20','Processing','\0','2db3424d-bf9f-42e5-86af-99910ea1eafc',1,2,1,12,2,3,'Special Request 2'),(83,'2017-09-27','Processing','\0','94d7b102-0cfe-44c1-95d4-173c01b38086',1,2,1,12,1,2,''),(84,'2017-10-04','Processing','\0','d20e7923-46c9-4aab-bf9b-bb1e56ebfd97',1,1,1,12,2,4,'');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_contact`
--

LOCK TABLES `order_contact` WRITE;
/*!40000 ALTER TABLE `order_contact` DISABLE KEYS */;
INSERT INTO `order_contact` (`order_contact_id`, `contact_email`, `contact_name`, `contact_phone`, `contact_title`, `order_id`, `alt_contact_info`) VALUES (4,'22222222','Dan Whitehouse','222222','22222222',47,NULL),(8,'email@email.com','aaa','dddd','Title',48,NULL),(9,'jhjh','jhjhj','','',49,NULL),(10,'jhjh','jhjhj','hh','hh',50,NULL),(11,'1','1','1','11',51,NULL),(12,'1','1','1','1',52,NULL),(13,'1','1','111','11',53,NULL),(14,'1','1','1','1',54,NULL),(15,'kjkj','kjk','kjkj','jkkj',55,NULL),(16,'1','1','1','111',56,NULL),(17,'1','1','11111','1',57,NULL),(18,'1','1','1','1',58,NULL),(19,'j','j','j','j',59,NULL),(20,'','lll','','lll',60,NULL),(21,'1','1','1','1',61,NULL),(22,'','1','','1',62,NULL),(23,'1','1','1','1',63,NULL),(24,'1','1','1','1',64,NULL),(25,'2','2','2','2',65,NULL),(26,'1','1','1','1',67,NULL),(27,'1','1','1','1',68,NULL),(28,'','4','','',69,NULL),(29,'1','1','1','1',70,NULL),(30,'1','11','1','1',71,NULL),(31,'1','1','1','11',72,NULL),(32,'1','1','1','1',75,NULL),(33,'1','1','1','1',76,NULL),(34,'1','1','','1',77,NULL),(35,'1','1','','1',78,NULL),(36,'testname@email.com','Test Name','514856325','DR',79,NULL),(37,'r','r','r','rrrr',80,NULL),(38,'1','1','1','1',81,NULL),(39,'email@email.com','Guy Might','5183939143','Master',82,'Ship it somewhere else!2'),(40,'email@email.com','Daniel Whitehouse','11111','1',83,''),(41,'email@email.com2','Daniel Whitehouse2','51881038802','title2',84,'');
/*!40000 ALTER TABLE `order_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_document`
--

LOCK TABLES `order_document` WRITE;
/*!40000 ALTER TABLE `order_document` DISABLE KEYS */;
INSERT INTO `order_document` (`order_document_id`, `document_amount`, `document_id`, `order_id`) VALUES (51,1,3,47),(52,1,3,48),(53,1,3,51),(54,1,3,52),(55,1,3,53),(56,1,3,54),(57,1,3,56),(58,NULL,3,57),(59,1,3,58),(60,NULL,3,59),(61,1,3,60),(62,1,3,61),(63,1,3,62),(64,1,3,63),(65,1,3,64),(66,1,3,65),(68,1,3,67),(69,1,3,68),(70,1,3,70),(71,1,3,71),(74,1,3,75),(75,1,3,76),(76,1,3,77),(77,1,3,78),(78,1,3,79),(79,111,3,81),(81,14,3,82),(82,1,3,83),(88,10,3,84);
/*!40000 ALTER TABLE `order_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_exam`
--

LOCK TABLES `order_exam` WRITE;
/*!40000 ALTER TABLE `order_exam` DISABLE KEYS */;
INSERT INTO `order_exam` (`order_exam_id`, `answer_sheet_amount`, `exam_amount`, `students_per_csv`, `exam_id`, `order_id`) VALUES (75,'2',2,'2',1,47),(76,'1',1,'1',1,48),(77,'1',NULL,'1',1,51),(78,'1',NULL,'1',13,51),(79,'1',NULL,'1',1,52),(80,'1',1,'1',1,53),(81,'1',1,'1',1,54),(82,'',NULL,'',1,55),(83,'1',1,'1',1,56),(84,'1',1,'1',1,57),(85,'1',1,'1',1,58),(86,'',NULL,'',1,59),(87,'1',1,'1',1,60),(88,'1',1,'1',1,61),(89,'1',1,'1',1,62),(90,'1',1,'1',1,63),(91,'1',1,'1',1,64),(92,'1',1,'1',1,65),(94,'1',1,'1',1,67),(95,'1',1,'1',1,68),(96,'1',1,'1',1,70),(97,'1',1,'1',1,71),(98,'1',1,'1',1,72),(101,'1',1,'1',1,75),(102,'1',1,'1',1,76),(103,'1',1,'1',1,77),(104,'1',1,'1',1,78),(105,'1',1,'1',1,79),(106,'1',NULL,'1',1,81),(109,'2',NULL,'2',13,82),(110,'1',NULL,'1',1,82),(111,'1',NULL,'1',1,83),(122,'10',NULL,'10',1,84),(123,'10',NULL,'10',13,84);
/*!40000 ALTER TABLE `order_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orderform`
--

LOCK TABLES `orderform` WRITE;
/*!40000 ALTER TABLE `orderform` DISABLE KEYS */;
INSERT INTO `orderform` (`orderForm_id`, `endDate`, `locked`, `name`, `startDate`, `uuid`, `visible`, `active`, `period`, `inDistrictScanFee`, `nonSecureDocumentFee`, `processingFee`, `rescanFee`) VALUES (1,'2017-09-25','\0','OF1','2017-07-19','6ce053c6-c401-40e2-bd73-1c8752fe471d','','\0','June',0,0,0,0),(2,'2017-08-03','','OF2','2017-07-27','7de80812-be35-4e68-9803-a2e875154776','','\0','January',0,0,0,0),(3,'2017-06-27','','OF3','2017-05-05','6cc81962-04c5-413c-8d93-0ea55ca13620','','\0','January',0,0,0,0),(6,'2017-07-27','','OF5','2017-07-26','0077ae6d-e715-4528-a2db-aad7e0f9127b','','\0','June',0,0,0,0),(8,'2017-07-27','','OF6','2017-07-26','6983319a-906c-4347-bbc9-d4c36b40c1e2','','\0','June',0,0,0,0),(11,'2017-08-10','','OF8','2017-08-03','adf1495d-b9c0-408b-95d1-c8bcdbbd5688','','\0','June',0,0,0,0),(12,'2017-09-20','','OF 10','2017-08-02','dbfa085e-c20e-411f-b6e3-8275dce83e08','','','August',1,0.15,0.75,1.1),(13,'2017-12-24','','OF11','2017-12-23','ba4f373a-f6a8-43a7-8226-6e0b99e576fd','','\0','August',0,0,0,0),(14,'2017-09-30','\0','OF12','2017-09-15','cf7acd8d-a759-441b-9c40-888a4d131130','','\0','August',2,0.4,4,1);
/*!40000 ALTER TABLE `orderform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orderform_document`
--

LOCK TABLES `orderform_document` WRITE;
/*!40000 ALTER TABLE `orderform_document` DISABLE KEYS */;
INSERT INTO `orderform_document` (`orderForm_document_id`, `document_id`, `orderForm_id`) VALUES (1,2,6),(2,1,8),(3,2,8),(5,1,11),(6,3,11),(85,1,13),(86,1,1),(94,1,14),(95,7,12),(96,3,12),(97,5,12),(98,6,12),(99,4,12),(100,2,12),(101,1,12);
/*!40000 ALTER TABLE `orderform_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orderform_exam`
--

LOCK TABLES `orderform_exam` WRITE;
/*!40000 ALTER TABLE `orderform_exam` DISABLE KEYS */;
INSERT INTO `orderform_exam` (`orderForm_exam_id`, `exam_id`, `orderForm_id`) VALUES (1,1,6),(2,2,6),(3,1,8),(4,2,8),(7,1,11),(8,2,11),(124,2,13),(125,1,13),(126,1,1),(140,1,14),(141,11,12),(142,8,12),(143,1,12),(144,2,12),(145,5,12),(146,3,12),(147,4,12),(148,12,12),(149,9,12),(150,13,12),(151,6,12),(152,10,12),(153,7,12);
/*!40000 ALTER TABLE `orderform_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` (`school_id`, `name`, `district_id`, `uuid`) VALUES (2,'School 1-1',1,'05843f7f-8807-11e7-9627-00ffaf6e1663'),(3,'School 2',2,'124c1332-3788-472c-93d7-a0acc290a73c'),(4,'School 3',2,'94e759cb-85ab-4263-9740-090d8aec1c67');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_district`
--

LOCK TABLES `user_district` WRITE;
/*!40000 ALTER TABLE `user_district` DISABLE KEYS */;
INSERT INTO `user_district` (`user_district_id`, `district_id`, `user_id`) VALUES (3,2,4),(4,1,5),(5,2,5),(10,1,1),(11,2,1),(13,1,8),(14,2,8),(15,1,3),(16,1,9),(17,2,10);
/*!40000 ALTER TABLE `user_district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` (`id`, `TYPE`) VALUES (1,'ADMIN'),(3,'USER');
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

-- Dump completed on 2017-10-04 10:06:30
