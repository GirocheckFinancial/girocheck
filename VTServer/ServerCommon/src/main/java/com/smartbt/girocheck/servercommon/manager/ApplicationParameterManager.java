package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.ApplicationParameterDAO;
import com.smartbt.girocheck.servercommon.display.AgrupationDisplay;
import com.smartbt.girocheck.servercommon.display.ApplicationParameterDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.model.ApplicationParameter;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.enums.EnumApplicationParameter;
import com.smartbt.vtsuite.servercommon.validators.ApplicationParameterValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.ApplicationType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class ApplicationParameterManager {
    
    private static ApplicationParameterManager INSTANCE;
    
    public static ApplicationParameterManager get(){
        if(INSTANCE == null){
            INSTANCE = new ApplicationParameterManager();
        }
        return INSTANCE;
    }

    private ApplicationParameterDAO applicationParameterDAO = ApplicationParameterDAO.get();

    public Map<String, String> listParameterByApplication( ApplicationType applicationType ) {
        Map<String, String> map = new HashMap<String, String>();

        List<ApplicationParameter> list = applicationParameterDAO.listParameterByApplication( applicationType );

        for ( ApplicationParameter applicationParameter : list ) {
            map.put( applicationParameter.getName(), applicationParameter.getValue() );
        }

        return map;
    }

    public Map listParameterValue() {
        Map map = new HashMap();

        List<ApplicationParameter> list = applicationParameterDAO.listParameterValue();

        for ( ApplicationParameter applicationParameter : list ) {
            map.put( applicationParameter.getName(), applicationParameter.getValue() );
        }
        return map;
    }

    public ResponseDataList search( String searchFilter, int pageNumber, int rowsPerPage ) throws Exception {
        ApplicationParameterValidator.search( searchFilter, pageNumber, rowsPerPage );
        ResponseDataList response = new ResponseDataList();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        response.setData( applicationParameterDAO.search( searchFilter, pageNumber * rowsPerPage, rowsPerPage ) );

        int total = applicationParameterDAO.search( searchFilter, -1, -1 ).size();
        response.setTotalPages( (int) Math.ceil( (float) total / (float) rowsPerPage ) );
        return response;
    }

    public ResponseData<ApplicationParameterDisplay> saveOrUpdate( ApplicationParameterDisplay display ) throws Exception {
        ResponseData<ApplicationParameterDisplay> response = new ResponseData<ApplicationParameterDisplay>();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );

        ApplicationParameter parameter = new ApplicationParameter();
        if ( display.getId() != null ) {
            parameter.setId( display.getId() );
        }

        parameter.setName( display.getName() );
        parameter.setValue( display.getValue() );
        parameter.setReadOnly( display.isReadOnly() );
        parameter.setApplication( display.getApplication() );
        parameter.setDescription( display.getDescription() );

        applicationParameterDAO.saveOrUpdate( parameter );

        display.setId( parameter.getId() );
        response.setData( display );
        return response;
    }

     public BaseResponse delete(int applicationParameterId) throws Exception {
        BaseResponse response = new BaseResponse();
        applicationParameterDAO.delete(applicationParameterId);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
     
     public ApplicationParameter getAplicationParameterByName(String name){
         return applicationParameterDAO.getAplicationParameterByName(name);
     }
     
     public Map<EnumApplicationParameter, Double> getAmountAplicationParameters(){
          Map<EnumApplicationParameter, Double> map = new HashMap();
         
          getAmountParameter( map, EnumApplicationParameter.AMOUNT_MIN_CHECK, 10D);
          getAmountParameter( map, EnumApplicationParameter.AMOUNT_MAX_CHECK, 5000D);
          getAmountParameter( map, EnumApplicationParameter.AMOUNT_MIN_CASH, 10D);
          getAmountParameter( map, EnumApplicationParameter.AMOUNT_MAX_CASH, 1000D);
          getAmountParameter( map, EnumApplicationParameter.ACTIVATION_FEE, 4.95D); //4.95 is the default value
          
         return map;
     }
     
     private void getAmountParameter(Map<EnumApplicationParameter, Double> map, EnumApplicationParameter parameterName, Double defaultValue){
         ApplicationParameter applicationParameter = getAplicationParameterByName(parameterName.toString());
         Double value = defaultValue;
         if(applicationParameter != null){
             try{
                 value = Double.parseDouble(applicationParameter.getValue());
             }catch(Exception e){}
         }
         map.put(parameterName, value);
     }
}
