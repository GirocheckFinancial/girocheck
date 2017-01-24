INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(5, 'patch_5_2', now(), 'Create filters_report table');

CREATE TABLE `filters_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `search_filter` varchar(255) DEFAULT NULL,
  `start_range_date` datetime DEFAULT NULL,
  `end_range_date` datetime DEFAULT NULL,
  `transaction_type` int(10) DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL,
  `filter_ammount` bit(1) DEFAULT NULL,
  `ammount_type` int(10) DEFAULT NULL,
  `optype` int(10) DEFAULT NULL,
  `ammount` varchar(10) DEFAULT NULL,
  `pending`  bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
