INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(6, 'patch_6_6', now(), 'Add privileges for Client Black List');
INSERT INTO `privilege` (`id`,`name`,`description`) VALUES (27,"Allow Client Black List", "Allow Client Black List");
INSERT INTO `role_privilege` (`role`,`privilege`) VALUES (1,27);