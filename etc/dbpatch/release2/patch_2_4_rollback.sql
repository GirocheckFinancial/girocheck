DELETE from girocheck.dbpatch WHERE name = 'patch_2_4';

update card c set successful_loads = 0;