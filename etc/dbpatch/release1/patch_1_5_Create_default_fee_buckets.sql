INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(1, 'patch_1_5', now(), 'Inset fee_buckets for default schedules');

--cash
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (10.0, 3.95, 0.0,(select id from girocheck.fee_schedules where method_id = 2 AND isdefault = true));

--check
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (10.0, 2.95, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (100.1, 3.50, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (200.1, 4.50, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (300.1, 5.25, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (400.1, 7.00, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (500.1, 8.75, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (600.1, 10.50, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (700.1, 12.25, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (800.1, 14.00, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (900.1, 15.75, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1000.1,17.50, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1100.1,19.25, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1200.1, 21.00, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1300.1, 22.75, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1400.1, 24.50, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1500.1, 26.25, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1600.1, 0, 1.75,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true)); 






