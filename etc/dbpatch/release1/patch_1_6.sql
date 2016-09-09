INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(1, 'patch_1_6', now(), 'Inset application params related to amount limits.');

INSERT INTO girocheck.application_parameter(name,value,data_type,read_only,application,description) VALUES ('amountMinCheck' ,'10',3,1,1,'Minimun amount allowed for check');
INSERT INTO girocheck.application_parameter(name,value,data_type,read_only,application,description) VALUES ('amountMaxCheck' ,'5000',3,1,1,'Maximum amount allowed for check');
INSERT INTO girocheck.application_parameter(name,value,data_type,read_only,application,description) VALUES ('amountMinCash' ,'10',3,1,1,'Minimun amount allowed for cash');
INSERT INTO girocheck.application_parameter(name,value,data_type,read_only,application,description) VALUES ('amountMaxCash' ,'1000',3,1,1,'Maximum amount allowed for cash');