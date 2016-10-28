INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(2, 'patch_2_3', now(), 'Add_successful_loads_field_to_table_client');

alter table client add column successful_loads integer;

