INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(7, 'patch_7_1', now(), 'Create mobile_client table');
 
CREATE TABLE `mobile_client` (
  `id` int(10) NOT NULL,
  `device_type` varchar(45) DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT NULL,
  `forgot_password_key` varchar(45) DEFAULT NULL,
  `client` int(10) NOT NULL,
  `card` int(10) NOT NULL,
  `key_expiration_time` timestamp NULL DEFAULT NULL,
  `username` varchar(55) DEFAULT NULL,
  `password` varchar(55) DEFAULT NULL,
  `pin` varchar(25) DEFAULT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,    
  PRIMARY KEY (`id`),
  KEY `sadsafs_idx` (`client`),
  KEY `mobile_client_fk2_idx` (`card`),
  CONSTRAINT `mobile_client_fk` FOREIGN KEY (`client`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mobile_client_fk2` FOREIGN KEY (`card`) REFERENCES `card` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


alter table vtsession add constraint vtsession_fk2 foreign key(id_mobile_client) references mobile_client(id);