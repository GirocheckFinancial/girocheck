INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(8, 'patch_8_1', now(), 'ADD forgot_password_alert email table');


INSERT INTO `girocheck`.`email` (`id`, `name`, `username`, `password`, `recipients`, `title`, `body`) VALUES ('4', 'alert_mobile_forgot_password_key', 'cards@girocheck.com', 'Girocheck1', '', 'Mobile Application Forgot Password Alert', 'Hello client_name,<br>Thank you for choosing VoltCash. Your password key is: forgot_password_key .Please use this key to change your password.<br>If you did not make this request or do not wish to change your password, you may ignore this message.<br><br> Customer Service Department.<br> Girocheck Financial Inc.<br> 703 NW 62nd Ave Suite 230<br> Miami, FL 33126 (U.S.A).');
 

 