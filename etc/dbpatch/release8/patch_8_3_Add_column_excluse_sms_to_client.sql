INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(8, 'patch_8_3', now(), "Add_column_exclude_sms_to_client");

ALTER TABLE `girocheck`.`client` 
ADD COLUMN `exclude_sms` TINYINT(1) NULL DEFAULT 0 AFTER `blacklist_card2bank`;

 
