/*
 ** File: ClientDAO.java
 **
 ** Date Created: March 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.common.CardBrand;
import com.smartbt.vtsuite.servercommon.display.common.model.AccountDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.AddressDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.AddressTypeDisplay;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.Client;
import com.smartbt.vtsuite.vtcommon.enums.ActivityFilter;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.servercommon.display.common.model.ClientDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.MerchantDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.PhoneTypeDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.StateDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TelephoneDisplay;
import static com.smartbt.vtsuite.servercommon.dao.ClientDAO.dao;
import com.smartbt.vtsuite.servercommon.model.Account;
import com.smartbt.vtsuite.servercommon.model.AddressType;
import com.smartbt.vtsuite.servercommon.model.Card;
import com.smartbt.vtsuite.servercommon.model.Check;
import com.smartbt.vtsuite.servercommon.model.ClientAddress;
import com.smartbt.vtsuite.servercommon.model.ClientTelephone;
import com.smartbt.vtsuite.servercommon.model.Country;
import com.smartbt.vtsuite.servercommon.model.State;
import com.smartbt.vtsuite.servercommon.model.TelephoneType;
import com.smartbt.vtsuite.vtcommon.enums.AccountType;
import static com.smartbt.vtsuite.vtcommon.enums.ActivityFilter.ACTIVE;
import static com.smartbt.vtsuite.vtcommon.enums.ActivityFilter.INACTIVE;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CUSTOMER;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.MERCHANT;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomAddressType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import static com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication.VT_MOBILE;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomTelephoneType;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 * @author Chris, Ariel Saavedra
 */
public class ClientDAO extends BaseDAO<Client> {

    public static final int MAX_FILE_SIZE = 3000;
    protected static ClientDAO dao;

    public ClientDAO() {
        // super(Client.class);
    }

    public static ClientDAO get() {
        if (dao == null) {
            dao = new ClientDAO();
        }
        return dao;
    }

    protected class AddressTypeDAO extends BaseDAO<AddressType> {
    }

    protected class PhoneTypeDAO extends BaseDAO<TelephoneType> {
    }

