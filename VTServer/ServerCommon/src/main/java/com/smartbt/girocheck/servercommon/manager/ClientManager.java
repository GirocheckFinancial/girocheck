/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */
package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.AddressDAO;
import com.smartbt.girocheck.servercommon.dao.ClientDAO;
import com.smartbt.girocheck.servercommon.dao.CountryDAO;
import com.smartbt.girocheck.servercommon.dao.CreditCardDAO;
import com.smartbt.girocheck.servercommon.dao.PersonalIdentificationDAO;
import com.smartbt.girocheck.servercommon.dao.StateDAO;
import com.smartbt.girocheck.servercommon.display.ClientDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.Country;
import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
import com.smartbt.girocheck.servercommon.model.State;
import java.sql.SQLException;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class ClientManager {

    protected static ClientManager INSTANCE;

    public ClientManager() {
    }

    public static ClientManager get() {
        if (INSTANCE == null) {
            INSTANCE = new ClientManager();
        }
        return INSTANCE;
    }
    private ClientDAO clientDAO = ClientDAO.get();
    private AddressDAO addressDAO = AddressDAO.get();
    private CountryDAO countryDAO = CountryDAO.get();
    private StateDAO stateDAO = StateDAO.get();
    private CreditCardDAO creditCardDAO = CreditCardDAO.get();
    private PersonalIdentificationDAO personalIdentificationDAO = PersonalIdentificationDAO.get();

    public Client createOrGet(String ssn, byte[] addressForm) throws SQLException {
        return clientDAO.createOrGet(ssn, addressForm);
    }

    public void saveOrUpdate(Client client) {
        client.setBlacklistCard2bank(false);
        if (client.getAddress() != null) {
            if (client.getAddress().getCountry() != null && client.getAddress().getCountry().getAbbreviation() != null) {
                Country country = countryDAO.getByAbbreviation(client.getAddress().getCountry().getAbbreviation());
                if (country != null) {
                    client.getAddress().setCountry(country);
                }
            }
            if (client.getAddress().getState() != null && client.getAddress().getState().getAbbreviation() != null) {
                State state = stateDAO.getByAbbreviation(client.getAddress().getState().getAbbreviation());
                if (state != null) {
                    client.getAddress().setState(state);
                } else {
                    client.getAddress().setState(null);
                }
            }
        }

//        PersonalIdentification transientIdentification = null;
//        if ( client.getData_SD().size() > 0 ) {
//            transientIdentification = (PersonalIdentification) ( client.getData_SD().toArray()[0] );
//
//            personalIdentificationDAO.removeByClientAndType( client.getId(), transientIdentification.getIdType() );
//            System.out.println( "after remove" );
//
//            if ( transientIdentification.getState() != null ) {
//                System.out.println( "--------:: transientIdentification.getState().getAbbreviation() :: " + transientIdentification.getState().getAbbreviation() );
//                State state = stateDAO.getByAbbreviation( transientIdentification.getState().getAbbreviation() );
//                System.out.println( "STATE = " + ( state == null ? "NLL" : "NOT NULL" ) );
//                if ( state != null ) {
//                    transientIdentification.setState( state );
//                } else {
//                    transientIdentification.setState( null );
//                }
//            } else {
//                System.out.println( "transientIdentification.getState() is NULL" );
//            }
//            transientIdentification.setClient( client );
//        }

        clientDAO.saveOrUpdate(client);
    }
//
//    public void saveOrUpdate( Client client ) {
//
//        if ( client.getAddress() != null ) {
//            if ( client.getAddress().getCountry() != null && client.getAddress().getCountry().getAbbreviation() != null ) {
//                Country country = countryDAO.getByAbbreviation( client.getAddress().getCountry().getAbbreviation() );
//                if ( country != null ) {
//                    client.getAddress().setCountry( country );
//                }
//            }
//            if ( client.getAddress().getState() != null && client.getAddress().getState().getAbbreviation() != null ) {
//                State state = stateDAO.getByAbbreviation( client.getAddress().getState().getAbbreviation() );
//                if ( state != null ) {
//                    client.getAddress().setState( state );
//                }
//            }
//        }
//
//        if ( client.getData_SD().size() > 0 ) {
//            PersonalIdentification transientIdentification = (PersonalIdentification) ( client.getData_SD().toArray()[0] );
//
//           //PersonalIdentification identificationPointer = new PersonalIdentification();
//            PersonalIdentification persistentPersonalIdentification = personalIdentificationDAO.getByClientAndType( client.getId(), transientIdentification.getIdType() );
//
//            
//            System.out.println( "persistentPersonalIdentification :: " + (persistentPersonalIdentification == null ? "NLL " : "NOT NULL") );
//            
//            if ( persistentPersonalIdentification != null ) {
//                
//                if ( transientIdentification.getIdentification() != null ) {
//                    persistentPersonalIdentification.setIdentification( transientIdentification.getIdentification() );
//                }
//                if ( transientIdentification.getState() != null ) {
//                    State state = stateDAO.getByAbbreviation( transientIdentification.getState().getAbbreviation() );
//                    if ( state != null ) {
//                        persistentPersonalIdentification.setState( state );
//                    }
//                }
//                if ( transientIdentification.getExpirationDate() != null ) {
//                    persistentPersonalIdentification.setExpirationDate( transientIdentification.getExpirationDate() );
//                }
//                if ( transientIdentification.getIdFront() != null ) {
//                    persistentPersonalIdentification.setIdFront( transientIdentification.getIdFront() );
//                }
//                if ( transientIdentification.getIdBack() != null ) {
//                    persistentPersonalIdentification.setIdBack( transientIdentification.getIdBack() );
//                }
//                
//                persistentPersonalIdentification.setClient( client );
//                
//                Set<PersonalIdentification> set  = new HashSet<PersonalIdentification>();
//                set.add( persistentPersonalIdentification);
//                
//                client.setData_SD( set );
//               // personalIdentificationDAO.saveOrUpdate( persistentPersonalIdentification );
//            } else {
//                if ( transientIdentification.getState() != null ) {
//                    System.out.println( "--------:: transientIdentification.getState().getAbbreviation() :: " + transientIdentification.getState().getAbbreviation() );
//                    State state = stateDAO.getByAbbreviation( transientIdentification.getState().getAbbreviation() );
//                    System.out.println( "STATE = " + (state == null ? "NLL" : "NOT NULL")  );
//                    if ( state != null ) {
//                        transientIdentification.setState( state );
//                    }
//                }
//                transientIdentification.setClient( client );
//              //  personalIdentificationDAO.saveOrUpdate( transientIdentification );
//            }
//
//        }
//        
//        System.out.println( ":: ClientManager :: cliet  = " + (client == null ? " NULL " : " NUT NULL") );
////        clientDAO.saveOrUpdate( client );
//        clientDAO.merge(client );
//    }

    public ResponseDataList searchClients(String searchFilter, int pageNumber, int rowsPerPage, Boolean blackList, Boolean optOut) throws Exception {


        return clientDAO.searchClients(searchFilter, pageNumber * rowsPerPage, rowsPerPage, blackList, optOut);

    }

    public PersonalIdentification getIdentificationByClientId(int idClient) throws SQLException {

        return personalIdentificationDAO.getByClientId(idClient);

    }

    public ResponseData updateClientBlackList(ClientDisplay clientDisplay) {
        return clientDAO.updateClientBlackList(clientDisplay);
    }

    public ResponseData updateClientOptOut(int id) {
        return clientDAO.updateClientOptOut(id);
    }
}
