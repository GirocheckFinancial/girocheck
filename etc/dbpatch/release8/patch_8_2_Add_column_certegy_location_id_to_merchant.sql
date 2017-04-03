INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(6, 'patch_8_2', now(), "Add_column_certegy_location_id_to_merchant");

ALTER TABLE `girocheck`.`merchant` 
ADD COLUMN `certegy_location_id` VARCHAR(20);

 
