INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(8, 'patch_8_6', now(), 'Add_privilege_for_sms');


INSERT INTO `privilege` (`id`,`name`,`description`) VALUES (28,"Allow SMS Messages", "Allow SMS Messages");


INSERT INTO `role_privilege` (`role`,`privilege`) VALUES (1,28);