    public void updateWithStream(InputStream inputStream, int idMerchant) {
        byte[] byteArray = new byte[MAX_FILE_SIZE];
        String[] dataStrings, clientStrings;
        ClientDisplay client = null;
        List<AddressDisplay> addresses = null;
        AddressDisplay address1 = null, address2 = null;
        TelephoneDisplay clientPhone = null;
        MerchantDisplay merchant = new MerchantDisplay();
        merchant.setId(idMerchant);
        AddressTypeDisplay addressType1 = new AddressTypeDisplay(), addressType2 = new AddressTypeDisplay();
        PhoneTypeDisplay phoneType = new PhoneTypeDisplay();

        // default phone type parameters
        List<TelephoneDisplay> clientPhoneList = null;
        PhoneTypeDAO phoneTypeDao = new PhoneTypeDAO();
        TelephoneType phoneTypeDb = phoneTypeDao.findById(NomTelephoneType.MOBILE.getId());

        phoneType.setId(phoneTypeDb.getId());
        phoneType.setName(phoneTypeDb.getName());
        phoneType.setDescription(phoneTypeDb.getDescription());

        // default address type parameters
        AddressTypeDAO addressTypeDao = new AddressTypeDAO();
        AddressType addressTypeDb1 = addressTypeDao.findById(NomAddressType.BILLING.getId()),
                addressTypeDb2 = addressTypeDao.findById(NomAddressType.PERSONAL.getId());

        addressType1.setId(addressTypeDb1.getId());
        addressType1.setName(addressTypeDb1.getName());
        addressType1.setDescription(addressTypeDb1.getDescription());
        addressType2.setId(addressTypeDb2.getId());
        addressType2.setName(addressTypeDb2.getName());
        addressType2.setDescription(addressTypeDb2.getDescription());

        try {
            byteArray = Arrays.copyOf(byteArray, inputStream.read(byteArray));
            clientStrings = new String(byteArray).split("\n");
            for (String clientString : clientStrings) {
                dataStrings = clientString.split(",");
                for (int i = 0; i < dataStrings.length; i++) {
                    switch (i % 10) {
                        case 0:
                            client = new ClientDisplay();
                            addresses = new LinkedList<AddressDisplay>();
                            client.setClientAddressList(addresses);

                            clientPhoneList = new LinkedList<TelephoneDisplay>();
                            client.setClientTelephoneList(clientPhoneList);

                            client.setFirstName(dataStrings[i]);
                            break;
                        case 1:
                            client.setLastName(dataStrings[i]);
                            break;
                        case 2:
                            client.setCompany(dataStrings[i]);
                            break;
                        case 3:
                            address1 = new AddressDisplay();
                            address1.setAddress1(dataStrings[i]);
                            address2 = new AddressDisplay();
                            address2.setAddress1(dataStrings[i]);
                            break;
                        case 4:
                            address1.setAddress2(dataStrings[i]);
                            address2.setAddress2(dataStrings[i]);
                            break;
                        case 5:
                            address1.setCity(dataStrings[i]);
                            address2.setCity(dataStrings[i]);
                            break;
                        case 6:
                            address1.setState(GeneralDAO.get().stateByName(dataStrings[i]));
                            address2.setState(GeneralDAO.get().stateByName(dataStrings[i]));
                            break;
                        case 7:
                            address1.setZip(dataStrings[i]);
                            address1.setAddressType(addressType1);
                            addresses.add(address1);

                            address2.setZip(dataStrings[i]);
                            address2.setAddressType(addressType2);
                            addresses.add(address2);
                            break;
                        case 8:
                            clientPhone = new TelephoneDisplay();
                            clientPhone.setNumber(dataStrings[i]);
                            clientPhone.setTelephoneType(phoneType);
                            clientPhoneList.add(clientPhone);
                            break;
                        case 9:
                            client.setEmail(dataStrings[i]);
                            client.setActive(Boolean.TRUE);
                            client.setMerchant(merchant);
                            saveOrUpdateClient(client);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveOrUpdateClient(ClientDisplay clientDisplay) {
        Client client;
        if (clientDisplay.getId() != null && clientDisplay.getId() != 0) {
            client = findById(clientDisplay.getId());
        } else {
            client = new Client();
            client.setCreatedAt(new Date());
            client.setMerchant(MerchantDAO.get().findById(clientDisplay.getMerchant().getId()));
        }

        Session session = HibernateUtil.getSession();

        // Updating addresses
        Collection<ClientAddress> addresses = new LinkedList<ClientAddress>();
        for (AddressDisplay addressDisplay : clientDisplay.getClientAddressList()) {
            ClientAddress address = new ClientAddress();
            //address.setId(addressDisplay.getId());
            address.setAddress1(addressDisplay.getAddress1());
            address.setAddress2(addressDisplay.getAddress2());
            address.setCity(addressDisplay.getCity());
            address.setZip(addressDisplay.getZip());
            address.setClient(client);
            address.setCountry((Country) session.get(Country.class, 1));
            address.setState((State) session.get(State.class, addressDisplay.getState().getId()));
            address.setAddressType((AddressType) session.get(AddressType.class, addressDisplay.getAddressType().getId()));
            addresses.add(address);
        }
        if (addresses.size() == 1) {
            ClientAddress address = new ClientAddress();
            address.setClient(client);
            address.setAddress1(((List<ClientAddress>) addresses).get(0).getAddress1());
            address.setAddress2(((List<ClientAddress>) addresses).get(0).getAddress2());
            address.setCity(((List<ClientAddress>) addresses).get(0).getCity());
            address.setZip(((List<ClientAddress>) addresses).get(0).getZip());

            address.setCountry(((List<ClientAddress>) addresses).get(0).getCountry());
            address.setState(((List<ClientAddress>) addresses).get(0).getState());

            if (((List<ClientAddress>) addresses).get(0).getAddressType().getId() == NomAddressType.PERSONAL.getId()) {
                address.setAddressType((AddressType) session.get(AddressType.class, NomAddressType.BILLING.getId()));
            } else if (((List<ClientAddress>) addresses).get(0).getAddressType().getId() == NomAddressType.BILLING.getId()) {
                address.setAddressType((AddressType) session.get(AddressType.class, NomAddressType.PERSONAL.getId()));
            }
            addresses.add(address);
        }
        if (client.getClientAddress() != null) {
            for (ClientAddress clientAddress : client.getClientAddress()) {
                HibernateUtil.getSession().delete(clientAddress);
            }
        }
        //Updating Telephones
        Collection<ClientTelephone> telephones = new LinkedList<ClientTelephone>();
        if (clientDisplay.getClientTelephoneList() != null) {
            for (TelephoneDisplay telephoneDisplay : clientDisplay.getClientTelephoneList()) {
                ClientTelephone telephone = new ClientTelephone();
                //telephone.setId(telephoneDisplay.getId());
                telephone.setClient(client);
                telephone.setNumber(telephoneDisplay.getNumber().replace(" ", "").replace("(", "").replace(")", "").replace("-", ""));
                telephone.setTelephoneType((TelephoneType) session.get(TelephoneType.class, telephoneDisplay.getTelephoneType().getId()));
                telephones.add(telephone);
            }
        }
        if (client.getClientTelephone() != null) {
            for (ClientTelephone clientTelephone : client.getClientTelephone()) {
                HibernateUtil.getSession().delete(clientTelephone);
            }
        }
        //Adding new cards to client
        Collection<Account> cards = new LinkedList<Account>();
        if (clientDisplay.getAccounts() != null) {
            for (AccountDisplay accountDisplay : clientDisplay.getAccounts()) {
                Card newCard = new Card();
                newCard.setClient(client);
                newCard.setBillingZipCode(accountDisplay.getBillingZipCode());
                newCard.setCardHolderName(accountDisplay.getCardHolderName());
                newCard.setExpDate(accountDisplay.getExpDate());
                newCard.setEncryptedData(accountDisplay.getPan());
                cards.add(newCard);
            }
        }
        client.setFirstName(clientDisplay.getFirstName());
        client.setLastName(clientDisplay.getLastName());
        client.setEmail(clientDisplay.getEmail());
        client.setCompany(clientDisplay.getCompany());
        client.setActive(clientDisplay.getActive());

        client.setClientAddress(addresses);
        client.setClientTelephone(telephones);
        client.setAccount(cards);
        session.saveOrUpdate(client);
        clientDisplay.setId(client.getId());
    }

    public void deleteClient(int clientId) {
        Client client = findById(clientId);
        client.setActive(Boolean.FALSE);
        HibernateUtil.getSession().saveOrUpdate(client);
    }

    protected ClientDisplay clientDBToDisplay(Client clientDb) {
        ClientDisplay client = new ClientDisplay();

        client.setId(clientDb.getId());
        client.setFirstName(clientDb.getFirstName());
        client.setLastName(clientDb.getLastName());
        client.setEmail(clientDb.getEmail());
        client.setActive(clientDb.getActive());
        client.setCompany(clientDb.getCompany());

        MerchantDisplay merchantDisplay = new MerchantDisplay();
        merchantDisplay.setName(clientDb.getMerchant().getName());
        merchantDisplay.setId(clientDb.getMerchant().getId());
        client.setMerchant(merchantDisplay);

        // Set the addresses
        List<AddressDisplay> clientAddressList = new LinkedList<AddressDisplay>();
        for (ClientAddress clientAddressDb : clientDb.getClientAddress()) {
            AddressDisplay clientAddress = new AddressDisplay();

            clientAddress.setId(clientAddressDb.getId());
            clientAddress.setAddress1(clientAddressDb.getAddress1());
            clientAddress.setAddress2(clientAddressDb.getAddress2());
            clientAddress.setCity(clientAddressDb.getCity());
            clientAddress.setZip(clientAddressDb.getZip());

            AddressTypeDisplay addressType = new AddressTypeDisplay();
            addressType.setId(clientAddressDb.getAddressType().getId());
            addressType.setName(clientAddressDb.getAddressType().getName());
            addressType.setDescription(clientAddressDb.getAddressType().getDescription());
            clientAddress.setAddressType(addressType);

            StateDisplay state = new StateDisplay();
            state.setId(clientAddressDb.getState().getId());
            state.setAbbreviation(clientAddressDb.getState().getAbbreviation());
            state.setName(clientAddressDb.getState().getName());
            clientAddress.setState(state);

            clientAddressList.add(clientAddress);
        }
        client.setClientAddressList(clientAddressList);

        // Set the phone numbers
        List<TelephoneDisplay> clientPhoneList = new LinkedList<TelephoneDisplay>();
        for (ClientTelephone clientPhoneDb : clientDb.getClientTelephone()) {
            TelephoneDisplay clientPhone = new TelephoneDisplay();

            clientPhone.setId(clientPhoneDb.getId());
            clientPhone.setNumber(clientPhoneDb.getNumber());

            // set the phone type
            PhoneTypeDisplay clientPhoneType = new PhoneTypeDisplay();
            clientPhoneType.setId(clientPhoneDb.getTelephoneType().getId());
            clientPhoneType.setName(clientPhoneDb.getTelephoneType().getName());
            clientPhoneType.setDescription(clientPhoneDb.getTelephoneType().getDescription());
            clientPhone.setTelephoneType(clientPhoneType);

            clientPhoneList.add(clientPhone);
        }
        client.setClientTelephoneList(clientPhoneList);
        // Set the accounts
//        List<AccountDisplay> accounts = new LinkedList<AccountDisplay>();
//        for (Account accountDB : clientDb.getAccount()) {
//            AccountDisplay accountDisplay = new AccountDisplay();
//            accountDisplay.setId(accountDB.getId());
//            accountDisplay.setPan(accountDB.getEncryptedData().substring(accountDB.getEncryptedData().length() - 4));
//            
//            accounts.add(accountDisplay);
//        }
//        client.setAccounts(accounts);

        return client;
    }

    public ClientDisplay getClient(int clientId) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Client.class).add(Restrictions.idEq(clientId));
        Client clientDb = (Client) criteria.uniqueResult();
        return clientDBToDisplay(clientDb);
    }

    public List<AccountDisplay> getClientAccounts(int clientId, AccountType accountType) {
        List<AccountDisplay> accountDisplays = new LinkedList<AccountDisplay>();
        if (clientId != 0) {
            Client client = (Client) HibernateUtil.getSession().get(Client.class, clientId);
            if (client != null) {
                for (Account account : client.getAccount()) {
                    AccountDisplay accountDisplay = new AccountDisplay();
                    accountDisplay.setId(account.getId());
                    accountDisplay.setPan(account.getEncryptedData().substring(account.getEncryptedData().length() - 4));
                    accountDisplay.setCardBrand(CardBrand.infer(account.getEncryptedData()).toString());
                    if (account instanceof Card && AccountType.CARD == accountType) {
                        accountDisplay.setCardHolderName(((Card) account).getCardHolderName());
                        accountDisplay.setExpDate(((Card) account).getExpDate());
                        accountDisplay.setBillingZipCode(((Card) account).getBillingZipCode());
                        accountDisplays.add(accountDisplay);
                    }
                    if (account instanceof Check && AccountType.CHECK == accountType) {
                        accountDisplay.setCheckNumber(((Check) account).getCheckNumber());
                        accountDisplay.setDate(((Check) account).getDate());
                        accountDisplays.add(accountDisplay);
                    }
                }
            }
        }
        return accountDisplays;
    }

    public void addAccountToClient(ClientDisplay clientDisplay, AccountDisplay accountDisplay) {
        Client client = findById(clientDisplay.getId());
        Account account = (Account) HibernateUtil.getSession().get(Account.class, accountDisplay.getId());
        account.setClient(client);
        client.getAccount().add(account);
        saveOrUpdate(client);
    }

    public void deleteAllClients(int id, EntityType entityType) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Client.class);
        switch (entityType) {
            case CUSTOMER:
                criteria.createAlias("merchant", "merchant");
                criteria.createAlias("merchant.customer", "customer");
                criteria.add(Restrictions.eq("customer.id", id));
                criteria.add(Restrictions.eq("active", true));
                break;
            case MERCHANT:
                criteria.createAlias("merchant", "merchant");
                criteria.add(Restrictions.eq("merchant.id", id));
                criteria.add(Restrictions.eq("active", true));
                break;
        }
        List<Client> clients = criteria.list();
        for (Client client : clients) {
            client.setActive(Boolean.FALSE);
            HibernateUtil.getSession().saveOrUpdate(client);
        }
    }

    public List<ClientDisplay> searchClients(int id, EntityType entityType, String searchFilter, ActivityFilter activityFilter, int firstResult, int maxResult, NomApplication application) {
        List<ClientDisplay> clients = new LinkedList<ClientDisplay>();

        Criteria criteria = HibernateUtil.getSession().createCriteria(Client.class).addOrder(Order.desc("createdAt"));
        switch (entityType) {
            case CUSTOMER:
                criteria.createAlias("merchant", "merchant");
                criteria.createAlias("merchant.customer", "customer");
                criteria.add(Restrictions.eq("customer.id", id));
                break;
            case MERCHANT:
                criteria.createAlias("merchant", "merchant");
                criteria.add(Restrictions.eq("merchant.id", id));
                break;
        }
        if (activityFilter != null) {
            switch (activityFilter) {
                case ACTIVE:
                    criteria.add(Restrictions.eq("active", true));
                    break;
                case INACTIVE:
                    criteria.add(Restrictions.eq("active", false));
                    break;
            }
        }
        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }

        if (searchFilter != null && !searchFilter.isEmpty()) {
            criteria.createAlias("clientTelephone", "clientTelephone", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("clientAddress", "clientAddress");
            criteria.createAlias("clientAddress.state", "state");
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("firstName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("lastName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("email", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("company", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("clientTelephone.number", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("state.name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("clientAddress.zip", searchFilter, MatchMode.ANYWHERE).ignoreCase());

            if (application == NomApplication.VT_AMS || application == NomApplication.VT_APPLICATION) {
                disjunction.add(Restrictions.like("state.abbreviation", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                        .add(Restrictions.like("clientAddress.address1", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                        .add(Restrictions.like("clientAddress.address2", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                        .add(Restrictions.like("clientAddress.city", searchFilter, MatchMode.ANYWHERE).ignoreCase());
            }

            criteria.add(disjunction);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        }

        List<Client> clientsDb = criteria.list();
        for (Client clientDb : clientsDb) {
            ClientDisplay client = clientDBToDisplay(clientDb);
            clients.add(client);
        }
        return clients;
    }
}
