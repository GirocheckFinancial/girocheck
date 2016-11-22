DELETE FROM girocheck.dbpatch WHERE name = 'patch_2_3';

alter table card drop column successful_loads;