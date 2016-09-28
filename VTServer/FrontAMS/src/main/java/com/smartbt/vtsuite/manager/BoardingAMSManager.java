package com.smartbt.vtsuite.manager;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.dao.AddressTypeDAO;
import com.smartbt.vtsuite.dao.ApplicationParameterValueDAO;
import com.smartbt.vtsuite.dao.ClerkRoleDAO;
import com.smartbt.vtsuite.dao.CountryDAO;
import com.smartbt.vtsuite.servercommon.dao.ApplicationDAO;

import com.smartbt.vtsuite.servercommon.dao.CustomerDAO;
import com.smartbt.vtsuite.servercommon.dao.HostDAO;
import com.smartbt.vtsuite.dao.HostModeDAO;
import com.smartbt.vtsuite.dao.MerchantParameterDAO;
import com.smartbt.vtsuite.dao.MerchantParameterValueDAO;
import com.smartbt.vtsuite.dao.StateDAO;
import com.smartbt.vtsuite.dao.TelephoneTypeDAO;
import com.smartbt.vtsuite.dao.TerminalHostParameterDAO;
import com.smartbt.vtsuite.dao.TerminalParameterValueDAO;
import com.smartbt.vtsuite.servercommon.dao.SystemPropertyDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.email.EmailUtils;
import com.smartbt.girocheck.servercommon.i18n.I18N;
import com.smartbt.girocheck.servercommon.i18n.Messages;
import com.smartbt.vtsuite.servercommon.model.Application;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.ClerkRole;
import com.smartbt.vtsuite.servercommon.model.Customer;
import com.smartbt.vtsuite.servercommon.model.Host;
import com.smartbt.vtsuite.servercommon.model.HostMode;
import com.smartbt.vtsuite.servercommon.model.HostModeMerchant;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.MerchantAddress;
import com.smartbt.vtsuite.servercommon.model.MerchantHost;
import com.smartbt.vtsuite.servercommon.model.MerchantHostParameter;
import com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue;
import com.smartbt.vtsuite.servercommon.model.MerchantParameter;
import com.smartbt.vtsuite.servercommon.model.MerchantParameterValue;
import com.smartbt.vtsuite.servercommon.model.MerchantTelephone;
import com.smartbt.vtsuite.servercommon.model.Telephone;
import com.smartbt.vtsuite.servercommon.model.TelephoneType;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.model.TerminalHost;
import com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue;
import com.smartbt.vtsuite.servercommon.model.TerminalParameterValue;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.validators.BoardingAMSValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomCountry;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomMerchantParameter;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomSystemProperties;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomTerminalHostParameter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;

/**
 * @author Roberto Rodriguez :: < roberto.rodriguez@smartbt.com >
 */
public class BoardingAMSManager {

    private BoardingAMSValidator boardingValidator = new BoardingAMSValidator();

