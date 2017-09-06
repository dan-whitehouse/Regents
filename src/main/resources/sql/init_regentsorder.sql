use regentsorder;
insert into user_profile values(1, 'ADMIN'), (2, 'DBA'), (3, 'USER');

insert into district values(1,999999,0,'Admin District',0);


insert into APP_USER(username, password, first_name, last_name, email, uuid, visible, locked)
VALUES ('dan','$2a$10$AUHlIZIoGNOkzYdRKlTS.O2.7OnRUG0oQ/uCaZxytJlkjnWuapnGi', 'Dan','Whitehouse','wh1tehouse@password.com', uuid(), 0, 0),
('andrew','$2a$10$ny3S4PqK/SRndFMhrh6En.kgTMF406ZoR7uGtGaMcr0Y4zWGTAdoC', 'Andrew','Pieniezny','andrew@school.com', uuid(), 0, 0);

INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where (user.id in ('1', '2') and profile.type='ADMIN');
  
  

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` (`document_id`, `locked`, `name`, `visible`, `uuid`) 
VALUES 	(1,'\0','Comprehensive English','','93a6eeec-87fc-11e7-9627-00ffaf6e1663'),
		(2,'\0','Global History & Geography','','93a70977-87fc-11e7-9627-00ffaf6e1663'),
        (3,'\0','Chemistry','','93a71bee-87fc-11e7-9627-00ffaf6e1663'),
        (4,'\0','Physical Settings/Earth Science','','93a7aa85-87fc-11e7-9627-00ffaf6e1663'),
        (5,'\0','US History & Government','','93a7b6f0-87fc-11e7-9627-00ffaf6e1663'),
        (6,'\0','Physics','','8f77cfe2-5f26-4bb7-ac61-ad9d5c82bd94'),
        (7,'\0','ELA Common Core','','c6c6d72c-1cb8-4ee0-b946-6f328b86b34d');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;



LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` (`exam_id`, `code`, `locked`, `name`, `visible`, `uuid`) 
VALUES 	(1,'02052CC','\0','Algebra 1','','20a0dc43-8800-11e7-9627-00ffaf6e1663'),
		(2,'01003','\0','Comprehensive English','','20a0e7c8-8800-11e7-9627-00ffaf6e1663'),
        (3,'02106','\0','Algebra 2/Trigonometry','','20a0f4db-8800-11e7-9627-00ffaf6e1663'),
        (4,'01003CC','\0','ELA (Common Core)','','20a1b431-8800-11e7-9627-00ffaf6e1663'),
        (5,'02072CC','\0','Geometry (Common Core)','','20a1c011-8800-11e7-9627-00ffaf6e1663'),
        (6,'02052','\0','Integrated Algebra','','20a1cf26-8800-11e7-9627-00ffaf6e1663'),
        (7,'04052','\0','Global History & Geography','','ecbcfed8-bf78-4ce1-924a-224482026851'),
        (8,'03051','\0','Living Environment','','23ddba00-11e5-4486-ad6c-8acede7d2761'),
        (9,'03101','\0','Physical Settings/Chemistry','','e95c104b-ec42-44f8-8fe4-d2e33417738a'),
        (10,'03001','\0','Physical Settings/Earth Science','','d3247b35-82dd-4e5c-ab10-b9d68d5c8738'),
        (11,'03151','\0','Physical Settings/Physics','','9608f0d0-d35a-4847-a2fd-90f4b824606f'),
        (12,'04101','\0','US History & Government','','ac71ebba-a442-410c-aa8b-139b5ec63918'),
        (13,'20106CC','\0','Algebra 2 (Common Core)','','051ef534-7e2e-4c9f-a8f1-4e9aa0e542fb');
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `option_print` WRITE;
/*!40000 ALTER TABLE `option_print` DISABLE KEYS */;
INSERT INTO `option_print` (`option_print_id`, `locked`, `name`, `visible`, `uuid`) 
VALUES 	(1,'\0','Alpha (Building/Student)','','90a76814-8802-11e7-9627-00ffaf6e1663'),
		(2,'\0','Teacher (Building/Teacher/Student)','','90a776bc-8802-11e7-9627-00ffaf6e1663'),
        (3,'\0','Course Section (Building/Course Section/Student)','','90a78659-8802-11e7-9627-00ffaf6e1663');
/*!40000 ALTER TABLE `option_print` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `option_scan`
--

LOCK TABLES `option_scan` WRITE;
/*!40000 ALTER TABLE `option_scan` DISABLE KEYS */;
INSERT INTO `option_scan` (`option_scan_id`, `locked`, `name`, `visible`, `uuid`) 
VALUES 	(1,'\0','Regional BOCES Scores Exams','','8500383c-8802-11e7-9627-00ffaf6e1663'),
		(2,'\0','Manually Score Exams','','8500448e-8802-11e7-9627-00ffaf6e1663'),
		(3,'\0','Scan Answer Sheets In-District','','7739bc29-9ae8-455c-bbc0-6ab193da9a46');
/*!40000 ALTER TABLE `option_scan` ENABLE KEYS */;
UNLOCK TABLES;  
  
  

ALTER TABLE app_user 
	MODIFY COLUMN district_id int(11) NOT NULL AFTER id,
	MODIFY COLUMN FIRST_NAME varchar(255) NOT NULL AFTER district_id,
	MODIFY COLUMN LAST_NAME varchar(255) NOT NULL AFTER FIRST_NAME,
    MODIFY COLUMN EMAIL varchar(255) NOT NULL AFTER LAST_NAME,
	MODIFY COLUMN USERNAME varchar(255) NOT NULL AFTER EMAIL,
	MODIFY COLUMN PASSWORD varchar(255) NOT NULL AFTER USERNAME;

