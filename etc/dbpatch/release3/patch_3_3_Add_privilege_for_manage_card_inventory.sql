INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(3, 'patch_3_3', now(), 'Add_privilege_for_manage_card_inventory');

INSERT INTO `privilege` (`id`,`name`,`description`) VALUES (24,"Allow Manage Card Inventory", "Allow Manage Card Inventory");
