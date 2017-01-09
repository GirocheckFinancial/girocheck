INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(4, 'patch_4_1', now(), 'Add_privilege_for_fee_management');

INSERT INTO `privilege` (`id`,`name`,`description`) VALUES (25,"Allow Fee Management", "Allow Fee Management");

INSERT INTO `role_privilege` (`role`,`privilege`) VALUES (1,25);