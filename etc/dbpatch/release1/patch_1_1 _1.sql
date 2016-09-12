INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(1, 'patch_1_1_1', now(), 'Create transaction_method table');

--Create table transaction_method

CREATE TABLE `transaction_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `description` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO girocheck.transaction_method(`operation`,description) VALUES ('01' ,'Check transactions.');
INSERT INTO girocheck.transaction_method(`operation`,description) VALUES ('02' ,'Cash transactions.');