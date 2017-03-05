/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.manager;

import com.alodiga.persistence.dao.CityDAO;
import com.alodiga.persistence.dao.DestinatarioDAO;
import com.alodiga.persistence.dao.StateDAO;
import com.alodiga.persistence.domain.City;
import com.alodiga.persistence.domain.Destinatario;
import com.alodiga.persistence.domain.State;
import com.alodiga.persistence.dto.Estado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrodriguez
 */
@Service
@Transactional
public class OperationManager {
     
    @Autowired
    private StateDAO stateDAO;
     
    @Autowired
    private CityDAO cityDAO;
    
    @Autowired
    IntegrationManager integrationManager;

    public void migrateStateList() {
       List<Estado> estados = integrationManager.listarEstados("US");
       
        for (Estado estado : estados) {
            stateDAO.saveOrUpdate(new State(estado));
        }
    }
    
     public void validateStateCities() {
         System.out.println("----- validateStateCities -----");
       List<State> states = stateDAO.list();
       
        for (State state : states) {
           List<String> cities = cityDAO.listCitiesFromState(state.getStateCode());
           
           if(cities == null || cities.isEmpty()){
               System.out.println("State " + state.getName() + " does not have cities");
           }
        }
    }
    
    public List<String> listCitiesFromStateCode(String code){
        String stateCode = stateDAO.getStateCodeByCode(code);
        System.out.println("stateCode = " + stateCode);
       return cityDAO.listCitiesFromState(stateCode);
    }
    
}
