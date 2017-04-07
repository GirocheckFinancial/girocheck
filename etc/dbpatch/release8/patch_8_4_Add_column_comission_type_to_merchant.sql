INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(8, 'patch_8_4',  now(), "Add_column_comission_type_to_merchant");

alter table girocheck.merchant ADD commission_type char(1) DEFAULT 'D';