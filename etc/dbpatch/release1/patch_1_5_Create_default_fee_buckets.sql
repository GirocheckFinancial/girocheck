INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(1, 'patch_1_5', now(), 'Inset fee_buckets for default schedules');

INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (0.0, 3.95, 0.0,(select id from girocheck.fee_schedules where method_id = 2 AND isdefault = true));

INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (0.0, 2.95, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (100.01, 3.50, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (200.01, 4.50, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (300.01, 5.25, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (400.01, 7.00, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (500.01, 8.75, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (600.01, 10.50, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (700.01, 12.25, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (800.01, 14.00, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (900.01, 15.75, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1000.01,17.50, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1100.01,19.25, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1200.01, 21.00, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1300.01, 22.75, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1400.01, 24.50, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1500.01, 26.25, 0.0,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true));
INSERT INTO girocheck.fee_buckets (minimum, fixed, percentage, fee_schedule_id) VALUES (1600.01, 0, 1.75,(select id from girocheck.fee_schedules where method_id = 1 AND isdefault = true)); 






