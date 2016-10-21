/**
 *
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be reproduced, transmitted,
 * transcribed, stored in a retrieval system, or translated into any language or
 * computer language, in any form or by any means, electronic, mechanical,
 * magnetic, optical, chemical, manual or otherwise, without the prior written
 * permission of Smart Business Technology, Inc.
 *
 */

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>, Roberto Rodriguez :: <Roberto.Rodriguez@smartbt.com>
 */

var xmlhttp;

/**
 * Clears out the error messages on input focus
 * 
 * @returns 
 */
window.onload = function(){

    var elems = ["username", "password"];
    var target = document.getElementById( "error" );
    for ( i = 0; i < elems.length; i++ ) {
        var sources = document.getElementById( elems[i] );
        sources.onfocus = function(){
            target.innerHTML = "";
        };
    }
    document.getElementById('appVersion').innerHTML = "AMS v01.00.05";
};


/**
 * 
 * @param {String} username
 * @param {String} password
 * @returns {xmlhttp}
 */
function login( username, password ){
    var username = document.getElementById( "username" ).value;
    var password = document.getElementById( "password" ).value;
    
    if(!validate( username, password ))return;
   
    document.cookie = "USER_NAME=" + username;

    if ( !xmlhttp ) {
        xmlhttp = getXHR();
    }

    var url = "/FrontAMS/webresources/VTAMS/authenticateUser";
    xmlhttp.open( 'POST', url, true );
    xmlhttp.setRequestHeader( "Content-type", "application/x-www-form-urlencoded" );
    xmlhttp.onreadystatechange = getThings;
    xmlhttp.send( 'username=' + encodeURIComponent( username ) + '&password=' + encodeURIComponent( password ) );

}

/**
 * Basic Empty Validation
 * 
 * @returns {undefined|String}
 */


function validate( username, password ){
    return username.length > 0 && password.length > 0;
}
/**
 * 
 * @returns {undefined}
 */
function getThings(){
    if ( xmlhttp.readyState === 4 && xmlhttp.status === 200 ) {
        var a = xmlhttp['responseText'];
        var obj = eval( '(' + a + ')' );

         if (obj['status'] === 100) {
            var data = obj['data'];//token
            redirect(data);
        } else {
            document.getElementById('username').value = "";
            document.getElementById('password').value = "";
            document.getElementById('error').innerHTML = obj['statusMessage'];//"Invalid User"; // Invalid User
        }        
    }

}
/**
 * 
 * @returns {ActiveXObject|XMLHttpRequest}
 */

function getXHR(){

    if ( window.XMLHttpRequest ) {
        // Chrome, Firefox, IE7+, Opera, Safari
        return new XMLHttpRequest();
    }
    // IE6
    try {
        // The latest stable version. It has the best security, performance, 
        // reliability, and W3C conformance. Ships with Vista, and available 
        // with other OS's via downloads and updates. 
        return new ActiveXObject( 'MSXML2.XMLHTTP.6.0' );
    } catch ( e ) {
        try {
            // The fallback.
            return new ActiveXObject( 'MSXML2.XMLHTTP.3.0' );
        } catch ( e ) {
            alert( 'This browser is not AJAX enabled.' );
            return null;
        }
    }
}

/**
 * 
 * @returns {url}
 */
function redirect(token){
    document.cookie = "TOKEN=" + token;
//    window.location = "VTAMS.html?token=" + token;
    window.location = "VTAMS.html";
}