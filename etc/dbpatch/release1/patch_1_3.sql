INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(1, 'patch_1_3', now(), 'Create default fee_schedules');

INSERT INTO girocheck.fee_schedules(method_id, isdefault) VALUES((select id from girocheck.transaction_method where `operation` = "01"), true);
INSERT INTO girocheck.fee_schedules(method_id, isdefault) VALUES((select id from girocheck.transaction_method where `operation` = "02"), true);