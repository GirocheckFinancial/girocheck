INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(7, 'patch_7_1', now(), 'Create mobile_client table');
 
DROP table girocheck.mobile_client;
 
CREATE TABLE `mobile_client` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `device_type` varchar(45) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT NULL,
  `forgot_password_key` varchar(45) DEFAULT NULL,
  `client` int(10) NOT NULL,
  `card` int(10) NOT NULL,
  `key_expiration_time` timestamp NULL DEFAULT NULL,
  `username` varchar(55) DEFAULT NULL,
  `password` varchar(55) DEFAULT NULL,  
  PRIMARY KEY (`id`),
  KEY `sadsafs_idx` (`client`) USING BTREE,
  KEY `mobile_client_fk2_idx` (`card`),
  CONSTRAINT `mobile_client_fk` FOREIGN KEY (`client`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mobile_client_fk2` FOREIGN KEY (`card`) REFERENCES `card` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `girocheck`.`email` (`id`, `name`, `username`, `password`, `recipients`, `title`, `body`) VALUES ('4', 'alert_mobile_forgot_password_key', 'cards@girocheck.com', 'Girocheck1', '', 'Mobile Application Forgot Password Alert', 'Hello client_name,<br>Thank you for choosing Mobile Application.<br>Your request for forgot password key is: forgot_password_key <br> <br> Customer Service Department.<br> Girocheck Financial Inc.<br> 703 NW 62nd Ave Suite 230<br> Miami, FL 33126 (U.S.A).');

 