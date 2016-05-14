/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.model.Mode;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Carlos
 */
public class ModeDAO extends BaseDAO<Mode> {

    protected static ModeDAO dao;

    public ModeDAO() {
        //super(Application.class);
    }

    public static ModeDAO get() {
        if (dao == null) {
            dao = new ModeDAO();
        }
        return dao;
    }

    /**
     * Get mode object from name.
     *
     * @param modeName The transaction mode
     * @return Mode object
     *
     */
    public Mode getModeByName(String modeName) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Mode.class).
                add(Restrictions.eq("name", modeName).ignoreCase());
        return (Mode) criteria.uniqueResult();
    }
}
