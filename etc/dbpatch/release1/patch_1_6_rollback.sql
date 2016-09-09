DELETE FROM girocheck.dbpatch WHERE name = 'patch_1_6';

DELETE FROM girocheck.application_parameter WHERE name IN ('amountMinCheck' ,'amountMaxCheck' ,'amountMinCash','amountMaxCash');