DELETE FROM girocheck.dbpatch WHERE name = 'patch_2_2';

ALTER TABLE girocheck.user
  DROP COLUMN failed_attempts,
  DROP COLUMN last5passwords,
  DROP COLUMN last_time_failed_attempt,
  DROP COLUMN last_time_update_password;