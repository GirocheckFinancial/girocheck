INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(1, 'patch_1_3', now(), 'Create default fee_schedules');

INSERT INTO girocheck.fee_schedules(method_id, isdefault) VALUES(1, true);
INSERT INTO girocheck.fee_schedules(method_id, isdefault) VALUES(2, true);