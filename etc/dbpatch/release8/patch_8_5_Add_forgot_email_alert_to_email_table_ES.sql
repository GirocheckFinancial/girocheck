INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(8, 'patch_8_5', now(), 'Add_forgot_email_alert_to_email_table_ES');


 
INSERT INTO `girocheck`.`email` (`id`, `name`, `username`, `password`, `recipients`, `title`, `body`) VALUES ('4', 'alert_mobile_forgot_password_key_ES', 'cards@girocheck.com', 'Girocheck1', '', 'Recuperar contraseña de la aplicación Voltcash ', 'Hola client_name,<br>Gracias por elegir VoltCash. Su Código de Acceso es : forgot_password_key . Use este código para loguearse y cambiar su contraseña.<br>Si usted no ha hecho esta solicitud o no desea cambiar su contraseña, puede ignorar este mensaje.<br><br> Departamento de Atención al Cliente.<br> Girocheck Financial Inc.<br> 703 NW 62nd Ave Suite 230<br> Miami, FL 33126 (U.S.A).');

 