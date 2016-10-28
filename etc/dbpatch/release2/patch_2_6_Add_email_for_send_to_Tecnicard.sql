INSERT INTO girocheck.dbpatch (release_number, name, applydate, description) VALUES(2, 'patch_2_6', now(), 'Add_email_for_send_to_Tecnicard');

INSERT INTO `email` VALUES (1,'2_successful_loads_to_tecnicard','cubacomprar','hocuspocus','robertosoftwareengineer@gmail.com,titorobe@yahoo.com','Send new card', 
'This is an email generated automatically by <b>Girocheck</b>,
<br>
The reason of this email is comunicate to <b>Tecnicard</b> that the user user_name user_lastname performed a successful second load to the card ending in {card_last4}.
<br><br>
This is the personal information of the user:
<br>
<b>Name:</b>&nbsp;user_name
<br>
<b>Last Name:</b>&nbsp;user_lastname
<br>
<b>SSN:</b>&nbsp;user_ssn
<br>
<b>DOB:</b>&nbsp;user_dob
<br>
<b>Phone:</b>&nbsp;user_phone
<br>
<b>Address:</b>&nbsp;user_address
<br>
For further information, please contact Girocheck Financial INC.
' );