INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(6, 'patch_6_1', now(), 'Add_Inventory_fields_to_Merchant_table');

alter table ach_card add column creditcard integer;


update ach_card ac set creditcard = (select id from card c where ac.card_number = (select SHA1( AES_DECRYPT(FROM_BASE64(c.data_s), SHA2(SHA2(CONCAT((SELECT concat(c.mode, c.reference, c.sessions, c.types) FROM configs c), 'SELECT * FROM configs'), 512), 512)))));

alter table ach_card drop column card_number;