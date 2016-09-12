DELETE FROM girocheck.dbpatch WHERE name = 'patch_1_7';

update girocheck.binnumb_host set `default`= 1 WHERE host_code = 01;
update girocheck.binnumb_host set `default`= 0 WHERE host_code = 02;