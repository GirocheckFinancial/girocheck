INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(1, 'patch_1_7', now(), 'Seting Tecnicard as default host.');

update girocheck.binnumb_host set `default`= 0 WHERE host_code = 01;
update girocheck.binnumb_host set `default`= 1 WHERE host_code = 02;