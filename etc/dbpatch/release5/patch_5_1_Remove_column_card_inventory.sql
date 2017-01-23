INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(5, 'patch_5_1', now(), 'Remove_column_card_inventory');

ALTER TABLE merchant DROP card_inventory;