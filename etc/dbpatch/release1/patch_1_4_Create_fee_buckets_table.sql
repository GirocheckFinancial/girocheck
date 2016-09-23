INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(1, 'patch_1_4', now(), 'Create table fee_buckets');

CREATE TABLE `fee_buckets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `minimum` decimal(10,2) DEFAULT NULL,
  `fixed` decimal(10,2) DEFAULT NULL,
  `percentage` decimal(10,2) DEFAULT NULL,
  `fee_schedule_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `schedule_id` (`fee_schedule_id`),
  CONSTRAINT `schedule_id` FOREIGN KEY (`fee_schedule_id`) REFERENCES `fee_schedules` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
