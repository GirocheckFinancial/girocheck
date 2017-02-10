package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.ActivityReportTransactionDisplay;
import com.smartbt.girocheck.servercommon.display.AddressImageFormDisplay;
import com.smartbt.girocheck.servercommon.display.SubTransactionImageDisplay;
import com.smartbt.girocheck.servercommon.display.TransactionDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.ImgConvTiffToPng;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activity.ActivityRequiredException;
import javax.xml.bind.DatatypeConverter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class TransactionDAO extends BaseDAO<Transaction> {

//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TransactionDAO.class);
    protected static TransactionDAO dao;

    public TransactionDAO() {
    }

    public static TransactionDAO get() {
        if (dao == null) {
            dao = new TransactionDAO();
        }
        return dao;
    }
    
    public Map activityReport(Map input) {
        Map output = new HashMap();

        try {
            String terminalId = (String) input.get(ParameterName.TERMINAL_ID);
            Date dateStart = (Date) input.get(ParameterName.START_DATE);
            Date dateEnd = (Date) input.get(ParameterName.END_DATE);

            List<ActivityReportTransactionDisplay> checkTransactions = getActivityCriteriaCheckCash(terminalId, dateStart, dateEnd, "01", true).list();
            List<ActivityReportTransactionDisplay> cashTransactions = getActivityCriteriaCheckCash(terminalId, dateStart, dateEnd, "02", true).list();
            List<ActivityReportTransactionDisplay> card2MerchantTransactions = getActivityCriteriaCard2Bank(terminalId, dateStart, dateEnd, true).list();

            System.out.println("DAO checkTransactions.size = " + checkTransactions.size());
            System.out.println("DAO cashTransactions.size = " + cashTransactions.size());
            System.out.println("DAO card2MerchantTransactions.size = " + card2MerchantTransactions.size());
            
            Double checkTotal = (Double) getActivityCriteriaCheckCash(terminalId, dateStart, dateEnd, "01", false).uniqueResult();
            Double cashTotal = (Double) getActivityCriteriaCheckCash(terminalId, dateStart, dateEnd, "02", false).uniqueResult();
            Double cardTotal = (Double) getActivityCriteriaCard2Bank(terminalId, dateStart, dateEnd, false).uniqueResult();

            if(checkTotal == null) checkTotal = 0D;
            if(cashTotal == null) cashTotal = 0D;
            if(cardTotal == null) cardTotal = 0D;
            
            output.put(ParameterName.CHECK2CARD_TRANSACTIONS, checkTransactions);
            output.put(ParameterName.CASH2CARD_TRANSACTIONS, cashTransactions);
            output.put(ParameterName.CARD2MERCHANT_TRANSACTIONS, card2MerchantTransactions);

            output.put(ParameterName.CHECK2CARD_COUNT, checkTransactions.size());
            output.put(ParameterName.CASH2CARD_COUNT, cashTransactions.size());
            output.put(ParameterName.CARD2MERCHANT_COUNT, card2MerchantTransactions.size());

            output.put(ParameterName.CHECK2CARD_TOTAL, checkTotal);
            output.put(ParameterName.CASH2CARD_TOTAL, cashTotal);
            output.put(ParameterName.CARD2MERCHANT_TOTAL, cardTotal);

            output.put(ParameterName.CASH_IN, cashTotal);
            output.put(ParameterName.CASH_OUT, cardTotal);
            output.put(ParameterName.NET_CASH, cashTotal - cardTotal);

            output.put(ParameterName.TOTAL_ROWS, checkTransactions.size() + cashTransactions.size() + card2MerchantTransactions.size());
            output.put(ParameterName.SUCCESS, true);
        } catch (Exception e) {
            e.printStackTrace();
            output.put(ParameterName.SUCCESS, false);
        }
        return output;
    }

    private Criteria getActivityCriteriaCheckCash(String terminalId, Date dateStart, Date dateEnd, String operation, boolean isList) {
        return getActivityCriteria(terminalId, dateStart, dateEnd, isList).add(Restrictions.eq("operation", operation));
    }

    private Criteria getActivityCriteriaCard2Bank(String terminalId, Date dateStart, Date dateEnd, boolean isList) {
        return getActivityCriteria(terminalId, dateStart, dateEnd, isList).add(Restrictions.eq("transactionType", TransactionType.TECNICARD_CARD_TO_BANK.getCode()));
    }

    private Criteria getActivityCriteria(String terminalId, Date dateStart, Date dateEnd, boolean isList) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("terminal", "terminal")
                .add(Restrictions.eq("terminal.serialNumber", terminalId))
                .add(Restrictions.ge("dateTime", dateStart))
                .add(Restrictions.le("dateTime", dateEnd));
        
        criteria.add(Restrictions.eq("resultCode", 0));

        if (isList) {
            ProjectionList projectionList = Projections.projectionList()
                    .add(Projections.property("dateTime").as("dateTime"))
                    .add(Projections.property("ammount").as("amount"));

            criteria.setProjection(projectionList)
                    .setResultTransformer(Transformers.aliasToBean(ActivityReportTransactionDisplay.class));
        } else {
            criteria.setProjection(Projections.sum("ammount"));
        }
        return criteria;
    }

    public boolean cancelTransaction(String requestId) {
        Criteria cri = HibernateUtil.getSession().createCriteria(Transaction.class).add(Restrictions.eq("requestId", requestId));

        cri.setMaxResults(1);

        Transaction transaction = (Transaction) cri.uniqueResult();

        if (transaction == null) {
            return false;
        }

        if (transaction.isCancelable()) {
            transaction.setCancelated(true);
            saveOrUpdate(transaction);
            return true;
        } else {
            return false;
        }

    }

    //TODO this method is not longer required
    public boolean isCanceled(String requestId, boolean cancelable) {
//        Criteria cri = HibernateUtil.getSession().createCriteria(Transaction.class).add(Restrictions.eq("requestId", requestId));
//
//        cri.setMaxResults(1);
//
//        Transaction transaction = (Transaction) cri.uniqueResult();
//
//        if (transaction == null) {
//            return false;
//        }
//
//        if (!cancelable) {
//            transaction.setCancelable(cancelable);
//            saveOrUpdate(transaction);
//        }
//        return transaction.isCancelated() == null ? false : transaction.isCancelated();
        return false;
    }

    public AddressImageFormDisplay getAddressImageFromClientByTerminalSerialNumber(String serialNumber, boolean rotate) throws Exception {
        AddressImageFormDisplay addressImage = new AddressImageFormDisplay();

        try {
            System.out.println("[TransactionDAO] getAddressImageFromClientByTerminalSerialNumber() terminal serial number: " + serialNumber);
            Date datelow = new Date();

            datelow.setTime(datelow.getTime() - 180000);// 3 minutes less
            Date dateHigh = new Date();

            if (serialNumber != null) {

                Criteria criteria = HibernateUtil.getSession().createCriteria(Transaction.class).
                        createAlias("terminal", "terminal").
                        createAlias("client", "client").
                        add(Restrictions.eq("terminal.serialNumber", serialNumber)).
                        add(Restrictions.between("dateTime", datelow, dateHigh)).
                        add(Restrictions.isNotNull("client.addressForm")).
                        addOrder(Order.desc("id"));

                List<Transaction> transactions = criteria.list();
                Client client;

                if (transactions != null && !transactions.isEmpty()) {

                    System.out.println("[TransactionDAO] getAddressImageFromClientByTerminalSerialNumber() transaction id value: " + transactions.get(0).getId());

                    client = transactions.get(0).getClient();

                } else {
                    System.out.println("[TransactionDAO] getAddressImageFromClientByTerminalSerialNumber() There is not transactions with the require specifications. ");
                    client = null;
                    throw new Exception("EmptyException");
                }

                if (client != null) {
                    if (client.getAddressForm() != null) {
                        byte[] bdata = client.getAddressForm().getBytes(1, (int) client.getAddressForm().length());

                        ImgConvTiffToPng convTiffToPng = new ImgConvTiffToPng();
                        byte[] addressF;
                        String base64bytes;

                        try {
                            addressF = convTiffToPng.convert(bdata);
                            if (rotate) {
                                base64bytes = DatatypeConverter.printBase64Binary(convTiffToPng.rotate180(addressF));
                            } else {
                                base64bytes = DatatypeConverter.printBase64Binary(addressF);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
                            if (rotate) {
                                base64bytes = DatatypeConverter.printBase64Binary(convTiffToPng.rotate180(bdata));
                            } else {
                                base64bytes = DatatypeConverter.printBase64Binary(bdata);
                            }
                        }

                        addressImage.setAddressImage("data:image/png;base64," + base64bytes);

                    }
                }
            }

            return addressImage;
        } catch (Exception e) {
            System.out.println("[TransactionDAO] getAddressImageFromClientByTerminalSerialNumber() Error during the method execution");
            e.printStackTrace();
            throw e;
        }
    }

    public ResponseDataList searchTransactions(String searchFilter, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, int transactionType, String operation,
            boolean filterAmmount, int ammountType, int opType, String ammountString, boolean pending) {
        Criteria cri = getSearchCriteria( searchFilter,  startRangeDate,  endRangeDate,  firstResult,  maxResult,  transactionType,  operation,
             filterAmmount,  ammountType,  opType,  ammountString,  pending);
        
        if (firstResult >= 0) {
            cri.setFirstResult(firstResult);
            cri.setMaxResults(maxResult);
        }
 
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("transactionType").as("transactionType"))
                .add(Projections.property("dateTime").as("createdAt"))
                .add(Projections.property("operation").as("operation"))
                .add(Projections.property("data_sc1.maskCardNumber").as("accountSuffix"))
                .add(Projections.property("ammount").as("ammount"))
                .add(Projections.property("feeAmmount").as("feeAmmount"))
                .add(Projections.property("payoutAmmount").as("payoutAmmount"))
                .add(Projections.property("single").as("single"))
                .add(Projections.property("resultCode").as("resultCode"))
                .add(Projections.property("resultMessage").as("resultMessage"))
                .add(Projections.property("merchant.legalName").as("merchant"))
                .add(Projections.property("terminal.serialNumber").as("terminal"))
                .add(Projections.property("client.firstName").as("clientFirstName"))
                .add(Projections.property("client.lastName").as("clientLastName"))
                .add(Projections.property("transactionFinished").as("transactionFinished"));
        cri.setProjection(projectionList);
        cri.setResultTransformer(new TransformerComplexBeans(TransactionDisplay.class));

        List<TransactionDisplay>  list = cri.list();
        
        Criteria countCriteria = getSearchCriteria(searchFilter, startRangeDate, endRangeDate, firstResult, maxResult, transactionType, operation, filterAmmount, ammountType, opType, ammountString, pending);
        countCriteria.setProjection(Projections.rowCount());
        Long total = (Long)countCriteria.uniqueResult();
        
        
        ResponseDataList response = new ResponseDataList();

        response.setData(list);

        response.setTotalPages((int) Math.ceil((float) total / (float) maxResult));
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        
        return response;
    }
    
    private Criteria getSearchCriteria(String searchFilter, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, int transactionType, String operation,
            boolean filterAmmount, int ammountType, int opType, String ammountString, boolean pending){
        
         Criteria cri = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("terminal", "terminal", JoinType.LEFT_OUTER_JOIN)
                .createAlias("terminal.merchant", "merchant", JoinType.LEFT_OUTER_JOIN)
                .createAlias("merchant.agrupation", "agrupation", JoinType.LEFT_OUTER_JOIN)
                 .createAlias( "data_sc1", "data_sc1", JoinType.LEFT_OUTER_JOIN )
                .createAlias("client", "client", JoinType.LEFT_OUTER_JOIN)
                .addOrder(Order.desc("dateTime"));

        System.out.println("startRangeDate + " + startRangeDate);
        System.out.println("endRangeDate + " + endRangeDate);
        
        if(startRangeDate != null){
            startRangeDate.setHours(0);
            startRangeDate.setMinutes(0);
            startRangeDate.setSeconds(0);
        }
        
        if(endRangeDate != null){
            endRangeDate.setHours(23);
            endRangeDate.setMinutes(59);
            endRangeDate.setSeconds(59);
        }
         
        if (startRangeDate != null) {
            cri.add(Restrictions.ge("dateTime", startRangeDate));
        }
        if (endRangeDate != null) {
            endRangeDate.setHours(24);
            cri.add(Restrictions.le("dateTime", endRangeDate));
        }

        if (operation != null && (operation.contains("01") || operation.contains("02"))) {
            cri.add(Restrictions.like("operation", operation, MatchMode.ANYWHERE).ignoreCase());
        }

        if (transactionType != 0) {
            cri.add(Restrictions.eq("transactionType", transactionType));
        }else{
            cri.add(Restrictions.ne("transactionType", 5));
        }

        if (pending) {
            //if pending is checked gonna bring unfinished transactions
            cri.add(Restrictions.eq("transactionFinished", false));
        }

        try {
            if (filterAmmount) {
                Double ammount = Double.parseDouble(ammountString);

                String field;

                switch (ammountType) {
                    case 1:
                        field = "ammount";
                        break;
                    case 2:
                        field = "feeAmmount";
                        break;
                    case 3:
                        field = "payoutAmmount";
                        break;
                    default:
                        field = "ammount";
                }

                switch (opType) {
                    case 1:
                        cri.add(Restrictions.gt(field, ammount));
                        break;
                    case 2:
                        cri.add(Restrictions.eq(field, ammount));
                        break;
                    case 3:
                        cri.add(Restrictions.lt(field, ammount));
                        break;
                    default:
                        field = "ammount";
                }
            }
        } catch (NumberFormatException e) {
//            log.debug( "[TransactionDAO] NumberFormatException" );
            e.printStackTrace();
        }

        if (searchFilter != null && !searchFilter.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    //  .add( Restrictions.like( "resultCode", searchFilter, MatchMode.ANYWHERE ).ignoreCase() )
                    .add(Restrictions.like("resultMessage", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("account", searchFilter, MatchMode.ANYWHERE).ignoreCase())
//                    .add(Restrictions.like("cardNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("errorCode", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("errorCode", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.legalName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idTecnicardCheck", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idTecnicardCash", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idIstreamTecnicardCash", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idIstreamTecnicardCheck", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idIstreamFuzeCash", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.idIstreamFuzeCheck", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("terminal.serialNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.firstName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.ssn", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.lastName", searchFilter, MatchMode.ANYWHERE).ignoreCase());

            Criterion dateRestriction = DateUtils.getRestrictionForDateFilter(searchFilter, "dateTime");
            if (dateRestriction != null) {
//                log.debug( "[TransactionDAO] ( dateRestriction != null )" );
                disjunction.add(dateRestriction);
            }
            cri.add(disjunction);

        }
        
        return cri;
    }

    public SubTransactionImageDisplay getTransactionImage(int idTransaction) throws SQLException {

        SubTransactionImageDisplay subTransactionImage = new SubTransactionImageDisplay();
       return subTransactionImage;

    }

    public List<Transaction> getAll() {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("terminal", "terminal", JoinType.LEFT_OUTER_JOIN)
                .createAlias("terminal.merchant", "merchant", JoinType.LEFT_OUTER_JOIN)
                .createAlias("merchant.agrupation", "agrupation", JoinType.LEFT_OUTER_JOIN)
                // .createAlias( "data_sc1", "data_sc1", JoinType.LEFT_OUTER_JOIN )
                .createAlias("client", "client", JoinType.LEFT_OUTER_JOIN)
                .addOrder(Order.desc("dateTime"));

        return criteria.list();
    }

}