    @Transactional
    public BaseResponse saveOrUpdateCustomer(Customer customer) throws ValidationException {

        List<String> merchantNumberList = new ArrayList<>();
        List<String> clerkUserNameList = new ArrayList<>();
        List<String> terminalIdList = new ArrayList<>();
        Map<String, String> userPasswList = new HashMap<String, String>();

        for (Merchant transientMerchant : customer.getMerchant()) {

            boardingValidator.validateDontInsertRepeteadMerchant(merchantNumberList, transientMerchant);

            boolean isNewMerchant = transientMerchant.getId() == 0;

            Merchant persistentMerchant = merchantCompleteNullFields(transientMerchant);

            if (transientMerchant.getClerk() != null) {
                for (Clerk transientClerk : transientMerchant.getClerk()) {

                    boardingValidator.validateDontInsertRepeteadClerkUserName(clerkUserNameList, transientClerk);

                    Clerk persistentClerk = clerkCompleteNullFields(userPasswList, transientClerk);

                    persistentClerk.setMerchant(persistentMerchant);
                    if (!isNewMerchant) {
                        persistentMerchant.getClerk().add(persistentClerk);
                    }
                }
            }

            if (transientMerchant.getTerminal() != null) {
                for (Terminal transientTerminal : transientMerchant.getTerminal()) {

                    boardingValidator.validateDontInsertRepeteadTerminalId(terminalIdList, transientTerminal);
                    boolean isNewTerminal = transientTerminal.getId() == 0;
                    Terminal persistentTerminal = terminalCompleteNullFields(transientTerminal);
                    persistentTerminal.setMerchant(persistentMerchant);

                    if (transientTerminal.getClerk() != null) {
                        for (Clerk transientClerk : transientTerminal.getClerk()) {

                            boardingValidator.validateDontInsertRepeteadClerkUserName(clerkUserNameList, transientClerk);

                            Clerk persistentClerk = clerkCompleteNullFields(userPasswList, transientClerk);

                            persistentClerk.setTerminal(persistentTerminal);
                            if (!isNewTerminal) {
                                persistentTerminal.getClerk().add(persistentClerk);
                            }
                        }
                    }

                    persistentTerminal.setMerchant(persistentMerchant);

                    if (isNewTerminal) {
                        terminalCompleteHostParameters(persistentTerminal);
                    }

                    if (!isNewMerchant) {
                        persistentMerchant.getTerminal().add(persistentTerminal);
                    }
                    updateTerminalParameterValues(persistentTerminal, isNewTerminal);
                }
            }

            if (isNewMerchant) {
                merchantCompleteHostParameters(persistentMerchant);
            }
            updateMerchantParameterValue(transientMerchant, persistentMerchant, isNewMerchant);

            HibernateUtil.getSession().saveOrUpdate(persistentMerchant);
        }

        try {
            sendEmailToNewClerks(userPasswList);
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw new ValidationException(I18N.get(Messages.ERROR_SENDING_EMAIL));
        }

        BaseResponse response = new BaseResponse();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    private Merchant merchantCompleteNullFields(Merchant transientMerchant) throws ValidationException {

        Merchant persistentMerchant = (Merchant) HibernateUtil.getSession().get(Merchant.class, transientMerchant.getId());

        if (persistentMerchant == null) {
            boardingValidator.validateNewMerchant(transientMerchant);

            Customer customer = CustomerDAO.get().findById(1);
            transientMerchant.setCustomer(customer);
            transientMerchant.setActivationDate(new Date());

            transientMerchant.setAliveSessionTime(600);
            transientMerchant.setActive(true);
        } else {
            boardingValidator.validateUpdateMerchant(persistentMerchant);
        }

        Merchant merchantPointer = (persistentMerchant == null) ? transientMerchant : persistentMerchant;

        //-----------  filling MERCHANT_ADDRESS ------------------
        if (transientMerchant.getMerchantAddress() != null) {
            for (MerchantAddress merchantAddress : transientMerchant.getMerchantAddress()) {
                int idAddressType = (merchantAddress.getAddressType() == null || merchantAddress.getAddressType().getId() == 0) ? 1 : merchantAddress.getAddressType().getId();
                merchantAddress.setAddressType(AddressTypeDAO.get().findById(idAddressType));
                int idState = (merchantAddress.getState() == null || merchantAddress.getState().getId() == 0) ? 1 : merchantAddress.getState().getId();
                merchantAddress.setState(StateDAO.get().findById(idState));
                int idCountry = (merchantAddress.getCountry() == null || merchantAddress.getCountry().getId() == 0) ? NomCountry.EUA.getId() : merchantAddress.getCountry().getId();
                merchantAddress.setCountry(CountryDAO.get().findById(idCountry));

                merchantAddress.setMerchant(merchantPointer);

                if (persistentMerchant != null) {
                    if (merchantAddress.getId() == 0) { // if is a new address
                        persistentMerchant.getMerchantAddress().add(merchantAddress);
                    } else {
                        for (MerchantAddress ma : persistentMerchant.getMerchantAddress()) {
                            if (ma.getId() == merchantAddress.getId()) {
                                ma.setAddressType(merchantAddress.getAddressType());
                                ma.setState(merchantAddress.getState());
                                ma.setCountry(merchantAddress.getCountry());
                                ma.setAddress1(merchantAddress.getAddress1());
                                ma.setAddress2(merchantAddress.getAddress2());
                                //ma.setCity(merchantAddress.getCity());
                                ma.setZip(merchantAddress.getZip());
                                ma.setMerchant(merchantPointer);
                            }
                        }
                    }
                }

            }
        }

        //-----------  filling MERCHANT_TELEPHONE ------------------
        if (transientMerchant.getMerchantTelephone() != null) {
            for (MerchantTelephone merchantTelephone : transientMerchant.getMerchantTelephone()) {
                int idTelephoneType = (((Telephone) merchantTelephone).getTelephoneType() == null || ((Telephone) merchantTelephone).getTelephoneType().getId() == 0)
                        ? 1 : ((Telephone) merchantTelephone).getTelephoneType().getId();
                TelephoneType telephoneType = TelephoneTypeDAO.get().findById(idTelephoneType);
                if (telephoneType != null) {
                    merchantTelephone.setTelephoneType(telephoneType);
                }

                merchantTelephone.setMerchant(merchantPointer);

                if (persistentMerchant != null) {
                    if (merchantTelephone.getId() == 0) { // if is a new address
                        persistentMerchant.getMerchantTelephone().add(merchantTelephone);
                    } else {
                        for (MerchantTelephone ma : persistentMerchant.getMerchantTelephone()) {
                            if (ma.getId() == merchantTelephone.getId()) {
                                ma.setTelephoneType(merchantTelephone.getTelephoneType());
                                ma.setNumber(merchantTelephone.getNumber());
                                ma.setMerchant(merchantPointer);
                            }
                        }
                    }
                }
            }
        }

        //-------------------------------------------------------
        if (persistentMerchant == null) {
            return transientMerchant;
        }

        if (transientMerchant.getNumber() != null) {
            persistentMerchant.setNumber(transientMerchant.getNumber());
        }
        if (transientMerchant.getName() != null) {
            persistentMerchant.setName(transientMerchant.getName());
        }
        if (transientMerchant.getDescription() != null) {
            persistentMerchant.setDescription(transientMerchant.getDescription());
        }
        if (transientMerchant.getActive() != null) {
            persistentMerchant.setActive(transientMerchant.getActive());
        }
        if (transientMerchant.getMonitor() != null) {
            persistentMerchant.setMonitor(transientMerchant.getMonitor());
        }
        if (transientMerchant.getDeactivationDate() != null) {
            persistentMerchant.setDeactivationDate(transientMerchant.getDeactivationDate());
        }
        if (transientMerchant.getSic() != null) {
            persistentMerchant.setSic(transientMerchant.getSic());
        }
        if (transientMerchant.getIndustryType() != null) {
            persistentMerchant.setIndustryType(transientMerchant.getIndustryType());
        }
        if (transientMerchant.getAliveSessionTime() != null) {
            persistentMerchant.setAliveSessionTime(transientMerchant.getAliveSessionTime());
        }
        if (transientMerchant.getLogo() != null) {
            persistentMerchant.setLogo(transientMerchant.getLogo());
        }

        persistentMerchant.setTerminal(new ArrayList<Terminal>());
        persistentMerchant.setClerk(new ArrayList<Clerk>());
        persistentMerchant.setMerchantParameterValue(new ArrayList<MerchantParameterValue>());
        persistentMerchant.setHostModeMerchant(new ArrayList<HostModeMerchant>());
        persistentMerchant.setMerchantHost(new ArrayList<MerchantHost>());
        return persistentMerchant;
    }

    private Terminal terminalCompleteNullFields(Terminal transientTerminal) throws ValidationException {

        Terminal persistentTerminal = (Terminal) HibernateUtil.getSession().get(Terminal.class, transientTerminal.getId());

        if (persistentTerminal == null) { // if Iam inserting a new Terminal
            boardingValidator.validateNewTerminal(transientTerminal);
            transientTerminal.setMonitored(false);
            transientTerminal.setActivationDate(new Date());
        } else {
            boardingValidator.validateUpdateTerminal(transientTerminal);
        }

        Terminal terminalPointer = (persistentTerminal == null) ? transientTerminal : persistentTerminal;

        if (transientTerminal.getApplication() != null && transientTerminal.getApplication().getId() != 0) {
            Application persistentApplication = ApplicationDAO.get().findById(transientTerminal.getApplication().getId());
            terminalPointer.setApplication(persistentApplication);
        }

        if (persistentTerminal == null) {
            return transientTerminal;
        }

        //----------------------- FILLING NULL VALUES ------------------------------------------------------------
        if (transientTerminal.getTerminalId() != null) {
            persistentTerminal.setTerminalId(transientTerminal.getTerminalId());
        }
        if (transientTerminal.getSerialNumber() != null) {
            persistentTerminal.setSerialNumber(transientTerminal.getSerialNumber());
        }
        if (transientTerminal.getDescription() != null) {
            persistentTerminal.setDescription(transientTerminal.getDescription());
        }
        if (transientTerminal.getActive() != null) {
            persistentTerminal.setActive(transientTerminal.getActive());
        }

        if (transientTerminal.getStartedMonitorAt() != null) {
            persistentTerminal.setStartedMonitorAt(transientTerminal.getStartedMonitorAt());
        }
        if (transientTerminal.getActivationDate() != null) {
            persistentTerminal.setActivationDate(transientTerminal.getActivationDate());
        }
        if (transientTerminal.getDeactivationDate() != null) {
            persistentTerminal.setDeactivationDate(transientTerminal.getDeactivationDate());
        }

//        if (transientTerminal.getProductType() != null && transientTerminal.getProductType().getId() != 0) {
//            if (persistentTerminal.getProductType().getId() != transientTerminal.getProductType().getId()) {
//                ProductType productType = ProductTypeDAO.get().findById(transientTerminal.getProductType().getId());
//                persistentTerminal.setProductType(productType);
//            }
//        }
        persistentTerminal.setClerk(new ArrayList<Clerk>());
        persistentTerminal.setTerminalParameterValues(new ArrayList<TerminalParameterValue>());
        return persistentTerminal;
    }

    private Clerk clerkCompleteNullFields(Map<String, String> userPasswList, Clerk transientClerk) throws ValidationException {

        Clerk persistentClerk = (Clerk) HibernateUtil.getSession().get(Clerk.class, transientClerk.getId());

        if (persistentClerk == null) {
            boardingValidator.validateClerk(transientClerk);

            transientClerk.setFirstTime(true);
            transientClerk.setActive(Boolean.TRUE);
            if (transientClerk.getClerkRole() != null && transientClerk.getClerkRole().getId() != 0) {
                ClerkRole clerkRole = ClerkRoleDAO.get().findById(transientClerk.getClerkRole().getId());
                transientClerk.setClerkRole(clerkRole);
            }

            userPasswList.put(transientClerk.getUsername(), transientClerk.getPassword());
        }

        String encryptPassword = "";
        if (transientClerk.getPassword() != null) {
            Clerk clerkPointer = (persistentClerk == null) ? transientClerk : persistentClerk;

            try {
                encryptPassword = LoginAMSManager.encryptPassword(transientClerk.getPassword());
                clerkPointer.setPassword(encryptPassword);
            } catch (NoSuchAlgorithmException ex) {
                throw new ValidationException(VTSuiteMessages.CANNOT_ENCRYPT_NULL_PASSWORD);
            }
        }

        if (persistentClerk == null) {
            return transientClerk;
        }

        if (transientClerk.getUsername() != null) {
            persistentClerk.setUsername(transientClerk.getUsername());
        }
        if (transientClerk.getPassword() != null) {
            persistentClerk.setPassword(encryptPassword);
        }

        if (transientClerk.getFirstName() != null) {
            persistentClerk.setFirstName(transientClerk.getFirstName());
        }
        if (transientClerk.getLastName() != null) {
            persistentClerk.setLastName(transientClerk.getLastName());
        }
        if (transientClerk.getActive() != null) {
            persistentClerk.setActive(transientClerk.getActive());
        }
        if (transientClerk.getMiddleInitial() != null) {
            persistentClerk.setMiddleInitial(transientClerk.getMiddleInitial());
        }
        if (transientClerk.getDeactivationDate() != null) {
            persistentClerk.setDeactivationDate(transientClerk.getDeactivationDate());
        }
        if (transientClerk.getClerkRole() != null && transientClerk.getClerkRole().getId() != 0) {
            if (persistentClerk.getClerkRole().getId() != transientClerk.getClerkRole().getId()) {
                ClerkRole clerkRole = ClerkRoleDAO.get().findById(transientClerk.getClerkRole().getId());

                persistentClerk.setClerkRole(clerkRole);
            }

        }

        return persistentClerk;
    }

    private void merchantCompleteHostParameters(Merchant persistentMerchant) {
        //------ SAVING HOST_MODE_MERCHANT ------------

        List<HostMode> hostModeList = HostModeDAO.get().list();
        HostModeMerchant hostModeMerchant;
        for (HostMode hostMode : hostModeList) {
            hostModeMerchant = new HostModeMerchant();
            hostModeMerchant.setHostMode(hostMode);
            hostModeMerchant.setMerchant(persistentMerchant);
            persistentMerchant.getHostModeMerchant().add(hostModeMerchant);

        }

        //------ SAVING MERCHANT_HOST --------------------
        Host host = HostDAO.get().findById(NomHost.ISTREAM.getId());

        MerchantHost merchantHost;
        MerchantHostParameterValue merchantHostParameterValue;

        merchantHost = new MerchantHost();
        merchantHost.setMerchant(persistentMerchant);
        merchantHost.setHost(host);
        merchantHost.setHostCapture(true);
        for (MerchantHostParameter merchantHostParameter : host.getMerchantHostParameters()) {
            merchantHostParameterValue = new MerchantHostParameterValue();
            merchantHostParameterValue.setMerchantHostParameters(merchantHostParameter);
            merchantHostParameterValue.setValue(persistentMerchant.getNumber());
            merchantHostParameterValue.setMerchantHost(merchantHost);

            merchantHost.getMerchantHostParameterValue().add(merchantHostParameterValue);

        }

        persistentMerchant.getMerchantHost().add(merchantHost);
    }

    public void updateMerchantParameterValue(Merchant transientMerchant, Merchant merchantPointer, boolean isNewMerchant) {
        //-----------  MERCHANT PARAMETER VALUE -----------------

        MerchantParameterValue parameterValue = new MerchantParameterValue();

        if (transientMerchant.getName() != null) {

            if (!isNewMerchant) {
                MerchantParameterValueDAO.get().removeMerchantParameterValueByIdMerchant_IdMerchantParameter(merchantPointer.getId(), NomMerchantParameter.HEADER_LINE_1.getId());
            }

            parameterValue.setValue(transientMerchant.getName());
            parameterValue.setMerchant(merchantPointer);
            parameterValue.setMerchantParameter(MerchantParameterDAO.get().findById(NomMerchantParameter.HEADER_LINE_1.getId()));
            merchantPointer.getMerchantParameterValue().add(parameterValue);
        }

        if (isNewMerchant) {
            parameterValue = new MerchantParameterValue();
            parameterValue.setValue("-----");
            parameterValue.setMerchant(merchantPointer);
            parameterValue.setMerchantParameter(MerchantParameterDAO.get().findById(NomMerchantParameter.HEADER_LINE_2.getId()));
            merchantPointer.getMerchantParameterValue().add(parameterValue);
        }

        if (!transientMerchant.getMerchantAddress().isEmpty()) {
            if (!isNewMerchant) {
                MerchantParameterValueDAO.get().removeMerchantParameterValueByIdMerchant_IdMerchantParameter(merchantPointer.getId(), NomMerchantParameter.HEADER_LINE_3.getId());
                MerchantParameterValueDAO.get().removeMerchantParameterValueByIdMerchant_IdMerchantParameter(merchantPointer.getId(), NomMerchantParameter.HEADER_LINE_4.getId());
            }

            MerchantParameter merchantParameter3 = MerchantParameterDAO.get().findById(NomMerchantParameter.HEADER_LINE_3.getId());
            MerchantParameter merchantParameter4 = MerchantParameterDAO.get().findById(NomMerchantParameter.HEADER_LINE_4.getId());
            for (MerchantAddress merchantAddress : merchantPointer.getMerchantAddress()) {
                parameterValue = new MerchantParameterValue();
                parameterValue.setValue(merchantAddress.getAddress1());
                parameterValue.setMerchant(merchantPointer);
                parameterValue.setMerchantParameter(merchantParameter3);
                merchantPointer.getMerchantParameterValue().add(parameterValue);

                parameterValue = new MerchantParameterValue();
                String address = merchantAddress.getZip() + ", " + merchantAddress.getState().getAbbreviation();
                parameterValue.setValue(address);
                parameterValue.setMerchant(merchantPointer);
                parameterValue.setMerchantParameter(merchantParameter4);
                merchantPointer.getMerchantParameterValue().add(parameterValue);
            }
        }

        if (!transientMerchant.getMerchantTelephone().isEmpty()) {
            if (!isNewMerchant) {
                MerchantParameterValueDAO.get().removeMerchantParameterValueByIdMerchant_IdMerchantParameter(merchantPointer.getId(), NomMerchantParameter.HEADER_LINE_5.getId());
            }

            MerchantParameter merchantParameter5 = MerchantParameterDAO.get().findById(NomMerchantParameter.HEADER_LINE_5.getId());
            for (MerchantTelephone merchantTelephone : merchantPointer.getMerchantTelephone()) {
                parameterValue = new MerchantParameterValue();
                parameterValue.setValue(merchantTelephone.getNumber());
                parameterValue.setMerchant(merchantPointer);
                parameterValue.setMerchantParameter(merchantParameter5);
                merchantPointer.getMerchantParameterValue().add(parameterValue);
            }
        }

        if (isNewMerchant) {
            parameterValue = new MerchantParameterValue();
            parameterValue.setValue(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_1.getId()).getDefaultValue());
            parameterValue.setMerchant(merchantPointer);
            parameterValue.setMerchantParameter(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_1.getId()));
            merchantPointer.getMerchantParameterValue().add(parameterValue);

            parameterValue = new MerchantParameterValue();
            parameterValue.setValue(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_2.getId()).getDefaultValue());
            parameterValue.setMerchant(merchantPointer);
            parameterValue.setMerchantParameter(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_2.getId()));
            merchantPointer.getMerchantParameterValue().add(parameterValue);

            parameterValue = new MerchantParameterValue();
            parameterValue.setValue(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_3.getId()).getDefaultValue());
            parameterValue.setMerchant(merchantPointer);
            parameterValue.setMerchantParameter(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_3.getId()));
            merchantPointer.getMerchantParameterValue().add(parameterValue);
        }
    }

    private void terminalCompleteHostParameters(Terminal persistentTerminal) {
        //------ SAVING TERMINAL_HOST --------------------

        Host host = HostDAO.get().findById(NomHost.ISTREAM.getId());

        TerminalHostParameterValue terminalHostParameterValue;

        TerminalHost terminalHost = new TerminalHost();
        terminalHost.setTerminal(persistentTerminal);
        terminalHost.setHost(host);
        terminalHost.setTerminalCapture(true);

        terminalHostParameterValue = new TerminalHostParameterValue();
        terminalHostParameterValue.setTerminalHostParameter(TerminalHostParameterDAO.get().findById((NomTerminalHostParameter.WORLDPAY_SEQNUM.getId())));
        terminalHostParameterValue.setValue("0");
        terminalHostParameterValue.setTerminalHost(terminalHost);
        terminalHost.getTerminalHostParameterValue().add(terminalHostParameterValue);

        terminalHostParameterValue = new TerminalHostParameterValue();
        terminalHostParameterValue.setTerminalHostParameter(TerminalHostParameterDAO.get().findById((NomTerminalHostParameter.WORLDPAY_CHK.getId())));
        terminalHostParameterValue.setValue("8");
        terminalHostParameterValue.setTerminalHost(terminalHost);
        terminalHost.getTerminalHostParameterValue().add(terminalHostParameterValue);

        terminalHostParameterValue = new TerminalHostParameterValue();
        terminalHostParameterValue.setTerminalHostParameter(TerminalHostParameterDAO.get().findById((NomTerminalHostParameter.WORLDPAY_TERMINALID.getId())));
        terminalHostParameterValue.setValue(persistentTerminal.getTerminalId());
        terminalHostParameterValue.setTerminalHost(terminalHost);
        terminalHost.getTerminalHostParameterValue().add(terminalHostParameterValue);

        persistentTerminal.getTerminalHost().add(terminalHost);
    }

    public void updateTerminalParameterValues(Terminal persistentTerminal, boolean isNewTerminal) {
        //----------------------- UPDATING TERMINAL_PARAMETER_VALUE for this terminal -----------------------------------------------------------
        if (persistentTerminal.getApplication() != null) {
            if (!isNewTerminal) {
                TerminalParameterValueDAO.get().removeTerminalParameterValueByIdTerminal(persistentTerminal.getId());
            }

            Application persistentApplication = persistentTerminal.getApplication();

            List<TerminalParameterValue> listTerminalParameterValue = new ArrayList<TerminalParameterValue>();
            List<ApplicationParameterValue> list = ApplicationParameterValueDAO.get().getApplicationParameterValueByIdApplication(persistentApplication.getId());

            TerminalParameterValue terminalParameterValue;
            for (ApplicationParameterValue applicationParameterValue : list) {
                terminalParameterValue = new TerminalParameterValue();
                terminalParameterValue.setApplicationParameter(applicationParameterValue.getApplicationParameter());
                terminalParameterValue.setValue(applicationParameterValue.getValue());
                terminalParameterValue.setTerminal(persistentTerminal);

                listTerminalParameterValue.add(terminalParameterValue);
            }
            persistentTerminal.setTerminalParameterValues(listTerminalParameterValue);
        }
    }

    public void sendEmailToNewClerks(Map<String, String> userPasswList) throws Exception {
        String receiptTittle = I18N.get(Messages.AMS_BOARDING_EMAIL_TITTLE);

        String server_address = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_ADDRESS.getViewValue());
        String server_port = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_PORT.getViewValue());
        String server_username = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_USERNAME.getViewValue());
        String server_password = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_PASSWORD.getViewValue());
        String server_from_address = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_FROM_ADDRESS.getViewValue());
        String email_debug_setting = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_DEBUG.getViewValue());

        boolean email_debug = false;

        if (email_debug_setting != null && email_debug_setting.toLowerCase().compareTo("true") == 0) {
            email_debug = true;
        }

        for (Map.Entry<String, String> entry : userPasswList.entrySet()) {
            String userName = entry.getKey();
            String password = entry.getValue();

            String[] recipients = new String[]{userName};

            EmailUtils email;

            if (server_username != null && !server_username.isEmpty()) {
                email = new EmailUtils(server_address, server_port, server_username, server_password);
            } else {
                email = new EmailUtils(server_address, server_port);
            }

            email.setMessage("<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=Shift_JIS\"><style type=\"text/css\">body{border:0px none;}</style></head><body>" + I18N.get(Messages.AMS_BOARDING_EMAIL_BODY, "<br/>", userName, password) + "</body></html>", "text/html; charset=Shift_JIS");

        }

    }
}
