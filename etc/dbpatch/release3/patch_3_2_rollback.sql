DELETE FROM girocheck.dbpatch WHERE name= 'patch_3_2';

delete from email WHERE name = 'alert_inventory_reach_threshold';
delete from email WHERE name = 'alert_inventory_reach_zero';