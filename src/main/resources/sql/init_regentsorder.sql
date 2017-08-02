use regentsorder;
insert into user_profile values(1, 'ADMIN'), (2, 'DBA'), (3, 'USER');

insert into district values(1,999999,0,'Admin District',0);


INSERT INTO APP_USER(username, password, first_name, last_name, email, district_id)
VALUES ('dan','$2a$10$AUHlIZIoGNOkzYdRKlTS.O2.7OnRUG0oQ/uCaZxytJlkjnWuapnGi', 'Dan','Whitehouse','wh1tehouse@password.com', 1),
('andrew','$2a$10$ny3S4PqK/SRndFMhrh6En.kgTMF406ZoR7uGtGaMcr0Y4zWGTAdoC', 'Andrew','Pieniezny','andrew@school.com', 1);

INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where (user.id in ('1', '2') and profile.type='ADMIN');

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

