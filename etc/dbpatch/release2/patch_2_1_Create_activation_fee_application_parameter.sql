INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(2, 'patch_2_1', now(), 'Create activation fee in application parameter table');

INSERT INTO girocheck.application_parameter(
   name
  ,value
  ,data_type
  ,read_only
  ,application
  ,description
) VALUES (
  'activation_fee' 
  ,'5' 
  ,3   
  ,1   
  ,1   
  ,'Activation Fee to be sent to the Terminal in new Card Loads' 
)