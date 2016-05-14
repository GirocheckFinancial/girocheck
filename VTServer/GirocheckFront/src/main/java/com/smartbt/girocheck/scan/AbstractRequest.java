/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.List;

/**
 *
 * @author Roberto
 */
public abstract class AbstractRequest {
    
    public boolean validateRequest(){
        List<ParameterName> emptyFields = validate();
        
        if(getUser().isEmpty())emptyFields.add(ParameterName.USER);
        if(getPassword().isEmpty())emptyFields.add(ParameterName.PASSWORD);
        if(getRequestId().isEmpty())emptyFields.add(ParameterName.REQUEST_ID);
        if(getTerminalId() == 0)emptyFields.add(ParameterName.TERMINAL_ID);
        
        if(emptyFields.size() != 0){
            StringBuffer string = new StringBuffer("Field" + (emptyFields.size() == 1 ? "" : "s"));
            
            for (int i = 0; i < emptyFields.size(); i++) {
                string.append(emptyFields.get(i).toString() + (i == (emptyFields.size() - 1) ? " " : ", "));
            }
            
            string.append("required.");
        }
        
        return true;
    }
    
    public abstract List<ParameterName> validate();
   
    public abstract String getUser();

    public abstract String getPassword();

    public abstract String getRequestId();

    public abstract int getTerminalId();
    
    
}
