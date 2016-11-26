INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(3, 'patch_3_2', now(), 'Add_email_for_alert_about_inventory_threshold');

INSERT INTO `email` ( `name`,`username`,`password`,`recipients`,`title`,`body`) VALUES ('alert_inventory_reach_threshold','cards@girocheck.com','Girocheck1','processing@girocheck.com','Card Inventory Threshold Alert', 
'This is an alert email notifying that the Merchant _merchant has met the low card count alert threshold of _threshold.
<br> <br>
Customer Service Department.<br>
Girocheck Financial Inc.<br>
703 NW 62nd Ave Suite 230<br>
Miami, FL 33126 (U.S.A).<br>
Ph: +1-800-249-3042<br>
customerservice@girocheck.com<br>
www.girocheck.com' );


INSERT INTO `email` ( `name`,`username`,`password`,`recipients`,`title`,`body`) VALUES ('alert_inventory_reach_zero','cards@girocheck.com','Girocheck1','processing@girocheck.com','Card Inventory Threshold Alert', 
'This is an alert email notifying that the Merchant _merchant has met the amount of ZERO cards in the inventory.
<br> <br>
Customer Service Department.<br>
Girocheck Financial Inc.<br>
703 NW 62nd Ave Suite 230<br>
Miami, FL 33126 (U.S.A).<br>
Ph: +1-800-249-3042<br>
customerservice@girocheck.com<br>
www.girocheck.com' );