ALTER TABLE district
	MODIFY COLUMN name varchar(255) NOT NULL AFTER district_id,
	MODIFY COLUMN bedsCode varchar(255) DEFAULT NULL AFTER name,
	MODIFY COLUMN locked bit(1) DEFAULT NULL AFTER bedsCode,
	MODIFY COLUMN visible bit(1) DEFAULT NULL AFTER locked;

ALTER TABLE document
	MODIFY COLUMN name varchar(255) NOT NULL AFTER document_id,
	MODIFY COLUMN locked bit(1) DEFAULT NULL AFTER name,
	MODIFY COLUMN visible bit(1) DEFAULT NULL AFTER locked;

ALTER TABLE exam
	MODIFY COLUMN name varchar(255) NOT NULL AFTER exam_id,
	MODIFY COLUMN code varchar(255) NOT NULL AFTER name,
	MODIFY COLUMN locked bit(1) DEFAULT NULL AFTER code,
	MODIFY COLUMN visible bit(1) DEFAULT NULL AFTER locked;

ALTER TABLE option_print
	MODIFY COLUMN name varchar(255) NOT NULL AFTER option_print_id,
	MODIFY COLUMN locked bit(1) DEFAULT NULL AFTER name,
	MODIFY COLUMN visible bit(1) DEFAULT NULL AFTER locked;	

ALTER TABLE option_scan
	MODIFY COLUMN name varchar(255) NOT NULL AFTER option_scan_id,
	MODIFY COLUMN locked bit(1) DEFAULT NULL AFTER name,
	MODIFY COLUMN visible bit(1) DEFAULT NULL AFTER locked;	

ALTER TABLE `order`
	MODIFY COLUMN uuid varchar(255) NOT NULL AFTER order_id,
	MODIFY COLUMN option_print_id int(11) NOT NULL AFTER uuid,
	MODIFY COLUMN option_scan_id int(11) NOT NULL AFTER option_print_id,
	MODIFY COLUMN user_id int(11) NOT NULL AFTER option_scan_id,
	MODIFY COLUMN order_date date NOT NULL AFTER user_id,
	MODIFY COLUMN order_status varchar(255) NOT NULL AFTER order_date,
	MODIFY COLUMN report_to_level_one bit(1) NOT NULL AFTER order_status;

ALTER TABLE order_contact
	MODIFY COLUMN order_id int(11) NOT NULL AFTER order_contact_id,
	MODIFY COLUMN school_id int(11) NOT NULL AFTER order_id,
	MODIFY COLUMN contact_name varchar(255) DEFAULT NULL AFTER school_id,
	MODIFY COLUMN contact_phone varchar(255) DEFAULT NULL AFTER contact_name,
	MODIFY COLUMN contact_email varchar(255) DEFAULT NULL AFTER contact_phone,
	MODIFY COLUMN contact_title varchar(255) DEFAULT NULL AFTER contact_email,
	MODIFY COLUMN alt_contact_name varchar(255) DEFAULT NULL AFTER contact_title,
	MODIFY COLUMN alt_line1 varchar(255) DEFAULT NULL AFTER alt_contact_name,
	MODIFY COLUMN alt_line2 varchar(255) DEFAULT NULL AFTER alt_line1,
	MODIFY COLUMN alt_city varchar(255) DEFAULT NULL AFTER alt_line2,
	MODIFY COLUMN alt_state varchar(255) DEFAULT NULL AFTER alt_city,
	MODIFY COLUMN alt_zip_code varchar(255) DEFAULT NULL AFTER alt_state;

ALTER TABLE order_document
	MODIFY COLUMN document_id int(11) NOT NULL AFTER order_document_id,
	MODIFY COLUMN order_id int(11) NOT NULL AFTER document_id,
	MODIFY COLUMN document_amount int(11) DEFAULT NULL AFTER order_id;

ALTER TABLE order_exam
	MODIFY COLUMN exam_id int(11) NOT NULL AFTER order_exam_id,
	MODIFY COLUMN order_id int(11) NOT NULL AFTER exam_id,
	MODIFY COLUMN answer_sheet_amount varchar(255) DEFAULT NULL AFTER order_id,
	MODIFY COLUMN exam_amount int(11) DEFAULT NULL AFTER answer_sheet_amount,
	MODIFY COLUMN pas bit(1) DEFAULT NULL AFTER exam_amount,
	MODIFY COLUMN students_per_csv varchar(255) DEFAULT NULL AFTER pas;

ALTER TABLE orderform
	MODIFY COLUMN uuid varchar(255) NOT NULL AFTER orderForm_id,
	MODIFY COLUMN name varchar(255) NOT NULL AFTER uuid,
	MODIFY COLUMN startDate date NOT NULL AFTER name,
	MODIFY COLUMN endDate date NOT NULL AFTER startDate,
	MODIFY COLUMN locked bit(1) NOT NULL AFTER endDate,
	MODIFY COLUMN visible bit(1) NOT NULL AFTER locked;

ALTER TABLE orderform_document
	MODIFY COLUMN orderForm_id int(11) NOT NULL AFTER orderForm_document_id,
	MODIFY COLUMN document_id int(11) NOT NULL AFTER orderForm_id;

ALTER TABLE orderform_exam
	MODIFY COLUMN orderForm_id int(11) NOT NULL AFTER orderForm_exam_id,
	MODIFY COLUMN exam_id int(11) NOT NULL AFTER orderForm_id;
 
ALTER TABLE school
	MODIFY COLUMN district_id int(11) NOT NULL AFTER school_id,
	MODIFY COLUMN name varchar(255) NOT NULL AFTER district_id;

