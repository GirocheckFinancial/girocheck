INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(6, 'patch_6_3', now(), "Add column blacklist_card2bank to client table");

alter table client add column blacklist_card2bank  bit(1) DEFAULT false;