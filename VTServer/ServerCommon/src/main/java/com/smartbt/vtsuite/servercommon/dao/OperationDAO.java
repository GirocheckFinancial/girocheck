/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.model.Operation;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Carlos
 */
public class OperationDAO extends BaseDAO<Operation> {

    protected static OperationDAO dao;

    public OperationDAO() {
        //super(Application.class);
    }

    public static OperationDAO get() {
        if (dao == null) {
            dao = new OperationDAO();
        }
        return dao;
    }

    /**
     * Get operation object from name.
     *
     * @param operationName The transaction operation
     * @return Operation object
     *
     */
    public Operation getOperationByName(String operationName) {
        operationName = operationName.equalsIgnoreCase(NomOperation.REVERSAL.toString()) ? NomOperation.VOID.toString() : operationName;
        Criteria criteria = HibernateUtil.getSession().createCriteria(Operation.class).
                add(Restrictions.eq("name", operationName).ignoreCase());
        return (Operation) criteria.uniqueResult();
    }
}
