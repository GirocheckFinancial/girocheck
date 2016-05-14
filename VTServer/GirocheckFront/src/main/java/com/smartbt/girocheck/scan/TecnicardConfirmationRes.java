package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.IBuilder;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "TecnicardConfirmationRes", propOrder = {
    "resultCode",
    "resultMessage",
    "printLogo"
} )
public class TecnicardConfirmationRes extends MainResponseContainer implements IBuilder {
//    @XmlElement( name = "checkId" )
//    protected String checkId;
    
    @XmlElement( name = "resultCode" )
    protected String resultCode;
    @XmlElement( name = "resultMessage" )
    protected String resultMessage;
    @XmlElement( name = "PrintLogo" )
    private String printLogo;

    @Override
    public TecnicardConfirmationRes build( Map map ) throws Exception {
//        checkId = MapUtil.getStringValueFromMap(map, ParameterName.CHECK_ID );
        setResultCode( (String)map.get(ParameterName.RESULT_CODE));
        setResultMessage((String)map.get(ParameterName.RESULT_MESSAGE));
        setPrintLogo((String)map.get(ParameterName.PRINTLOGO));
        return this;
    }
    
    public TecnicardConfirmationRes mock(){
//        setCheckId(checkId);
        setResultCode(ResultCode.SUCCESS.getCode() + "");
        setResultMessage(ResultMessage.SUCCESS.getMessage());
        return this;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @return the printLogo
     */
    public String getPrintLogo() {
        return printLogo;
    }

    /**
     * @param printLogo the printLogo to set
     */
    public void setPrintLogo(String printLogo) {
        this.printLogo = printLogo;
    }
    
    


}
