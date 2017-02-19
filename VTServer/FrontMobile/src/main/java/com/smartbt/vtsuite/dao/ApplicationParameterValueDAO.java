

package com.smartbt.vtsuite.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue;

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;



/**
 *
 * @author Roberto Rodriguez
 */
public class ApplicationParameterValueDAO extends BaseDAO<ApplicationParameterValue> {

    protected static ApplicationParameterValueDAO dao;

    public static ApplicationParameterValueDAO get() {
        if (dao == null) {
            dao = new ApplicationParameterValueDAO();
        }
        return dao;
    }

     public List<ApplicationParameterValue> getApplicationParameterValueByIdApplication(int idApplication) {
        Criteria cri = HibernateUtil.getSession().createCriteria(ApplicationParameterValue.class);
        cri.createAlias("application", "application");
        cri.add(Restrictions.eq("application.id", idApplication));
        return cri.list();
    }
     
    

}
