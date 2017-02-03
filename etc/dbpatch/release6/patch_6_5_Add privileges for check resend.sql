INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(6, 'patch_6_5', now(), 'Add_privilege_for_check_resend');
INSERT INTO `privilege` (`id`,`name`,`description`) VALUES (26,"Allow Check Resend", "Allow Check Resend");
INSERT INTO `role_privilege` (`role`,`privilege`) VALUES (1,26);