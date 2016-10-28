INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(2, 'patch_2_4', now(), 'Update_successful_loads_value_for_every_client_based_on_transactions');

update client c set successful_loads = (select count(*) from transaction where (operation = '01' OR operation = '02') AND result_code = 0 AND client = c.id);