/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servercommon.dao.UtilsDAO;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.TransactionDAO;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue;
import com.smartbt.vtsuite.servercommon.model.MerchantParameterValue;
import com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomClerkRole;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomMerchantHostParameter;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomParameterGroup;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomTerminalHostParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Carlos
 */
public class TransactionUtilsDAO {

    protected static TransactionUtilsDAO dao;

    public TransactionUtilsDAO() {
    }

    public static TransactionUtilsDAO get() {
        if (dao == null) {
            dao = new TransactionUtilsDAO();
        }
        return dao;
    }

    /**
     * Gets terminalId for respective terminal/host.
     *
     * @param idTransaction The transaction id
     * @return terminalId The terminal id
     *
     */
    public String getTerminalHostIdFromTransactionId(Integer idTransaction) {
        String terminalId = "";
        TerminalHostParameterValue terminalHostParameterValue = (TerminalHostParameterValue) HibernateUtil.getSession().createCriteria(TerminalHostParameterValue.class, "TerminalHostParameterValue").
                createAlias("TerminalHostParameterValue.terminalHost", "TerminalHost").
                createAlias("TerminalHostParameterValue.terminalHostParameter", "TerminalHostParameter").
                createAlias("TerminalHost.terminal", "Terminal").
                createAlias("TerminalHost.host", "Host").
                createAlias("Terminal.transaction", "Transaction").
                add(Restrictions.eq("Host.name", NomHost.ISTREAM).ignoreCase()).
                add(Restrictions.eq("TerminalHostParameter.parameter", NomTerminalHostParameter.WORLDPAY_TERMINALID).ignoreCase()).
                add(Restrictions.eq("Transaction.id", idTransaction)).uniqueResult();

        if (terminalHostParameterValue != null) {
            terminalId = terminalHostParameterValue.getValue();
        }

        return terminalId;
    }

    /**
     * Gets merchant number for respective merchant/host.
     *
     * @param idTransaction The transaction id
     * @return merchant number The merchant id
     *
     */
    public String getMerchantHostIdFromTransactionId(Integer idTransaction) {
        String merchantId = "";
        MerchantHostParameterValue merchantHostParameterValue = (MerchantHostParameterValue) HibernateUtil.getSession().createCriteria(MerchantHostParameterValue.class, "MerchantHostParameterValue").
                createAlias("MerchantHostParameterValue.merchantHost", "MerchantHost").
                createAlias("MerchantHostParameterValue.merchantHostParameters", "MerchantHostParameter").
                createAlias("MerchantHost.merchant", "Merchant").
                createAlias("MerchantHost.host", "Host").
                createAlias("Merchant.terminal", "Terminal").
                createAlias("Terminal.transaction", "Transaction").
                add(Restrictions.eq("Host.name", NomHost.ISTREAM).ignoreCase()).
                add(Restrictions.eq("MerchantHostParameter.parameter", NomMerchantHostParameter.WORLDPAY_MERCHANT_NUMBER).ignoreCase()).
                add(Restrictions.eq("Transaction.id", idTransaction)).uniqueResult();

        if (merchantHostParameterValue
                != null) {
            merchantId = merchantHostParameterValue.getValue();
        }
        return merchantId;
    }

    /**
     * Verifies if user can perform the current transaction.
     *
     * @param idClerk The clerk id
     * @return user access result.
     *
     */
    public boolean verifyUserAccess(Integer idClerk) {
        Clerk clerk = (Clerk) HibernateUtil.getSession().createCriteria(Clerk.class, "Clerk").
                createAlias("Clerk.clerkRole", "ClerkRole").
                add(Restrictions.eq("Clerk.id", idClerk)).
                add(Restrictions.not(Restrictions.eq("ClerkRole.name", NomClerkRole.VT_NO_TRANSACTION).ignoreCase())).uniqueResult();
        return clerk != null;
    }

    /**
     * Gets merchant parameter values
     *
     * @param clerk Clerk object
     * @return List of parameter values
     *
     */
    public List<MerchantParameterValue> getMerchantParameterValueList(Clerk clerk) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(MerchantParameterValue.class)
                .createAlias("merchant", "merchant")
                .createAlias("merchantParameter", "merchantParameter")
                .createAlias("merchantParameter.parameterGroup", "parameterGroup")
                .add(Restrictions.eq("merchant.id", clerk.getCustomer() == null ? clerk.getMerchant().getId() : ((Merchant) clerk.getCustomer().getMerchant().toArray()[0]).getId()))
                .add(Restrictions.eq("parameterGroup.name", NomParameterGroup.RECEIPT).ignoreCase());
        return criteria.list();
    }
//     /**
//     * Gets merchant parameter values
//     *
//     * @param clerk Clerk object
//     * @return List of parameter values
//    
//     */
//    public List<MerchantParameterValue> getMerchantParameterValueList(Clerk clerk) {
//        List<MerchantParameterValue> merchantParameterValue = null;
//        
//        try {
//            
//            
//            Criteria criteria = HibernateUtil.getSession().createCriteria(MerchantParameterValue.class).createAlias("merchant", "merchant").createAlias("merchantParameter", "merchantParameter").createAlias("merchantParameter.parameterGroup", "parameterGroup").add(Restrictions.eq("merchant.id", clerk.getCustomer() == null ? clerk.getMerchant().getId() : ((Merchant)clerk.getCustomer().getMerchant().toArray()[0]).getId())).add(Restrictions.eq("parameterGroup.name", NomParameterGroup.RECEIPT).ignoreCase());
//            merchantParameterValue = criteria.list();
//            
//        } catch (Exception ex) {
//            
//            ex.printStackTrace();
//            throw new Exception("" + ex.getCause(), ex.getCause());
//
//        }
//        return merchantParameterValue;
//    }
}
