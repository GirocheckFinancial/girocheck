
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.Map;

/**
 *
 * @author Roberto Rodriguez
 */
public class ProvissionalValues {
    //cambio
    //AUTO_INCREMENTAL VALUE
public static final String REQUEST_ID = "REQUEST_ID";


public static final String IdType = "IdType";
public static final String State = "State";
public static final String DateOfBirth = "DateOfBirth";
public static final String Address = "Address";
public static final String City = "City";
public static final String IdState = "IdState";
public static final String TelephoneAreaCode = "TelephoneAreaCode";
public static final String Cellphone = "Cellphone";
public static final String ZipCode = "ZipCode";
public static final String MiddleName = "MiddleName";
public static final String Telephone = "Telephone";
public static final String FaxAreaCode = "FaxAreaCode";
public static final String LastName = "LastName";
public static final String WorkphoneAreaCode = "WorkphoneAreaCode";
public static final String Country = "Country";
public static final String CellphoneAreaCode = "CellphoneAreaCode";
public static final String PersonTitle = "PersonTitle";
public static final String Email = "Email";
public static final String CurrentAddress = "CurrentAddress";
public static final String RBService = "RBService";
public static final String FirstName = "FirstName";
public static final String Id = "Id";
public static final String MaidenName = "MaidenName";
public static final String Workphone = "Workphone";
public static final String IdCountry = "IdCountry";
public static final String Faxphone = "Faxphone";
public static final String IdExpiration = "IdExpiration";

public static void addProvissionalValuesToMap(Map map){
    map.put(ParameterName.IDTYPE, IdType);
    map.put(ParameterName.STATE, State);
    map.put(ParameterName.DATEOF_BIRTH, DateOfBirth);
    map.put(ParameterName.ADDRESS, Address);
    map.put(ParameterName.CITY, City);
    map.put(ParameterName.IDSTATE, IdState);
    map.put(ParameterName.TELEPHONE_AREA_CODE, TelephoneAreaCode);
    map.put(ParameterName.CELL_PHONE, Cellphone);
    map.put(ParameterName.ZIPCODE, ZipCode);
    map.put(ParameterName.MIDDLE_NAME, MiddleName);
    map.put(ParameterName.TELEPHONE, Telephone);
    map.put(ParameterName.FAX_AREA_CODE, FaxAreaCode);
    map.put(ParameterName.LAST_NAME, LastName);
    map.put(ParameterName.WORKPHONE_AREA_CODE, WorkphoneAreaCode);
    map.put(ParameterName.COUNTRY, Country);
    map.put(ParameterName.CELL_PHONE_AREA, CellphoneAreaCode);
    map.put(ParameterName.PERSON_TITLE, PersonTitle);
    map.put(ParameterName.EMAIL, Email);
    map.put(ParameterName.CURRENT_ADDRESS, CurrentAddress);
    map.put(ParameterName.RB_SERVICE, RBService);
    map.put(ParameterName.FIRST_NAME, FirstName);
    map.put(ParameterName.ID, Id);
    map.put(ParameterName.MAIDEN_NAME, MaidenName);
    map.put(ParameterName.WORK_PHONE, Workphone);
    map.put(ParameterName.IDCOUNTRY, IdCountry);
    map.put(ParameterName.FAX_PHONE, Faxphone);
    map.put(ParameterName.IDEXPIRATION, IdExpiration);
}

}
