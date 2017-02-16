INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(6, 'patch_6_4', now(), "Add column status creation-date processing date to check table");

ALTER TABLE `girocheck`.`check` 
ADD COLUMN `status` VARCHAR(45) NULL DEFAULT NULL AFTER `locationId`,
ADD COLUMN `creation_date` DATETIME NULL DEFAULT NULL AFTER `status`,
ADD COLUMN `processing_date` DATETIME NULL DEFAULT NULL AFTER `creation_date`;

 
