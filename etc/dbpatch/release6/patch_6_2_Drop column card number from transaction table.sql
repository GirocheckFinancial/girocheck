INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(6, 'patch_6_2', now(), 'Drop column card number from transaction table);

alter table transaction drop  card_number;