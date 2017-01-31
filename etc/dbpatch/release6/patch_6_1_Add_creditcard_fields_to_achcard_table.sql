INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(2, 'patch_3_1', now(), 'Add_Inventory_fields_to_Merchant_table');

alter table merchant add column inventory integer;
alter table merchant add column threshold integer;


update merchant set inventory = 0;
update merchant set threshold = 0;
