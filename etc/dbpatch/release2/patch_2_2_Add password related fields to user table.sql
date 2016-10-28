INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(2, 'patch_2_2', now(), 'Add password related fields to user table');

ALTER TABLE user ADD failed_attempts integer,ADD last5passwords varchar(255),ADD last_time_failed_attempt datetime,ADD last_time_update_password datetime;



