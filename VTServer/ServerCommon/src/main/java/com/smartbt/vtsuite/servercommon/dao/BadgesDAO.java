/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.vtsuite.servercommon.display.common.model.BadgeDisplay;
import com.smartbt.vtsuite.servercommon.model.Client;
import com.smartbt.vtsuite.servercommon.model.Transaction;
import com.smartbt.vtsuite.servercommon.model.VTSession;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CUSTOMER;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.MERCHANT;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.TERMINAL;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Carlos
 */
public class BadgesDAO {

    /**
     * Get total tips amount
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The amount
     * @throws Exception
     */
    public BadgesDAO() {
    }

    public BadgeDisplay getAppBadges(VTSession vTSession) throws Exception {
        BadgeDisplay clientBadges = new BadgeDisplay();

        EntityType entityType = VTSessionDAO.get().clientHierarchy(vTSession.getToken());
        if (entityType != null) {
            int id = (entityType == EntityType.TERMINAL) ? vTSession.getTerminal().getId() : vTSession.getTerminal().getMerchant().getId();
            clientBadges.setAmountOfCustomers(totalClientsCount(vTSession.getTerminal().getMerchant().getId(), entityType) + "");
            clientBadges.setCardPaymentByMerchant(totalTransactionsDayAmount(id, DateUtils.startDate(), DateUtils.endDate(), entityType) + "");
            clientBadges.setCountTransactions(totalTransactionsCount(id, DateUtils.startDate(), DateUtils.endDate(), entityType) + "");            
        } else {
            throw new Exception();
        }
        return clientBadges;
    }

