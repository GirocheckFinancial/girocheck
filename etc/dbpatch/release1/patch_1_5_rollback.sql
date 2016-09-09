DELETE FROM girocheck.dbpatch WHERE name ='patch_1_5';

--cash
DELETE FROM girocheck.fee_buckets WHERE fee_schedule_id = (select id from girocheck.fee_schedules where method_id = 2 AND isdefault = true);

--check
DELETE FROM girocheck.fee_buckets WHERE fee_schedule_id = (select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true);





