INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(2, 'patch_2_6', now(), 'Add_email_for_send_to_Tecnicard');

INSERT INTO `email` VALUES (1,'2_successful_loads_to_tecnicard','cards@girocheck.com','Girocheck1',' personalizedcards@girocheck.com','Send new card', 
'Hello Team,<br>
Please send an embossed card to the customers listed below:<br><br>
<b>Name:</b> user_name &nbsp; user_lastname <br>
<b>Card #:</b> masked_card  <br> <br>

We will appreciate your confirmation once this request has been worked out.
Regards,
<br> <br>
Customer Service Department.<br>
Girocheck Financial Inc.<br>
703 NW 62nd Ave Suite 230<br>
Miami, FL 33126 (U.S.A).<br>
Ph: +1-800-249-3042<br>
customerservice@girocheck.com<br>
www.girocheck.com' );