    public double totalTipsAmount(int id, Date fromdate, Date todate, EntityType entityType) throws Exception {
        double totalAmount = 0;
        Criteria criteria;
        Object var;
        ProjectionList projectionList = Projections.projectionList().add(Projections.sum("tipAmount"));
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("terminal.merchant", "merchant").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("voided", false)).add(Restrictions.eq("operation.id", NomOperation.SALE.getId())).add(Restrictions.eq("merchant.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    totalAmount = Double.parseDouble(var.toString());
                }
                break;
            case TERMINAL:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("voided", false)).add(Restrictions.eq("operation.id", NomOperation.SALE.getId())).add(Restrictions.eq("terminal.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    totalAmount = Double.parseDouble(var.toString());
                }
                break;
        }
        return totalAmount;
    }

    /**
     * Get total tax amount
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The amount
     * @throws Exception
     */
    public double totalTaxAmount(int id, Date fromdate, Date todate, EntityType entityType) throws Exception {
        double totalAmount = 0;
        Criteria criteria;
        Object var;
        ProjectionList projectionList = Projections.projectionList().add(Projections.sum("taxAmount"));
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("terminal.merchant", "merchant").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("voided", false)).add(Restrictions.eq("operation.id", NomOperation.SALE.getId())).add(Restrictions.eq("merchant.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    totalAmount = Double.parseDouble(var.toString());
                }
                break;
            case TERMINAL:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("voided", false)).add(Restrictions.eq("operation.id", NomOperation.SALE.getId())).add(Restrictions.eq("terminal.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    totalAmount = Double.parseDouble(var.toString());
                }
                break;
        }
        return totalAmount;
    }

    /**
     * Get total amount
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The amount
     * @throws Exception
     */
    public double totalSaleAmount(int id, Date fromdate, Date todate, EntityType entityType, boolean accepVoided) throws Exception {
        double totalAmount = 0;
        Criteria criteria;
        Object var;
        ProjectionList projectionList = Projections.projectionList().add(Projections.sum("totalAmount"));
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("terminal.merchant", "merchant").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("operation.id", NomOperation.SALE.getId())).add(Restrictions.eq("merchant.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                if (!accepVoided) {
                    criteria.add(Restrictions.eq("voided", false));
                }
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    totalAmount = Double.parseDouble(var.toString());
                }
                break;
            case TERMINAL:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("operation.id", NomOperation.SALE.getId())).add(Restrictions.eq("terminal.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                if (!accepVoided) {
                    criteria.add(Restrictions.eq("voided", false));
                }
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    totalAmount = Double.parseDouble(var.toString());
                }
                break;
        }
        return totalAmount;
    }

    /**
     * Get count
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The amount
     * @throws Exception
     */
    public int totalSaleCount(int id, Date fromdate, Date todate, EntityType entityType) throws Exception {
        int count = 0;
        Criteria criteria;
        Object var;
        ProjectionList projectionList = Projections.projectionList().add(Projections.rowCount());
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("terminal.merchant", "merchant").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("operation.id", NomOperation.SALE.getId())).add(Restrictions.eq("merchant.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    count = Integer.parseInt(var.toString());
                }
                break;
            case TERMINAL:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("operation.id", NomOperation.SALE.getId())).add(Restrictions.eq("terminal.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    count = Integer.parseInt(var.toString());
                }
                break;
        }
        return count;
    }

    /**
     * Get total amount of voided transactions
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The amount
     * @throws Exception
     */
    public double totalVoidAmount(int id, Date fromdate, Date todate, EntityType entityType) throws Exception {

        double totalAmount = 0;
        Criteria criteria;
        Object var;
        ProjectionList projectionList = Projections.projectionList().add(Projections.sum("totalAmount"));
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("terminal.merchant", "merchant").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("operation.id", NomOperation.VOID.getId())).add(Restrictions.eq("merchant.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    totalAmount = Double.parseDouble(var.toString());
                }
                break;
            case TERMINAL:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("operation.id", NomOperation.VOID.getId())).add(Restrictions.eq("terminal.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    totalAmount = Double.parseDouble(var.toString());
                }
                break;
        }
        return totalAmount;
    }

    /**
     * Get count of voided transactions
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The count
     * @throws Exception
     */
    public int totalVoidCount(int id, Date fromdate, Date todate, EntityType entityType) throws Exception {
        int count = 0;
        Criteria criteria;
        Object var;
        ProjectionList projectionList = Projections.projectionList().add(Projections.rowCount());
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("terminal.merchant", "merchant").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("operation.id", NomOperation.VOID.getId())).add(Restrictions.eq("merchant.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    count = Integer.parseInt(var.toString());
                }
                break;
            case TERMINAL:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("operation.id", NomOperation.VOID.getId())).add(Restrictions.eq("terminal.id", id)).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    count = Integer.parseInt(var.toString());
                }
                break;
        }
        return count;
    }

    /**
     * Get total amount refund
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The amount
     * @throws Exception
     */
    public double totalRefundAmount(int id, Date fromdate, Date todate, EntityType entityType, boolean accepVoided) throws Exception {
        double totalAmount = 0;
        Criteria criteria;
        Object var;
        ProjectionList projectionList = Projections.projectionList().add(Projections.sum("totalAmount"));
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("terminal.merchant", "merchant").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("merchant.id", id)).add(Restrictions.eq("operation.id", NomOperation.REFUND.getId())).add(Restrictions.between("createdAt", fromdate, todate));
                if (!accepVoided) {
                    criteria.add(Restrictions.eq("voided", false));
                }
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    totalAmount = Double.parseDouble(var.toString());
                }
                break;
            case TERMINAL:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("terminal.id", id)).add(Restrictions.eq("operation.id", NomOperation.REFUND.getId())).add(Restrictions.between("createdAt", fromdate, todate));
                if (!accepVoided) {
                    criteria.add(Restrictions.eq("voided", false));
                }
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    totalAmount = Double.parseDouble(var.toString());
                }
                break;
        }

        return totalAmount;
    }

    /**
     * Get count refund
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The count
     * @throws Exception
     */
    public int totalRefundCount(int id, Date fromdate, Date todate, EntityType entityType) throws Exception {
        int count = 0;
        Criteria criteria;
        Object var;
        ProjectionList projectionList = Projections.projectionList().add(Projections.rowCount());
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("terminal.merchant", "merchant").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("merchant.id", id)).add(Restrictions.eq("operation.id", NomOperation.REFUND.getId())).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    count = Integer.parseInt(var.toString());
                }
                break;
            case TERMINAL:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).createAlias("terminal", "terminal").createAlias("operation", "operation").add(Restrictions.ilike("disposition", "%APPROVED%")).add(Restrictions.eq("terminal.id", id)).add(Restrictions.eq("operation.id", NomOperation.REFUND.getId())).add(Restrictions.between("createdAt", fromdate, todate));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    count = Integer.parseInt(var.toString());
                }
                break;
        }
        return count;
    }

    /**
     * Get a amount of clients
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The amount
     * @throws Exception
     */
    public int totalClientsCount(int id, EntityType entityType) throws Exception {
        int countRow = 0;
        Criteria criteria;
        Object var;
        ProjectionList projectionList = Projections.projectionList().add(Projections.rowCount());
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
                criteria = HibernateUtil.getSession().createCriteria(Client.class).createAlias("merchant", "merchant").add(Restrictions.eq("merchant.id", id)).add(Restrictions.eq("active", true));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult().toString();
                if (var != null) {
                    countRow = Integer.parseInt(var.toString());
                }
                break;
            case TERMINAL:
                criteria = HibernateUtil.getSession().createCriteria(Client.class).createAlias("merchant", "merchant").createAlias("merchant.terminal", "terminal").add(Restrictions.eq("terminal.id", id)).add(Restrictions.eq("active", true));
                criteria.setProjection(projectionList);
                var = criteria.uniqueResult().toString();
                if (var != null) {
                    countRow = Integer.parseInt(var.toString());
                }
                break;
        }
        return countRow;
    }

    /**
     * Get a count transaction
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The count
     * @throws Exception
     */
    public int totalTransactionsCount(int id, Date fromdate, Date todate, EntityType entityType) throws Exception {
        int countRow = 0;
        Criteria criteria;
        Object var;
        ProjectionList projectionList = Projections.projectionList().add(Projections.rowCount());
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).
                        createAlias("terminal", "terminal").
                        createAlias("terminal.merchant", "merchant").
                        add(Restrictions.ilike("disposition", "%APPROVED%")).
                        add(Restrictions.eq("voided", false)).
                        add(Restrictions.eq("merchant.id", id)).
                        add(Restrictions.between("createdAt", fromdate, todate)).
                        addOrder(Order.desc("createdAt"));

                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    countRow = Integer.parseInt(var.toString());
                }
                break;
            case TERMINAL:
                criteria = HibernateUtil.getSession().createCriteria(Transaction.class).
                        createAlias("terminal", "terminal").
                        add(Restrictions.ilike("disposition", "%APPROVED%")).
                        add(Restrictions.eq("voided", false)).
                        add(Restrictions.eq("terminal.id", id)).
                        add(Restrictions.between("createdAt", fromdate, todate)).
                        addOrder(Order.desc("createdAt"));

                criteria.setProjection(projectionList);
                var = criteria.uniqueResult();
                if (var != null) {
                    countRow = Integer.parseInt(var.toString());
                }
                break;
        }
        return countRow;
    }

    /**
     * Get a amount of the day transactions
     *
     * @param id
     * @param fromdate The from date
     * @param todate The to date
     * @return The count
     * @throws Exception
     */
    public double totalTransactionsDayAmount(int id, Date fromdate, Date todate, EntityType entityType) throws Exception {
        return totalSaleAmount(id, fromdate, todate, entityType, false) - totalRefundAmount(id, fromdate, todate, entityType, false);
    }
}
