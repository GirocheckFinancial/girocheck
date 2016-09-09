--Create table dbpatch

CREATE TABLE `dbpatch` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `release_number` int(10) DEFAULT NULL,  
  `name` varchar(255) DEFAULT NULL,
  `applydate` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=188;

INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(1, 'patch_1_1', now(), 'Create dbpatch table');